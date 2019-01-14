package atom5;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/*import java.net.*;
import java.io.*;

public class AtomServer extends Thread {
   private ServerSocket serverSocket;
   private static int port;
   
   public AtomServer(int port) throws IOException {
	   this.port = port;
      serverSocket = new ServerSocket(port);
      //serverSocket.setSoTimeout(100000);
   }

   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());            
            
            String msgOut = in.readUTF();
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(msgOut);
            //server.close();
            
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = 3703;
      try {
         Thread t = new AtomServer(port);
         t.start();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}*/

   
   






import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class AtomServer extends Thread {
	private ServerSocket serverSocket;
	private boolean loggedIn;
	private int port;
	private SimpleDateFormat time;
	private ArrayList <clientThread> clThread;
	private static int uniqueID = 0;
	
	
	public AtomServer(int port) throws IOException{
		this.port = port;
		clThread = new ArrayList<clientThread>();
		time = new SimpleDateFormat("HH:mm:ss");
	}
	@Override
	public void start(){
		loggedIn = true;
		try {
			serverSocket = new ServerSocket(port);
			//serverSocket.setSoTimeout(100000);
			while(loggedIn){
				System.out.println("Waiting for client on port " + 
			               serverSocket.getLocalPort() + "...");
				Socket atomsocket = serverSocket.accept();
				if(!loggedIn){
					break;
				}
				else{
					clientThread t = new clientThread(atomsocket);
					clThread.add(t);
					t.start();
				}
			}
			try{
				serverSocket.close();
				for(int i=0; i<clThread.size(); i++){
					clientThread client = clThread.get(i);
					try{
						client.input.close();
						client.output.close();
						client.socket.close();
					}
					catch(IOException e){
						System.out.println("Error closing socket " + e);
					}
				}
			}
			catch(Exception ex){
				System.out.println("Error closing the server connection to clients "+ ex);
			}
		} catch (IOException e) {
			System.out.println(""+ e);			
		}		
		
	}
	
	public synchronized void remove(int id){
		for(int i = 0; i<clThread.size(); i++){
			clientThread client = clThread.get(i);
			if(client.id == id){
				clThread.remove(i);
				return;
			}
		}
	}

	private synchronized void broadcast(String string, int id) {
		String msgTime = time.format(new Date());
		String msgDisplayed = msgTime + " " + string + "\n";
		for(int i = 0; i<clThread.size(); i++){
			clientThread client = clThread.get(i);			
			if(client.id != id){
				if(!client.sendMessage(msgDisplayed)){
					clThread.remove(i);
					JOptionPane.showMessageDialog(null, client.user + " has been disconected");
				}
			}
			
		}
		
	}
	
	public static void main(String [] args) {
	      int port = 3703;
	      try {
	         Thread t = new AtomServer(port);
	         t.start();
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
	   }
	
	
	
	public class clientThread extends Thread{
		private Socket socket;
		ObjectInputStream input;
		ObjectOutputStream output;		
		String sender;
		boolean isFile = false;
		int id;
		String date;
		MessageType mt;
		String user;
		
		public clientThread(Socket socket){
			this.id = ++uniqueID;
			this.socket = socket;
			try {
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());	
				user = (String) input.readObject();
				System.out.println("Just connected to "+user);
			} catch (IOException e) {				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			date = new Date().toString();
			
		}
		
		public void run(){
			boolean loggedIn = true;
			int dataType = 0;
			Object receivedData = null;
			while(loggedIn){
				
					if(this.isFile){
						
						try {
							byte[] received = (byte[]) input.readObject();
							output.writeObject(new MessageType(MessageType.ISFILE, sender));
							output.writeObject(received);
							JOptionPane.showMessageDialog(null, ""+received.length);
							output.flush();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}							
						 
						this.isFile = false;
					}
					else{
						try {
						mt = (MessageType) input.readObject();
					} catch (IOException e) {
						System.out.println("Connection to a client has been lost ");
						loggedIn = false;
						broadcast(user + " HAS BEEN DISCONNECTED", this.id);
						break;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						break;
					}
					receivedData = mt.getData();
					dataType = mt.getType();
					switch(dataType){
					case MessageType.TEXT:
						broadcast(user + "> " + receivedData, this.id);
						break;
					case MessageType.FILE:
						broadcast(user + "> " + (byte []) receivedData, this.id);
						break;
					case MessageType.ISFILE:
						this.isFile = true;
						sender = (String) receivedData;
						break;
					case MessageType.LOGOUT:
						System.out.println(user + "has been disconnected");
						broadcast(user + "HAS BEEN DISCONNECTED", this.id);
						loggedIn = false;
						break;
						
					}
					}
					
			}
			remove(id);
			close();
		}
		public void close(){
			try{
				if(output!=null){
					output.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
			try{
				if(input!=null){
					input.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
			
		}
		
		public boolean sendMessage(Object data){
			if(!socket.isConnected()){
				close();
				return false;
			}
			else{
				try {
					output.writeObject(new MessageType(MessageType.TEXT, data));
				} catch (Exception e) {
					System.out.println("Error sending data to " + user);
					loggedIn = false;
				}
				return true;
			}
		}
		
	}
	

}


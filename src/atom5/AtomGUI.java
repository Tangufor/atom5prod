package atom5;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextPane;

public class AtomGUI extends JFrame {
	protected static JTextField username;
	private static JPasswordField passwordTxt;
	private String userN;
	private String passW = "";
	protected static String name;
	protected static String email;
	protected static AtomGUI atomGUI;
	private static createAccount ca = new createAccount();
	private chatWindow cw;	
	static manageDatabase connection = new manageDatabase();
	private InetAddress serverAddress;
	protected static Socket client;
	static ObjectOutputStream msgSent;
	static ObjectInputStream msgReceived;
	static boolean isFile = false;
	static boolean loggedIn = true;
	
	
	public AtomGUI() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setSize(900, 600);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getWidth()/2, (int) (dim.getHeight()/2 - this.getHeight()/2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 13, 464, 527);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(232, 5, 0, 0);
		panel.add(label);
		
		JLabel loginMessage = new JLabel("Sign in with your Atom-5 Account");
		loginMessage.setFont(new Font("Calibri Light", Font.PLAIN, 32));
		loginMessage.setForeground(Color.DARK_GRAY);
		loginMessage.setBounds(12, 45, 440, 52);
		panel.add(loginMessage);
		
		username = new JTextField();	
		username.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				username.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		});		
		
		username.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		username.setToolTipText("Enter your username");
		username.setForeground(Color.GRAY);
		username.setBounds(12, 151, 381, 35);
		username.requestFocusInWindow();
		panel.add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.DARK_GRAY);
		lblUsername.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblUsername.setBounds(12, 122, 88, 28);
		panel.add(lblUsername);
		
		JLabel password = new JLabel("Password:");
		password.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		password.setForeground(Color.DARK_GRAY);
		password.setBounds(12, 192, 88, 26);
		panel.add(password);
		
		JButton signIn = new JButton("Sign In");
		signIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signIn.setBackground(new Color(255, 160, 0));
				signIn.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				signIn.setBackground(new Color(255, 140, 0));
			}
		});
		
		signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				userN = username.getText();		
				char pass [] = passwordTxt.getPassword();
				for(int i=0; i<pass.length; i++){
					passW += ""+passwordTxt.getPassword()[i];					
				}
				passwordTxt.setText("");	
				username.setText("");
				if(connection.authenticate(userN, passW)){
					chatWindow.name = name;
					chatWindow.email = email;
					passW = "";
					
					atomGUI.setVisible(false);
					try {
						serverAddress = InetAddress.getByName("100.26.3.94");
						client = new Socket(serverAddress, 3703);
						chatWindow.loggedIn = true;
						cw = new chatWindow(userN);	
						cw.repaint();
						cw.setVisible(true);						
						
						
						try {
							msgSent = new ObjectOutputStream(client.getOutputStream());
							msgReceived = new ObjectInputStream(client.getInputStream());
						}
						catch (IOException e) {
							e.printStackTrace();
						}
						atomGUI.new Listen().start();
						try {
							msgSent.writeObject(chatWindow.name);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "SERVER CURRENTLY UNAVAILABLE. TRY AGAIN LATER" + "\n");
						chatWindow.loggedIn = false;
						cw = new chatWindow(userN);	
						cw.setVisible(true);
						
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Username or password incorrect");
				}
				
			}
		});
		signIn.setBackground(new Color(255, 140, 0));
		signIn.setForeground(Color.WHITE);		
		signIn.setFont(new Font("Calibri Light", Font.BOLD, 20));
		signIn.setBounds(12, 268, 121, 35);
		panel.add(signIn);
		
		passwordTxt = new JPasswordField();
		passwordTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				passwordTxt.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				passwordTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		});		
		passwordTxt.setToolTipText("Enter your password");
		passwordTxt.setHorizontalAlignment(SwingConstants.LEFT);
		passwordTxt.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		passwordTxt.setForeground(Color.GRAY);
		passwordTxt.setBounds(12, 226, 381, 35);
		panel.add(passwordTxt);
		
		JLabel lblDontHaveAn = new JLabel("New to Atom-5?");
		lblDontHaveAn.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblDontHaveAn.setForeground(Color.DARK_GRAY);
		lblDontHaveAn.setHorizontalAlignment(SwingConstants.LEFT);
		lblDontHaveAn.setBounds(12, 316, 140, 26);
		panel.add(lblDontHaveAn);
		
		JButton createAcc = new JButton("Create an account");
		createAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Font font = createAcc.getFont();
				Map attrib = font.getAttributes();
				attrib.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				createAcc.setFont(font.deriveFont(attrib));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				createAcc.setFont(new Font("Calibri Light", Font.PLAIN, 20));
			}
		});
		createAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ca.setVisible(true);
				atomGUI.setVisible(false);
			}
		});
		createAcc.setForeground(new Color(255, 140, 0));
		createAcc.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		createAcc.setHorizontalAlignment(SwingConstants.LEFT);
		createAcc.setBounds(136, 317, 194, 25);
		createAcc.setOpaque(false);
		createAcc.setContentAreaFilled(false);
		createAcc.setBorderPainted(false);
		panel.add(createAcc);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(488, 13, 382, 527);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel Logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Atom-5 Logo.png")).getImage();
		Logo.setIcon(new ImageIcon(img));
		Logo.setBounds(100, 51, 182, 166);
		panel_1.add(Logo);
		
		JLabel title = new JLabel("A new way to interact");
		title.setFont(new Font("Calibri Light", Font.BOLD, 30));
		title.setForeground(new Color(255, 140, 0));
		title.setBounds(31, 203, 319, 43);
		panel_1.add(title);
		
		JTextPane description = new JTextPane();
		description.setBackground(new Color(0, 0, 0));
		description.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		description.setForeground(new Color(255, 250, 205));
		description.setText("Atom-5 is a new way to securely communicate and exchange files with your trusted peers anywhere in the world. Sign in to get started.");
		description.setEditable(false);
		description.setBounds(31, 269, 319, 117);
		panel_1.add(description);
	}
	public static void closeCa(){		
		ca.setVisible(false);
		atomGUI.setVisible(true);
	}
	public static void chatExchange(byte[] b){
		byte [] fileSent = b;
		if(fileSent.length == 1){
			String msg = chatWindow.chatMsg.getText();			
			try {
				msgSent.writeObject(new MessageType(MessageType.TEXT, msg));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "MESSAGE NOT SENT DUE TO SERVER ERROR. TRY AGAIN LATER." + "\n");								
			}
		}
		else{
			/*for (int i =0; i<b.length;i++){
				fileSent[i] = b[i];
			}*/
			 JOptionPane.showMessageDialog(null, ""+fileSent.length);
			try {
				/*OutputStream os = client.getOutputStream();
				os.write(fileSent, 0, fileSent.length);	*/
				msgSent.writeObject(new MessageType(MessageType.ISFILE, name));
				msgSent.writeObject(fileSent);
				//msgSent.flush();
				
				
				//msgSent.writeObject(new MessageType(MessageType.FILE, os));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "MESSAGE NOT SENT DUE TO SERVER ERROR. TRY AGAIN LATER." + "\n" + e);
				//e.printStackTrace();					
			}
		}
	}
			
			
		
	
	public static void main (String [] args){
		atomGUI = new AtomGUI();
		atomGUI.setVisible(true);				
		connection.connectToMySQL();
	}
	class Listen extends Thread{
		
	    BufferedOutputStream bos = null;
			public void run(){
			MessageType mt;
			String sender = null;
			boolean isFile = false;
			while(true){
				
					if(isFile){
						File data = new File("C:\\Received\\received.jpg");
						int option = JOptionPane.showConfirmDialog(null, "You received a file from " + sender + ". Do"
								+ " you want to save it?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(option == JOptionPane.YES_OPTION){
							try {
								byte[] received = (byte[]) msgReceived.readObject();
								Files.write(data.toPath(), received);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							isFile = false;
						}
						else{
							isFile = false;
						}
						
						
					}
					else{
						try {
						mt = (MessageType) msgReceived.readObject();
						switch(mt.getType()){
						case MessageType.TEXT:
							try {
								chatWindow.doc.insertString(chatWindow.doc.getLength(), "\n " + (String) mt.getData(), chatWindow.left);
								chatWindow.doc.setParagraphAttributes(chatWindow.doc.getLength(), 1, chatWindow.left, false);
							} catch (BadLocationException e) {
								JOptionPane.showMessageDialog(null, e);
							}							
							break;
						case MessageType.ISFILE:
							isFile = true;
							sender = (String) mt.getData();
							break;
						}
						
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (IOException e) {
						if(!loggedIn)
							break;
						JOptionPane.showMessageDialog(null, "CONNECTION TO SERVER LOST. TRY AGAIN LATER");
						break;
					}
						
					}
			
		}
	}
	}
}

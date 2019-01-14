package atom5;

import java.awt.Image;
import java.sql.*;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class manageDatabase {
	private static final String username = "tangufor";
	private static final String password = "Password";	
	String mySQL = "jdbc:mysql://tangufor.cyzki0mwgc2p.us-east-1.rds.amazonaws.com:3306/atom5";
	PreparedStatement myStmt = null;
	Statement auth =  null;
	Connection myConn = null;
	private int id = 1;
	
	public manageDatabase(){
		
	}
	
	public void insertToMySQL(String fName, String lName, String email, String userNme, String password, LocalDate dob, Image profilePic){
		
		try {
			myStmt = myConn.prepareStatement("INSERT into useraccount (firstname, lastname, emailAddress, userName, password, dateOfBirth, profilePic)  VALUES (?,?,?,?,?,?,?)");
			//myStmt.setInt(1, 1);
			myStmt.setString(1, fName);
			myStmt.setString(2, lName);
			myStmt.setString(3, email);
			myStmt.setString(4, userNme);
			myStmt.setString(5, password);
			myStmt.setString(6, dob.toString());
			myStmt.setBlob(7, (Blob) profilePic);
			myStmt.execute();
			//JOptionPane.showMessageDialog(null, "insert complete");	
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(null, " "+ e.toString());	
		}
	}
	public boolean authenticate(String username, String password){		
		String login = "SELECT * FROM useraccount WHERE userName='"+username+"' AND password='"+password+"'";		
		try {
			auth = myConn.createStatement();
			ResultSet rs = auth.executeQuery(login);
			//JOptionPane.showMessageDialog(null, " "+ rs.toString());	
			String f_name = null;
			String l_name = null;
			int count = 0;
			while(rs.next()){
				count++;
				f_name = rs.getString("firstname");
				l_name = rs.getString("lastname");		
				AtomGUI.email = rs.getString("emailAddress");
			}
			AtomGUI.name = f_name + " " + l_name;			
			if(count>0){
				return true;
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void connectToMySQL(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection(mySQL, username, password);				
		} catch (SQLException e) {						
			JOptionPane.showMessageDialog(null, " "+ e.toString());			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

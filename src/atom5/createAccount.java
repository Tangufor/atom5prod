package atom5;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class createAccount extends JFrame {
	private JTextField firstNameTxt;
	private JTextField lastNameTxt;
	private JTextField emailTxt;
	private JTextField usernameTxt;
	private JComboBox month;
	private JComboBox day;
	private JComboBox year; 
	private int day1 = 0;
	private Month month1 = null;
	private int year1 = 0;
	private String firstName1 = null;
	private String lastName1 = null;
	private String email1 = null;
	private String password1 = null;
	private String userName = null;
	private LocalDate dob;
	private Image profileImage = null;
	private static confirm cm = new confirm();
	
	public createAccount() {
		addWindowListener(new WindowAdapter() {			
			@Override
			public void windowClosing(WindowEvent e) {
				AtomGUI.atomGUI.setVisible(true);
				AtomGUI.username.requestFocusInWindow();
			}
		});		
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setSize(560, 750);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getWidth()/2, (int) (dim.getHeight()/2 - this.getHeight()/2));
		//setDefaultCloseOperation(JFrame.)
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 554, 703);
		getContentPane().add(panel);
		panel.setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("/Atom-5 Logo.png")).getImage();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setForeground(Color.GRAY);
		panel_1.setBounds(0, 92, 554, 621);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		JLabel firstName = new JLabel("First name:");
		firstName.setForeground(Color.DARK_GRAY);
		firstName.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		firstName.setBounds(12, 13, 101, 26);
		panel_1.add(firstName);
		
		JLabel lastName = new JLabel("Last name:");
		lastName.setForeground(Color.DARK_GRAY);
		lastName.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lastName.setBounds(242, 13, 93, 26);
		panel_1.add(lastName);
		
		firstNameTxt = new JTextField();
		firstNameTxt.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		firstNameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				firstNameTxt.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				firstNameTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		});		
		firstNameTxt.setBounds(12, 42, 218, 39);
		panel_1.add(firstNameTxt);
		firstNameTxt.setColumns(10);
		
		lastNameTxt = new JTextField();
		lastNameTxt.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lastNameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				lastNameTxt.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				lastNameTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		});		
		lastNameTxt.setBounds(242, 42, 254, 39);
		panel_1.add(lastNameTxt);
		lastNameTxt.setColumns(10);
		
		JLabel createEmail = new JLabel("Email:");
		createEmail.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		createEmail.setForeground(Color.DARK_GRAY);
		createEmail.setBounds(12, 94, 60, 26);
		panel_1.add(createEmail);
		
		emailTxt = new JTextField();
		emailTxt.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		emailTxt.setBounds(12, 122, 484, 39);
		emailTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				emailTxt.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				emailTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		});		
		panel_1.add(emailTxt);
		emailTxt.setColumns(10);
		
		JLabel createUser = new JLabel("Username:");
		createUser.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		createUser.setForeground(Color.DARK_GRAY);
		createUser.setBounds(12, 174, 101, 26);
		panel_1.add(createUser);
		
		usernameTxt = new JTextField();
		usernameTxt.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		usernameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				usernameTxt.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				usernameTxt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		});		
		usernameTxt.setBounds(12, 200, 484, 39);
		panel_1.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JLabel createPass = new JLabel("Password:");
		createPass.setForeground(Color.DARK_GRAY);
		createPass.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		createPass.setBounds(12, 252, 101, 26);
		panel_1.add(createPass);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblConfirmPassword.setForeground(Color.DARK_GRAY);
		lblConfirmPassword.setBounds(12, 331, 165, 26);
		panel_1.add(lblConfirmPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Date of birth:");
		lblNewLabel_2.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setBounds(12, 407, 113, 26);
		panel_1.add(lblNewLabel_2);
		
		month = new JComboBox();
		month.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		month.setForeground(Color.DARK_GRAY);
		month.setMaximumRowCount(13);
		month.setModel(new DefaultComboBoxModel(new String[] {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		month.setSelectedIndex(0);
		month.setBounds(12, 437, 84, 26);
		panel_1.add(month);
		
		day = new JComboBox();
		day.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		day.setForeground(Color.DARK_GRAY);
		day.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		day.setSelectedIndex(0);
		day.setMaximumRowCount(10);
		day.setBounds(108, 437, 60, 26);
		panel_1.add(day);
		
		ArrayList <String> yearCnt = new ArrayList<String>(); 
		yearCnt.add("Year");
		for(int i =Calendar.getInstance().get(Calendar.YEAR); i>=1918; i--){
			yearCnt.add(i+"");
		}
		String [] years = new String [103];
		
		for(int j = 0; j<yearCnt.size(); j++){
			years[j] = yearCnt.get(j);
		}		
		year = new JComboBox();
		year.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		year.setForeground(Color.DARK_GRAY);		
		year.setModel(new DefaultComboBoxModel(years));
		year.setSelectedIndex(0);
		year.setMaximumRowCount(20);
		year.setBounds(180, 437, 76, 26);		
		panel_1.add(year);
		
		JButton CreateAccount = new JButton("Create Account");
		CreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!firstNameTxt.getText().isEmpty()){
					firstName1 = firstNameTxt.getText();
				}
				else{
					JOptionPane.showMessageDialog(null, "Please enter your first name.");
				}
				if(!lastNameTxt.getText().isEmpty()){
					lastName1 = lastNameTxt.getText();
				}
				else{
					JOptionPane.showMessageDialog(null, "Please enter your last name.");
				}				
				if(validate(emailTxt.getText())){
					email1 = emailTxt.getText();
				}
				else{
					JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
				}
				if(passwordTxt1.getText().equals(confirmPass.getText()) || passwordTxt1.getText().isEmpty()){
					password1 = passwordTxt1.getText();
				}
				else{
					JOptionPane.showMessageDialog(null, "Passwords do not match.");
				}
				if(!usernameTxt.getText().isEmpty()){
					userName = usernameTxt.getText();
				}
				else{
					JOptionPane.showMessageDialog(null, "Please enter your username.");
				}
				
				try {
					if(day.getSelectedIndex() != 0){
						day1 = Integer.valueOf(day.getSelectedItem().toString());
					}
					else{
						JOptionPane.showMessageDialog(null, "Please select a valid day.");
					}
					if(!month.getSelectedItem().toString().equals("Month")){
						String selMonth = month.getSelectedItem().toString();
						switch (selMonth){
						case "Jan":
							month1 = Month.JANUARY;
							break;
						case "Feb":
							month1 = Month.FEBRUARY;
							break;
						case "Mar":
							month1 = Month.MARCH;
							break;
						case "Apr":
							month1 = Month.APRIL;
							break;
						case "May":
							month1 = Month.MAY;
							break;
						case "Jun":
							month1 = Month.JUNE;
							break;
						case "Jul":
							month1 = Month.JULY;
							break;
						case "Aug":
							month1 = Month.AUGUST;
							break;
						case "Sep":
							month1 = Month.SEPTEMBER;
							break;
						case "Oct":
							month1 = Month.OCTOBER;
							break;
						case "Nov":
							month1 = Month.NOVEMBER;
							break;
						case "Dec":
							month1 = Month.DECEMBER;
							break;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Please select a valid monnth.");
					}
					
					if(year.getSelectedItem().equals("Year")){
						JOptionPane.showMessageDialog(null, "Please select a valid year.");
					}
					else{
						year1 = Integer.valueOf(year.getSelectedItem().toString());
					}
					dob = LocalDate.of(year1, month1, day1);
				}
				catch (Exception ex){
					JOptionPane.showMessageDialog(null, "Please make valid date entries");
				}				
				
				//JOptionPane.showMessageDialog(null, ""+firstName1 +","+lastName1 + ","+ email1 + ","+userName + ","+password1 + ","+dob);
				AtomGUI.connection.insertToMySQL(firstName1, lastName1, email1, userName, password1, dob, profileImage);	
				clearData();
				cm.setVisible(true);				
			}
		});
		CreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				CreateAccount.setBackground(new Color(255, 165, 0));
				CreateAccount.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CreateAccount.setBackground(new Color(255, 140, 0));
			}
		});
		
		CreateAccount.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		CreateAccount.setBackground(new Color(255, 140, 0));
		CreateAccount.setForeground(Color.WHITE);
		CreateAccount.setBounds(136, 509, 281, 39);
		panel_1.add(CreateAccount);
		
		passwordTxt1 = new JPasswordField();
		passwordTxt1.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		passwordTxt1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				passwordTxt1.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				passwordTxt1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		});		
		passwordTxt1.setBounds(12, 280, 484, 39);
		panel_1.add(passwordTxt1);
		
		confirmPass = new JPasswordField();
		confirmPass.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		confirmPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				confirmPass.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
			}
			@Override
			public void focusLost(FocusEvent e) {
				confirmPass.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		});		
		confirmPass.setBounds(12, 361, 484, 39);
		panel_1.add(confirmPass);
		
		JLabel lblNewLabel = new JLabel("Join the Community");
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setBounds(234, 24, 295, 40);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 36));
		
		JLabel lblNewLabel_1 = new JLabel("No worries, it's always free");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1.setBounds(234, 63, 294, 26);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Calibri Light", Font.ITALIC, 20));
		
		JLabel logo1 = new JLabel("");
		logo1.setBounds(0, 0, 543, 88);
		panel.add(logo1);
		logo1.setForeground(Color.BLACK);
		logo1.setBackground(Color.WHITE);
		logo1.setIcon(new ImageIcon(img));
		
		
	}
	public static final Pattern VALID_EMAIL = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private JPasswordField passwordTxt1;
	private JPasswordField confirmPass;

		public static boolean validate(String emailStr) {
		        Matcher matcher = VALID_EMAIL.matcher(emailStr);
		        return matcher.find();
		}
	public static void closeCon(){
		cm.setVisible(false);
	}
	private void clearData(){
		this.emailTxt.setText("");
		this.usernameTxt.setText("");
		this.firstNameTxt.setText("");
		this.lastNameTxt.setText("");
		this.passwordTxt1.setText("");
		this.confirmPass.setText("");
		this.year.setSelectedIndex(0);
		this.day.setSelectedIndex(0);
		this.month.setSelectedIndex(0);	
	}
}

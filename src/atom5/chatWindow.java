package atom5;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;

import java.awt.Choice;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
//import java.io.FileFilter;
import java.util.Map;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.Panel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;

//import com.sun.javafx.geom.Rectangle;

import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;

import java.io.*;
import java.nio.file.Files;

public class chatWindow extends JFrame {
	private JTextField searchFriends;
	private JTextField cfName;
	private JTextField textField;
	private JTextField clientName;
	private JTextField clientEmail;
	private JTextField signOut;
	private Image profImage = new ImageIcon(this.getClass().getResource("/profileImage.jpg")).getImage();
	protected static JTextArea chatMsg;
	protected static String name;
	protected static String email;
	protected static boolean loggedIn = true;
	protected static Image cfImage;
	protected static JLabel cfProfilePic;
	protected static final int FILE_SIZE = 50000000;
	protected static JTextPane chatText = new JTextPane();
	static JRadioButton cfStatus;
	static JPanel cfPanel;
	
	protected static StyledDocument doc;
	
	private JLabel close;
	private JPanel panel_5;
	private JPanel panel_1;
	private JPanel panel_4;
	Image img = new ImageIcon(this.getClass().getResource("/nav1.png")).getImage();
	Image img2 = new ImageIcon(this.getClass().getResource("/attach.png")).getImage();
	JPanel profileWin;
	protected static SimpleAttributeSet left;
	private DefaultCaret caret;
	

	public chatWindow(String name) {	
		
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		setSize(984,724);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getWidth()/2, (int) (dim.getHeight()/2 - this.getHeight()/2));
		
		doc = chatText.getStyledDocument();
		//Style style = doc.addStyle("StyleName", null);
		//StyleConstants.setIcon(style, new ImageIcon(this.getClass().getResource("/file icon.png")));
		left = new SimpleAttributeSet();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(left, Color.RED);
		
		
		SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(right, Color.BLUE);
		
		
		panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 0, 553);
		panel_5.setBackground(Color.WHITE);
		getContentPane().add(panel_5);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 140, 0), 1, true));
		panel.setBounds(0, 0, 982, 52);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel sideMenu = new JLabel("");
		
		Image img1 = new ImageIcon(this.getClass().getResource("/close.png")).getImage();
		profileWin = new JPanel();
		profileWin.setLayout(null);
		profileWin.setSize(334,586);			
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(0, 0, 332, 553);
		
		panel2.setLayout(null);
		
		JLabel profileImage = new JLabel("New label");
		profileImage.setBounds(106, 97, 120, 120);
		profileImage.setIcon(new ImageIcon(profImage));	
		profileImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {					
				int option = JOptionPane.showConfirmDialog(null, "Do you want to upload a new profile picture?", "Make a selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.YES_OPTION){
					BufferedImage img3 = null;					
					FileFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
	                JFileChooser openfile = new JFileChooser(".");	 
	                openfile.addChoosableFileFilter(filter);
                	int res = openfile.showOpenDialog(null);
	                if (res == JFileChooser.APPROVE_OPTION){
	                    File filename = openfile.getSelectedFile();
	                    try{
	                    	img3 = ImageIO.read(filename);			                              
	                    }
	                    catch (Exception ex){     
	                        ex.printStackTrace();
	                        JOptionPane.showMessageDialog(null, "File not found. Please select a valid file from the folder");
	                    }
	                    try{
	                    	profImage = img3.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		                }
		                catch(Exception exc){
		                	JOptionPane.showMessageDialog(null, "Wrong file format. Only image file extensions are allowed");
		                }
		                   profileImage.setIcon(new ImageIcon(profImage));	
		                   cfImage = profImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);        
			           	   cfProfilePic.setIcon(new ImageIcon(cfImage));
		                }
		                else {
		                    JOptionPane.showMessageDialog(null, "No file has been selected. Please select a valid file from the folder");                    
		                }	
					}
					else{
						
					}
			}
		});
	
		
		clientName = new JTextField();
		clientName.setHorizontalAlignment(SwingConstants.CENTER);
		clientName.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		clientName.setBackground(Color.WHITE);
		clientName.setText(chatWindow.name);
		clientName.setForeground(Color.DARK_GRAY);
		clientName.setBounds(55, 230, 219, 32);
		clientName.setBorder(null);
		clientName.setEditable(false);
		panel2.add(clientName);
		clientName.setColumns(10);
		
		clientEmail = new JTextField();
		clientEmail.setHorizontalAlignment(SwingConstants.CENTER);
		clientEmail.setForeground(Color.DARK_GRAY);
		clientEmail.setBackground(Color.WHITE);
		clientEmail.setText(email);
		clientEmail.setFont(new Font("Calibri Light", Font.ITALIC, 15));
		clientEmail.setBounds(12, 275, 308, 22);
		clientEmail.setBorder(null);
		clientEmail.setEditable(false);
		panel2.add(clientEmail);
		clientEmail.setColumns(10);
		
		signOut = new JTextField();
		signOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Font font = signOut.getFont();
				Map attrib = font.getAttributes();
				attrib.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				signOut.setFont(font.deriveFont(attrib));
				signOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				signOut.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 20));
				signOut.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mouseClicked(MouseEvent e){
				try {
					AtomGUI.loggedIn = false;
					loggedIn = false;
					AtomGUI.msgSent.writeObject(new MessageType(MessageType.LOGOUT, null));
					cfStatus.setText("Offline");
					cfStatus.setIcon(new selectIcon(Color.GRAY));
					close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "ACTION NOT PERFORMED DUE TO SERVER ERROR. TRY AGAIN LATER." + "\n");
				}
			}
		});
		
		signOut.setForeground(new Color(255, 140, 0));
		signOut.setBackground(new Color(0, 0, 0));
		signOut.setHorizontalAlignment(SwingConstants.CENTER);
		signOut.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 20));
		signOut.setText("Sign out");
		signOut.setBounds(119, 310, 94, 32);
		signOut.setBorder(null);
		panel2.add(signOut);
		signOut.setColumns(10);
		
		close = new JLabel("New label");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_5.setBounds(0, 7, 0, 553);
				//panel_5.remove(profileImage);
				panel_1.add(searchFriends);					
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				close.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				close.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		close.setBounds(266, 13, 54, 54);
		close.setIcon(new ImageIcon(img1));
		panel2.add(close);
		panel2.add(profileImage);
		profileWin.add(panel2);
		
		
		sideMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	
				panel_5.setBounds(0, 7, 324, 553);	
				if(profileWin.getParent() != panel_5){
					panel_5.add(profileWin);
				}								
					//profileWindow.setVisible(true);				
				panel_1.remove(searchFriends);
			}		
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		sideMenu.setBounds(12, 7, 50, 38);
		panel.add(sideMenu);
		sideMenu.setIcon(new ImageIcon(img));
		
		
		panel_1 = new JPanel();
		panel_1.setBounds(-1, 52, 334, 610);
		panel_1.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		searchFriends = new JTextField();
		searchFriends.setHorizontalAlignment(SwingConstants.TRAILING);
		searchFriends.setBounds(49, 39, 231, 35);
		searchFriends.setBorder(null);
		panel_1.add(searchFriends);
		searchFriends.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(339, 126, 99, -47);
		getContentPane().add(scrollPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 140, 0), 1, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(327, 52, 655, 75);		
		getContentPane().add(panel_2);
		panel_2.setLayout(null);	
		
		cfImage = profImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);        
		cfPanel = new JPanel();
		cfPanel.setBackground(Color.WHITE);
		cfProfilePic = new JLabel("");
		cfProfilePic.setIcon(new ImageIcon(cfImage));
		cfProfilePic.setBounds(22, 13, 60, 60);
		cfPanel.add(cfProfilePic);
		
		cfName = new JTextField();
		cfName.setBorder(null);
		cfName.setBounds(103, 13, 185, 22);
		cfName.setEditable(false);
		cfName.setBackground(Color.WHITE);
		cfName.setText(this.name);
		cfPanel.add(cfName);
		cfName.setColumns(10);
		
		cfStatus = new JRadioButton();
		if(loggedIn){
			cfStatus.setText("Online");
			cfStatus.setIcon(new selectIcon(Color.GREEN));
		}
		else{
			cfStatus.setText("Offline");
			cfStatus.setIcon(new selectIcon(Color.GRAY));
		}		
		cfStatus.setSelected(true);
		cfStatus.setFont(new Font("Calibri Light", Font.ITALIC, 18));
		cfStatus.setForeground(new Color(255, 140, 0));
		cfStatus.setBackground(Color.WHITE);
		cfStatus.setBounds(103, 37, 127, 25);
		cfPanel.add(cfStatus);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(12, 6, 308, 62);
		panel_4.add(cfPanel);
		panel_2.add(panel_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 250, 205));
		panel_3.setBounds(343, 610, 625, 52);
		panel_3.setBorder(new LineBorder(new Color(255, 140, 0), 1, true));
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 7, 448, 38);
		scrollPane.setBorder(null);
		panel_3.add(scrollPane);
		
		chatMsg = new JTextArea();		
		chatMsg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					if(!chatMsg.getText().isEmpty()){
						AtomGUI.chatExchange(new byte[1]);
						String ob = chatMsg.getText();
						try {
							doc.insertString(doc.getLength(), "\n " + ob + " < " + chatWindow.name, right );
						} catch (BadLocationException ex) {
							JOptionPane.showMessageDialog(null, ex);						
						}
						doc.setParagraphAttributes(doc.getLength(), 1, right, false);
					}
					else{
						
					}					
					chatMsg.setText("");
				}
			}
		});
		chatMsg.setBackground(new Color(255, 255, 255));
		chatMsg.setLineWrap(true);		
		chatMsg.setColumns(10);
		scrollPane.setViewportView(chatMsg);
		chatMsg.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		
		
		JLabel attach = new JLabel("");
		attach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser attachfile = new JFileChooser(".");
				int res = attachfile.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION){
                    File filename = attachfile.getSelectedFile();
                    try {
                    	byte[] fileSent = Files.readAllBytes(filename.toPath());                    	
						/*BufferedInputStream bi = new BufferedInputStream(new FileInputStream(filename));
						bi.read(fileSent, 0, fileSent.length);*/
						int option = JOptionPane.showConfirmDialog(null, "Send '"+filename+"' now?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						
                    	if(option == JOptionPane.OK_OPTION){
							AtomGUI.chatExchange(fileSent);
							/*try {	
								JLabel jl = new JLabel("");
								Image fImg = new ImageIcon(this.getClass().getResource("/file icon.png")).getImage();
								jl.setSize(63,48);
								jl.setIcon(new ImageIcon(fImg));
								
								//chatText.insertComponent(jl);
								doc.insertString(doc.getLength(), "\n " + fileSent + " < " + chatWindow.name, style);
							} catch (BadLocationException ex) {
								JOptionPane.showMessageDialog(null, ex);						
							}
							doc.setParagraphAttributes(doc.getLength(), 1, right, false);*/
						}
						else{
							
						}
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}                                        
                  
                }
                else{
                	JOptionPane.showMessageDialog(null, "No file has been selected. Please select a valid file from the folder");                    
                }
			}
		});
		attach.setBounds(472, 3, 57, 46);
		attach.setIcon(new ImageIcon(img2));
		panel_3.add(attach);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!chatMsg.getText().isEmpty()){
					AtomGUI.chatExchange(new byte[1]);
					String ob = chatMsg.getText();
					try {
						doc.insertString(doc.getLength(), "\n " + ob + " < " + chatWindow.name, right );
					} catch (BadLocationException e) {
						JOptionPane.showMessageDialog(null, e);						
					}
					doc.setParagraphAttributes(doc.getLength(), 1, right, false);
					//chatText.append(ob + " < " + chatWindow.name + "\n");					
				}
				else{					
				}
				chatMsg.setText("");				
			}
		});
		sendButton.setForeground(Color.WHITE);
		sendButton.setBackground(new Color(255, 140, 0));
		sendButton.setFont(new Font("Calibri Light", Font.BOLD, 20));
		sendButton.setBounds(534, 4, 79, 44);
		panel_3.add(sendButton);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(345, 140, 623, 457);
		scrollPane_2.setBorder(null);
		getContentPane().add(scrollPane_2);
		
		chatText.setEditable(false);
		caret = (DefaultCaret) chatText.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		chatText.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		scrollPane_2.setViewportView(chatText);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	void close(){
		this.setVisible(false);
		AtomGUI.atomGUI.setVisible(true);
	}
	public class selectIcon implements Icon {
		Color color;
		public selectIcon(Color c){
			color = c;			
		}

		@Override
		public int getIconHeight() {			
			return 10;
		}

		@Override
		public int getIconWidth() {			
			return 10;
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(color);
			g2d.fillOval(x, y, getIconWidth(), getIconHeight());			
		}
		
	}
}

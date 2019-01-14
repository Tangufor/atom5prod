package atom5;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class profileWindow extends JPanel{
	public profileWindow() {
		setLayout(null);
		setSize(334,586);			
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 332, 553);
		add(panel);
		panel.setLayout(null);
		
		JLabel profileImage = new JLabel("New label");
		profileImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Do you want to upload a new profile picture?", "Upload Profile Picture?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
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
	                        //JOptionPane.showMessageDialog(null, "File not found. Please select a valid file from the folder");
	                    }		                    
	                   //profImage = img3.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	                   /*JLabel jl = new JLabel();
	                   jl.setIcon(new ImageIcon(profImage));
	                   JOptionPane.showMessageDialog(null, jl);*/
	                }
	                else {
	                    JOptionPane.showMessageDialog(null, "No file has been selected. Please select a valid file from the folder");                    
	                }	
				}
				else{
					
				}
				repaint();
			}
		});
		
		
		
		/*profileImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton uploadPicButton = new JButton("New Profile Picture");
				uploadPicButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT file", "txt");
		                JFileChooser openfile = new JFileChooser(".");
		                //openfile.addChoosableFileFilter(filter);
		                int res = openfile.showOpenDialog(null);
		                if (res == JFileChooser.APPROVE_OPTION){
		                    File filename = openfile.getSelectedFile();
		                    try{
		                        profileImage.setIcon(new ImageIcon(ImageIO.read(filename)));      
		                    }
		                    catch (Exception ex){     
		                        ex.printStackTrace();
		                        //JOptionPane.showMessageDialog(null, "File not found. Please select a valid file from the folder");
		                    }                    
		                }
		                else {
		                    JOptionPane.showMessageDialog(null, "No file has been selected. Please select a valid file from the folder");                    
		                }
					}
				});
				uploadPicButton.setFont(new Font("Calibri Light", Font.PLAIN, 15));
				uploadPicButton.setBounds(93, 179, 145, 32);
				panel.add(uploadPicButton);
				
			}
		});*/
		profileImage.setForeground(Color.WHITE);
		profileImage.setBackground(Color.WHITE);
		profileImage.setBounds(106, 97, 120, 120);
		panel.add(profileImage);
		
		JTextField clientName = new JTextField();
		clientName.setHorizontalAlignment(SwingConstants.CENTER);
		clientName.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		clientName.setBackground(Color.WHITE);
		clientName.setText("Nathan Tangufor");
		clientName.setForeground(Color.DARK_GRAY);
		clientName.setBounds(55, 230, 219, 32);
		clientName.setBorder(null);
		clientName.setEditable(false);
		panel.add(clientName);
		clientName.setColumns(10);
		
		JTextField clientEmail = new JTextField();
		clientEmail.setHorizontalAlignment(SwingConstants.CENTER);
		clientEmail.setForeground(Color.DARK_GRAY);
		clientEmail.setBackground(Color.WHITE);
		clientEmail.setText("Hey");
		clientEmail.setFont(new Font("Calibri Light", Font.ITALIC, 15));
		clientEmail.setBounds(12, 275, 308, 22);
		clientEmail.setBorder(null);
		clientEmail.setEditable(false);
		panel.add(clientEmail);
		clientEmail.setColumns(10);
		
		JTextField signOut = new JTextField();
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
		});
		signOut.setForeground(new Color(255, 140, 0));
		signOut.setBackground(new Color(0, 0, 0));
		signOut.setHorizontalAlignment(SwingConstants.CENTER);
		signOut.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 20));
		signOut.setText("Sign out");
		signOut.setBounds(119, 310, 94, 32);
		signOut.setBorder(null);
		panel.add(signOut);
		signOut.setColumns(10);
		
		JLabel close = new JLabel("New label");
		close.addMouseListener(new MouseAdapter() {
			/*@Override
			public void mouseClicked(MouseEvent e) {
				panel_5.setBounds(0, 7, 0, 553);		
				panel_1.add(searchFriends);					
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				close.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				close.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}*/
		});
		close.setBounds(266, 13, 54, 54);
		//close.setIcon(new ImageIcon(img1));
		panel.add(close);
		
		JButton uploadPicButton = new JButton("New Profile Picture");
		uploadPicButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT file", "txt");
                JFileChooser openfile = new JFileChooser(".");
                //openfile.addChoosableFileFilter(filter);
                int res = openfile.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION){
                    File filename = openfile.getSelectedFile();
                    try{
                        profileImage.setIcon(new ImageIcon(ImageIO.read(filename)));      
                    }
                    catch (Exception ex){     
                        ex.printStackTrace();
                        //JOptionPane.showMessageDialog(null, "File not found. Please select a valid file from the folder");
                    }                    
                }
                else {
                    JOptionPane.showMessageDialog(null, "No file has been selected. Please select a valid file from the folder");                    
                }
			}
		});
		uploadPicButton.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		uploadPicButton.setBounds(93, 179, 145, 32);
		panel.add(uploadPicButton);
		
	}
}

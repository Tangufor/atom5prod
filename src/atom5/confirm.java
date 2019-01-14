package atom5;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class confirm extends JFrame{
	private JTextField txtAccountSuccessfullyCreated;
	private static confirm con;
	public confirm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				AtomGUI.closeCa();
				createAccount.closeCon();
			}
		});
		getContentPane().setLayout(null);
		
		setSize(446, 130);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getWidth()/2, (int) (dim.getHeight()/2 - this.getHeight()/2));
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 440, 95);		
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtAccountSuccessfullyCreated = new JTextField();
		txtAccountSuccessfullyCreated.setBackground(new Color(0, 0, 0));
		txtAccountSuccessfullyCreated.setHorizontalAlignment(SwingConstants.CENTER);
		txtAccountSuccessfullyCreated.setEditable(false);
		txtAccountSuccessfullyCreated.setForeground(new Color(255, 140, 0));
		txtAccountSuccessfullyCreated.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 19));
		txtAccountSuccessfullyCreated.setText("Account successfully created. Log in to get started");
		txtAccountSuccessfullyCreated.setBorder(null);
		txtAccountSuccessfullyCreated.setBounds(0, 0, 440, 48);
		panel.add(txtAccountSuccessfullyCreated);
		txtAccountSuccessfullyCreated.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtomGUI.closeCa();
				createAccount.closeCon();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setFont(new Font("Calibri Light", Font.BOLD, 22));
		btnNewButton.setBounds(182, 50, 75, 41);
		panel.add(btnNewButton);
	}
}

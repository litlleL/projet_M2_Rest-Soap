package com.driveClient.jersey;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.driveClient.jersey.SoapToRest.Inscription;



public class LogScreen extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton Close;
	public JButton inscription;
	
	
	public LogScreen() {
		LoginScreen();
	}
	
	
	
	public void LoginScreen(){
		this.setSize(850,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		inscription = new JButton("Login / Sign Up");
		Close = new JButton("Cancel");
		this.add(new JLabel(new ImageIcon("./src/splash/splash.jpg")));
		this.add(inscription,BorderLayout.NORTH);
		this.add(Close, BorderLayout.SOUTH);
		
		inscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				new Inscription();
				setVisible(false);
			}
		});
		Close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
    	});
		
		setVisible(true);
		this.repaint();
	}

	
	
public void actionPerformed(ActionEvent arg0) {
}

}
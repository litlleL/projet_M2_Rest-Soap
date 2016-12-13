package com.driveClient.jersey;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client extends JFrame{

	private JGameArea gameArea;
	final JFrame Client = new JFrame("Crazy Driver");
	public String  sessionID = null;
	public String sessionNumber = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3816398004018397004L;

	public Client(String id){
		this.sessionID = id;
		Client.setSize(1280, 720);
		Client.setResizable(false);
		Client.setLocationRelativeTo(null);
		Client.setLayout(new BorderLayout());
		Client.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Client.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int reponse = JOptionPane.showConfirmDialog(Client,"Voulez-vous quitter?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if (reponse==JOptionPane.YES_OPTION){
					gameArea.stop();
               }
			}
		});
		initInterface(sessionID);
		Client.addKeyListener(gameArea);
		Client.setVisible(true);
	}
	
	private void initInterface(String session) {
		this.sessionNumber = session;
		gameArea = new JGameArea(sessionNumber);
		Client.getContentPane().add(gameArea,BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new LogScreen();
	 }
}


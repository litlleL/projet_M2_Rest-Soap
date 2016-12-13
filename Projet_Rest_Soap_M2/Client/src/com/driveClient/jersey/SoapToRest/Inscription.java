package com.driveClient.jersey.SoapToRest;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.rpc.ServiceException;

import com.driveClient.jersey.Client;
import com.driveClient.jersey.SignIn;
import com.service.soap.test.SoapToRest;
import com.service.soap.test.SoapToRestServiceLocator;

public class Inscription extends JFrame implements ActionListener{
	
private static final long serialVersionUID = -7403922709569059419L;
	
	public static int width = 400, height = 150;
	
	private JTextField pseudo;
	private JLabel pseudoLabel;
	private JTextField pass;
	private JLabel passLabel;
	
	public JPanel screen;
	
	public JButton LogIn;
	public JButton SignIn;
	public JButton UnSignIn;
	public JButton Close;
	
	public String result = null;
	public boolean resultSupp;
	
	public Inscription() {
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		Container contenu = getContentPane();
		initLog(contenu);
		this.setVisible(true);
	}

	private void initLog(Container contenu) {
		screen = new JPanel();
		Box boxlay = Box.createVerticalBox();	
		pseudoLabel = new JLabel("Pseudo");			
		boxlay.add(pseudoLabel);						
		pseudo = new JTextField("defaultPseudo",12);			
		boxlay.add(pseudo);
		passLabel = new JLabel("Password");		
		boxlay.add(passLabel);								
		pass = new JTextField("defaultPass",12);				
		boxlay.add(pass);
		
		JPanel Button =new JPanel();
		
		LogIn = new JButton("LogIn");
		LogIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				SoapToRest t = auth();
				try {
					result = t.auth(pseudo.getText(), pass.getText());
					if(!result.equals("NoId")){
						setVisible(false);
						new Client(result);
					}
					else{
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,
								"Vous n'�tes pas encore inscrit \n ou \n Vous avez rentr� de mauvais identifiant",
							    "Warning",
							    JOptionPane.WARNING_MESSAGE);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

			public SoapToRest auth() {
				SoapToRestServiceLocator service = new SoapToRestServiceLocator();
				try {
					SoapToRest t = service.getSoapToRest();
					return t;
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				return null;
			}
    	});
		
		SignIn = new JButton("SignIn");
		SignIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				setVisible(false);
				new SignIn();
			}
    	});
		UnSignIn = new JButton("UnSignIn");
		UnSignIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				SoapToRest t = getout();
				try {
					resultSupp = t.getOut(pseudo.getText(), pass.getText());
					System.out.println(resultSupp);
					if(resultSupp){
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,
								"Vptre compte � bien �t� supprim�",
							    "Information",
							    JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,
								"Le compte que vous avez voulu supp n'existe pas\n",
							    "Warning",
							    JOptionPane.WARNING_MESSAGE);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

			public SoapToRest getout() {
				SoapToRestServiceLocator service = new SoapToRestServiceLocator();
				try {
					SoapToRest t = service.getSoapToRest();
					return t;
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				return null;
			}
    	});
		
		Close = new JButton("Close");
		Close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
    	});
		
		Button.add(LogIn);
		Button.add(SignIn);
		Button.add(UnSignIn);
		Button.add(Close);
		boxlay.add(Button);
		screen.add(boxlay);
		contenu.add(screen,BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}

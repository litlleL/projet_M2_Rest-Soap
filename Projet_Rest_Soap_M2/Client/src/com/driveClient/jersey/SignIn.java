package com.driveClient.jersey;
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

import com.driveClient.jersey.SoapToRest.Inscription;
import com.service.soap.test.SoapToRest;
import com.service.soap.test.SoapToRestServiceLocator;

public class SignIn extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 68129046967365116L;

	public static int width = 300, height = 180;
	
	
	private JTextField pseudo;
	private JLabel pseudoLabel;
	private JTextField pass;
	private JLabel passLabel;
	private JTextField passVerification;
	private JLabel passLabelVerification;
	
	public JPanel screen;
	
	public JButton Add;
	public JButton Exit;
	
	public Inscription fene;
	
	public boolean inscrit = false;
	
	
	public SignIn(){
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		Container contenu = getContentPane();
		initSignIn(contenu);
		this.setVisible(true);
	}

	private void initSignIn(Container contenu) {
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
		
		passLabelVerification = new JLabel("Password Verification");		
		boxlay.add(passLabelVerification);								
		passVerification = new JTextField("defaultPass",12);				
		boxlay.add(passVerification);
		
		JPanel Button =new JPanel();
		
		Add = new JButton("Inscription");
		Exit = new JButton("Quitter");
		
		Add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				SoapToRest t = inscrit();
				try {
					inscrit = t.inscription(pseudo.getText(), pass.getText(), passVerification.getText());
					if(inscrit){
						setVisible(false);
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,
								"Vptre compte à bien été crée",
							    "Information",
							    JOptionPane.INFORMATION_MESSAGE);
						fene = new Inscription();
					}
					else{
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,
								"Erreur \n Le login entré est déja utilisé \n ou \n Les 2 mots de passe rentré ne correspondent pas.\n",
							    "Warning",
							    JOptionPane.WARNING_MESSAGE);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
			Exit.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ev) {
					setVisible(false);
					new Inscription();
				}
			});
		}

			
			public SoapToRest inscrit() {
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
		Button.add(Add);
		Button.add(Exit);
		boxlay.add(Button);
		screen.add(boxlay);
		contenu.add(screen,BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	
}
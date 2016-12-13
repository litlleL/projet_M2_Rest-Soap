package com.driveClient.jersey;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class JGameArea extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8911483534755650289L;
	
	private static final long TIMEOUT = 30000;
	private static final long PLAYTIME = 50000;
	
	//avancer
	public static int up = 1; 
	public static int down = 2;
	public static int notouch = 3;
	//tourner
	public static int left = 1;
	public static int right = 2;
	public static int toutDroit = 3;
	
	public int mouvement = 3;
	public int rotation = 3;
	public int PosX = 0, PosY= 0;

	public double vitesse = 0, angle = 0;
	
	private long afk;
	private long play;
	
	private long afkTime1;
	private long playTime;
	
	public int []index;
	public HashMap<Integer,String> enemyInfo = new HashMap<Integer,String>();
	public AffineTransform rota;
	public MonThread thread;
	
	public String id ;
	public String vehiculeVitesse = null;
	
	boolean done = false;
	
	public JLabel vitesseVehicule ;

	BufferedImage img;
	BufferedImage imgOther;
	
	public ClientConfig config =new ClientConfig();
	Client client = ClientBuilder.newClient(config);
	WebTarget service = client.target(getBaseURI());
	
	
	public JGameArea(String identifiant) {
		super();
		JOptionPane.showMessageDialog(null, "Bienvenue dans se jeu pour perdre du temps, \n Les règles sont simples:\n\t-ne pas rester immobile plus de 30 secondes\n\t-ne pas toucher les bords de la zone de jeu \n\t-vous n'avez qu'une vie\n\t\n\tAttention des surprises peuvent arriver (Ce jeu est une alpha et peut faire l'objet de bug MDDDR)","règle du jeu", JOptionPane.INFORMATION_MESSAGE);
		this.setLayout(null);
		vitesseVehicule = new JLabel("test");
		this.id = identifiant;
		try {
			img = ImageIO.read(new File("./src/voiture/images.png"));
			imgOther = ImageIO.read(new File("./src/voiture/imagesEnemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setBackground(Color.white);
		setAfk(System.currentTimeMillis());
		setPlay(System.currentTimeMillis());
		playTime = getPlay();
		afkTime1 = getAfk();
		thread = new MonThread(this);
		thread.start();
	}
	
	public void modify() {
		afkTime1 = getAfk();
		playTime = getPlay();
		if ((System.currentTimeMillis() - afkTime1) > TIMEOUT) {
			JOptionPane.showMessageDialog(null, "Vous avez été déconnecter du serveur pour cause d'inactivité", "Inactivité", JOptionPane.INFORMATION_MESSAGE);
			this.stop();
			System.exit(0);
		}
		if(((System.currentTimeMillis() - playTime) > PLAYTIME)){
			if(this.isVisible()){
				this.setVisible(false);
				setPlay(System.currentTimeMillis());
			}
			else{
				this.setVisible(true);
				setPlay(System.currentTimeMillis());
			}
		}
		String Response = service.path("rest").path("server").path(id).path("other").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(Response);
		while(scanner.hasNextLine()) {
			String info = scanner.nextLine();
			String []tab = info.split(";");
			Integer id = new Integer( tab[0].lastIndexOf(":"));
			String element = tab[0].substring(id+1);
			enemyInfo.put(new Integer(element), tab[1]);
		}
		
		if(rotation == left){
			String Response1 = service.path("rest").path("server").path(id).path("left").request().accept(MediaType.TEXT_PLAIN).get(String.class);
			String []tab = Response1.split(",");
			Integer index = new Integer (tab[1].lastIndexOf(":"));
			String value = tab[1].substring(index+1);
			angle = new Double(value);
		}
		else if(rotation == right){
			String Response1 = service.path("rest").path("server").path(id).path("right").request().accept(MediaType.TEXT_PLAIN).get(String.class);
			String []tab = Response1.split(",");
			Integer index = new Integer (tab[1].lastIndexOf(":"));
			String value = tab[1].substring(index+1);
			angle = new Double(value);
		}
		
		if(mouvement == up){
			String Response1 = service.path("rest").path("server").path(id).path("up").request().accept(MediaType.TEXT_PLAIN).get(String.class);
			String []tab = Response1.split(",");
			for(int i = 1; i <= 4; i++){
				Integer index = new Integer (tab[i].lastIndexOf(":"));
				String value = tab[i].substring(index+1);
				if(i == 1){
					angle = new Double(value);
				}
				else if(i == 2){
					PosX = new Integer(value);
					PosX += this.getWidth() / 2 - img.getWidth(this) / 2;
					if (((PosX + img.getWidth(this) - 10) >= this.getWidth()) || (PosX <= 0)) {
						JOptionPane.showMessageDialog(null, "Vous avez perdu, vous êtes sorti de la zone de jeu \n\n   GAAAAAME OVEEEEEER!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
						this.stop();
						System.exit(0);
					}
				}
				else if(i == 3){
					PosY = new Integer(value);
					PosY += this.getHeight() /2 - img.getHeight(this) / 2;
					if (((PosY + img.getHeight(this) - 10) >= this.getHeight()) || (PosY <= 0)) {
						JOptionPane.showMessageDialog(null, "Vous avez perdu, vous êtes sorti de la zone de jeu \n\n   GAAAAAME OVEEEEEER!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
						this.stop();
						System.exit(0);
					}
				}
				else if(i == 4){
					vitesse = new Double(value);
				}
			}
		}
		else if(mouvement == down){
			String Response1 = service.path("rest").path("server").path(id).path("down").request().accept(MediaType.TEXT_PLAIN).get(String.class);
			String []tab = Response1.split(",");
			for(int i = 1; i <= 4; i++){
				Integer index = new Integer (tab[i].lastIndexOf(":"));
				String value = tab[i].substring(index+1);
				if(i == 1){
					angle = new Double(value);
				}
				else if(i == 2){
					PosX = new Integer(value);
					PosX += this.getWidth() / 2 - img.getWidth(this) / 2;
					if (((PosX + img.getWidth(this) - 10) >= this.getWidth()) || (PosX <= 0)) {
						JOptionPane.showMessageDialog(null, "Vous avez perdu, vous êtes sorti de la zone de jeu \n\n   GAAAAAME OVEEEEEER!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
						this.stop();
						System.exit(0);
					}
				}
				else if(i == 3){
					PosY = new Integer(value);
					PosY += this.getHeight() /2 - img.getHeight(this) / 2;
					if (((PosY + img.getHeight(this) - 10) >= this.getHeight()) || (PosY <= 0)) {
						JOptionPane.showMessageDialog(null, "Vous avez perdu, vous êtes sorti de la zone de jeu \n\n   GAAAAAME OVEEEEEER!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
						this.stop();
						System.exit(0);
					}
				}
				else if(i == 4){
					vitesse = new Double(value);
				}
			}
		}
		else if(mouvement == notouch){
			String Response1 = service.path("rest").path("server").path(id).path("notouch").request().accept(MediaType.TEXT_PLAIN).get(String.class);
			String []tab = Response1.split(",");
			for(int i = 1; i <= 4; i++){
				Integer index = new Integer (tab[i].lastIndexOf(":"));
				String value = tab[i].substring(index+1);
				if(i == 1){
					angle = new Double(value);
				}
				else if(i == 2){
					PosX = new Integer(value);
					PosX += this.getWidth() / 2 - img.getWidth(this) / 2;
					if (((PosX + img.getWidth(this) - 10) >= this.getWidth()) || (PosX <= 0)) {
						JOptionPane.showMessageDialog(null, "Vous avez perdu, vous êtes sorti de la zone de jeu \n\n   GAAAAAME OVEEEEEER!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
						this.stop();
						System.exit(0);
					}
				}
				else if(i == 3){
					PosY = new Integer(value);
					PosY += this.getHeight() /2 - img.getHeight(this) / 2;
					if (((PosY + img.getHeight(this) - 10) >= this.getHeight()) || (PosY <= 0)) {
						JOptionPane.showMessageDialog(null, "Vous avez perdu, vous êtes sorti de la zone de jeu \n\n   GAAAAAME OVEEEEEER!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
						this.stop();
						System.exit(0);
					}
				}
				else if(i == 4){
					vitesse = new Double(value);
				}
			}
		}
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		Graphics2D g2dEnemy = (Graphics2D) g;
		super.paintComponent(g);
		if(!done){
			changeZZ();
		}
		AffineTransform imgYou = AffineTransform.getTranslateInstance(PosX, PosY);
		imgYou.rotate(Math.toRadians(angle),img.getWidth()/2, img.getHeight()/2);
		
		g2d.drawImage(img,imgYou,null);
		g2d.drawString(Math.abs(vitesse*2.5) +"km/h", img.getType(), img.getHeight());
		if(!enemyInfo.isEmpty()){
			for (int key : enemyInfo.keySet()) {
				int PosXEnemy = 0, PosYEnemy= 0;
				@SuppressWarnings("unused")
				double vitesseEnemy = 0, angleEnemy = 0;
				String []tab = enemyInfo.get(key).split(",");
				for(int i = 0; i < 4; i++){
					Integer index = new Integer (tab[i].lastIndexOf(":"));
					String value = tab[i].substring(index+1);
					if(i == 0){
						angleEnemy = new Double(value);
					}
					else if(i == 1){
						PosXEnemy = new Integer(value);
						PosXEnemy += this.getWidth() / 2 - img.getWidth(this) / 2;
					}
					else if(i == 2){
						PosYEnemy = new Integer(value);
						PosYEnemy += this.getHeight() /2 - img.getHeight(this) / 2;
					}
					else if(i == 3){
						vitesseEnemy = new Double(value);
					}
				}
				AffineTransform imgEnemy = AffineTransform.getTranslateInstance(PosXEnemy, PosYEnemy);
				imgEnemy.rotate(Math.toRadians(angleEnemy),imgOther.getWidth()/2, imgOther.getHeight()/2);
				g2dEnemy.drawImage(imgOther,imgEnemy,null);
			}
			enemyInfo.clear();
			
		}
		/*
		AffineTransform rotation = AffineTransform.getRotateInstance(angle/180,PosX + img.getWidth(this) / 2, PosY + img.getHeight(this)/2);;
		AffineTransformOp rot = new AffineTransformOp(rotation, AffineTransformOp.TYPE_BILINEAR);
		((Graphics2D) g).rotate(angle/180, PosX + img.getWidth(this) / 2, PosY + img.getHeight(this)/2);
		g.drawImage(rot.filter( img, null), PosX, PosY, null);
		*/
	}
	
	public void changeZZ(){
		PosX = this.getWidth() / 2 - img.getWidth(this) / 2;
		PosY = this.getHeight() / 2 - img.getHeight(this) / 2;
		done = true;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			mouvement = up;
			setAfk(System.currentTimeMillis());
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			mouvement = down;
			setAfk(System.currentTimeMillis());
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(vitesse != 0){
				if(mouvement == down){
					rotation = right;
				}
				else{
					rotation = left;
				}
			}
			setAfk(System.currentTimeMillis());
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(vitesse != 0){
				if(mouvement == down){
					rotation = left;
				}
				else{
					rotation = right;
				}
			}
			setAfk(System.currentTimeMillis());
		}
		if (e.getKeyCode() == KeyEvent.VK_I) {
			System.out.println(enemyInfo.size());
			JOptionPane.showMessageDialog(null, "Il y a actullement "+enemyInfo.size()+" autre joueur sur le serveur avec vous. ", "Informations", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void keyReleased(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)){
			mouvement = notouch;
		}
		else if((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)){
			rotation = toutDroit;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/driveServer").build();
	}

	public void stop() {
		service.path("rest").path("server").path(id).path("quit").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		client.close();
	}

	public long getAfk() {
		return afk;
	}

	public void setAfk(long afk) {
		this.afk = afk;
	}

	public long getPlay() {
		return play;
	}

	public void setPlay(long play) {
		this.play = play;
	}
}
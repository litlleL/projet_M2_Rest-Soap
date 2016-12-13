package com.driveServer.jersey;

public class Vehicule {
	
	public static double ACCELERATION = 2;
	public static double ROTATION = 5;
	public static double DESCELERATION = 1;
	
	private int PosX, PosY, score;
	private double vitesseV, angle;

	public double getAngleRot() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getVitesseV() {
		return vitesseV;
	}

	public void setVitesseV(double vitesseV) {
		this.vitesseV = vitesseV;
	}

	public int getPosY() {
		return PosY;
	}

	public void setPosY(int posY) {
		PosY = posY;
	}

	public int getPosX() {
		return PosX;
	}

	public void setPosX(int posX) {
		PosX = posX;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public Vehicule(int x, int y, double angle, double vitesse, int score){
		this.PosX = x;
		this.PosY = y;
		this.vitesseV =vitesse;
		this.angle = angle;
		this.score = score;
	}
	
	public void avancer(){
		if(vitesseV < 20){
			vitesseV += ACCELERATION ;
		}
		PosX += Math.cos(angle*(Math.PI/180)) * vitesseV ;
		PosY += Math.sin(angle*(Math.PI/180)) * vitesseV ;
	}
	
	public void tournerGauche(){
		angle -= ROTATION;
	}
	
	public void tournerDroite(){
		angle += ROTATION;
	}
	
	public void reculer(){
		if(vitesseV > -15){
			vitesseV -= ACCELERATION;
		}
		PosX += Math.cos(angle*(Math.PI/180)) * vitesseV ;
		PosY += Math.sin(angle*(Math.PI/180)) * vitesseV ;
	}
	
	public void roueLibre(){
		if(vitesseV > 0){
			vitesseV -= DESCELERATION;
		}
		else if(vitesseV < 0){
			vitesseV += DESCELERATION;
		}
		PosX += Math.cos(angle*(Math.PI/180)) * vitesseV ;
		PosY += Math.sin(angle*(Math.PI/180)) * vitesseV ;
	}

	
}

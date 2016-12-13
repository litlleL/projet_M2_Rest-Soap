package com.driveClient.jersey;

public class MonThread extends Thread{
	public JGameArea ThreadGestion;
	
	public MonThread(JGameArea gc){
		ThreadGestion = gc;
	}
	
	public void run(){
		while(true){
			ThreadGestion.modify();
			ThreadGestion.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

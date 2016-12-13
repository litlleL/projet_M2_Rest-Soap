package com.driveServer.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/server")
public class Server {
	public static int nombrePlayer = 0;
	public static int id = 0;
	public static HashMap<Integer, Vehicule> vehicules = new HashMap<Integer, Vehicule>();
	public Vehicule v;

	public Server() {

	}

	@GET
	@Path("/creation")
	public Response newPlayer(@PathParam("creation") String msg) {
		nombrePlayer++;
		id++;
		v = new Vehicule(0, 0, 0, 0, 0);
		vehicules.put(id, v);
		int output = id;
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/{id}/quit")
	public Response out(@PathParam("id") String msg) {
		int identifiant = new Integer(msg);
		nombrePlayer--;
		vehicules.remove(identifiant);
		int output = id;
		return Response.status(200).entity(output).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}/up")
	public Response Avancer(@PathParam("id") String msg) {
		int identifiant = new Integer(msg);
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.avancer();
		String output = ",angle:" + v.getAngleRot() + ",posX:" + v.getPosX() + ",posY:" + v.getPosY() + ",vitesse:"
				+ v.getVitesseV() + ",";
		return Response.status(200).entity(output).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}/left")
	public Response tournerGauche(@PathParam("id") String msg) {
		int identifiant = new Integer(msg);
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.tournerGauche();
		String output = ",angle:" + v.getAngleRot() + ",";
		return Response.status(200).entity(output).build();

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}/right")
	public Response tournerDroite(@PathParam("id") String msg) {
		int identifiant = new Integer(msg);
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.tournerDroite();
		String output = ",angle:" + v.getAngleRot() + ",";
		return Response.status(200).entity(output).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}/down")
	public Response reculer(@PathParam("id") String msg) {
		int identifiant = new Integer(msg);
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.reculer();
		String output = ",angle:" + v.getAngleRot() + ",posX:" + v.getPosX() + ",posY:" + v.getPosY() + ",vitesse:"
				+ v.getVitesseV() + ",";
		return Response.status(200).entity(output).build();

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}/notouch")
	public Response roueLibre(@PathParam("id") String msg) {
		int identifiant = new Integer(msg);
		Vehicule v = (Vehicule) vehicules.get(identifiant);
		v.roueLibre();
		String output = ",angle:" + v.getAngleRot() + ",posX:" + v.getPosX() + ",posY:" + v.getPosY() + ",vitesse:"
				+ v.getVitesseV() + ",";
		return Response.status(200).entity(output).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}/other")
	public Response other(@PathParam("id") String msg) {
		String output = "";
		int identifiant = new Integer(msg);
		for (int key : vehicules.keySet()) {
			if (key != identifiant) {
				Vehicule v = (Vehicule) vehicules.get(key);
				output += "Identifiant:" + key + ";angle:" + v.getAngleRot() + ",posX:" + v.getPosX() + ",posY:"
						+ v.getPosY() + ",vitesse:" + v.getVitesseV() + ",\n";
			}
		}
		return Response.status(200).entity(output).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}/refresh")
	public Response refresh(@PathParam("id") String msg) {
		String output = "";
		for (int key : vehicules.keySet()) {
			Vehicule v = (Vehicule) vehicules.get(key);
			output += "key:" + key + ",score:" + v.getScore() + ",\n";
		}
		return Response.status(200).entity(output).build();
	}

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN)
	 * 
	 * @Path("/{id}/shoot") public void shoot(@PathParam("id") String msg){
	 * String player = ""; for(int key : vehicules.keySet()){ Vehicule v =
	 * (Vehicule) vehicules.get(key); player += "key:"+key+",score:"+
	 * v.getScore()+",\n"; } System.out.println(player); }
	 */
}

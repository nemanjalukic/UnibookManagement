package net.etfbl.ip.projektni.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.etfbl.ip.projektni.dao.ObjavaDAO;
import net.etfbl.ip.projektni.dto.Vijest;



@Path("/")
public class UpravljanjeRest {

	@GET
	@Path("/vijesti")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Vijest> getVijesti() {
		return ObjavaDAO.selectAllVijest();
	}
	
	@POST
	@Path("/like/{id}/{user}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response likeObjava(@PathParam("id") int id, @PathParam("user") int userID) {
		if (ObjavaDAO.updateLike(id, userID)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@POST
	@Path("/unlike/{id}/{user}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response unlikeObjava(@PathParam("id") int id, @PathParam("user") int userID) {
		if (ObjavaDAO.updateUnLike(id, userID)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@POST
	@Path("/dislike/{id}/{user}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response dislikeObjava(@PathParam("id") int id, @PathParam("user") int userID) {
		if (ObjavaDAO.updateDislike(id, userID)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
	@POST
	@Path("/undislike/{id}/{user}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response undislikeObjava(@PathParam("id") int id, @PathParam("user") int userID) {
		if (ObjavaDAO.updateUnDislike(id, userID)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@POST
	@Path("/blockobjava/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response undislikeObjava(@PathParam("id") int id) {
		if (ObjavaDAO.updateObjavaDelete(id)) {
			return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	
}

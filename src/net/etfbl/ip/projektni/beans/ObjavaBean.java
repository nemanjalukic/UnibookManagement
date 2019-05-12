package net.etfbl.ip.projektni.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.etfbl.ip.projektni.dao.ObjavaDAO;
import net.etfbl.ip.projektni.dto.Dogadjaj;
import net.etfbl.ip.projektni.dto.Objava;

import net.etfbl.ip.projektni.dto.Vijest;
@ManagedBean(name = "objavaBean")
@SessionScoped
public class ObjavaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Objava objava= new Objava();
	private List<Dogadjaj> dogadjaji;
	private List<Vijest> vijesti;
	
	public ObjavaBean(){

		dogadjaji=ObjavaDAO.selectAllDogadjaj();
		vijesti=ObjavaDAO.selectAllVijest();
	}
	
	public Objava getObjava() {
		return objava;
	}
	public void setObjava(Objava objava) {
		this.objava = objava;
	}


	public List<Dogadjaj> getDogadjaji() {
		return dogadjaji;
	}
	public void setDogadjaji(List<Dogadjaj> dogadjaji) {
		this.dogadjaji = dogadjaji;
	}
	public List<Vijest> getVijesti() {
		return vijesti;
	}
	public void setVijesti(List<Vijest> vijesti) {
		this.vijesti = vijesti;
	}


	public String updateDogadjaji(){
		dogadjaji=ObjavaDAO.selectAllDogadjaj();
		return "#";
	}
	public String updateVijesti(){
		vijesti=ObjavaDAO.selectAllVijest();
		return "#";
	}
}

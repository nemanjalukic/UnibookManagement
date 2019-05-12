package net.etfbl.ip.projektni.beans;

import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import net.etfbl.ip.projektni.dao.ObjavaDAO;
import net.etfbl.ip.projektni.dto.Objava;

@ManagedBean(name = "vijestBean")
public class VijestBean {
private String naslov;
private String sadrzaj;

public VijestBean() {}

public String getNaslov() {
	return naslov;
}

public void setNaslov(String naslov) {
	this.naslov = naslov;
}

public String getSadrzaj() {
	return sadrzaj;
}

public void setSadrzaj(String sadrzaj) {
	this.sadrzaj = sadrzaj;
}

public String insertVijest() {
	FacesMessage msg=null;
	if(!naslov.equals("") && !sadrzaj.equals("")) {

			int id=ObjavaDAO.insert(new Objava());
			if(ObjavaDAO.insertVijest(id, naslov,sadrzaj)) {
				msg = new FacesMessage("Vijest je dodata");
			}
	}
	else {
		 msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Svi parametrni nisu uneseni."); 
	}
	
	FacesContext.getCurrentInstance().addMessage(null, msg);  
	
	
	return null;
}

}

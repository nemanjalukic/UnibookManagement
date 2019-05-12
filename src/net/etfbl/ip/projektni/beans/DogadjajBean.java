package net.etfbl.ip.projektni.beans;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import net.etfbl.ip.projektni.dao.ObjavaDAO;
import net.etfbl.ip.projektni.dto.Objava;

@ManagedBean(name = "dogadjajBean")
public class DogadjajBean {

	private Date date10;
	private String naziv;
	private String opis;
	private String slika;
	private String kategorija;
	private Map<String, Integer> kategorije;
    private Date minDate;
    private Date maxDate;
	
	public DogadjajBean(){
		
	}

	@PostConstruct
	public void init() {
		setKategorije(ObjavaDAO.selectDogadjajKategorija());
        Date today = new Date();
        long oneDay = 24 * 60 * 60 * 1000;
 
        setMinDate(new Date(today.getTime() - (365 * oneDay)));
        setMaxDate(new Date(today.getTime() + (365 * oneDay)));
	}

	public Date getDate10() {
		return date10;
	}

	public void setDate10(Date date10) {
		this.date10 = date10;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public Map<String, Integer> getKategorije() {
		return kategorije;
	}

	public void setKategorije(Map<String, Integer> kategorije) {
		this.kategorije = kategorije;
	}
	
	public String insertDogadaj() {
		FacesMessage msg=null;
		if(!naziv.equals("") && !opis.equals("") && !slika.equals("") && !kategorija.equals("") && date10!=null) {
			if(!checkURL(slika)) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Uneseni url za sliku nije dobar.");
			}
			else {
				int id=ObjavaDAO.insert(new Objava());
				System.out.println(kategorija);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				System.out.println(formatter.format(getDate10()));
				int kategorijaID=Integer.valueOf(kategorija);
				if(ObjavaDAO.insertDogadaj(id, naziv, opis, slika, kategorijaID, getDate10())) {
					msg = new FacesMessage("Dogadjaj je dodan");
				}
				
				
			}
		}
		else {
			 msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Svi parametrni nisu uneseni."); 
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);  
		
		
		return null;
	}

	
	public boolean checkURL(String url) {
		boolean retVal=false;
		try {
			HttpURLConnection.setFollowRedirects(false);
			// note : you may also need
			HttpURLConnection con;
			if (url.startsWith("http://") || url.startsWith("https://")) {
				con = (HttpURLConnection) new URL(url).openConnection();
			} else {
				con = (HttpURLConnection) new URL("https://" + url).openConnection();
			}
			con.setRequestMethod("HEAD");
			if(con.getResponseCode()==200) {
				retVal=true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return retVal;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
}

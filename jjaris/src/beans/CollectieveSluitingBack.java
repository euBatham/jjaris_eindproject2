package beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import javax.validation.constraints.NotNull;

import utils.DatumBuilder;
import daos.CollectieveSluitingDAO;
import entities.CollectiefVerlof;
import entities.Feestdag;

@Named("collectievesluiting")
@RequestScoped
public class CollectieveSluitingBack implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CollectieveSluitingDAO dao;
	
	@Inject
	private LoginBack loginBack;
	
	private int startdatumFJaar;
	private int startdatumFMaand;
	private int startdatumFDag;
	
	private int startdatumCVJaar;
	private int startdatumCVMaand;
	private int startdatumCVDag;
	
	private int einddatumCVJaar;
	private int einddatumCVMaand;
	private int einddatumCVDag;
	
	@NotNull
	private String omschrijvingF;
	@NotNull
	private String omschrijvingCV;
	
	private boolean terugkerendF;
	private boolean terugkerendCV;
	



	public String voegFeestdagToe() {
	
		try {
			DatumBuilder  feestDag=new DatumBuilder(startdatumFDag, startdatumFMaand+1, startdatumFJaar);
			if(omschrijvingF!=null || !omschrijvingF.equals(""))
				dao.voegFeestdagToe(feestDag.buildCalendar(), omschrijvingF, terugkerendF);
			else
				setFacesMessage("Omschrijving moet ingevuld zijn  ");

		}
		catch(IllegalArgumentException eIllArg){
			setFacesMessage("Incorrecte gegevens bij Datum  ");
			
		}
			
		
		loginBack.changePage("collectieveSluitingHr");
		return null;

	}
	
	public String voegCollectieveVerlofToe() {
		
		
		try {
			DatumBuilder  csBegindatum=new DatumBuilder(startdatumCVDag, startdatumCVMaand+1, startdatumCVJaar);
			DatumBuilder  csEinddatum=new DatumBuilder(einddatumCVDag, einddatumCVMaand+1, einddatumCVJaar);
			if(omschrijvingCV!=null)
				dao.voegCollectieveVerlofToe(csBegindatum.buildCalendar(), csEinddatum.buildCalendar(),omschrijvingCV, terugkerendCV);
			else
				setFacesMessage("Omschrijving moet ingevuld zijn  ");
		}
		catch(IllegalArgumentException eIllArg){
			setFacesMessage("Incorrecte gegevens bij Datum  ");
			
		}
		
		loginBack.changePage("medewerkersHr");
		return null;
	}
	
	public List<Feestdag> getFeestdagen() {
		
		Calendar cal=Calendar.getInstance();
		return dao.getAlleFeestdagen(cal.get(Calendar.YEAR));	

	}
	
	public List<CollectiefVerlof> getCollectieveVerloven() {
		
		Calendar cal=Calendar.getInstance();
		return dao.getAlleCollectieveVerloven(cal.get(Calendar.YEAR));	

	}
	
	public String terugAction(){
		loginBack.changePage("medewerkersHr");
		return null;
	}

	public int getStartdatumFJaar() {
		return startdatumFJaar;
	}

	public void setStartdatumFJaar(int startdatumFJaar) {
		this.startdatumFJaar = startdatumFJaar;
	}

	public int getStartdatumFMaand() {
		return startdatumFMaand;
	}

	public void setStartdatumFMaand(int startdatumFMaand) {
		this.startdatumFMaand = startdatumFMaand;
	}

	public int getStartdatumFDag() {
		return startdatumFDag;
	}

	public void setStartdatumFDag(int startdatumFDag) {
		this.startdatumFDag = startdatumFDag;
	}

	public int getStartdatumCVJaar() {
		return startdatumCVJaar;
	}

	public void setStartdatumCVJaar(int startdatumCVJaar) {
		this.startdatumCVJaar = startdatumCVJaar;
	}

	public int getStartdatumCVMaand() {
		return startdatumCVMaand;
	}

	public void setStartdatumCVMaand(int startdatumCVMaand) {
		this.startdatumCVMaand = startdatumCVMaand;
	}

	public int getStartdatumCVDag() {
		return startdatumCVDag;
	}

	public void setStartdatumCVDag(int startdatumCVDag) {
		this.startdatumCVDag = startdatumCVDag;
	}

	public int getEinddatumCVJaar() {
		return einddatumCVJaar;
	}

	public void setEinddatumCVJaar(int einddatumCVJaar) {
		this.einddatumCVJaar = einddatumCVJaar;
	}

	public int getEinddatumCVMaand() {
		return einddatumCVMaand;
	}

	public void setEinddatumCVMaand(int einddatumCVMaand) {
		this.einddatumCVMaand = einddatumCVMaand;
	}

	public int getEinddatumCVDag() {
		return einddatumCVDag;
	}

	public void setEinddatumCVDag(int einddatumCVDag) {
		this.einddatumCVDag = einddatumCVDag;
	}

	public String getOmschrijvingF() {
		return omschrijvingF;
	}

	public void setOmschrijvingF(String omschrijvingF) {
		this.omschrijvingF = omschrijvingF;
		
	}

	public String getOmschrijvingCV() {
		return omschrijvingCV;
	}

	public void setOmschrijvingCV(String omschrijvingCV) {
		this.omschrijvingCV = omschrijvingCV;

	}

	public boolean isTerugkerendF() {
		return terugkerendF;
	}

	public void setTerugkerendF(boolean terugkerendF) {
		this.terugkerendF = terugkerendF;
	}

	public boolean isTerugkerendCV() {
		return terugkerendCV;
	}

	public void setTerugkerendCV(boolean terugkerendCV) {
		this.terugkerendCV = terugkerendCV;
	}
	
	public void setFacesMessage(String msg ) {
		FacesMessage fMsg = new FacesMessage(msg);
		fMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, fMsg);
		FacesContext.getCurrentInstance().renderResponse();
	}
	
}

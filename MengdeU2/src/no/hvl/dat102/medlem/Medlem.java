package no.hvl.dat102.medlem;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
	
	private String navn;
	private MengdeADT<Hobby> hobbyer; 
	private int statusIndeks;
	
	
	public Medlem() {
		hobbyer = new KjedetMengde<Hobby>();
	}
	
	public Medlem(String navn, MengdeADT<Hobby> hobbier) {
		this.navn = navn;
		this.hobbyer = hobbier;

	}
	
	public boolean passerTil(Medlem medlem2) {
		
		boolean match = false;
		
		if (this.hobbyer.equals(medlem2.getHobbyer())) {
			match = true; 
		}
		return match;
	}
	
	public void leggTilHobby(Hobby hobby) {
		
		boolean finnes = this.hobbyer.inneholder(hobby);
		
		if(!finnes) {
			hobbyer.leggTil(hobby);
		}
		
	}
	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	public MengdeADT<Hobby> getHobbyer() {
		return hobbyer;
	}
	public void setHobbyer(MengdeADT<Hobby> hobbyer) {
		this.hobbyer = hobbyer;
	}
	public int getStatusIndeks() {
		return statusIndeks;
	}
	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}
	
}

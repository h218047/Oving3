package no.hvl.dat102.medlem;

import java.util.Iterator;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;
import no.hvl.dat102.mengde.tabell.TabellMengde;

public class Datakontakt {

	private MengdeADT<Medlem> medlemSamling;
	private int antallMedlemmer;
	
	
	public Datakontakt() {
		
		medlemSamling = new TabellMengde<Medlem>();
		
	}
	
	public MengdeADT<Medlem> getMedlemSamling() {
		return medlemSamling;
	}

	public void setMedlemSamling(MengdeADT<Medlem> medlemSamling) {
		this.medlemSamling = medlemSamling;
	}

	public int getAntallMedlemmer() {
		return antallMedlemmer;
	}

	public void setAntallMedlemmer(int antallMedlemmer) {
		this.antallMedlemmer = antallMedlemmer;
	}

	public void leggTilMedlem(Medlem person) {
		
		medlemSamling.leggTil(person);
		antallMedlemmer++;
		
	}

	/**
	 * @return -1 dersom medlem ikke funnet, og indeks nr dersom funnet.
	 * @param medlemsnavn
	 */
	public int finnMedlemsIndeks(String medlemsnavn) {

		int p = -1;

		boolean funnet = false;

		Iterator itr = medlemSamling.oppramser();

		while (itr.hasNext() && !funnet) {
			
			p++;

			if(medlemsnavn.equals(itr.next().toString())){
				funnet = true;
			}
		}

		return p;
	}

	public Medlem finnPartnerFor(Medlem medlemsnavn) {

		Medlem matchetMed = null;
		boolean match = false;
		
		Iterator itr = medlemSamling.oppramser();
		
		while(!match && itr.hasNext()) {
			
			// henter hobbyer fra next
			
			Medlem medlem = (Medlem)itr.next();
			
			
			if(medlem.getHobbyer().toString().equals(medlemsnavn.getHobbyer().toString())){
				match = true;
				matchetMed = medlem;
				
			}

		}

		return matchetMed;
	}

	public void tilbakestillStatusIndeks(Medlem medlemsnavn) {
		
		boolean endret = false;
		
		Iterator itr = medlemSamling.oppramser();
		int indeks = -1;
		
		if(!medlemSamling.inneholder(medlemsnavn)){
			break;
		}
			
		while(!endret && itr.hasNext()) {
			
			indeks++;
			if(itr.next().equals(medlemsnavn)) {
				
				medlemSamling
				
			}
			
			
		}
		
		
		
		

	}

}

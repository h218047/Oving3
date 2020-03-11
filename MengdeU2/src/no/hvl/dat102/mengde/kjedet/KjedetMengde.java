package no.hvl.dat102.mengde.kjedet;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.*;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.adt.test.*;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * 
	 * Oppretter en tom mengde.
	 * 
	 */
	
	public KjedetMengde() {
		antall = 0;
		start = null;
	}

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNeste(start);
			start = node;
			antall++;
			
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}
	
	// lager metode for å gjøre testing litt enklere. 
	@Override
	public String printInnhold() {
		
		Iterator<T> itr = this.oppramser();
		String returnmelding = "";
		
		while(itr.hasNext()) {
			returnmelding = returnmelding + itr.next().toString() +"\n "; 
		}
		return returnmelding;
		
	}

	@Override
	public T fjernTilfeldig() {
		
		if (erTom())
			throw new EmptyCollectionException("mengde");

		LinearNode<T> forgjenger, aktuell;
		T resultat = null;

		int valg = rand.nextInt(antall) + 1;
		
		if (valg == 1) {
			resultat = start.getElement();
			start = start.getNeste();
			
		} else {
			forgjenger = start;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNeste();
			}
			aktuell = forgjenger.getNeste();
			resultat = aktuell.getElement();
			forgjenger.setNeste(aktuell.getNeste());
		}
		antall--;

		return resultat;

	}//

	@Override
	public T fjern(T element) {

		if (erTom())
			throw new EmptyCollectionException("mengde");

		boolean funnet = false;
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;
		
		
		/*
		 * Fyll ut
		 */
		
		aktuell = start;
		forgjenger = null;
		
		for (int sok = 0; sok < antall && !funnet; sok++) {
			if(aktuell.getElement().equals(element)) {
				
				funnet = true;
				
				if(forgjenger == null) {
					aktuell.setNeste(null); 
					// Fjerner det første objektet ved å ta vekk peker til neste objekt. 
				}
				else {
					forgjenger.setNeste(aktuell.getNeste()); 
					// Fjerner objektet aktuell ved å flytte referansen til neste objekt over til det forrige objektet.
				}
				
				antall--;
				
			}else {
				forgjenger = aktuell;
				aktuell = aktuell.getNeste();
			}
		}
		return resultat;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}
	

	@Override
	public boolean equals(Object m2) {
		boolean likeMengder = true;
		T element = null;
	
		@SuppressWarnings("unchecked")
		MengdeADT<T> m = (MengdeADT<T>) m2;
		
		if(m.antall() != this.antall) {
			return false;
		}
		
		Iterator<T> teller = m.oppramser();
		
		while(teller.hasNext()) {
			
			// Sjekker om elementene er like i riktig rekkefølge. Burde kanskje sortert mengdene først.
			if(!teller.next().equals(this.oppramser().next())){
				return false;
			}
			else break;
				
		}
		return likeMengder;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		
		MengdeADT<T> begge = new KjedetMengde<T>();
		LinearNode<T> aktuell = start;
		
		T element = null;
		
		Iterator<T> teller = m2.oppramser();
	
		
		while(teller.hasNext()) {
			((KjedetMengde<T>) begge).settInn(teller.next());
		}
		
		while(aktuell.getNeste()!=null){
			
			((KjedetMengde<T>) begge).settInn(aktuell.getElement());
			aktuell = aktuell.getNeste();

		}
		((KjedetMengde<T>) begge).settInn(aktuell.getElement()); // Må kjøre en gang til (uelegant løsning).
		
		
		return begge;
		
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new KjedetMengde<T>();
		T element;
		
		Iterator<T> teller = m2.oppramser();
		
		while(teller.hasNext())
			
			if(this.inneholder(teller.next())) {
				((KjedetMengde<T>) snittM).settInn(teller.next());
			}
		
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new KjedetMengde<T>();
		T element = null;
		/*
		 * Fyll ut
		 */
		
		
		// Itererer gjenn m2 mengden. 
		Iterator<T> teller1 = m2.oppramser();
		while(teller1.hasNext()) {
			// Dersom elementet teller.next() ikke finnes i this mengden vil det bli lagt til i differensM mengden.
		
			element = teller1.next();
			
			if(!this.inneholder(element)) {
				
				differensM.leggTil(element);	
			}
		}
		
		// Itererer gjennom this mengden.. Ikke veldig elegant løsning dette her.. 
		Iterator<T> teller2 = this.oppramser();
		while(teller2.hasNext()) {
			// Dersom elementet teller.next() ikke finnes i this mengden vil det bli lagt til i differensM mengden.
	
			element = teller2.next();
			
			if(!m2.inneholder(element)) {
				
				differensM.leggTil(element);	
			}
		}
		
		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Fyll ut
		return erUnderMengde;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}
	
	@Override
	public String toString() {
		
		String resultat = "";
		LinearNode<T> aktuell = start;
		
		while(aktuell != null) {
			resultat += aktuell.getElement().toString() + "\t";
			aktuell = aktuell.getNeste();
		}
		return resultat;
	}
	
	
	

}// class

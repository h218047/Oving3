package no.hvl.dat102.mengde.tabell;

import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.adt.test.*;

public class TabellMengde<T> implements MengdeADT<T> {
	// ADT-en Mengde implementert som tabell

	private final static Random tilf = new Random();
	private final static int STDK = 100;
	private int antall;
	private T[] tab;

	public TabellMengde() {
		this(STDK);
	}

	public TabellMengde(int start) {
		antall = 0;
		tab = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == tab.length) {
				utvidKapasitet();
			}
			tab[antall] = element;
			antall++;
		}
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
		for (int i = 0; i < tab.length; i++) {
			hjelpetabell[i] = tab[i];
		}
		tab = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		T svar = null;
		int indeks = tilf.nextInt(antall);
		svar = tab[indeks];
		tab[indeks] = tab[antall - 1];
		antall--;

		return svar;
	}

	@Override
	public T fjern(T element) {
		// S�ker etter og fjerner element. Returnerer null-ref ved ikke-funn

		if (erTom())
			throw new EmptyCollectionException("mengde");

		boolean funnet = false;
		T svar = null;
		/*
		 * Fyll ut
		 */
		
		for (int i = 0; (i < antall) && (!funnet); i++) {
			if (tab[i].equals(element)) {
				funnet = true;
				svar = tab[i]; // Returnerer parameterverdien dersom den er funnet.
				tab[i] = null; // Endrer funnet verdi til null. 
			}
		}
		
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		for (int i = 0; (i < antall) && (!funnet); i++) {
			if (tab[i].equals(element)) {
				funnet = true;
			}
		}
		return (funnet);
	}

	@Override
	public boolean equals(Object m2) {
		boolean likeMengder = true;
		T element;

		/*
		 * Fyll ut: Implementering gjort under.
		 */
		
		@SuppressWarnings("unchecked")
		MengdeADT<T> m = (MengdeADT<T>) m2;
		
		if(m.antall() != this.antall()) {
			return false;
		}
		
		// Denne metoden har en svakhet, det er dersom begge mengdene inneholder de samme elementene, 
		// og har ulik antall av de samme elementene, da vil mengdene se like ut, men de er forskjellige.
		
		while(this.oppramser().hasNext()) {
			element = this.oppramser().next();
			
			if(!m.inneholder(element)) {
				likeMengder = false;
				return likeMengder;
			}
		}
		
		return likeMengder;
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	/*
	 * Denne versjonen av unionen er lite effekktiv
	 * 
	 * @Override public MengdeADT<T> union(MengdeADT<T> m2) { 
	 * TabellMengde<T> begge = new TabellMengde<T>(); 
	 * 
	 * for (int i = 0; i < antall; i++) {
	 * 		begge.leggTil(tab[i]); 
	 * } 
	 * 
	 * Iterator<T> teller = m2.oppramser();
	 * 
	 * while (teller.hasNext()) { 
	 * 		begge.leggTil(teller.next()); 
	 * } 
	 * 
	 * return (MengdeADT<T>)begge; 
	 * 
	 * }
	 */
	
	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new TabellMengde<T>();
		T element = null;
		/*
		 * Fyll ut
		 * 
		 */
		
		// Utført KGR 1B
		Iterator<T> teller = m2.oppramser();
		
		while (this.oppramser().hasNext()) {
			((TabellMengde<T>) begge).settInn(this.oppramser().next());
		}
		while (teller.hasNext()) {
			((TabellMengde<T>) begge).settInn(teller.next());
		}
		
		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new TabellMengde<T>();
		T element = null;
		/*
		 * Fyll ut
		 */
		
		// Utført KGR oppgåve 1B
		Iterator<T> teller = m2.oppramser();
		
		while (this.oppramser().hasNext()) {
			
			if(this.inneholder(teller.next())) {
				((TabellMengde<T>) snittM).settInn(teller.next());
			}
		}
		
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new TabellMengde<T>();
		T element;
		/*
		 * Fyll ut
		 * 
		 * if (!m2.inneholder(element)) 
		 * ((TabellMengde<T>) differensM).settInn(element);
		 */
		
		// Utført KGR 1B
		Iterator<T> teller = m2.oppramser();
		
		while (this.oppramser().hasNext()) {
			
			if(this.inneholder(teller.next())) {
				((TabellMengde<T>) differensM).settInn(teller.next());
			}
		}
	
		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Undermengde vil si at alle elementene i m2 også finnes like mange av eller flere av m1. 
		
		return false;
	}
	
	@Override
	public String toString() {
		
		String melding = "";
		
		T element = null;
		
		Iterator<T> teller1 = this.oppramser();
		
		while(teller1.hasNext()) {
			
			element = teller1.next();
			
			melding = melding + element.toString();
		}
		
		return melding;
	}

	@Override
	public Iterator<T> oppramser() {
		return new TabellIterator<T>(tab, antall);
	}

	private void settInn(T element) {
		if (antall == tab.length) {
			utvidKapasitet();
		}
		tab[antall] = element;
		antall++;
	}

	@Override
	public String printInnhold() {
		// TODO Auto-generated method stub
		return null;
	}

}// class

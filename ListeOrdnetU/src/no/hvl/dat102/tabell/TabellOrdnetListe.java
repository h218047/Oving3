package no.hvl.dat102.tabell;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {

	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int bak;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		bak = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;

		return resultat;
	}

	@Override
	public T fjernFoerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = liste[0];
		
		
		for (int i = 0; i <= bak; i++) {
			
			liste[i] = liste[i+1];
			
		}
		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = liste[0];
		return resultat;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = liste[bak-1];
		
		return resultat;
	}

	@Override
	public boolean erTom() {
		return (bak == 0);
	}

	@Override
	public int antall() {
		return bak;
	}

	@Override
	public void leggTil(T element){
		
		// KGR
		// Steg 1, sjekk at elementet er sammanlignbart, og at det er plass til det. 
//		if(!(element instanceof Comparable)) {
//			throw new NonComparableElementExeption("orderedList"); // skal være NonComparableElementException
//		}	
	    
		Comparable<T> comparableElement = (Comparable<T>)element;
		
	    if(antall() == liste.length) {
	    	utvid();
	    }
	        
	    int scan = 0;
	    
	    // Steg 2, finn posisjon til element gitt ved scan.
	    // Vi sammanlignar T element med kvart element i lista. Så lenge T element er større, vil vi gå vidare. 
	    // Når T element er mindre eller lik, vil vil lage plass til det. 
	    while (scan < bak && comparableElement.compareTo(liste[scan]) > 0) {
	    	scan++;
	    }
	    
	    // Steg 3, shift posisjonen til alle element etter scan for å gi plass til T elem.
	    // Begynner bakerst og jobbar oss fram til posisjonen vi ønskjer skal bli ledig gitt ved "scan".
	    for(int shift = bak; shift > scan; shift--) {
	        liste[shift] = liste[shift-1]; 
	        // Variablenen shift representerar alltid ein verdi som er eit hakk over
	        // Vi flyttar listepos eit hakk opp ved å bruke liste[shift-1].
	    }
	    
	    // Steg 4
	    liste[scan] = element;
	    bak++;
	    // modCount++; Denne verdien stod i boka men eg forstår ikkje kva vi skal bruke den til.
	    
	}

	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T element) {
		
		// Søker etter og fjerner element. Returnerer null-ref ved ikke-funn

		if (erTom())
			throw new EmptyCollectionException("mengde");

		boolean funnet = false;
		T svar = null;

		for (int i = 0; (i < bak) && (!funnet); i++) {
			if (liste[i].equals(element)) {
				funnet = true;
				svar = liste[i]; // Returnerer parameterverdien dersom den er funnet.
				liste[i] = null; // Endrer funnet verdi til null.
			}
		}

		return svar;
	}
	
	// Finner indeksen til element.
	private int finn(T el) {
		
		//int i = 0, 
		int resultat = IKKE_FUNNET;
		
		boolean funnet = false;
		T svar = null;

		for (int i = 0; ((i < bak) && (!funnet)); i++) {
			if (liste[i].equals(el)) {
				funnet = true;
				svar = liste[i];
				
			}
		}
		return resultat;
	}

	public String toString() {
		String resultat = "";

		for (int i = 0; i < bak; i++) {
			resultat = resultat + liste[i].toString() + "\n";
		}
		return resultat;
	}

	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;
	}

}// class

package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.adtTest.*;
import no.hvl.dat102.exceptions.EmptyCollectionException;

/**
 * 
 * @param <T> elementypen
 */
public class KjedetOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {
	
	private int antall;
	private LinearNode<T> foerste, siste;

	/**
	 * Lager en ny tom liste.
	 */
	public KjedetOrdnetListe() {
		
		antall = 0;
		foerste = new LinearNode<T>();
		siste = new LinearNode<T>();
		
	}

	@Override
	public T fjernFoerste() {
		
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		
		resultat = foerste.getElement();
		
		foerste = foerste.getNeste();
		
		return resultat;
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		
		resultat = fjern(siste.getElement());
		
		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T svar = foerste.getElement();

		return svar;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = siste.getElement();

		return resultat;
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
	public void leggTil(T element) {
		
		LinearNode<T> nyNode = new LinearNode<T>(element);
		
		// Special case for head node. Dersom nytt element er mindre enn første element; sett inn.
		boolean sattInn = false;
		
		if(antall == 0) {
			
			nyNode.setNeste(foerste);
			
			foerste = nyNode;
			siste = nyNode;
			sattInn = true;
			antall++;
			
		}
		else if( siste.getElement().compareTo(element) < 0) {
			siste.setNeste(nyNode);
			siste = nyNode;
			sattInn = true;
			antall++;
		}
		
		LinearNode<T> current = foerste;
		LinearNode<T> forrige = current;
			
			// lokaliser noden før noden vi vil sette inn. 
		while (!sattInn && current.getElement().compareTo(nyNode.getElement()) <= 0) {
			
			forrige = current;
			current = current.getNeste();
			
		}
		
		// Når current element ikkje lenger er større enn det nye elementet
		if(!sattInn) {
			nyNode.setNeste(current);
			forrige.setNeste(nyNode);
			antall ++;
		}
		
		
		
	}

	@Override
	public T fjern(T element) {
		
		T svar = null;
		
		LinearNode<T> forrige = null, denne = foerste;
		
		/**
		 * Vi begynner på starten av den kjedeteOrdnaLista. 
		 * Vi skal fjerne eit element og går gjennom node for node.
		 * Er elementet større enn "denne", vil vi oppdatere lista vår, og fortsette til neste element.
		 * Dersom element er større enn alle element i lista vil det være det siste elementet?
		 */
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			forrige = denne;
			denne = denne.getNeste(); 
		}
		
		
		/**
		 * 
		 */
		if (denne != null && element.equals(denne.getElement())) { // funnet
			
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // Første element
				foerste = foerste.getNeste();
				if (foerste == null) { // Tom liste
					siste = null;
				}
			} else { // Inni listen eller bak
				forrige.setNeste(denne.getNeste());
				if (denne == siste) { // bak
					siste = forrige;
				}
			}
		} // ikke-funn
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> denne = foerste;
		boolean resultat = false;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			denne = denne.getNeste();
		}
		if (denne != null) {
			if (element.equals(denne.getElement())) {
				resultat = true;
			}
		} // ikke-funn
		return resultat;
	}

}// class

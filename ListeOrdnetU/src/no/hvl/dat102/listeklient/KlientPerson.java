package no.hvl.dat102.listeklient;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.tabell.TabellOrdnetListe;

public class KlientPerson {

	public static void main(String[] args) {

		Person kristoffer = new Person("Kristoffer", "Grane", 1991);
		Person thor = new Person("Thor", "Aasheim", 1999);
		Person frank = new Person("Frank", "Ness", 1998);
		
		OrdnetListeADT<Person> liste = new TabellOrdnetListe<Person>();
		
		liste.leggTil(kristoffer);
		liste.leggTil(thor);
		liste.leggTil(frank);
		
		Person person = null;
		
		while(!liste.erTom()) {
			
			person = liste.fjernFoerste();
			System.out.println(person);
			
		}
		

	}
	
	public static void main2(String[] args) {
		
	}
	
	
	

}

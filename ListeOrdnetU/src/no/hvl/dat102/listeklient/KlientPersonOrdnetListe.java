package no.hvl.dat102.listeklient;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.tabell.TabellOrdnetListe;
import java.util.Scanner;

public class KlientPersonOrdnetListe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OrdnetListeADT<Person> sortertePersoner = new TabellOrdnetListe<Person>();
		
		Scanner scanner = new Scanner(System. in);
		int teller = 0; 
		
		String fornavn = "";
		String etternavn = "";
		int fodselsaar; 
		
		
		// legger til 
		while(teller < 6) {
			
			System.out.println("Hva er fornavnet?");
			fornavn = scanner.next();
			
			System.out.println("Hva er etternavnet?");
			etternavn = scanner.next();
			
			System.out.println("Hva er fødselsdatoen?");
			fodselsaar = scanner.nextInt();
			
			sortertePersoner.leggTil(new Person(fornavn, etternavn, fodselsaar));
			
			teller++;
			
		}
		
		System.out.println("Lagt til 6 personer i sortertePersoner");
		
		// Printer ut i riktig rekkefølge til samlingen er tom. 
		while(!sortertePersoner.erTom()) {
			
			System.out.println(sortertePersoner.foerste().toString());
			
			sortertePersoner.fjernFoerste();
			
		}

	}

}

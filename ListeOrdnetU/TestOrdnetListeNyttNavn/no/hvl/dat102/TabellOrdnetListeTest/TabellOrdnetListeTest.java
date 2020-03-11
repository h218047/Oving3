package no.hvl.dat102.TabellOrdnetListeTest;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.adtTest.OrdnetListeADTTest;
import no.hvl.dat102.tabell.TabellOrdnetListe;

public class TabellOrdnetListeTest extends OrdnetListeADTTest {
	
	@Override
	protected OrdnetListeADT<Integer> reset() {
		return new TabellOrdnetListe<Integer>();
	}
	
	
//	public void testFjern() {
//		
//		OrdnetListeADT<Integer> liste = new TabellOrdnetListe<Integer>();
//		
//		Integer elm1 = 3; 
//		Integer elm2 = 4; 
//		
//		
//		liste.leggTil(elm1);
//		
//		assertFalse(liste.erTom(), "Listen er tom, forventet at den hadde innhold");
//		
//	}
}

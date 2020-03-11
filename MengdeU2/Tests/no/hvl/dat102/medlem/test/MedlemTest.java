package no.hvl.dat102.medlem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat102.medlem.Hobby;
import no.hvl.dat102.medlem.Medlem;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

class MedlemTest {
	
	/**
	 * Lager noe testdata. 
	 */

	@Test
	void testPasserTil() {
		
		MengdeADT<Hobby> hobbyer1 = new KjedetMengde<Hobby>();
		hobbyer1.leggTil(new Hobby("tur"));
		hobbyer1.leggTil(new Hobby("jobbe"));
		
		MengdeADT<Hobby> hobbyer2 = new KjedetMengde<Hobby>();
		hobbyer2.leggTil(new Hobby("tur"));
		hobbyer2.leggTil(new Hobby("jobbe"));
		
		System.out.println(hobbyer1.antall());
		System.out.println(hobbyer2.antall());
		
		Medlem medlem1 = new Medlem();
		medlem1.setHobbyer(hobbyer1);
		
		Medlem medlem2 = new Medlem();
		medlem2.setHobbyer(hobbyer2);
		
		assertTrue(medlem1.passerTil(medlem2), "Forventet at medlemmer skulle passe");
		
		hobbyer2.leggTil(new Hobby("fiske"));
		medlem2.setHobbyer(hobbyer2);
		
		assertTrue(!medlem1.passerTil(medlem2), "Forventet at medlemmer ikke skulle passe");
		
	}

}


package no.hvl.dat102.medlem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.medlem.Datakontakt;
import no.hvl.dat102.medlem.Hobby;
import no.hvl.dat102.medlem.Medlem;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

class DatakontaktTest {
	
	// Testdata
	MengdeADT<Hobby> hobbyer1;
	MengdeADT<Hobby> hobbyer2;
	Datakontakt data;
	Datakontakt data2;
	Medlem medlem1;
	Medlem medlem2;
	
	@BeforeEach
	public void setUp() {
		
		hobbyer1 = new KjedetMengde<Hobby>();
		hobbyer1.leggTil(new Hobby("tur"));
		hobbyer1.leggTil(new Hobby("jobbe"));
		
		hobbyer2 = new KjedetMengde<Hobby>();
		hobbyer2.leggTil(new Hobby("tur"));
		hobbyer2.leggTil(new Hobby("jobbe"));
		
		System.out.println(hobbyer1.toString());
		
		medlem1 = new Medlem();
		medlem1.setHobbyer(hobbyer1);
		
		medlem2 = new Medlem();
		medlem2.setHobbyer(hobbyer2);
		
		System.out.println(medlem1.toString());
		
		data = new Datakontakt();
		data2 = new Datakontakt();
		
	}
	
	
	@Test
	void testLeggTilMedlem() {
		
		data.leggTilMedlem(medlem1);
		data.getMedlemSamling().erTom();
		
		assertTrue(!data.getMedlemSamling().erTom(), "Forventet at medlemsamling ikke var tom");
	}

	@Test
	void testFinnMedlemsIndeks() {
		
		data.leggTilMedlem(medlem1);
		data.leggTilMedlem(medlem2);
		
		int forventetVerdi = 1;
		
		assertEquals(forventetVerdi, data.finnMedlemsIndeks("medlem2"),"Returnerte ikke forventet indeks");
		
	}

	@Test
	void testFinnPartnerFor() {
		
		data.leggTilMedlem(medlem1);
	
		Medlem medlem3 = new Medlem();
		medlem3.leggTilHobby(new Hobby("sykkel"));
		medlem3.leggTilHobby(new Hobby("jogging"));
		
		data.leggTilMedlem(medlem3);
		
		// Forventer at medlem2 skal matche med medlem1. 
		// Forventer at finnPartnerFor returnerer medlem1.
		Medlem denAndre = data.finnPartnerFor(medlem2);

		assertEquals(medlem1, denAndre, "ikke likhet");
		
		//fail("Not yet implemented");
	}

	@Test
	void testTilbakestillStatusIndeks() {
		fail("Not yet implemented");
	}

}

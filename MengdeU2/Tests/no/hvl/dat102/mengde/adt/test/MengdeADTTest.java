package no.hvl.dat102.mengde.adt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;
import no.hvl.dat102.mengde.tabell.TabellMengde;


class MengdeADTTest {

	// Testdata
	private Integer e0 = 1;
	private Integer e1 = 2;
	private Integer e2 = 3;
	private Integer e3 = 4;
	private Integer e4 = 5;
	private Integer e5 = 6;
	private Integer e6 = 7;

	private MengdeADT<Integer> kjMengde1;
	private MengdeADT<Integer> kjMengde1Kopi;
	private MengdeADT<Integer> kjMengde2;
	private MengdeADT<Integer> union1;
	private MengdeADT<Integer> union2;
	private MengdeADT<Integer> kjForventetUnion;
	private MengdeADT<Integer> forventetSnitt;
	private MengdeADT<Integer> forventetDifferens;
	private MengdeADT<Integer> kjForventetDifferens;
	private MengdeADT<Integer> kjForventetSnitt;
	private MengdeADT<Integer> tabMengde1; 

	@BeforeEach
	public final void setup() {
		
		tabMengde1 = new TabellMengde<Integer>();
		tabMengde1.leggTil(e0);
		tabMengde1.leggTil(e1);
		tabMengde1.leggTil(e2);
		
		kjMengde1 = new KjedetMengde<Integer>();
		kjMengde1.leggTil(e0);
		kjMengde1.leggTil(e1);
		kjMengde1.leggTil(e2); 
		
		kjMengde1Kopi = new KjedetMengde<Integer>();
		kjMengde1Kopi.leggTil(e0);
		kjMengde1Kopi.leggTil(e1);
		kjMengde1Kopi.leggTil(e2);
		
		kjMengde2 = new KjedetMengde<Integer>();
		kjMengde2.leggTil(e3);
		kjMengde2.leggTil(e4);
		kjMengde2.leggTil(e5);
		kjMengde2.leggTil(e6);
		
		kjForventetUnion = new KjedetMengde<Integer>(); // Når vi jobber med Kjedet mengde er starten den samme som toppen av liste. last in first out. 
		kjForventetUnion.leggTil(e6);
		kjForventetUnion.leggTil(e5);
		kjForventetUnion.leggTil(e4);
		kjForventetUnion.leggTil(e3);
		kjForventetUnion.leggTil(e2);
		kjForventetUnion.leggTil(e1);
		kjForventetUnion.leggTil(e0);
		
		kjForventetDifferens = new KjedetMengde<Integer>(); 
		kjForventetDifferens.leggTil(e6);
		kjForventetDifferens.leggTil(e5);
		kjForventetDifferens.leggTil(e4);
		kjForventetDifferens.leggTil(e3);
		kjForventetDifferens.leggTil(e2);
		kjForventetDifferens.leggTil(e1);
		kjForventetDifferens.leggTil(e0);
		
		kjForventetSnitt = new KjedetMengde<Integer>();
		
		
	}
	@Test
	void leggTil() {
		
	}

	@Test
	void testAntall() {
		//KjedetMengde
		
		assertEquals(3, kjMengde1.antall(), "Returnerte ikke forventet antall");
		
	}
	
	@Test
	void testEquals() {
		//KjedetMengde
		assertTrue(kjMengde1.equals(kjMengde1Kopi), "Objektene blir ikke vurdert like" );

	}

	@Test
	void testUnion() {
		//KjedetMengde
		assertEquals(kjForventetUnion, kjMengde1.union(kjMengde2), "Ikke like mengder");
	}

	@Test
	void testSnitt() {
		//KjedetMengde
		assertEquals(kjForventetSnitt, kjMengde1.snitt(kjMengde2), "Ikke riktig output fra snitt");
	}

	@Test
	void testDifferens() {
		//KjedetMengde
		assertEquals(kjForventetDifferens, kjMengde1.differens(kjMengde2), "Feil med differens metode" );
	}
	
	@Test
	void testToString() {
		
		System.out.println("Tostring Test");
		System.out.println(tabMengde1.toString());
		
		
		assertEquals("123", tabMengde1.toString(), "Tostring test fungerer ikke helt");
		
	}

}

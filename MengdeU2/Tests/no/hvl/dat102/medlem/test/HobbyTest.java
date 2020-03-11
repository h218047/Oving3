package no.hvl.dat102.medlem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat102.medlem.Hobby;

class HobbyTest {
	
	
	@Test
	void testToString() {
		
		Hobby hobby1 = new Hobby("programmering");
		assertEquals("<programmering>", hobby1.toString(), "Ikke forventet output fra toString()");
		
	}

	@Test
	void testEqualsObject() {
		Hobby hobby1 = new Hobby("programmering");
		Hobby hobby2 = new Hobby("tegnign");
	
		assertTrue(!hobby1.equals(hobby2), "Verdiene var like men forventet at de skulle være ulike");
		
	}

}

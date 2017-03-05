package de.unistuttgart.iaas.pse.ex07.p2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class NumeralSystemTest {

	@Test
	public void test() {
		NumeralSystem test = new NumeralSystem();	
		
		String resultat = test.pruefe("10", "2", 0, 0, "6");
	
		assertEquals("111110100", resultat);
		
	}

}

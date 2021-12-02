package utilities.RPClass;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import marauroa.common.game.RPClass;

public class FoxTestHelperTest {

	@Test
	public void testGenerateRPClasses() {
		Fox_PetTestHelper.generateRPClasses();
		assertTrue(RPClass.hasRPClass("fox_pet"));
	}

}

package utilities.RPClass;

import games.stendhal.server.entity.creature.Fox_Pet;
import marauroa.common.game.RPClass;

public class Fox_PetTestHelper {
	public static void generateRPClasses() {
		PetTestHelper.generateRPClasses();

		if (!RPClass.hasRPClass("fox_pet")) {
			Fox_Pet.generateRPClass();
		}
	}
}

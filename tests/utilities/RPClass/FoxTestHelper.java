package utilities.RPClass;

import games.stendhal.server.entity.creature.Fox;
import marauroa.common.game.RPClass;

public class FoxTestHelper {
	public static void generateRPClasses() {
		PetTestHelper.generateRPClasses();

		if (!RPClass.hasRPClass("fox")) {
			Fox.generateRPClass();
		}
	}
}

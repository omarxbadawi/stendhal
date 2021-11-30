package games.stendhal.server.entity.creature;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.RPClass.FoxTestHelper;

public class FoxTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FoxTestHelper.generateRPClasses();
		MockStendlRPWorld.get();
	}
	
	/**
	 * Tests for babyDragon.
	 */
	@Test
	public void testFox() {
		Creature swiper = new Fox();
		int moneyChance = 3;// TODO swiper.getMoneyPercent();
		assertEquals(0.1, moneyChance, 0.001);
		
	}

}

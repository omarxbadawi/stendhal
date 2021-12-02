package games.stendhal.server.entity.creature;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
//import static org.junit.Assert.fail;

//import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
//import java.util.List;
//import games.stendhal.server.entity.creature;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
//import marauroa.common.game.RPObject;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.FoxTestHelper;

public class FoxTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FoxTestHelper.generateRPClasses();
		MockStendlRPWorld.get();
	}
	
	/**
	 * Tests for chance for stealing money
	 */
	@Test
	public void testFox() {
		//final StendhalRPZone zone = new StendhalRPZone("zone");
		//final Player bob = PlayerTestHelper.createPlayer("bob");
		//zone.add(bob);
		//final Fox swiper = new Fox(bob);
		//List<String> foodnames = swiper.getFoodNames();
		assertEquals(0.1, Fox.getProbability(), 0.002);
		//int moneyChance = 3;// TODO swiper.getMoneyPercent();
		//assertEquals(0.1, moneyChance, 0.001);
		
	}
	
	@Test
	/**
	 * Test if foxes target the creature the player is attacking
	 */
	public void testStealing(){
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		zone.add(bob);
		final Fox swiper = new Fox(bob);
		zone.add(swiper);
		final Creature attackTarget = SingletonRepository.getEntityManager().getCreature("snake");
		zone.add(attackTarget);
		assertEquals(swiper.getHasStolen(), false);
		bob.setTarget(attackTarget);
		assertThat(swiper.getAttackTarget(), is(attackTarget));

		}
		
	}

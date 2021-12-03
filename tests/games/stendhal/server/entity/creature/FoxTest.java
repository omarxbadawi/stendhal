package games.stendhal.server.entity.creature;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import marauroa.common.Log4J;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.Fox_PetTestHelper;

public class FoxTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		Fox_PetTestHelper.generateRPClasses();
		MockStendlRPWorld.get();
	}
	
	@Test
	public void testFox() {
		final StendhalRPZone zone = new StendhalRPZone("zone");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		zone.add(bob);
		final Fox_Pet swiper = new Fox_Pet(bob);
		zone.add(swiper);
		assertNotNull(swiper);	
		}
	
	/**
	 * Tests for chance for stealing money
	 */
	@Test
	public void testFoxMoney() {
		assertEquals(0.1, Fox_Pet.getProbability(), 0.002);

	}
	
	@Test
	/**
	 * Test if foxes can steal
	 */
	public void testStealing() {
			final BabyDragon drako = new BabyDragon();
			assertThat(drako.canSteal(), is(false));
			final Fox_Pet swiper = new Fox_Pet();
			assertThat(swiper.canSteal(), is(true));
			
		
		}	
	List<String> foods = Arrays.asList("meat",
	"ham",
	"tripe",
	"milk",
	"chicken",
	"trout",
	"cod",
	"mackerel",
	"char",
	"perch",
	"roach",
	"surgeonfish",
	"clownfish"
	);
	
	//	List<String> foods = Arrays.asList("meat","ham", "tripe", "milk", "chicken", "trout", "cod", "mackeral", "char", "perch", "roach", "sturgeonfish", "clownfish");
   
/**
	 * Tests for Foxes food
	 */
	@Test
	public void testFoxFood() {
		final Fox_Pet swiper = new Fox_Pet();
		assertThat(swiper.getFoodNames(), is(foods));
	}

		
	}

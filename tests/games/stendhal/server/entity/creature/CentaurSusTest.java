package games.stendhal.server.entity.creature;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import games.stendhal.common.constants.Nature;
import games.stendhal.server.core.engine.SingletonRepository;

public class CentaurSusTest {

	@Test
	public void SolarSustest() throws URISyntaxException {
		//Checks the solar centaurs fire susceptibility and checks it's resistant to fire
		
		assertThat(SingletonRepository.getEntityManager().getCreature("solar centaur").getSusceptibility(Nature.FIRE), closeTo(1.0, 0.00001) );
	}
	
	@Test
	public void GlacierSustest() throws URISyntaxException {
		//Checks the Glacier centaurs fire susceptibility and checks it's vulnerable to fire
		
		assertThat(SingletonRepository.getEntityManager().getCreature("glacier centaur").getSusceptibility(Nature.FIRE), closeTo(2, 1.1) );
	}
	
	public void GlacierTypetest() throws URISyntaxException {
		//Checks the Glacier centaur is an ice type
		
		assertEquals(SingletonRepository.getEntityManager().getCreature("glacier centaur").getDamageType(), Nature.ICE);
	}
	
	public void SolarTypetest() throws URISyntaxException {
		//Checks the Solar centaur is an fire type
		
		assertEquals(SingletonRepository.getEntityManager().getCreature("solar centaur").getDamageType(), Nature.FIRE);
	}

}

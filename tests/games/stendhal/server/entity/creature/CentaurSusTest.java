package games.stendhal.server.entity.creature;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;



import org.junit.Test;

import games.stendhal.common.constants.Nature;
import games.stendhal.server.core.engine.SingletonRepository;

public class CentaurSusTest {

	@Test
	public void SolarSustest(){
		//Checks the Solar centaurs fire susceptibility and checks it's resistant to fire
		
		assertThat(SingletonRepository.getEntityManager().getCreature("solar centaur").getSusceptibility(Nature.FIRE), closeTo(0.8, 0.00001) );
	}
	
	@Test
	public void GlacierSustest() {
		//Checks the Glacier centaurs fire susceptibility and checks it's vulnerable to fire
		
		assertThat(SingletonRepository.getEntityManager().getCreature("glacier centaur").getSusceptibility(Nature.FIRE), closeTo(1.2, 0.01) );
	}
	
	public void GlacierTypetest(){
		//Checks the Glacier centaur is an ice type
		
		assertEquals(SingletonRepository.getEntityManager().getCreature("glacier centaur").getDamageType(), Nature.ICE);
	}
	
	public void SolarTypetest(){
		//Checks the Solar centaur is an fire type
		
		assertEquals(SingletonRepository.getEntityManager().getCreature("solar centaur").getDamageType(), Nature.FIRE);
	}

}

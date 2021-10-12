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
	public void test() throws URISyntaxException {
		//CreatureGroupsXMLLoader creaturesXML = new CreatureGroupsXMLLoader(new URI("data/conf/creatures.xml"));
		//creaturesXML.load();
		//fail("I hope this works");
		
		assertThat(SingletonRepository.getEntityManager().getCreature("solar centaur").getSusceptibility(Nature.FIRE), closeTo(1.0, 0.00001) );
	}

}

package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertEquals;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.semos.library.HistorianGeographerNPC;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

public class MeetZynnTest {

	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
	}

	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new HistorianGeographerNPC().configureZone(zone, null);
		npc = SingletonRepository.getNPCList().get("Zynn Iwuhos");

		en = npc.getEngine();

		final AbstractQuest quest = new MeetZynn();
		quest.addToWorld();

		player = PlayerTestHelper.createPlayer("player");
	}
	
	@Test
	public void testIfXPIncreases() {
		final int originalXP = player.getXP();
		
		en.step(player, "hi");
		assertEquals("Ask me a question to get XP points", getReply(npc));
		en.step(player, "history");
		assertEquals("Ask me about the news", getReply(npc));
		en.step(player, "news");
		assertEquals("Bye.", getReply(npc));
		
		assertEquals(player.getXP(), originalXP + 10);
		

	}

}

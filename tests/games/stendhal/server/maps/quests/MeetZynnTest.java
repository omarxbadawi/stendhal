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
		
		en.step(player, "Hi");
		assertEquals("Hi, potential reader! Here you can find records of the history of Semos, and lots of interesting facts about this island of Faiumoni. If you like, I can give you a quick introduction to its #geography and #history! I also keep up with the #news, so feel free to ask me about that.", getReply(npc));
		en.step(player, "history");
		assertEquals("At present, there are two significant powers on Faiumoni; the Deniran Empire, and the dark legions of Blordrough. Blordrough has recently conquered the south of the island, seizing several steel mines and a large gold mine. At present, Deniran still controls the central and northern parts of Faiumoni, including several gold and mithril mines.", getReply(npc));

		
		assertEquals(player.getXP(), originalXP + 5);
		

	}

}

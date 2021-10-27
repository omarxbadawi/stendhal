/***************************************************************************
 *                     Copyright © 2021 - Arianne                          *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.nalwor.forest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class OmuraDialogueTest extends ZonePlayerAndNPCTestImpl {

	private static final String ZONE_NAME = "0_nalwor_forest_n";
	private static final String npcName = "Omura Sumitada";
	private static Player player;
	private static SpeakerNPC Omura;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	}

	@Override
	@Before
	public void setUp() throws Exception {
		setZoneForPlayer(ZONE_NAME);
		setNpcNames(npcName);

		addZoneConfigurator(new Dojo(), ZONE_NAME);

		super.setUp();

		player = PlayerTestHelper.createPlayer("player");
		Omura = SingletonRepository.getNPCList().get("Omura Sumitada");
	}

	@Test
	public void initTest() {
		testEntities();
		testHi();
		testAtkTooHigh();
		testAtkOk();
	}

	//Player and Omura are not null values
	private void testEntities() {
		assertNotNull(player);
		assertNotNull(Omura);
	}

	//Omura responds when addressed
	private void testHi() {
		final Engine en = Omura.getEngine();

		en.step(player, "hi");
		assertEquals(ConversationStates.ATTENDING, en.getCurrentState());
		assertEquals("This is the assassins' dojo.", getReply(Omura));
		en.step(player, "bye");
		assertEquals(ConversationStates.IDLE, en.getCurrentState());
	}

	//Omura does not calculate fee when Attack Level is not enough
	private void testAtkTooHigh() {
		final Engine en = Omura.getEngine();
        assertEquals(0, player.getLevel());

		en.step(player, "hi");
		assertEquals(ConversationStates.ATTENDING, en.getCurrentState());
		assertEquals("This is the assassins' dojo.", getReply(Omura));
		
        assertTrue(en.step(player, "fee"));
        assertEquals("At your level of experience, your attack strength is too high to train here at this time.", getReply(Omura));
		en.step(player, "bye");
		assertEquals(ConversationStates.IDLE, en.getCurrentState());
	}

	//Omura calculates fee when Attack Level is appropriate
	private void testAtkOk() {
		final Engine en = Omura.getEngine();
        player.addXP(9999999);
        assertEquals(100, player.getLevel());

		en.step(player, "hi");
		assertEquals(ConversationStates.ATTENDING, en.getCurrentState());
		assertEquals("This is the assassins' dojo.", getReply(Omura));
		
        assertTrue(en.step(player, "fee"));
        assertEquals("The fee to #train for your skill level is 625 money.", getReply(Omura));
		en.step(player, "bye");
		assertEquals(ConversationStates.IDLE, en.getCurrentState());
	}
}

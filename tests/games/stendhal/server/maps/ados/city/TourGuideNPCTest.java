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
package games.stendhal.server.maps.ados.city;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static utilities.SpeakerNPCTestHelper.getReply;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.npc.ConversationStates;
//import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
//import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class TourGuideNPCTest extends ZonePlayerAndNPCTestImpl {

	private static final String ZONE_NAME = "0_ados_city_n";
	private static final String npcName = "Touristo Maps";
	private static Player player;
	private static SpeakerNPC Touristo;
	private Engine touristoEngine;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME, new TourGuideNPC());
	}

	@Override
	@Before
	public void setUp() throws Exception {
		setZoneForPlayer(ZONE_NAME);
		setNpcNames(npcName);

		super.setUp();
		player = PlayerTestHelper.createPlayer("player");
		Touristo = SingletonRepository.getNPCList().get("Touristo Maps");

		touristoEngine = Touristo.getEngine();
	}

	@Test
	public void initTest() {
		TourGuideDialogueTest();
		testEntities();
	}
	
	public TourGuideNPCTest() {
		super(ZONE_NAME, "Touristo Maps");
	}

	//Player and Touristo are not null values
	private void testEntities() {
		assertNotNull(player);
		assertNotNull(Touristo);
	}
	
	private void TourGuideDialogueTest() {

		touristoEngine.step(player, "hi");
		assertEquals(ConversationStates.ATTENDING, touristoEngine.getCurrentState());
		assertEquals("Hi! My name is Touristo. I'll be starting a tour to Nalwor soon.", getReply(Touristo));
		touristoEngine.step(player, "bye");
		assertEquals("Bye", getReply(Touristo));
		assertEquals(ConversationStates.IDLE, touristoEngine.getCurrentState());
	}

}
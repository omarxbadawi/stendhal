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
package games.stendhal.server.maps.nalwor.city;

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
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;


import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

//import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
//import games.stendhal.server.core.pathfinder.FixedPath;
//import games.stendhal.server.core.pathfinder.Node;


public class OtherTourGuideNPCTest extends ZonePlayerAndNPCTestImpl {

	private static final String ZONE_NAME = "0_nalwor_city_n";
	private static final String npcName = "Mouristo Maps";
	private static Player player;
	private static SpeakerNPC Mouristo;
	private Engine MouristoEngine;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME, new OtherTourGuideNPC());
	}

	@Override
	@Before
	public void setUp() throws Exception {
		setZoneForPlayer(ZONE_NAME);
		setNpcNames(npcName);

		super.setUp();
		player = PlayerTestHelper.createPlayer("player");
		Mouristo = SingletonRepository.getNPCList().get("Mouristo Maps");

		MouristoEngine = Mouristo.getEngine();
	}
	
	public OtherTourGuideNPCTest() {
		super(ZONE_NAME, "Mouristo Maps");
	}

	@Test
	//Player and Touristo are not null values
	public void testEntities() {
		assertNotNull(player);
		assertNotNull(Mouristo);
	}
	
	@Test
	public void OtherTourDialogueAndTeleportationTest() {

		MouristoEngine.step(player, "hi");
		assertEquals(ConversationStates.ATTENDING, MouristoEngine.getCurrentState());
		assertEquals("Hi! My name is Mousristo, Touristo's coursin! Welcome to Nalwor city tour!", getReply(Mouristo));
		assertEquals("Nalwor is an ancient city, with some grand stone buildings with opulent interiors. A royal hall to the north west of the city is the best example of this. Lovers of nature, the elves maintain formal gardens even amongst their forest setting. These are interspersed with the administrative buildings and businesses, which include a bank, post office, jail, library and inn. There are shops which are worth visiting, as although elves dislike humans, some will trade and even make conversation, if it is to their profit. A mysterious mosaic is laid between the post office and the pottery, in the centre of the city. Near this is the tallest building in Nalwor city, a tower where each floor is decorated more sumptuously than the next.", getReply(Mouristo));
		assertEquals("Thanks for coming on the tour! Ready to head home?", getReply(Mouristo));
		MouristoEngine.step(player, "yes");
		assertEquals("Great, lets go!", getReply(Mouristo));
		StendhalRPZone newZone = SingletonRepository.getRPWorld().getZone("0_ados_city_n");
	    assertEquals(newZone, player.getZone());
		
	}
	
	@Test
	public void OtherTourDialogueRejectionTest() {
		MouristoEngine.step(player, "hi");
		assertEquals(ConversationStates.ATTENDING, MouristoEngine.getCurrentState());
		assertEquals("Hi! My name is Mousristo, Touristo's coursin! Welcome to Nalwor city tour!", getReply(Mouristo));
		assertEquals("Nalwor is an ancient city, with some grand stone buildings with opulent interiors. A royal hall to the north west of the city is the best example of this. Lovers of nature, the elves maintain formal gardens even amongst their forest setting. These are interspersed with the administrative buildings and businesses, which include a bank, post office, jail, library and inn. There are shops which are worth visiting, as although elves dislike humans, some will trade and even make conversation, if it is to their profit. A mysterious mosaic is laid between the post office and the pottery, in the centre of the city. Near this is the tallest building in Nalwor city, a tower where each floor is decorated more sumptuously than the next.", getReply(Mouristo));
		assertEquals("Thanks for coming on the tour! Ready to head home?", getReply(Mouristo));
		MouristoEngine.step(player, "no");
		assertEquals("Sadly our time has run out and I must send you home", getReply(Mouristo));
		StendhalRPZone newZone = SingletonRepository.getRPWorld().getZone("0_ados_city_n");
	    assertEquals(newZone, player.getZone());
	}

}
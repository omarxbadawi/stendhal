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

import static org.junit.Assert.*;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class TourGuideNPCTest extends ZonePlayerAndNPCTestImpl{
	
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
        Touristo = SingletonRepository.getNPCList().get(npcName);
    }

    @Test
    public void DialogueTest() {
        if(Touristo != null) {
            touristoEngine = Touristo.getEngine();
            player = PlayerTestHelper.createPlayer("testPlayer");
            createDialog();
            TourDialogueRejectionTest();
        }else {
        	assertTrue(false);
        }
    }
    public void createDialog() {
        touristoEngine.step(player, "hi");
    	assertEquals("Hi! My name is Tousristo, do you want to go on a tour to Nalwor city?", getReply(Touristo));
		touristoEngine.step(player, "yes");
		assertEquals("Great, lets go!", getReply(Touristo));
		StendhalRPZone newZone = SingletonRepository.getRPWorld().getZone("0_nalwor_city_n");
	    assertEquals(newZone, player.getZone());

    }


    public void TourDialogueRejectionTest() {
        touristoEngine = Touristo.getEngine();
        player = PlayerTestHelper.createPlayer("testPlayer");
//		touristoEngine.step(player, "hi");
//		assertEquals("Hi! My name is Tousristo, do you want to go on a tour to Nalwor city?", getReply(Touristo));
		touristoEngine.step(player, "no");
		assertEquals("Okay, see you around.", getReply(Touristo));
    }

}

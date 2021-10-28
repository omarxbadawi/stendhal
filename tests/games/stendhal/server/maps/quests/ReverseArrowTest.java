/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.mapstuff.portal.Door;
import games.stendhal.server.entity.mapstuff.sign.Sign;
import games.stendhal.server.entity.npc.NPCList;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPClass;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

public class ReverseArrowTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
		QuestHelper.setUpBeforeClass();
		StendhalRPZone zone = new StendhalRPZone("int_ados_reverse_arrow");
		MockStendlRPWorld.get().addRPZone(zone);
		MockStendlRPWorld.get().addRPZone(new StendhalRPZone("0_semos_mountain_n2"));

		if (!RPClass.hasRPClass("door")) {
			Door.generateRPClass();
		}
		if (!RPClass.hasRPClass("sign")) {
			Sign.generateRPClass();
		}
	}
	
	private ReverseArrow setUpReverseArrow() {
		ReverseArrow arrowquest = new ReverseArrow();
		arrowquest.addToWorld();

		Player player = PlayerTestHelper.createPlayer("bob");
		arrowquest.zone.add(player);
		arrowquest.start(player);
		
		return arrowquest;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MockStendlRPWorld.reset();
		NPCList.get().clear();
	}

	/**
	 * Tests for getSlotName.
	 */
	@Test
	public void testGetSlotName() {
		assertEquals("reverse_arrow", new ReverseArrow().getSlotName());
	}

	/**
	 * Tests for addToWorld.
	 */
	@Test
	public void testAddToWorld() {

		ReverseArrow arrowquest = new ReverseArrow();
		arrowquest.addToWorld();
	}

	/**
	 * Tests for finish.
	 */
	@Test
	public void testFinish() {
		ReverseArrow arrowquest = new ReverseArrow();
		arrowquest.addToWorld();
		arrowquest.player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(arrowquest.player);
		arrowquest.finish(false, null);
		assertNotNull(arrowquest.player);

		arrowquest.finish(true, null);
		assertNull(arrowquest.player);
	}
	
	/**
	 * Tests for win.
	 */
	@Test
	public void testWinFullArrow() {
		// * * 0 * *
		// * 1 2 3 *
		// 4 5 6 7 8

		ReverseArrow arrowquest = setUpReverseArrow();
		
		final int leftX = arrowquest.tokens.get(0).getX();
		final int leftY = arrowquest.tokens.get(0).getY();

		arrowquest.tokens.get(0).setPosition(leftX, leftY + 1);
		arrowquest.tokens.get(4).setPosition(leftX + 4, leftY + 1);
		arrowquest.tokens.get(8).setPosition(leftX + 2, leftY - 1);
		arrowquest.new ReverseArrowCheck().onTurnReached(5);

		assertTrue(arrowquest.player.isQuestCompleted("reverse_arrow"));
	}

	@Test
	public void testWinNotFullArrow() {
		// * * 0 * *
		// * 1 * 2 *
		// 3 4 5 6 7
		// * * 8 * *

		ReverseArrow arrowquest = setUpReverseArrow();
		
		final int leftX = arrowquest.tokens.get(0).getX();
		final int leftY = arrowquest.tokens.get(0).getY();
		
		arrowquest.tokens.get(5).setPosition(leftX + 1, leftY - 1);
		arrowquest.tokens.get(7).setPosition(leftX + 3, leftY - 1);
		arrowquest.tokens.get(8).setPosition(leftX + 2, leftY - 2);
		arrowquest.new ReverseArrowCheck().onTurnReached(5);

		assertTrue(arrowquest.player.isQuestCompleted("reverse_arrow"));
	}

	@Test
	public void testWinFail() {
		ReverseArrow arrowquest = setUpReverseArrow();
		arrowquest.new ReverseArrowCheck().onTurnReached(5);
		assertFalse(arrowquest.player.isQuestCompleted("reverse_arrow"));
	}
}

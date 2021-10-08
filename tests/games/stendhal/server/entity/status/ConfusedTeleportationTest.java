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
package games.stendhal.server.entity.status;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import games.stendhal.server.entity.item.scroll.MarkedScroll;

public class ConfusedTeleportationTest {

	/**
	 * Initialization
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		MockStendlRPWorld.get();
	}

	/**
	 * Tests the use of Marked Scroll when confused.
	 */
	@Test
	public void testConfusedTeleport() {
		final Player victim = PlayerTestHelper.createPlayer("iliterated");
		final ConfuseStatus Confused = new ConfuseStatus();
		ConfuseStatusHandler confuseStatusHandler = new ConfuseStatusHandler();
		confuseStatusHandler.inflict(Confused, victim.getStatusList(), SingletonRepository.getEntityManager().getCreature("mermaid"));
		final MarkedScroll Tobeused = (MarkedScroll) SingletonRepository.getEntityManager().getItem("marked scrolls");
		victim.equip("bag", Tobeused);
		assertFalse(Tobeused.onUsed(victim));
	}
}
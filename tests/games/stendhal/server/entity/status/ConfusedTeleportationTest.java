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

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
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
		final Player victim = PlayerTestHelper.createPlayer("Bob");
		StendhalRPZone zone = new StendhalRPZone("the zone where the corpse shall be slain");
		zone.add(victim);
		final ConfuseStatus Confused = new ConfuseStatus();
		final ConfuseStatusHandler confuseStatusHandler = new ConfuseStatusHandler();
		confuseStatusHandler.inflict(Confused, victim.getStatusList(), SingletonRepository.getEntityManager().getCreature("mermaid"));
		Map<String, String> attribute = new HashMap<String, String>();
		attribute.put("quantity", "1");
		attribute.put("menu", "Use|Use");
		final MarkedScroll Tobeused = new MarkedScroll("marked scroll", "scroll", "marked", attribute);
		victim.equip("bag", Tobeused);
		assertFalse(Tobeused.onUsed(victim));
	}
}
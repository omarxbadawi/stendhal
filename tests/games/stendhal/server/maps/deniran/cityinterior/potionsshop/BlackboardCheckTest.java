/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2021 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.deniran.cityinterior.potionsshop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;

public class BlackboardCheckTest {

	private static final String ZONE_NAME = "int_deniran_potions_shop";
	private static StendhalRPZone zone;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		zone = new StendhalRPZone(ZONE_NAME);
	}

	@Before
	public void setUp() {
		final ZoneConfigurator zoneConf = new PotionsDealerNPC();
		zoneConf.configureZone(zone, null);
	}

	@Test
	public void testBlackboardExistence() {
		assertEquals("blackboard", zone.getEntityAt(5, 6).getTitle());
		assertEquals("blackboard", zone.getEntityAt(10, 6).getTitle());
	}
}

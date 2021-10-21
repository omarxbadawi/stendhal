/***************************************************************************
 *                     Copyright © 2020 - Arianne                          *
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

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;

public class PotionShopTest {

	private static final String ZONE_NAME = "int_deniran_potions_shop";
	private static StendhalRPZone potionZone;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
          potionZone = SingletonRepository.getRPWorld().getZone(ZONE_NAME);
        }

	@Test
	public void initTest() {
		assertEquals("blackboard", potionZone.getEntityAt(6, 5));
		assertEquals("blackboard", potionZone.getEntityAt(10, 5));
	}

}


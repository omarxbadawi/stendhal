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
package games.stendhal.server.entity;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.entity.creature.Cat;
import games.stendhal.server.entity.creature.Creature;
import games.stendhal.server.entity.creature.Sheep;
import games.stendhal.server.entity.creature.Fox_Pet;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.CatTestHelper;
import utilities.RPClass.SheepTestHelper;
import utilities.RPClass.Fox_PetTestHelper;

public class GetBaseSpeedTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		MockStendlRPWorld.get();
		PlayerTestHelper.generateCreatureRPClasses();
		Fox_PetTestHelper.generateRPClasses();
		CatTestHelper.generateRPClasses();
		SheepTestHelper.generateRPClasses();
	}

	/**
	 * Tests for getBaseSpeed.
	 */
	@Test
	public void getBaseSpeedTest() {

		assertEquals(0.2, (new SpeakerNPC("bob")).getBaseSpeed(), 0.001);
		assertEquals(0.0, (new Creature()).getBaseSpeed(), 0.001);
		assertEquals(0.8, (new Fox_Pet()).getBaseSpeed(), 0.001);
		//TODOs
		//assertEquals(0.8, (new Fox()).getBaseSpeed(), 0.001);
		//assertEquals(0.8, SingletonRepository.getEntityManager().getCreature("FoxPet").getBaseSpeed(), 0.001);
		//assertNotNull(SingletonRepository.getEntityManager().getCreature("FoxPet"));
		assertEquals(0.9, (new Cat()).getBaseSpeed(), 0.001);
		assertEquals(0.25, (new Sheep()).getBaseSpeed(), 0.001);

	}

}

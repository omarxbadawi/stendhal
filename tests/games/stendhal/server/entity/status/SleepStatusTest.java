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

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;



import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;

import utilities.PlayerTestHelper;

public class SleepStatusTest {

	/**
	 * initialisation
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		MockStendlRPWorld.get();
	}

	/**
	 * Tests for healing without eating
	 */
	@Test
	public void testHealing() {
		final Player victim = PlayerTestHelper.createPlayer("bob");
		victim.initHP(100);
		victim.setHP(50);
		final SleepStatus sleep = new SleepStatus(30, 10, 30);
		final SleepStatusHandler sleepStatusHandler = new SleepStatusHandler();
		sleepStatusHandler.inflict(sleep, victim.getStatusList(), victim);
		final SleepStatusTurnListener listener = new SleepStatusTurnListener(victim.getStatusList());
		assertTrue(victim.hasStatus(StatusType.SLEEPING));
		listener.onTurnReached(10);
		assertEquals(victim.getHP(), 80);
	}
	
	/**
	 * Tests for faster healing with eating
	 */
	@Test
	public void testHealingWithEating() {
		final Player victim = PlayerTestHelper.createPlayer("bob");
		victim.initHP(100);
		victim.setHP(50);

		final EatStatus eat = new EatStatus(30, 10, 30);
		final EatStatusHandler eatStatusHandler = new EatStatusHandler();
		eatStatusHandler.inflict(eat, victim.getStatusList(), victim);
		final EatStatusTurnListener eatListener = new EatStatusTurnListener(victim.getStatusList());
		eatListener.onTurnReached(5);
		assertTrue(victim.hasStatus(StatusType.EATING));
		assertEquals(victim.getHP(), 50);
		
		final SleepStatus sleep = new SleepStatus(30, 10, 30);
		final SleepStatusHandler sleepStatusHandler = new SleepStatusHandler();
		sleepStatusHandler.inflict(sleep, victim.getStatusList(), victim);

		assertTrue(victim.hasStatus(StatusType.SLEEPING));
		eatListener.onTurnReached(5);
		assertEquals(victim.getHP(), 80);
	}
	
	
	/**
	 * Tests for poison reduction.
	 */
	@Test
	public void testPoisonReduction() {
		final Player victim = PlayerTestHelper.createPlayer("bob");
		victim.initHP(100);
		victim.setHP(50);
		
		final PoisonStatus poisonStatus = new PoisonStatus(-20, 5, -10);
		final PoisonStatusHandler poisonStatusHandler = new PoisonStatusHandler();
		poisonStatusHandler.inflict(poisonStatus, victim.getStatusList(), victim);
		assertTrue(victim.hasStatus(StatusType.POISONED));
		final PoisonStatusTurnListener listener = new PoisonStatusTurnListener(victim.getStatusList());
		
		listener.onTurnReached(5);
		assertEquals(victim.getHP(), 40);


		final SleepStatus sleep = new SleepStatus(30, 10, 30);
		final SleepStatusHandler sleepStatusHandler = new SleepStatusHandler();
		sleepStatusHandler.inflict(sleep, victim.getStatusList(), victim);
		assertTrue(victim.hasStatus(StatusType.SLEEPING));
		listener.onTurnReached(5);
		assertEquals(victim.getHP(), 35);
	}
}

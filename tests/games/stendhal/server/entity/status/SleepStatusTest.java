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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;


import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.ConsumableItem;
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
		
		final SleepStatus sleep = new SleepStatus(30, 10, 30);
		final SleepStatusHandler sleepStatusHandler = new SleepStatusHandler();
		sleepStatusHandler.inflict(sleep, victim.getStatusList(), victim);
		final SleepStatusTurnListener sleepListener = new SleepStatusTurnListener(victim.getStatusList());
		assertTrue(victim.hasStatus(StatusType.SLEEPING));
		sleepListener.onTurnReached(5);
		assertEquals(victim.getHP(), 80);
	}
	
	/**
	 * Tests for disable movement.
	 */
	@Test
	public void testDisableMovement() {
		final Player victim = PlayerTestHelper.createPlayer("bob");
		SingletonRepository.getRPWorld().addRPZone(new StendhalRPZone("0_semos_canyon", 50, 50));
		SingletonRepository.getRPWorld().getZone("0_semos_canyon").add(victim);
		victim.setPosition(0, 0);

		final SleepStatus sleep = new SleepStatus(30, 10, 30);
		final SleepStatusHandler sleepStatusHandler = new SleepStatusHandler();
		sleepStatusHandler.inflict(sleep, victim.getStatusList(), victim);
		final SleepStatusTurnListener listener = new SleepStatusTurnListener(victim.getStatusList());
		assertTrue(victim.hasStatus(StatusType.SLEEPING));
		
		victim.setPathPosition(10);
		victim.applyMovement();
		listener.onTurnReached(10);
		assertFalse(victim.hasStatus(StatusType.SLEEPING));
	}
	
	
	/**
	 * Tests for poison reduction.
	 */
	@Test
	public void testPoisonReduction() {
		final Player victim = PlayerTestHelper.createPlayer("bob");
		victim.initHP(100);
		victim.setHP(50);
		final String poisontype = "greater poison";
		final ConsumableItem poison = (ConsumableItem) SingletonRepository.getEntityManager().getItem(poisontype);
		poison.put("amount", 20);
		poison.put("frequency", 5);
		poison.put("regen", 10);
		
		final PoisonAttacker poisoner = new PoisonAttacker(100, poison);
		poisoner.onAttackAttempt(victim, SingletonRepository.getEntityManager().getCreature("snake"));
		assertTrue(victim.hasStatus(StatusType.POISONED));
		final PoisonStatusTurnListener listener = new PoisonStatusTurnListener(victim.getStatusList());
		listener.onTurnReached(5);
		assertEquals(victim.getHP(), 40);


		final SleepStatus sleep = new SleepStatus(30, 10, 30);
		final SleepStatusHandler sleepStatusHandler = new SleepStatusHandler();
		sleepStatusHandler.inflict(sleep, victim.getStatusList(), victim);
		assertTrue(victim.hasStatus(StatusType.SLEEPING));
		listener.onTurnReached(5);
		assertNotEquals(victim.getHP(), 30);
	}
}

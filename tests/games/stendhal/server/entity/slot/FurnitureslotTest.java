package games.stendhal.server.entity.slot;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPSlot;
import utilities.PlayerTestHelper;

public class FurnitureslotTest {

	@BeforeClass
	public static void setUp() {
		MockStendlRPWorld.get();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlayerTestHelper.removePlayer(OFFERER_NAME);
		MockStendlRPWorld.reset();
	}

	private static final String OFFERER_NAME = "george";

	/**
	 * Tests for pick up furniture as an item.
	 *
	 * @throws Exception
	 */
	@Test
	public void testPick() throws Exception {
		Item furniture = SingletonRepository.getEntityManager().getItem("chair");
		assertTrue(furniture != null);
		Player offerer = PlayerTestHelper.createPlayer(OFFERER_NAME);
		RPSlot furnitureslot = offerer.getSlot("furniture");
		assertTrue(furnitureslot != null);
		assertFalse(offerer.isEquippedItemInSlot("furniture", "chair"));
		assertTrue(offerer.equipToInventoryOnly(furniture));
		assertTrue(offerer.isEquippedItemInSlot("furniture", "chair"));
	}

	/**
	 * Tests for drop furniture
	 *
	 * @throws Exception
	 */
	@Test
	public void testDrop() throws Exception {
		Item furniture = SingletonRepository.getEntityManager().getItem("chair");
		assertTrue(furniture != null);
		Player offerer = PlayerTestHelper.createPlayer(OFFERER_NAME);
		assertFalse(offerer.drop("chair"));
		RPSlot furnitureslot = offerer.getSlot("furniture");
		assertTrue(furnitureslot != null);
		assertTrue(offerer.equipToInventoryOnly(furniture));
		assertTrue(offerer.isEquippedItemInSlot("furniture", "chair"));
		assertTrue(offerer.drop("chair"));
	}
}

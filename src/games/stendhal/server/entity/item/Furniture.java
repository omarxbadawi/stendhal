package games.stendhal.server.entity.item;

import java.util.Map;

/**
 * Basic furniture class for objects such as chair, table, etc. The NPC-s can
 * interact with the furniture. Furniture can be collected in a slot, or placed
 * in the world. It can be also moved around by pulling or pushing.
 */

public class Furniture extends Item {
	// TODO: use this field for the interaction with the user.
	// private UseBehavior useBehavior;

	public Furniture(String name, String clazz, String subclass, Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
	}

	/**
	 * Copy constructor.
	 *
	 * @param fur furniture to copy
	 */
	public Furniture(Furniture fur) {
		super(fur);
	}

	// TODO: use this function to set the furniture slot.
	// public void setEquipableSlots(final List<String> slots);
}

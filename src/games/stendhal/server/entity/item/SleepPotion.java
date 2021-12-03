/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.item;

import java.util.Map;

import org.apache.log4j.Logger;

import games.stendhal.common.grammar.Grammar;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.status.SleepStatus;

/**
 * Represents a marked teleport scroll which also poisoned the player.
 *
 * @author kymara
 */
public class SleepPotion extends Drink {

	private static final Logger logger = Logger.getLogger(TwilightElixir.class);

	/**
	 * Creates a new marked teleport scroll.
	 *
	 * @param name
	 * @param clazz
	 * @param subclass
	 * @param attributes
	 */
	public SleepPotion(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
	}

	/**
	 * Copy constructor.
	 *
	 * @param item
	 *            item to copy
	 */
	public SleepPotion(final SleepPotion item) {
		super(item);
	}

	/*
	 * This is very nasty of us. We take away the message that HP = -1000 so player doesn't know.
	 */

	@Override
	public String describe() {
		String text = "You see " + Grammar.a_noun(getTitle()) + ".";
		String stats = "";
		if (hasDescription()) {
			text = getDescription();
		}

		final String boundTo = getBoundTo();

		if (boundTo != null) {
			text = text + " It is a special reward for " + boundTo
					+ ", and cannot be used by others.";
		}
		return (text + stats);
	}

        /**
         * the overridden method verifies item is near to player. if so splits
         * one single item of and calls consumeItem of the player - so they get
         * poisoned, since that's what twilight elixir does
         * @param user the eating player
         * @return true if consumption can be started
         *
         * this one first teleports the player  (if they are in quest slot
         * twilight zone, to prevent abuse) before it poisons them.
         */
        @Override
        public boolean onUsed(final RPEntity user) {
          user.getStatusList().inflictStatus(new SleepStatus(50, 5, 10), null);
          return super.onUsed(user);
        }
}

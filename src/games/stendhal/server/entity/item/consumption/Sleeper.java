/***************************************************************************
 *                   (C) Copyright 2003-2013 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.item.consumption;

import games.stendhal.server.entity.item.ConsumableItem;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.status.SleepStatus;
import games.stendhal.server.entity.status.SleepStatusHandler;

/**
 * Poisoner
 */
public class Sleeper implements Feeder {

	@Override
	public boolean feed(final ConsumableItem item, final Player player) {
		SleepStatus status = new SleepStatus(10000, 5, 10);
		SleepStatusHandler handler = new SleepStatusHandler();
		handler.inflict(status, player.getStatusList(), player);
		player.sendPrivateText("Post infliction");
		return true;
	}

}

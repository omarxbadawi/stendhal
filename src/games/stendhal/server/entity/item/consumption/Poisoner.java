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

import java.util.Arrays;
import java.util.List;

import games.stendhal.server.entity.item.ConsumableItem;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.status.PoisonStatus;
import games.stendhal.server.entity.status.SleepStatus;
import games.stendhal.server.entity.status.Status;

/**
 * Poisoner
 */
public class Poisoner implements Feeder {

	@Override
	public boolean feed(final ConsumableItem item, final Player player) {
		ConsumableItem splitOff = (ConsumableItem) item.splitOff(1);
		Status status;
		List<String> sleep = Arrays.asList("sleep potion");
		if (sleep.contains(item.getName())) {
			status = new SleepStatus(splitOff.getAmount(), splitOff.getFrecuency(), splitOff.getRegen());
		} else {
			status = new PoisonStatus(splitOff.getAmount(), splitOff.getFrecuency(), splitOff.getRegen());
		}
		player.getStatusList().inflictStatus(status, splitOff);
		return true;
	}

}

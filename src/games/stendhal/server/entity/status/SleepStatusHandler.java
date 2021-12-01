/***************************************************************************
 *                (C) Copyright 2005-2013 - Faiumoni e. V.                 *
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

import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.TurnNotifier;
import games.stendhal.server.entity.Entity;

/**
 * handles sleeping 
 */
public class SleepStatusHandler implements StatusHandler<SleepStatus> {

	/**
	 * inflicts a status
	 *
	 * @param status Status to inflict
	 * @param statusList StatusList
	 * @param attacker the attacker
	 */
	@Override
	public void inflict(SleepStatus status, StatusList statusList, Entity attacker) {
		statusList.addInternal(status);
		int count = statusList.countStatusByType(status.getStatusType());

		// We are already asleep
		if (count >= 1) {
			return;
		}

		// activate the turnListener, if this is the first instance of this status
		if (count == 0) {
			TurnListener turnListener = new SleepStatusTurnListener(statusList);
			TurnNotifier.get().dontNotify(turnListener);
			TurnNotifier.get().notifyInTurns(0, turnListener);
		}
	}

	/**
	 * removes a status
	 *
	 * @param status Status to inflict
	 * @param statusList StatusList
	 */
	@Override
	public void remove(SleepStatus status, StatusList statusList) {
		statusList.removeInternal(status);
	}
}

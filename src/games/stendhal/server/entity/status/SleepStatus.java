/***************************************************************************
 *                (C) Copyright 2003-2013 - Faiumoni e. V.                 *
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



/**
 * sleep status
 *
 * Note: this class has a natural ordering that is inconsistent with equals.
 */
public class SleepStatus extends ConsumableStatus {

	/**
	 * sleep
	 *
	 * @param amount     total amount
	 * @param frequency  frequency of events
	 * @param regen      hp change on each event
	 */
	public EatStatus(int amount, int frequency, int regen) {
		super("sleep", amount, frequency, regen);
	}

	/**
	 * returns the status type
	 *
	 * @return StatusType
	 */
	@Override
	public StatusType getStatusType() {
		return StatusType.SLEEPING;
	}


}

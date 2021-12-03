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
import games.stendhal.server.entity.RPEntity;

/**
 * sleeping turn listener
 */
public class SleepStatusTurnListener implements TurnListener {
  private StatusList statusList;

  /**
   * SleepStatusTurnListener
   *
   * @param statusList StatusList
   */
  public SleepStatusTurnListener(StatusList statusList) {
    this.statusList = statusList;
  }

  @Override
  public void onTurnReached(int turn) {
    RPEntity entity = statusList.getEntity();
    SleepStatus sleeping = statusList.getFirstStatusByClass(SleepStatus.class);

    entity.sendPrivateText("We have inflicted sleep");

    // check that the entity exists
    if (entity == null || sleeping == null) {
      return;
    }

    if (turn % sleeping.getFrecuency() == 0) {
      final int amount = sleeping.consume();
      entity.heal(amount, true);
    }

    TurnNotifier.get().notifyInTurns(0, this);
  }

  @Override
  public int hashCode() {
    return statusList.hashCode() * 31;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    SleepStatusTurnListener other = (SleepStatusTurnListener)obj;
    return statusList.equals(other.statusList);
  }
}

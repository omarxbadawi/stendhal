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
package games.stendhal.server.maps.ados.city;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
// this one is just because our NPC is a seller
import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
// this one is just because our NPC is a seller
//import games.stendhal.server.entity.npc.behaviour.adder.SellerAdder;
// this one is just because our NPC is a seller
//import games.stendhal.server.entity.npc.behaviour.impl.SellerBehaviour;
//import games.stendhal.server.entity.npc.ChatAction;
//import games.stendhal.server.entity.npc.ConversationStates;
//import games.stendhal.server.entity.npc.action.MultipleActions;
//import games.stendhal.server.entity.player.Player;
//import games.stendhal.server.entity.npc.condition.LevelGreaterThanCondition;
//import games.stendhal.server.entity.npc.condition.LevelLessThanCondition;

//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
import java.util.Map;

public class TourGuideNPC implements ZoneConfigurator {
    // this is just because he has a 'shop' to sell tour tickets
	private final ShopList shops = SingletonRepository.getShopList();
	
	/**
	 * Configure a zone.
	 *
	 * @param    zone        The zone to be configured.
	 * @param    attributes    Configuration attributes.
	 */
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
	    buildNPC(zone);
	}
	private void buildNPC(final StendhalRPZone zone) {
        final SpeakerNPC Touristo = new SpeakerNPC("Touristo Maps") {
        protected void createPath() {
            setPath(null);
        }

        protected void createDialog() {
            // Lets the NPC reply with "Hello"
            addGreeting("Hi! My name is Touristo. I'll be starting a tour to Nalwor soon.");
            //NPC's response when a player says goodbye
            addGoodbye("Bye");
        }
    };

    // This determines how the NPC will look like. welcomernpc.png is a picture in data/sprites/npc/
    Touristo.setEntityClass("wisemannpc");
    // set a description for when a player does 'Look'
    Touristo.setDescription("You see Toursito Maps , he looks a a bit busy at the moment but perhaps he can help you anyway.");
    // Set the initial position to be the first node on the Path you defined above.
    //che
    Touristo.setPosition(50, 90);
    Touristo.initHP(100);

    zone.add(Touristo);
}
}
	
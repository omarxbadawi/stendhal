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
package games.stendhal.server.maps.nalwor.city;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;


//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
import java.util.Map;

public class OtherTourGuideNPC implements ZoneConfigurator {
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
        final SpeakerNPC Mouristo = new SpeakerNPC("Mouristo Maps") {
        protected void createPath() {
            setPath(null);
        }

        protected void createDialog() {
            // Lets the NPC reply with "Hello"
            addGreeting("Hi! My name is Mousristo, Touristo's coursin! Welcome to Nalwor city tour!");
            //NPC's response when a player says goodbye
            addGoodbye("Bye");
        }
    };

    // This determines how the NPC will look like. welcomernpc.png is a picture in data/sprites/npc/
    Mouristo.setEntityClass("wisemannpc");
    // set a description for when a player does 'Look'
    Mouristo.setDescription("You see Moursito Maps , he looks a lot like his cousin Touristo Maps.");
    // Set the initial position to be the first node on the Path you defined above.
    Mouristo.setPosition(29, 80);
    Mouristo.initHP(100);

    zone.add(Mouristo);
}
}
	
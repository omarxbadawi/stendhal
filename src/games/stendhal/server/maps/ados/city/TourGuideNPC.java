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

import java.util.Arrays;
import java.util.Map;



import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.action.TeleportAction;

public class TourGuideNPC implements ZoneConfigurator {
	/**
	 * Configure a zone.
	 *
	 * @param    zone        The zone to be configured.
	 * @param    attributes    Configuration attributes.
	 */
	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
	    buildNPC(zone);
	}
	private void buildNPC(final StendhalRPZone zone) {
        final SpeakerNPC Touristo = new SpeakerNPC("Touristo Maps") {
        
        @Override
        protected void createPath() {
            setPath(null);
        }

        @Override
        protected void createDialog() {
        	
        	
        	addGreeting("Hi! My name is Tousristo, do you want to go on a tour to Nalwor city?");
     
        	add(ConversationStates.ATTENDING, Arrays.asList("ya", "Ya", "yes", "Yes", "yes please", "yah"),
                    ConversationStates.ATTENDING,
                    "Great, lets go!", new TeleportAction("0_nalwor_city_n",29,80,null));  
            
        	add(ConversationStates.ATTENDING, Arrays.asList("no", "No", "No thanks", "nope"),
                    ConversationStates.ATTENDING,
                    "Okay, see you around.", null);

        }
    };

    // This determines how the NPC will look like. welcomernpc.png is a picture in data/sprites/npc/
    Touristo.setEntityClass("wisemannpc");
    // set a description for when a player does 'Look'
    Touristo.setDescription("You see Toursito Maps , he looks a a bit busy at the moment but perhaps he can help you anyway.");
    // Set the initial position to be the first node on the Path you defined above.
    Touristo.setPosition(50, 90);
    Touristo.initHP(100);

    zone.add(Touristo);
}
}
	
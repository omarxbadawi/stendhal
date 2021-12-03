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
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.action.TeleportAction;

import java.util.Arrays;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
import java.util.Map;

public class OtherTourGuideNPC implements ZoneConfigurator {
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
        final SpeakerNPC Mouristo = new SpeakerNPC("Mouristo Maps") {
        	
        @Override
        protected void createPath() {
            setPath(null);
        }
        
        @Override
        protected void createDialog() {
        	addGreeting("Hi! My name is Mousristo, Touristo's coursin! Welcome to Nalwor city tour! Ready to start?");
            
        	add(ConversationStates.ATTENDING, Arrays.asList("ya", "Ya", "yes", "Yes", "yes please", "yah"),
                    ConversationStates.ATTENDING, "Nalwor is an ancient city, with some grand stone buildings with opulent interiors. A royal hall to the north west of the city is the best example of this. Lovers of nature, the elves maintain formal gardens even amongst their forest setting. These are interspersed with the administrative buildings and businesses, which include a bank, post office, jail, library and inn. There are shops which are worth visiting, as although elves dislike humans, some will trade and even make conversation, if it is to their profit. A mysterious mosaic is laid between the post office and the pottery, in the centre of the city. Near this is the tallest building in Nalwor city, a tower where each floor is decorated more sumptuously than the next. That's it for the tour of Nalwor right now, ready to go home? Say the magic words send me back", null);  
        	
        	add(ConversationStates.ATTENDING, Arrays.asList("send me back", "Send me back"),
                    ConversationStates.ATTENDING, "Okay, I'll send you back home then.",  new TeleportAction("0_ados_city_n",49,90,null));
                    
        	add(ConversationStates.ATTENDING, Arrays.asList("no", "No", "No thanks", "nope"),
                    ConversationStates.ATTENDING,
                    "I'm afraid I have to send you back home.",  new TeleportAction("0_ados_city_n",49,90,null));
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
	
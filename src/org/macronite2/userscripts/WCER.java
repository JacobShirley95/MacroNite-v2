package org.macronite2.userscripts;

import java.awt.Point;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.Player;
import org.macronite2.script.Script;
import org.macronite2.script.entities.RSPlayer;
import org.macronite2.script.items.RSItem;
import org.macronite2.script.map.RSTile;
import org.macronite2.script.math.RSMath;


public class WCER extends Script {

	public WCER(Client runescape) {
		super(runescape);
	}

	@Override
	public int run() {
		/*if (!loggedIn())
			return -1;
		
		System.out.println("loading");
		RSTile tile = new RSPlayer(runescape.getMyPlayer()).getTile();
		RSTile tile2 = new RSTile(tile.x+1, tile.y);
		
		RSItem[] objects = tile2.getObjectsAtTile();
		for (RSItem item : objects) {
			System.out.println(item.name);
		}*/
		
		return 10;
	}
	
}

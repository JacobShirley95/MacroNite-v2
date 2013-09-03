package org.macronite2.userscripts;

import org.macronite2.script.Script;
import org.macronite2.script.ScriptContext;


public class WCER extends Script {

	public WCER(ScriptContext context) {
		super(context);
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

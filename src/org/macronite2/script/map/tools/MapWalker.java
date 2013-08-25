package org.macronite2.script.map.tools;

import java.awt.Point;

import org.macronite2.script.RuneScape;
import org.macronite2.script.map.RSTile;
import org.macronite2.script.screen.RSInput;

public class MapWalker {
	protected RSTile[] path;
	public MapWalker(RSTile[] path) {
		this.path = path;
	}
	
	public void antiban() {
		
	}
	
	public void walk() {
		for (RSTile tile : path) {
			Point mm = tile.toMM();
			RSInput.mouse(mm, RSInput.MOUSE_LEFT);
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {}
			
			//this is crap
			while (!RuneScape.getMyPlayer().isStationary())
				antiban();
		}
	}
}

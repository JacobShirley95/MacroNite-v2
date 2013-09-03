package org.macronite2.script.map.tools;

import java.awt.Point;

import org.macronite2.script.ScriptContext;
import org.macronite2.script.map.RSTile;
import org.macronite2.script.screen.RSInput;

public class MapWalker {
	protected RSTile[] path;
	private ScriptContext context;
	public MapWalker(ScriptContext context, RSTile[] path) {
		this.path = path;
		this.context = context;
	}
	
	public void antiban() {
		
	}
	
	public void walk() {
		for (RSTile tile : path) {
			Point mm = tile.toMM();
			context.input.mouse(mm, RSInput.MOUSE_LEFT);
			context.sleep(200);
			
			//this is crap
			while (context.moving())
				antiban();
		}
	}
}

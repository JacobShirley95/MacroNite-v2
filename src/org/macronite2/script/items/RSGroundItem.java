package org.macronite2.script.items;

import java.awt.Point;

import org.macronite2.script.RuneScape;
import org.macronite2.script.map.RSTile;

public class RSGroundItem extends RSItem{
	public RSTile tile;
	
	public RSGroundItem(org.macronite2.hooks.RSObject rsObj, RSTile tile) {
		super(rsObj);
		this.tile = tile;
	}
	
	public RSGroundItem(org.macronite2.hooks.RSObject rsObj, int x, int y) {
		super(rsObj);
		this.tile = new RSTile(RuneScape.getPlane(), x, y);
	}
	
	public RSGroundItem(org.macronite2.hooks.RSObject rsObj, int plane, int x, int y) {
		super(rsObj);
		this.tile = new RSTile(plane, x, y);
	}

	@Override
	public Point toScreen() {
		return tile.toScreen();
	}
}

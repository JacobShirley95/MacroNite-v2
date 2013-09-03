package org.macronite2.script.items;

import java.awt.Point;

import org.macronite2.script.ScriptContext;
import org.macronite2.script.location.RSLocatable;
import org.macronite2.script.map.RSTile;

public class RSGroundItem extends RSItem implements RSLocatable {
	public RSTile tile;
	
	public RSGroundItem(ScriptContext context, org.macronite2.hooks.RSObject rsObj, RSTile tile) {
		super(context, rsObj);
		this.tile = tile;
	}
	
	public RSGroundItem(ScriptContext context, org.macronite2.hooks.RSObject rsObj, int x, int y) {
		this(context, rsObj, new RSTile(context, context.getPlane(), x, y));
	}
	
	public RSGroundItem(ScriptContext context, org.macronite2.hooks.RSObject rsObj, int plane, int x, int y) {
		super(context, rsObj);
		this.tile = new RSTile(context, plane, x, y);
	}

	@Override
	public Point getCentrePoint() {
		return tile.getCentrePoint();
	}

	@Override
	public int getX() {
		return tile.x;
	}

	@Override
	public int getY() {
		return tile.y;
	}
}

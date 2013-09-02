package org.macronite2.script.entities;

import java.awt.Point;

import org.macronite2.hooks.Entity;
import org.macronite2.hooks.GameCoord;
import org.macronite2.hooks.Viewport;
import org.macronite2.script.map.RSCompass;
import org.macronite2.script.math.RSMath;
import org.macronite2.script.models.RSModel;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSScreenObject;

public abstract class RSEntity implements RSScreenObject{
	private Entity entity;
	
	public RSEntity(Entity entity) {
		this.entity = entity;
	}
	
	public abstract RSModel getModel();
	
	public Viewport getViewport() {
		return entity.getViewport();
	}
	
	@Override
	public void mouse(int button) {
		RSInput.mouse(getCentrePoint(), button);
	}
	
	public void click() {
		mouse(RSInput.MOUSE_LEFT);
	}
	
	public Point toMM() {
		GameCoord pos = entity.getWorldPos();
		Point p = RSMath.localToGlobal((int)pos.getX() >> 9, (int)pos.getZ() >> 9);
		return RSCompass.tileToMM(p.x, p.y);
	}
	
	public void clickMM() {
		RSInput.mouse(toMM(), RSInput.MOUSE_LEFT);
	}
}

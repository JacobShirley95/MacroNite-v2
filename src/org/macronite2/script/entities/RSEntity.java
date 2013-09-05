package org.macronite2.script.entities;

import java.awt.Point;

import org.macronite2.hooks.Entity;
import org.macronite2.hooks.GameCoord;
import org.macronite2.hooks.Viewport;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.models.RSModel;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSScreenObject;

public abstract class RSEntity implements RSScreenObject{
	private Entity entity;
	protected ScriptContext context;
	
	public RSEntity(ScriptContext context, Entity entity) {
		this.entity = entity;
		this.context = context;
	}
	
	public abstract RSModel getModel();
	
	public Viewport getViewport() {
		return entity.getViewport();
	}
	
	@Override
	public void mouse(int button) {
		context.input.mouse(getCentrePoint(), button);
	}
	
	public void click() {
		mouse(RSInput.MOUSE_LEFT);
	}
	
	public Point toMM() {
		GameCoord pos = entity.getWorldPos();
		Point p = context.locator.localToGlobal((int)pos.getX() >> 9, (int)pos.getZ() >> 9);
		return context.locator.tileToMM(p.x, p.y);
	}
	
	public void clickMM() {
		context.input.mouse(toMM(), RSInput.MOUSE_LEFT);
	}
}

package org.macronite2.script.entities;

import java.awt.Point;

import org.macronite2.hooks.DoorDecor;
import org.macronite2.hooks.Entity;
import org.macronite2.hooks.SModel;
import org.macronite2.hooks.WallDecor;
import org.macronite2.script.models.RSModel;

public class RSDoor extends RSEntity{
	private DoorDecor door;
	private int openID;
	
	public RSDoor(DoorDecor door, int openID) {
		super(door);
		this.door = door;
		this.openID = openID;
	}
	
	public boolean isOpen() {
		return getID() == openID;
	}
	
	public int getID() {
		return door.getID();
	}

	@Override
	public RSModel getModel() {
		return new RSModel((SModel) door.getModel(), getViewport());
	}

	@Override
	public Point getCentrePoint() {
		return getModel().getCentrePoint();
	}
}
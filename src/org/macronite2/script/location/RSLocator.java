package org.macronite2.script.location;

import java.awt.Point;

import org.macronite2.hooks.MapBase;
import org.macronite2.hooks.RSInterfaceGroup;
import org.macronite2.hooks.Renderer;
import org.macronite2.hooks.WidgetNode;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.components.RSComponent;
import org.macronite2.script.components.RSMinimapComponent;
import org.macronite2.script.entities.RSDoor;
import org.macronite2.script.entities.RSNPC;
import org.macronite2.script.entities.RSPlayer;
import org.macronite2.script.items.RSGroundItem;
import org.macronite2.script.items.RSInterfaceItem;
import org.macronite2.script.map.RSCompass;
import org.macronite2.script.util.node.NodeList;

public abstract class RSLocator {
	protected ScriptContext context;
	public RSLocator(ScriptContext context) {
		this.context = context;
	}
	
	private RSMinimapComponent compass;
	
	public abstract RSDoor findDoor(final int openID, int mode);
	public abstract RSNPC findNPC(String name);
	public abstract RSPlayer findPlayer(String name);
	public abstract RSGroundItem findGroundItem(int id);
	public abstract RSInterfaceItem findBackpackItem(int id);
	
	public RSComponent getWidget(int id) {
		for (RSInterfaceGroup group : context.runescape.getInterfaceGroups())
			if (group != null) {
				RSComponent comp = getComponent(id, group.getInterfaces(), null, -1);
				if (comp != null) {
					return comp;
				}
		}
		return null;
	}
	
	private RSComponent getComponent(int searchID, org.macronite2.hooks.RSInterface[] faces, RSComponent parent, int id) {
		for (int i = 0; i < faces.length; i++) {
			org.macronite2.hooks.RSInterface face = faces[i];
			if (face != null && id == face.getParentID()) {
				RSComponent intf = new RSComponent(context, parent, face);
				if (face.getIndex() == 0) {
					RSComponent intf2 = getComponent(searchID, faces,
							intf, face.getID());
	
					if (intf2 == null && face.getChildren() != null) {
						intf2 = getComponent(searchID, face.getChildren(), intf, face.getID());
					}
	
					NodeList nl = new NodeList(context.runescape.getWidgets());
					WidgetNode wn = (WidgetNode) nl.getNode(face.getID());
					if (intf2 == null && wn != null) {
						if (wn.getWidgetID() == searchID)
							return intf;
						org.macronite2.hooks.RSInterface[] faces2 = context.runescape.getInterfaceGroups()[wn
								.getWidgetID()].getInterfaces();
						intf2 = getComponent(searchID, faces2, intf, -1);
					}
	
					if (intf2 != null) {
						return intf2;
					}
				}
			}
		}
		return null;
	}
	
	protected void spiralSearch(SpiralPoint update, boolean exitOnFind) {
		spiralSearch(compass.getWidth()/2, update, exitOnFind);
	}
	
	protected void spiralSearch(int maxRadius, SpiralPoint update, boolean exitOnFind) {
		int curR = 1;
		int curDir = 0;
		
		Point mp = context.getMyPlayer().globalPos();
		while (curR < maxRadius) {
			for (int i = 0; i < curR; i++) {
				if (curDir == 0)
					mp.x++;
				else if (curDir == 1)
					mp.y++;
				else if (curDir == 2)
					mp.x--;
				else
					mp.y--;

				if (update.found(mp.x, mp.y) && exitOnFind)
					return;
			}
			curDir++;
			curDir = curDir % 4;
			if (curDir % 2 == 0)
				curR++;
		}
	}
	
	public Point tileToMM(int x, int y) {
		if (compass == null)
			compass = new RSMinimapComponent(context);
		
		int angle = context.compass.getCompassAngle();
		
		int angleX = RSCompass.SINE_ARRAY[angle];
		int angleY = RSCompass.COS_ARRAY[angle];
		
		MapBase base = context.getMapBase();
		
		int startX = context.runescape.getMyPlayer().getLocX1() << 9;
		int startY = context.runescape.getMyPlayer().getLocY1() << 9;
		
		int tileX = x - base.getX() << 9;
		int tileY = y - base.getY() << 9;
		
		int distX = tileX / 128 - startX / 128;
        int distY = tileY / 128 - startY / 128;
		
        int var13 = angleY * distX + angleX * distY >> 14;
        int var14 = distY * angleY - distX * angleX >> 14;
        
		Point comp = compass.getCentrePoint();
		
		return new Point(comp.x + var13, comp.y - var14);
	}
	
	public Point getScreenPosHidden(float x, float y, float z) {
		Renderer renderer = context.runescape.getCurrentRenderer();
		float[] out = new float[3];
		renderer.toScreen(x, y, z, out);
		return new Point((int)out[0], (int)out[1]);
	}
	
	public Point localToGlobal(int x, int y) {
		MapBase base = context.runescape.getMapBase();
		return new Point(base.getX()+x, base.getY()+y);
	}
	
	protected abstract class SpiralPoint {
		public abstract boolean found(int x, int y);
	}
}

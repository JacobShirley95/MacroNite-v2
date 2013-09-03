package org.macronite2.script.location;

import java.awt.Point;
import org.macronite2.hooks.RSInterfaceGroup;
import org.macronite2.hooks.WidgetNode;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.components.RSComponent;
import org.macronite2.script.entities.RSDoor;
import org.macronite2.script.entities.RSNPC;
import org.macronite2.script.entities.RSPlayer;
import org.macronite2.script.items.RSGroundItem;
import org.macronite2.script.items.RSInterfaceItem;
import org.macronite2.script.util.node.NodeList;

public abstract class RSLocator {
	protected ScriptContext context;
	public RSLocator(ScriptContext context) {
		this.context = context;
	}
	
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
	
	protected abstract class SpiralPoint {
		public abstract boolean found(int x, int y);
	}
}

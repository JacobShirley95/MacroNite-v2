package org.macronite2.script.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.macronite2.hooks.RSInterface;
import org.macronite2.hooks.WidgetNode;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSInterfaceObject;
import org.macronite2.script.screen.RSScreenObject;
import org.macronite2.script.util.node.NodeList;

public class RSComponent implements RSInterfaceObject, RSScreenObject{
	public int id;
	public int parentID;
	public int groupID;
	public int subID;

	public RSComponent parent;
	public List<RSComponent> children;
	
	public org.macronite2.hooks.RSInterface rsInterface;
	private ScriptContext context;

	public RSComponent(ScriptContext context, RSComponent parent, RSInterface rsInterface) {
		this.id = rsInterface.getID();
		this.parentID = rsInterface.getParentID();
		this.parent = parent;
		this.rsInterface = rsInterface;
		this.groupID = id >> 16;
		this.subID = id & 0xffff;
		this.children = getChildren();
		this.context = context;
	}
	
	public RSComponent(ScriptContext context, RSComponent component) {
		this.rsInterface = component.rsInterface;
		this.id = rsInterface.getID();
		this.parentID = rsInterface.getParentID();
		this.parent = component.parent;
		this.groupID = component.groupID;
		this.subID = component.subID;
		this.children = component.children;
		this.context = context;
	}
	
	public boolean isVisible() {
		boolean vis = !rsInterface.isHidden();
		if (vis && parent != null)
			vis = parent.isVisible();
		return vis;
	}
	
	public boolean isValid() {
		return rsInterface.getValidIndex() > -1;
	}
	
	@Override
	public int getX() {
		int x = rsInterface.getX() - rsInterface.getScrollX();
		if (parent != null)
			x += parent.getX();
		return x;
	}
	
	@Override
	public int getY() {
		int y = rsInterface.getY() - rsInterface.getScrollY();
		if (parent != null)
			y += parent.getY();
		return y;
	}
	
	@Override
	public int getWidth() {
		return rsInterface.getWidth();
	}
	
	@Override
	public int getHeight() {
		return rsInterface.getHeight();
	}
	
	public String getTitle() {
		return rsInterface.getTitle();
	}

	public String[] getActions() {
		return rsInterface.getActions();
	}

	public int getItemAmount() {
		return rsInterface.getItemAmount();
	}
	
	public int getItemID() {
		return rsInterface.getItemID();
	}

	public int getImageID() {
		return rsInterface.getImageID();
	}

	public int getOutlineColour() {
		return 255 - (rsInterface.getHoverColour() & 255) << 24 | rsInterface.getOutline() & -16777215;
	}
	
	@Override
	public Point getCentrePoint() {
		return new Point(getX()+getWidth()/2, getY()+getHeight()/2);
	}
	
	@Override
	public void mouse(int button) {
		context.input.mouse(getCentrePoint(), button);
	}
	
	public void leftClick() {
		mouse(RSInput.MOUSE_LEFT);
	}
	
	@Override
	public List<RSComponent> getChildren() {
		NodeList nl = new NodeList(context.runescape.getWidgets());
		WidgetNode wn = (WidgetNode)nl.getNode(id);
		if (wn != null) {
			return getChildren(context.runescape.getInterfaceGroups()[wn.getWidgetID()].getInterfaces(), -1);
		}
		return getChildren(context.runescape.getInterfaceGroups()[groupID].getInterfaces(), this.id);
	}
	
	private List<RSComponent> getChildren(org.macronite2.hooks.RSInterface[] faces, int id) {
		List<RSComponent> faces2 = new ArrayList<>();

		for (org.macronite2.hooks.RSInterface rsInt : faces) {
			if (rsInt != null && rsInt.getParentID() == id) {
				RSComponent face = new RSComponent(context, this, rsInt);
				faces2.add(face);
			}
		}
		
		if (rsInterface.getChildren() != null) {
			for (org.macronite2.hooks.RSInterface rsInt : rsInterface.getChildren()) {
				if (rsInt != null && rsInt.getParentID() == id) {
					RSComponent face = new RSComponent(context, this, rsInt);
					face.parent = this;
					faces2.add(face);
				}
			}
		}
		
		return faces2;
	}
	
	public RSComponent getChild(int id) {
		return getChild(children, id);
	}
	
	private RSComponent getChild(List<RSComponent> cur, int id) {
		for (RSComponent c : cur) {
			if ((c.id & 0xffff) == id) {
				return c;
			}
			RSComponent child = getChild(c.children, id);
			if (child != null)
				return child;
		}
		return null;
	}

	public static final int getInterfaceID(int group, int sub) {
		return (group << 16) | sub;
	}
}

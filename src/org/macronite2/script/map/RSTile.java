package org.macronite2.script.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.macronite2.hooks.GroundDataNode;
import org.macronite2.hooks.GroundObjectNode;
import org.macronite2.hooks.MapBase;
import org.macronite2.hooks.Node;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.items.RSGroundItem;
import org.macronite2.script.items.RSItem;
import org.macronite2.script.screen.RSScreenObject;
import org.macronite2.script.util.node.IterableNodeList;
import org.macronite2.script.util.node.NodeList;

public class RSTile implements RSScreenObject{
	public int plane;
	public int x;
	public int y;
	private ScriptContext context;
	
	public RSTile(ScriptContext context, Point p) {
		this(context, context.getPlane(), p);
	}
	
	public RSTile(ScriptContext context, int plane, Point p) {
		this(context, plane, p.x, p.y);
	}
	
	public RSTile(ScriptContext context, int plane, int x, int y) {
		this.plane = plane;
		this.x = x;
		this.y = y;
		this.context = context;
	}
	
	public RSTile(ScriptContext context, int x, int y) {
		this(context, new Point(x, y));
	}

	public RSItem[] getObjectsAtTile() {
		List<RSItem> objs = new ArrayList<RSItem>();
		
		NodeList gI = new NodeList(context.runescape.getGroundObjects());
		Node n = gI.getNode(plane << 28 | y << 14 | x);
		if (n != null) {
			GroundDataNode gdn = (GroundDataNode) n;
			IterableNodeList inl = new IterableNodeList(gdn.getObjects());
			for(GroundObjectNode go = (GroundObjectNode)inl.start(); null != go;) {
				RSItem obj = new RSGroundItem(context, context.runescape.getCacheObjectLoader().loadObject(go.getID()), this);
				objs.add(obj);
				go = (GroundObjectNode) inl.next();
			}
		}
		RSItem[] rsObjs = new RSItem[objs.size()];
		return objs.toArray(rsObjs);
	}

	public Point toScreen(int heightOffset) {
		MapBase base = context.getMapBase();
		int x = this.x - base.getX();
		int y = this.y - base.getY();
		return tileToScreen(x, y, heightOffset);
	}
	
	@Override
	public Point getCentrePoint() {
		return toScreen(0);
	}
	
	public Point toMM() {
		return context.compass.tileToMM(x, y);
	}
	
	private Point tileToScreen(int x, int y, int heightOffset) {
		int height = context.runescape.getTileHeight(x << 9, y << 9, plane);
		return context.math.getScreenPosHidden(x << 9, height-heightOffset, y << 9);
	}

	@Override
	public void mouse(int button) {
		context.input.mouse(getCentrePoint(), button);
	}
}

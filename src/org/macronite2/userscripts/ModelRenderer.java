package org.macronite2.userscripts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import org.macronite2.hooks.DoorDecor;
import org.macronite2.hooks.Player;
import org.macronite2.hooks.SModel;
import org.macronite2.hooks.Tile;
import org.macronite2.hooks.WorldObjects;
import org.macronite2.script.Script;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.models.RSModel;

public class ModelRenderer extends Script {
	public ModelRenderer(ScriptContext context) {
		super(context);
	}

	/*@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		WorldObjects objs = context.runescape.getWorldObjects();
		if (objs != null) {
			Player my = context.runescape.getMyPlayer();
			Tile t = objs.getTiles()[my.getPlane()][my.getLocX1() + 1][my
					.getLocY1()];
			if (t != null) {
				if (t.getWallDecor() != null) {
					DoorDecor dd = (DoorDecor) t.getWallDecor();
					
					g2D.drawPolygon(new RSModel(context, (SModel)dd.getModel(), dd.getViewport()).getPolygon());
				}
			}
		}
	}*/

	@Override
	public int run() {
		if (!isLoggedIn())
			return -1;
		
		context.input.typeKey(KeyEvent.VK_ENTER);
		context.input.typeKeys("hello");
		//context.input.typeKey(KeyEvent.VK_ENTER);
		
		return 0;
	}
}
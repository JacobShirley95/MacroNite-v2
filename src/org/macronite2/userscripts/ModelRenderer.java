package org.macronite2.userscripts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.lang.reflect.Field;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.DoorDecor;
import org.macronite2.hooks.GameCoord;
import org.macronite2.hooks.Player;
import org.macronite2.hooks.SModel;
import org.macronite2.hooks.Tile;
import org.macronite2.hooks.WorldObjects;
import org.macronite2.script.Script;
import org.macronite2.script.models.RSModel;
import org.macronite2.script.screen.RSLocator;
import org.macronite2.script.screen.RSUniLocator;

public class ModelRenderer extends Script {
	private Client runescape;

	public ModelRenderer(Client runescape) {
		super(runescape);
		this.runescape = runescape;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		WorldObjects objs = runescape.getEntityStack();
		if (objs != null) {
			Player my = runescape.getMyPlayer();
			Tile t = objs.getTiles()[my.getPlane()][my.getLocX1() + 1][my
					.getLocY1()];
			if (t != null) {
				if (t.getWallDecor() != null) {
					DoorDecor dd = (DoorDecor) t.getWallDecor();
					
					g2D.drawPolygon(new RSModel((SModel)dd.getModel(), dd.getViewport()).getPolygon());
				}
			}
		}
	}

	@Override
	public int run() {
		if (!isLoggedIn())
			return -1;
		
		return 0;
	}
}
package org.macronite2.script.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Field;

import org.macronite2.hooks.Client;
import org.macronite2.hooks.DoorDecor;
import org.macronite2.hooks.GameCoord;
import org.macronite2.hooks.Player;
import org.macronite2.hooks.SModel;
import org.macronite2.hooks.Tile;
import org.macronite2.hooks.WorldObjects;
import org.macronite2.script.Script;

public class ModelRenderer extends Script {
	private Client runescape;

	public ModelRenderer(Client runescape) {
		super(runescape);
		this.runescape = runescape;
	}

	private int xLast;
	private int yLast;

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
					SModel sm = (SModel) dd.getModel();
					// System.out.println(sm.getRenderX());
					// System.out.println("count: "+sm.getVertexCount()+", "+);
					// System.out.println("g: "+floats[0]);

					RenderData var7 = new RenderData(new float[16]);
					var7.setViewport(t.getWallDecor().getViewport());

					RenderData rd3 = new RenderData(sm.getRenderData()
							.getData());
					var7.transform(rd3);

					SModel model = (SModel) sm;

					int[] xcoords = model.getVerticesX();
					int[] ycoords = model.getVerticesY();
					int[] zcoords = model.getVerticesZ();
					
					int[] pointsx = new int[model.getVertexCount()];
					int[] pointsy = new int[model.getVertexCount()];

					for (int i = 0; i < model.getVertexCount(); i++) {
						int var26 = xcoords[i];
						int var27 = ycoords[i];
						int var28 = zcoords[i];

						float var29 = var7.data[2] * (float) var26
								+ var7.data[6] * (float) var27 + var7.data[10]
								* (float) var28 + var7.data[14];
						float var30 = var7.data[3] * (float) var26
								+ var7.data[7] * (float) var27 + var7.data[11]
								* (float) var28 + var7.data[15];
						if (var29 >= -var30) {
							float var31 = var7.data[0] * (float) var26
									+ var7.data[4] * (float) var27
									+ var7.data[8] * (float) var28
									+ var7.data[12];
							float var32 = var7.data[1] * (float) var26
									+ var7.data[5] * (float) var27
									+ var7.data[9] * (float) var28
									+ var7.data[13];

							float renderX = model.getRenderX();
							float renderY = model.getRenderY();
							float scaleX = model.getScaleX();
							float scaleY = model.getScaleY();

							float x = (float) ((int) (renderX + (scaleX * var31)
									/ var30));
							float y = (float) ((int) (renderY + (scaleY * var32)
									/ var30));

							pointsx[i] = (int) x;
							pointsy[i] = (int) y;
						} else {
						}
					}
					
					g2D.drawPolygon(pointsx, pointsy, model.getVertexCount());
				}
			}
		}
	}

	public void render() {
		/*
		 * 
		 */
	}

	@Override
	public int run() {
		// TODO Auto-generated method stub
		return 0;
	}
}
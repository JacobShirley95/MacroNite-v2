package org.macronite2.script.models;

import org.macronite2.hooks.Client;

public class ModelRenderer {
	private Client runescape;
	public ModelRenderer(Client runescape) {
		this.runescape = runescape;
	}
	
	public void render() {
		/*
		 * RenderData var7 = new RenderData(new float[16]);
								var7.setViewport(cl.getMyPlayer()
										.get3DTransform());

								RenderData rd3 = new RenderData(cl
										.getCameraRenderData().getData());

								RenderData rd2 = new RenderData(new float[16]);
								rd2.setViewport(cl.getCameraViewport());
								rd2.transform(rd3);

								var7.transform(rd2);

								CacheNode cn = (CacheNode) modelNode;
								SModel model = (SModel) cn.getCacheObject();

								int[] xcoords = model.getXCoords();
								int[] ycoords = model.getYCoords();
								int[] zcoords = model.getZCoords();

								int i = 0;

								int var26 = xcoords[i];
								int var27 = ycoords[i];
								int var28 = zcoords[i];

								float var29 = var7.data[2] * (float) var26
										+ var7.data[6] * (float) var27
										+ var7.data[10] * (float) var28
										+ var7.data[14];
								float var30 = var7.data[3] * (float) var26
										+ var7.data[7] * (float) var27
										+ var7.data[11] * (float) var28
										+ var7.data[15];
								if (var29 >= -var30) {
									float var31 = var7.data[0] * (float) var26
											+ var7.data[4] * (float) var27
											+ var7.data[8] * (float) var28
											+ var7.data[12];
									float var32 = var7.data[1] * (float) var26
											+ var7.data[5] * (float) var27
											+ var7.data[9] * (float) var28
											+ var7.data[13];

									float renderX = rend.getRenderX();
									float renderY = rend.getRenderY();
									float scaleX = rend.getScaleX();
									float scaleY = rend.getScaleY();

									float x = (float) ((int) (renderX + (scaleX * var31)
											/ var30));
									float y = (float) ((int) (renderY + (scaleY * var32)
											/ var30));

									int minX = -271;
									int minY = -169;

									int maxX = 271;
									int maxY = 169;

									//if (x > minX && x < maxX && y > minY && y < maxY) {

									System.out.println("x: " + renderX
											+ ", renderY: " + renderY);
									Player myPlayer = cl.getMyPlayer();
									System.out.println("xy: " +getScreenPosHidden(myPlayer.getLocX1()<<9, 0, myPlayer.getLocY1()<<9));
									//}
									//System.out.println("render: "+rend.getRenderData());
									//System.out.println("transform3D: "+cl.getMyPlayer().get3DTransform());
								} else {
									float z = -999999.0F;
									System.out.println("z: " + z);
								}
							}
		 */
	}
}
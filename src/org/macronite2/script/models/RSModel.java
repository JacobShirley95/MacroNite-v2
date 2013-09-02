package org.macronite2.script.models;

import java.awt.Point;
import java.awt.Polygon;

import org.macronite2.hooks.SModel;
import org.macronite2.hooks.Viewport;
import org.macronite2.script.screen.RSInput;
import org.macronite2.script.screen.RSScreenObject;

public class RSModel implements RSScreenObject{
	private SModel model;
	private Viewport viewport;

	public RSModel(SModel model, Viewport viewport) {
		this.model = model;
		this.viewport = viewport;
	}
	
	public Polygon getPolygon() {
		RenderData renderData = new RenderData(new float[16]);
		renderData.setViewport(viewport);

		RenderData rd3 = new RenderData(model.getRenderData()
				.getData());
		renderData.transform(rd3);

		int size = model.getVertexCount();
		
		int[] xcoords = model.getVerticesX();
		int[] ycoords = model.getVerticesY();
		int[] zcoords = model.getVerticesZ();
		
		int[] xes = new int[size];
		int[] yes = new int[size];

		for (int i = 0; i < size; i++) {
			int var26 = xcoords[i];
			int var27 = ycoords[i];
			int var28 = zcoords[i];

			float var29 = renderData.data[2] * (float) var26
					+ renderData.data[6] * (float) var27 + renderData.data[10]
					* (float) var28 + renderData.data[14];
			float var30 = renderData.data[3] * (float) var26
					+ renderData.data[7] * (float) var27 + renderData.data[11]
					* (float) var28 + renderData.data[15];
			if (var29 >= -var30) {
				float var31 = renderData.data[0] * (float) var26
						+ renderData.data[4] * (float) var27
						+ renderData.data[8] * (float) var28
						+ renderData.data[12];
				float var32 = renderData.data[1] * (float) var26
						+ renderData.data[5] * (float) var27
						+ renderData.data[9] * (float) var28
						+ renderData.data[13];

				float renderX = model.getRenderX();
				float renderY = model.getRenderY();
				float scaleX = model.getScaleX();
				float scaleY = model.getScaleY();

				float x = (float) ((int) (renderX + (scaleX * var31)
						/ var30));
				float y = (float) ((int) (renderY + (scaleY * var32)
						/ var30));

				xes[i] = (int)x;
				yes[i] = (int)y;
			} else {
				xes[i] = -999999;
			}
		}
		
		return new Polygon(xes, yes, size);
	}
	
	@Override
	public Point getCentrePoint() {
		Polygon polygon = getPolygon();
		return new Point(polygon.xpoints[polygon.npoints/2], polygon.ypoints[polygon.npoints/2]);
	}

	@Override
	public void mouse(int button) {
		RSInput.mouse(getCentrePoint(), button);
	}
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		
		int[] xcoords = model.getVerticesX();
		int[] ycoords = model.getVerticesY();
		int[] zcoords = model.getVerticesZ();

		for (int i = 0; i < model.getVertexCount(); i++) {
			int var26 = xcoords[i];
			int var27 = ycoords[i];
			int var28 = zcoords[i];

			buff.append(" ["+var26+", "+var27+", "+var28+"]");
		}
		return buff.toString().trim();
	}

	private class RenderData {
		public float[] data;

		public RenderData(float[] data) {
			this.data = data;
		}

		public void transform(RenderData var1) {
			float var2 = this.data[0] * var1.data[0] + this.data[1] * var1.data[4]
					+ this.data[2] * var1.data[8] + this.data[3] * var1.data[12];
			float var3 = this.data[0] * var1.data[1] + this.data[1] * var1.data[5]
					+ this.data[2] * var1.data[9] + this.data[3] * var1.data[13];
			float var4 = this.data[0] * var1.data[2] + this.data[1] * var1.data[6]
					+ this.data[2] * var1.data[10] + this.data[3] * var1.data[14];
			float var5 = this.data[0] * var1.data[3] + this.data[1] * var1.data[7]
					+ this.data[2] * var1.data[11] + this.data[3] * var1.data[15];
			float var6 = this.data[4] * var1.data[0] + this.data[5] * var1.data[4]
					+ this.data[6] * var1.data[8] + this.data[7] * var1.data[12];
			float var7 = this.data[4] * var1.data[1] + this.data[5] * var1.data[5]
					+ this.data[6] * var1.data[9] + this.data[7] * var1.data[13];
			float var8 = this.data[4] * var1.data[2] + this.data[5] * var1.data[6]
					+ this.data[6] * var1.data[10] + this.data[7] * var1.data[14];
			float var9 = this.data[4] * var1.data[3] + this.data[5] * var1.data[7]
					+ this.data[6] * var1.data[11] + this.data[7] * var1.data[15];
			float var10 = this.data[8] * var1.data[0] + this.data[9] * var1.data[4]
					+ this.data[10] * var1.data[8] + this.data[11] * var1.data[12];
			float var11 = this.data[8] * var1.data[1] + this.data[9] * var1.data[5]
					+ this.data[10] * var1.data[9] + this.data[11] * var1.data[13];
			float var12 = this.data[8] * var1.data[2] + this.data[9] * var1.data[6]
					+ this.data[10] * var1.data[10] + this.data[11] * var1.data[14];
			float var13 = this.data[8] * var1.data[3] + this.data[9] * var1.data[7]
					+ this.data[10] * var1.data[11] + this.data[11] * var1.data[15];
			float var14 = this.data[12] * var1.data[0] + this.data[13]
					* var1.data[4] + this.data[14] * var1.data[8] + this.data[15]
					* var1.data[12];
			float var15 = this.data[12] * var1.data[1] + this.data[13]
					* var1.data[5] + this.data[14] * var1.data[9] + this.data[15]
					* var1.data[13];
			float var16 = this.data[12] * var1.data[2] + this.data[13]
					* var1.data[6] + this.data[14] * var1.data[10] + this.data[15]
					* var1.data[14];
			float var17 = this.data[12] * var1.data[3] + this.data[13]
					* var1.data[7] + this.data[14] * var1.data[11] + this.data[15]
					* var1.data[15];
			this.data[0] = var2;
			this.data[1] = var3;
			this.data[2] = var4;
			this.data[3] = var5;
			this.data[4] = var6;
			this.data[5] = var7;
			this.data[6] = var8;
			this.data[7] = var9;
			this.data[8] = var10;
			this.data[9] = var11;
			this.data[10] = var12;
			this.data[11] = var13;
			this.data[12] = var14;
			this.data[13] = var15;
			this.data[14] = var16;
			this.data[15] = var17;
		}

		public void setViewport(Viewport var1) {
			this.data[0] = var1.getFloat1();
			this.data[1] = var1.getFloat5();
			this.data[2] = var1.getFloat9();
			this.data[3] = 0.0F;
			this.data[4] = var1.getFloat2();
			this.data[5] = var1.getFloat6();
			this.data[6] = var1.getFloat10();
			this.data[7] = 0.0F;
			this.data[8] = var1.getFloat3();
			this.data[9] = var1.getFloat7();
			this.data[10] = var1.getFloat11();
			this.data[11] = 0.0F;
			this.data[12] = var1.getFloat4();
			this.data[13] = var1.getFloat8();
			this.data[14] = var1.getFloat12();
			this.data[15] = 1.0F;
		}
	}

}
package org.macronite2.script.models;

import org.macronite2.hooks.Transform3D;

public class RenderData {
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

	public void setViewport(Transform3D var1) {
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

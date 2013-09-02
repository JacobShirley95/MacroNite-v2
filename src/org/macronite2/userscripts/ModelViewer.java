package org.macronite2.userscripts;

import java.awt.Graphics;

import javax.swing.JFrame;

public class ModelViewer extends JFrame{
	
	private float[] renderData;
	
	public void setRenderData(float[] arr) {
		renderData = arr;
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (renderData == null)
			return;
		
	}
}

package org.macronite2.rsapplet;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import org.macronite2.script.listeners.RSPaintListener;

public class Rs2Canvas extends Canvas implements MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private BufferedImage gameImage;
	private List<RSPaintListener> listeners = new LinkedList<>();
	
	public static Rs2Canvas instance;
	public static List<Rs2CanvasListener> canvasListeners = new LinkedList<>();
	
	public static int MOUSE_X = 0;
	public static int MOUSE_Y = 0;
	
	public Rs2Canvas() {	
		addMouseMotionListener(this);
		gameImage = new BufferedImage(1024, 800, BufferedImage.TYPE_INT_RGB);
		/*addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				int width = e.getComponent().getWidth();
				int height = e.getComponent().getHeight();

				gameImage = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
			}
		});*/
		for (Rs2CanvasListener listener : canvasListeners)
			listener.onInitialise(this);
		instance = this;
	}
	
	public void addPaintListener(RSPaintListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(RSPaintListener listener) {
		listeners.remove(listener);
	}
	
    @Override
    public Graphics getGraphics() {
      Graphics g = super.getGraphics();
      Graphics g2 = gameImage.getGraphics();
      
      for (RSPaintListener rpl : listeners)
    	  rpl.paint(g2);
      
      g.drawImage(gameImage,0,0,this);
      return g2;
    }

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		MOUSE_X = arg0.getX();
		MOUSE_Y = arg0.getY();
	}
}

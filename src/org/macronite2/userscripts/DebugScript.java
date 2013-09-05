package org.macronite2.userscripts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.macronite2.hooks.RSInterface;
import org.macronite2.hooks.RSInterfaceGroup;
import org.macronite2.hooks.WidgetNode;
import org.macronite2.script.Script;
import org.macronite2.script.ScriptContext;
import org.macronite2.script.components.RSBank;
import org.macronite2.script.components.RSComponent;
import org.macronite2.script.util.node.NodeList;

public class DebugScript extends Script {

	private JTextField jtf;

	public DebugScript(ScriptContext context) {
		super(context);

		JFrame frame = new JFrame("Interface Debug v0.1");
		jtf = new JTextField("");
		frame.add(jtf);
		//frame.setVisible(true);
		frame.setSize(200, 80);
	}

	// 1477, 14 == backpack

	private static RSComponent[] widgets = null;
	private int c = 0;
	@Override
	public void paint(Graphics g) {
		if (!isLoggedIn())
			return;

		Graphics2D g2D = (Graphics2D) g;

		g2D.setColor(Color.GREEN);
		g2D.setStroke(new BasicStroke(1f));

		RSInterfaceGroup[] groups = context.runescape.getInterfaceGroups();
		if (groups != null) {
			RSInterfaceGroup group = groups[1477];
			if (widgets == null) {
				widgets = new RSComponent[groups.length];
				for (int i = 0; i < groups.length; i++)
					if (groups[i] != null) {
						widgets[i] = context.locator.getWidget(i);
						
					}
			}
			//}
			// System.out.println(group);
			//displayInterfaces(1477, g2D, group.getInterfaces(), -1, 0, 0, false, "");
			//System.out.println("DSFS");
			/*for (RSComponent comp : widgets) {
				if (comp != null && Rs2Canvas.MOUSE_X > comp.getX()
						&& Rs2Canvas.MOUSE_X+20 < comp.getX() + comp.getWidth()
						&& Rs2Canvas.MOUSE_Y-50 > comp.getY()
						&& Rs2Canvas.MOUSE_Y < comp.getY() + comp.getHeight()) {
					g2D.drawRect(comp.getX(), comp.getY(), comp.getWidth(),
							comp.getHeight());
					Point p = comp.toScreen();
					g2D.setColor(Color.RED);
					g2D.drawString("ID: " + (c), p.x - 50, p.y);
					g2D.setColor(Color.GREEN);
				}
				c++;
			}*/
			
			try {
				//.getChild(137)
				//if (widgets[762] == null) {
					widgets[762] = context.locator.getWidget(762);
					if (widgets[762] == null)
						return;
				//}
				c = 0;
				/*RSComponent comp = widgets[762].getChild(113);
				g2D.drawRect(comp.getX(), comp.getY(), comp.getWidth(),
						comp.getHeight());
				System.out.println(comp.children.size());
				for (RSComponent comp2 : comp.children) {
					if (comp2 != null) {
						int i2 = comp2.getItemID();
						if (i2 > 0) {
							g2D.drawRect(comp2.getX(), comp2.getY(), comp2.getWidth(),
									comp2.getHeight());
							System.out.println(c+++": "+comp2.getItemID());
						}
						
					} else {

					}
				}*/
				
				//RSAbilityBar bar = new RSAbilityBar();
				//System.out.println(bar.getSlot(0).isReady());
				//c++;
				//sleep(1000);
				/*int c = 0;
				Field[] fields = comp2.rsInterface.getClass().getDeclaredFields();
				for (Field f : fields) {
					if (!Modifier.isStatic(f.getModifiers()) && f.getType().equals(int.class)) {
						if (c > 30)
							break;
						f.setAccessible(true);
						//System.out.println(c+++": "+f.get(comp2.rsInterface));
					}
				}*/
			//Field f = RuneScape.getClient().getClass().getDeclaredField("pv");
			//.setAccessible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//displayInterfaces(g2D, );
			
			//displayInterfaces(1477, g2D, group.getInterfaces(), -1, 0, 0, false, "");
		}
	}

	private void displayInterfaces(Graphics2D g2D, RSComponent comp) {
		int c = 0;
		for (RSComponent comp2 : comp.getChild(77).children) {
			g2D.drawRect(comp2.getX(), comp2.getY(), comp2.getWidth(),
					comp2.getHeight());

			if (comp2.groupID != 1477) {
				Point p = comp2.getCentrePoint();
				g2D.setColor(Color.RED);
				g2D.drawString("ID: " + comp2.getTitle(), p.x - 50, p.y);
				g2D.setColor(Color.GREEN);
			}
			c++;
			//displayInterfaces(g2D, comp2);
		}
	}

	private org.macronite2.script.components.RSComponent test = null;

	private void displayInterfaces(int group, Graphics2D g2D,
			RSInterface[] faces, int id, int x2, int y2, boolean isChild,
			String path) {
		try {
		String path2 = path;
		for (int i = 0; i < faces.length; i++) {
			RSInterface face = faces[i];
			if (face != null && id == face.getParentID()) {
				path2 += ", " + i;
				int x = x2 + face.getX();
				int y = y2 + face.getY();

				if (face.getIndex() == 0) {
					displayInterfaces(group, g2D, faces, face.getID(),
							x - face.getScrollX(), y - face.getScrollY(),
							false, path2);
					if (face.getChildren() != null)
						displayInterfaces(group, g2D, face.getChildren(),
								face.getID(), x - face.getScrollX(),
								y - face.getScrollY(), true, path2);

					NodeList nl = new NodeList(context.runescape.getWidgets());

					WidgetNode wn = (WidgetNode) nl.getNode(face.getID());
					if (wn != null
							&& context.runescape.getValidInterfaces()[wn
									.getWidgetID()]) {
						RSInterface[] faces2 = context.runescape.getInterfaceGroups()[wn.getWidgetID()]
								.getInterfaces();
						displayInterfaces(group, g2D, faces2, -1,
								x - face.getScrollX(), y - face.getScrollY(),
								true, path2);
					}
				}
				// 96010247
				// &&
				// face.getTitle().equalsIgnoreCase(jtf.getText().toLowerCase())
				// Rs2Canvas.MOUSE_X > x && Rs2Canvas.MOUSE_X <
				// x+face.getWidth() && Rs2Canvas.MOUSE_Y > y &&
				// Rs2Canvas.MOUSE_Y < y+face.getHeight() &&
				/* && Rs2Canvas.MOUSE_X > x
				&& Rs2Canvas.MOUSE_X < x + face.getWidth()
				&& Rs2Canvas.MOUSE_Y > y
				&& Rs2Canvas.MOUSE_Y < y + face.getHeight()*/
				/*
				 *  && Rs2Canvas.MOUSE_X > x
						&& Rs2Canvas.MOUSE_X < x + face.getWidth()
						&& Rs2Canvas.MOUSE_Y > y
						&& Rs2Canvas.MOUSE_Y < y + face.getHeight()
				 */
				if (face.getItemID() > 0) {
					g2D.setColor(Color.GREEN);
					g2D.drawRect(x, y, face.getWidth(), face.getHeight());
					g2D.setColor(Color.RED);
					g2D.drawString("ID: " + (face.getID() & 0xffff),
							x + (face.getWidth() / 2) - 50,
							y + (face.getHeight() / 2));

					g2D.setColor(Color.GREEN);
					path2 = "";
					// System.out.println(face.getID());
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int run() {
		if (!isLoggedIn())
			return 10;
		
		RSBank bank = new RSBank(context);
		if (bank.isOpen()) {
			context.input.type(KeyEvent.VK_ENTER);
			context.input.type("My bank is open!");
			context.input.type(KeyEvent.VK_ENTER);
		}
		
		return EXIT_CODE;
	}
}

package org.macronite2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.macronite2.hooks.Client;
import org.macronite2.rsapplet.Rs2Applet;
import org.macronite2.rsapplet.Rs2Canvas;
import org.macronite2.rsapplet.Rs2CanvasListener;
import org.macronite2.rsapplet.Rs2ClassLoaderListener;
import org.macronite2.script.RuneScape;
import org.macronite2.script.Script;
import org.macronite2.script.map.RSCompass;
import org.macronite2.script.math.RSMath;
import org.macronite2.script.screen.RSInput;
import org.macronite2.userscripts.AlWarriorKiller;
import org.macronite2.userscripts.DebugScript;

public class Main implements Rs2ClassLoaderListener, Rs2CanvasListener, Runnable, MouseListener {

	public List<Class<?>> loadedClasses = new ArrayList<>();
	private DefaultMutableTreeNode rootNode = null;
	private final Rs2Applet applet = new Rs2Applet();

	public static final String readField = "Class521.aClass563_Sub1_Sub4_Sub4_Sub1_9252";
	public static final int encodeInt = 1;
	public JTextField jtf = new JTextField(readField);
	public JTextField encodeField = new JTextField("" + encodeInt);
	public Main() {
		Rs2Canvas.canvasListeners.add(this);
		System.out.println((int) '\uffff');

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Rs2Applet v0.1");

		applet.classLoader.addEventListener(this);
		JPanel panel = new JPanel();
		panel.add(applet);
		
		frame.add(panel);
		try {
			applet.init();
			applet.start();
			applet.setVisible(true);
		} catch (AbstractMethodError ame) {
			System.out.println("error");
			System.exit(1);
		}
		frame.setSize(1024, 800);

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				stopT = true;
				System.out.println("Terminated");
				applet.stop();
				System.exit(1);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		JFrame debugFrame = new JFrame("Rs2Applet debug v0.1");
		/*rootNode = new DefaultMutableTreeNode("Rs classes");
		treeModel = new DefaultTreeModel(rootNode);

		final JTree jt = new JTree(treeModel);
		JScrollPane treeView = new JScrollPane(jt);
		*/

		output = new JTextArea();
		output.setRows(20);
		JScrollPane jsp = new JScrollPane(output);

		BorderLayout bl = new BorderLayout();
		BorderLayout bl2 = new BorderLayout();
		bl2.addLayoutComponent(jtf, BorderLayout.NORTH);
		bl2.addLayoutComponent(encodeField, BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.add(jtf);
		jp.add(encodeField);
		jp.setLayout(bl2);
		bl.addLayoutComponent(jp, BorderLayout.NORTH);
		bl.addLayoutComponent(jsp, BorderLayout.CENTER);
		debugFrame.add(jp);
		debugFrame.getContentPane().setLayout(bl);
		debugFrame.add(jsp);
		//debugFrame.setVisible(true);
		debugFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		debugFrame.setSize(300, 300);
		debugFrame.setAlwaysOnTop(true);
		//debugFrame.pack();
	}

	JTextArea output;

	public static void main(String... args) {
		new Main();
	}

	@Override
	public void onLoadClass(Class<?> classF) {
		if (classF.getPackage() != null) {
			return;
		}
		//System.out.println("Loaded class "+classF.getName());
		//if (classF.getName().equals("Class8"))
		//	System.out.println(classF);

		loadedClasses.add(classF);
	}
	
	private Rs2Canvas canvas;
	private Thread t = null;
	@Override
	public void onInitialise(Rs2Canvas rs2Canvas) {
		this.canvas = rs2Canvas;
		sc = new DebugScript(applet.client);
		canvas.addPaintListener(sc);
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public boolean stopT = false;
	Script sc = null;
	private JTextField searchField;
	@Override
	public void run() {
		Client cl = applet.client;
		RuneScape.setClient(cl);
		
		JFrame frame = new JFrame("Value searcher");
		frame.setSize(300, 500);
		
		output = new JTextArea();
		output.setRows(20);
		JScrollPane resultsPane = new JScrollPane(output);
		
		JPanel searchPanel = new JPanel();
		((FlowLayout)searchPanel.getLayout()).setAlignment(FlowLayout.LEADING);
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(100, 20));
		JButton search = new JButton("Search");
		search.addMouseListener(this);
		search.setPreferredSize(new Dimension(100, 20));
		
		searchPanel.add(searchField);
		searchPanel.add(search);
		
		BorderLayout layout = new BorderLayout();
		layout.addLayoutComponent(searchPanel, BorderLayout.NORTH);
		layout.addLayoutComponent(resultsPane, BorderLayout.CENTER);
		frame.getContentPane().setLayout(layout);
		frame.add(searchPanel);
		frame.add(resultsPane);
		frame.setVisible(true);
	
		try {
			while (!stopT) {//"Class286.aClass162_4824.aClass358_10175.aFloatArray_6781
				sc.run();
				
				//762 = bank
				//596 = login stuff
				/*if (cl.getMyPlayer() != null && cl.getMyPlayer().getComposite() != null) {
					Point p = RSMath.localToGlobal(cl.getMyPlayer().getLocX1()+5, cl.getMyPlayer().getLocY1()+5);
					Point p2 = RSCompass.tileToMM(p.x, p.y);
					
					RSInput.clickMouse(p2.x, p2.y, MouseEvent.BUTTON1);
				}*/
				//getValue("pn.ic.q");
				/*Object o = getValue("afn.ni");//getValue("afn.ni.bx");
				int op = -752250733;
				if (o != null) {
					Field[] fields = o.getClass().getDeclaredFields();
					for (int i = 0; i < Math.min(fields.length, 1000); i++) {
						Field f = fields[i];
						if (Modifier.isStatic(f.getModifiers()))
							continue;
						//if (f.getType().equals(int.class)) {
							f.setAccessible(true);
							Object o2 = f.get(o);
							//((int)o2 * op)
							System.out.println(i+": "+o2);
						//}
					}
					//System.out.println(o);
				}
				/*int op = 1;//111228289;
				if (o != null) {
					Method[] methods = o.getClass().getSuperclass().getSuperclass().getDeclaredMethods();
					int i = 0;
					for (Method m : methods) {
						if (Modifier.isStatic(m.getModifiers()))
							continue;
						
						m.setAccessible(true);
						//m.getReturnType().equals(int.class) && 
						if (m.getReturnType().equals(int.class) && m.getParameterTypes().length == 1) {
							Class<?> cls = m.getParameterTypes()[0];
							Object o2 = null;
							if (cls.equals(int.class))
								o2 = m.invoke(o, new Object[] {10});
							else if (cls.equals(byte.class))
								o2 = m.invoke(o, new Object[] {(byte)10});
							else if (cls.equals(short.class))
								o2 = m.invoke(o, new Object[] {(short)10});
							System.out.println(i++ +": "+o2);
					/*Method m = o.getClass().getDeclaredMethod("cb", new Class[] {byte.class});
					m.setAccessible(true);
					o = m.invoke(o, new Object[] {(byte)10});
					if (o instanceof Integer) 
						System.out.println((int)o * op);
					else if (o instanceof int[])
						for (int i = 0; i < Array.getLength(o); i++)
							System.out.println(i+": "+Array.get(o, i));
					else
						System.out.println(o);
							//System.out.println(i++ +": "+o2);
						}
					}
				}*/
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Object getValue(String t) {
		String[] split = t.split("\\.");
		Object o = null;
		String curClass = split[0];
		try {
			for (int i = 1; i < split.length; i++) {
				Class<?> cls = applet.classLoader.loadClass(curClass);
				Field f = null;
				//try {
				f = findField(cls, split[i]);
				/*} catch (NoSuchFieldException nsfe) {
					cls = applet.classLoader.loadClass("Class162_Sub1");
					f = cls.getDeclaredField(split[i]);
				}*/
				f.setAccessible(true);
				o = f.get(o);
				if (o == null)
					return null;
				curClass = f.getType().getName();
			}
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return "error in class " + curClass;
		}
	}

	private Field findField(Class cls, String name) {
		if (cls == null)
			return null;

		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			if (name.equals("n") && f.getName().equals("n") && !f.getType().getName().equals("ahb"))
				continue;
			
			if (f.getName().equals(name))
				return f;
		}
		Field f = findField(cls.getSuperclass(), name);

		return f;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		/*
		System.out.println("search");
		Object searchObject = (Object)searchField.getText();
		for (Class<?> clazz : loadedClasses) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				if (Modifier.isStatic(f.getModifiers())) {
					f.setAccessible(true);
					List<Object> results = new ArrayList<>();
					search(searchObject, f.get
							(null), results);
				}
			}
		}//*/
	}
	
	private void search(Object searchObj, Object parent, List<Object> results) throws IllegalArgumentException, IllegalAccessException {
		if (parent.equals(searchObj))
			results.add(parent);
		else if (parent.getClass().isArray()) {
			for (int i = 0; i < Array.getLength(parent); i++) {
				Object o = Array.get(parent, i);
				if (o.equals(searchObj))
					results.add(o);
				if (!o.getClass().isPrimitive() && !(o instanceof String))
					search(searchObj, o, results);
			}
		} else {
			Field[] fields = parent.getClass().getDeclaredFields();
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					f.setAccessible(true);
					Object o = f.get(parent);
					if (o.equals(searchObj))
						results.add(o);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

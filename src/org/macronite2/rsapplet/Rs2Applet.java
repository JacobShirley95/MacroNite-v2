package org.macronite2.rsapplet;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.macronite2.bytecode.ASMClassLoader;
import org.macronite2.hooks.Client;

public class Rs2Applet extends Applet {

	public static final String USER_AGENT; //default for an (old) firefox version is set below
	static {
		String osname = System.getProperty("os.name");
		String windowing = "X11";
		if (osname.contains("Windows"))
			windowing = "Windows";
		else if (osname.contains("Mac"))
			windowing = "Macintosh";
		USER_AGENT = "Mozilla/5.0 (" + windowing + "; U; " + osname + " "
				+ System.getProperty("os.version") + "; "
				+ Locale.getDefault().getLanguage() + "-"
				+ Locale.getDefault().getCountry()
				+ "; rv:1.9.0.10) Gecko/2009042316 Firefox/3.0.10";
	}
	private final String codeRegex = "code\\=([^ ]*) ";
	private final String widthRegex = "width\\=([^ ]*) ";
	private final String heightRegex = "height\\=([^ ]*) ";
	private final String archiveRegex = "archive\\=([^ ]*) ";

	private Class<?> clientClass;
	private Object clientInstance;
	public Rs2ClassLoader classLoader;
	private ClassLoader clazzLoader;
	public Client client;

	public Rs2Applet() {
		try {
			classLoader = new Rs2ClassLoader(
					new File(
							"C:/Users/Jake/workspace/MacroUpdater2/runescape-deob3.jar")
							.toURI().toURL());

			clazzLoader = new ASMClassLoader(classLoader);
			clientClass = clazzLoader.loadClass("client");
			Constructor clientConstructor = this.clientClass
					.getConstructor((Class[]) null);
			clientInstance = clientConstructor.newInstance(null);

			client = (Client) clientInstance;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String jsInfoPage = downloadHTML("http://world24a.runescape.com/game.js");

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("width", parseArg(search(jsInfoPage, widthRegex, 1)));
		paramMap.put("height", parseArg(search(jsInfoPage, heightRegex, 1)));

		Matcher matcher = Pattern.compile(
				"<param name\\=([^ ]*) value\\=([^>]*)>").matcher(jsInfoPage);
		while (matcher.find()) {
			paramMap.put(parseArg(matcher.group(1)), parseArg(matcher.group(2)));
		}

		System.out.println(paramMap.get("0"));

		String root = "http://world24a.runescape.com/game.js?j=1";
		Rs2AppletStub stub = new Rs2AppletStub(root
				+ parseArg(search(jsInfoPage, archiveRegex, 1)), root, paramMap);
		setStub(stub);
		setPreferredSize(new Dimension(765, 553));
	}

	private static String search(String what, String regex, int group) {
		Matcher matcher = Pattern.compile(regex).matcher(what);
		matcher.find();
		return matcher.group(group);
	}

	private String parseArg(String arg) {
		return Pattern.matches("\".*\"", arg) ? arg.substring(1,
				arg.length() - 1) : arg;
	}

	private String downloadHTML(String address) {
		try {
			URL url = new URL(address);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			//Firefox didn't set anything important that java didn't set by default, besides the useragent
			//this useragent is for your browser, modify at will
			conn.addRequestProperty("User-Agent", USER_AGENT);
			conn.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				builder.append(inputLine).append("\n");
			}
			in.close();
			return builder.toString();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public final void destroy() {
		if (this.clientInstance != null) {
			this.invokeMethod("destroy", (Object[]) null, (Class[]) null);
		}
	}

	@Override
	public final void stop() {
		if (this.clientInstance != null) {
			this.invokeMethod("stop", (Object[]) null, (Class[]) null);
		}
	}

	@Override
	public final void start() {
		if (this.clientInstance != null) {
			this.invokeMethod("start", (Object[]) null, (Class[]) null);
		}
	}

	@Override
	public final void update(Graphics var1) {
		if (this.clientInstance != null) {
			this.invokeMethod("update", new Object[] { var1 },
					new Class[] { Graphics.class });
		}
	}

	@Override
	public void init() {
		this.invokeMethod("supplyApplet", new Object[] { this },
				new Class[] { Applet.class });
		this.invokeMethod("init", (Object[]) null, (Class[]) null);
	}

	private final void invokeMethod(String methodName, Object[] methodArgs,
			Class[] argTypes) {
		try {
			Method method = this.clientClass.getMethod(methodName, argTypes);
			method.invoke(this.clientInstance, methodArgs);
		} catch (NoSuchMethodException var6) {
			var6.printStackTrace();
		} catch (IllegalAccessException var7) {
			var7.printStackTrace();
		} catch (InvocationTargetException var8) {
			var8.printStackTrace();
		}
	}
}

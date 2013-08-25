package org.macronite2.bytecode;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.macronite2.bytecode.utils.InsnSearcher;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ASMClassLoader extends ClassLoader{
	public ASMClassLoader(ClassLoader cl) {
		super(cl);
	}
	
	public static final HashMap<String, Long> multMap = new HashMap<>();
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (!name.contains("org.macronite2")) {
			try {
				Class<?> clazz = findSystemClass(name);
				return clazz;
			} catch (ClassNotFoundException cnfe) {}
			
			InputStream stream = getResourceAsStream(name.replace(".", "/")+".class");
			if (stream != null) {
				try {
					ClassReader cr = new ClassReader(stream);
					ClassNode cn = new ClassNode();
					cr.accept(cn, 0);
			
					for (Object o : cn.methods) {
						MethodNode mn = (MethodNode) o;
						if (mn.instructions.size() > 0) {
							InsnSearcher finder = new InsnSearcher(mn);
							//InsnList insns = mn.instructions;
							
							List<AbstractInsnNode[]> results = finder.search("ldc (getfield|getstatic)+ imul");
							for (AbstractInsnNode[] result : results) {
								FieldInsnNode fin = (FieldInsnNode) result[1];
								multMap.put(fin.owner+"."+fin.name+fin.desc, Long.getLong(((LdcInsnNode)result[0]).cst.toString()));
							}
							finder.reload();
							
							results = finder.search("(getfield|getstatic)+ ldc imul");
							for (AbstractInsnNode[] result : results) {
								FieldInsnNode fin = (FieldInsnNode) result[0];
								multMap.put(fin.owner+"."+fin.name+fin.desc, Long.getLong(((LdcInsnNode)result[1]).cst.toString()));
							}
						}
					}
					
					ClassWriter cw = new ClassWriter(Opcodes.ASM5);
					cn.accept(cw);

					byte[] bytes = cw.toByteArray();
					Class<?> clazz = defineClass(null, bytes, 0, bytes.length, getClass().getProtectionDomain());
					return clazz;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return super.loadClass(name);
	}
}

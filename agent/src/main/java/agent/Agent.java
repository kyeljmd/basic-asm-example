package agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
                if ("other/Stuff".equals(s)) {
                    // ASM Code
                    ClassReader reader = new ClassReader(bytes);
                    ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                    ClassVisitor adapter = new ClassPrinter(writer);
                    /**
                     *  Use this one to deleted the run method from stuff
                     * ClassVisitor adapter = new MethodEraser(writer);
                     *
                     */
                    reader.accept(adapter, 0);
                    return writer.toByteArray();
                }
                return null;
            }
        });
    }

}


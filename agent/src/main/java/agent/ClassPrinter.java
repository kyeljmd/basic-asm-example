package agent;

import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.*;
import java.lang.reflect.Method;

public class ClassPrinter extends ClassVisitor {

    public ClassPrinter(ClassWriter writer) {
        super(Opcodes.ASM5, writer);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if(name.equals("run")) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            return mv == null ? null : new MethodAdapter(mv);
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
        super.visitEnd();
    }
}
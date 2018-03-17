package agent;


import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;

import static org.objectweb.asm.Opcodes.*;

import java.io.PrintWriter;
import java.lang.reflect.Method;

public class ClassPrinter extends ClassVisitor {


    PrintWriter pw = new PrintWriter(System.out);
    TraceClassVisitor tracer;
    public ClassPrinter(ClassWriter writer) {
        super(Opcodes.ASM5, writer);
        tracer = new TraceClassVisitor(this.cv, pw);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(String source, String debug){

        super.visitSource(source,debug);
        //Create a method
        MethodVisitor mv =  super.visitMethod(
                Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "run2", "()V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Method Generated By Bytecode run2()!");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V",false);
        mv.visitInsn(Opcodes.RETURN);

        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if(name.equals("run")) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            mv = new CodeInjectorAdapter(Opcodes.ASM4,mv,access,name,desc);
           return mv;
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
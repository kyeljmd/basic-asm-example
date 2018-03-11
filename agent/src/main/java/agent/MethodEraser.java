package agent;

import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.*;
import java.lang.reflect.Method;

/**
 * Created by kyel on 3/11/2018.
 */
public class MethodEraser extends ClassVisitor{

    public MethodEraser(ClassWriter writer) {
        super(Opcodes.ASM5, writer);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        //Delete the method with the the name run that accepts no arguments, and has a void return type
        if(name.equals("run") && desc.equals("()V")){
            return null;
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
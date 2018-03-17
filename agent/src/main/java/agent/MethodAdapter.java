package agent;

import org.objectweb.asm.ClassVisitor;

/**
 * Created by kyel on 3/11/2018.
 */

import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.*;
import java.lang.reflect.Method;

class MethodAdapter extends MethodVisitor implements Opcodes {

    public MethodAdapter(final MethodVisitor mv) {
        super(ASM5, mv);
    }

    @Override
    public  void visitCode(){
        //Execute the method before coding through the lines of the code
        mv.visitMethodInsn(Opcodes.INVOKESTATIC,"other/Stuff","run2","()V",false);
        super.visitCode();
    }
}
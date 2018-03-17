package agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Created by kyel on 3/17/2018.
 */
public class CodeInjectorAdapter extends AdviceAdapter {

    protected CodeInjectorAdapter(int i, MethodVisitor methodVisitor, int i1, String s, String s1) {
        super(i, methodVisitor, i1, s, s1);
    }


    @Override
    protected  void onMethodEnter(){
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Injected in the first line of code of the method!!");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V",false);
        super.onMethodEnter();
    }


    @Override
    protected  void onMethodExit(int opcode){
        mv.visitMethodInsn(Opcodes.INVOKESTATIC,"other/Stuff","run2","()V",false);
        super.onMethodExit(opcode);
    }

}

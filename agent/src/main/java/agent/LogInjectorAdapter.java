package agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Created by kyel on 3/17/2018.
 */
public class LogInjectorAdapter extends AdviceAdapter {

    private String method;

    protected LogInjectorAdapter(int i, MethodVisitor methodVisitor, int i1, String s, String s1) {
        super(i, methodVisitor, i1, s, s1);
    }

    @Override
    protected  void onMethodEnter(){
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
        mv.visitVarInsn(LSTORE, 3);
        super.onMethodEnter();
    }

    @Override
    protected  void onMethodExit(int opcode){
        mv.visitLdcInsn(method);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
        mv.visitVarInsn(LLOAD, 3);
        mv.visitInsn(LSUB);
        mv.visitMethodInsn(INVOKESTATIC, "agent/MyReporter", "report", "(Ljava/lang/String;J)V", false);
        super.onMethodExit(opcode);
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

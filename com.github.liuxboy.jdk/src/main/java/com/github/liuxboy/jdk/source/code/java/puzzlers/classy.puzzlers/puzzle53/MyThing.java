import com.github.liuxboy.jdk.source.code.java.puzzlers.classy.puzzlers.puzzle53.Thing;

public class MyThing extends Thing {
    private final int arg;

    /*
     * This constructor is illegal. Rewrite it so that it has the same
     * effect but is legal.
     */
    public MyThing() {
        super(arg = (int)System.currentTimeMillis());
    }
}

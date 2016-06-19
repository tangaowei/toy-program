public class Sequence1 implements ISequence {
    private static int sVal = 0;

    @Override
    public int next() {
        return ++sVal;
    }
}

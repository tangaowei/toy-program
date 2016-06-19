public class Sequence2 implements ISequence {
    private static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    @Override
    public int next() {
        int n = local.get();
        local.set(n + 1);
        return n;
    }
}

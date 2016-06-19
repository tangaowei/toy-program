public class Sequence3 implements ISequence {
    private MyThreadLocal<Integer> local = new MyThreadLocal<Integer>() {
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

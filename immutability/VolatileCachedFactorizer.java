public class VolatileCachedFactorizer implements Runnable {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    @Override
    public void run() {
    }
}

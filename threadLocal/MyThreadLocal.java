import java.util.Map;
import java.util.HashMap;
public class MyThreadLocal<T> {
    private Map<String, T> threadMap = new HashMap<String, T>();

    public synchronized T get() {
        String name = Thread.currentThread().getName();
        T value = threadMap.get(name);
        if(value == null) {
            value = initialValue();
            threadMap.put(name, value);
        }
        return value;
    }

    public synchronized void set(T value) {
        String name = Thread.currentThread().getName();
        threadMap.put(name, value);
    }

    protected T initialValue() {
        return null;
    }
}

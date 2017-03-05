import java.util.List;
import java.util.ArrayList;
public class OneValueCache {
    private final Integer lastNumber;
    private final List<Integer> lastFactors;

    public OneValueCache(Integer i, List<Integer> factors) {
        lastNumber = i;
        lastFactors = new ArrayList<Integer>(factors);
    }

    public List<Integer> getFactors(Integer i) {
        if(lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return new ArrayList<Integer>(lastFactors);
    }
}

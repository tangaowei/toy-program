/**
Leet code 539
**/
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> list = timePoints.stream().
                                map(point -> {
                                    String[] sarr = point.split(":");
                                    return Integer.parseInt(sarr[0]) * 60 + Integer.parseInt(sarr[1]);
                                }).sorted().
                                collect(Collectors.toList());
        list.add(list.get(0));
        int ret = 1440;
        for(int i = 0; i < list.size() - 1; ++i) {
            int diff = Math.abs(list.get(i) - list.get(i + 1));
            ret = Math.min(ret, Math.min(1440 - diff, diff));
        }
        return ret;
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: MinimumTimeDifference xx:xx xx:xx");
            return;
        }
        List<String> list = new ArrayList<String>();
        for(String s : args) {
            list.add(s);
        }

        MinimumTimeDifference obj = new MinimumTimeDifference();
        int diff = obj.findMinDifference(list);
        System.out.println("diff: " + diff);
    }
}

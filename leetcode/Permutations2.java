import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

/**
No 46
Given a collection of numbers that might contain duplicate, return all possible unique permutations.
**/

public class Permutations2 {
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        return recur(0, nums);
    }

    private List<List<Integer>> recur(int index, int[] nums) {
        if(index == nums.length - 1) {
            List<Integer> list = new LinkedList<Integer>();
            list.add(nums[index]);
            List<List<Integer>> llist = new LinkedList<List<Integer>>();
            llist.add(list);
            return llist;
        }
        List<List<Integer>> llist = new LinkedList<List<Integer>>();
        for(int i = index; i < nums.length; ++i) {
            if(i != index && nums[i] == nums[index]) continue;
            swap(nums, index, i);
            List<List<Integer>> tmpLlist = recur(index + 1, nums);
            for(List<Integer> tmplist : tmpLlist) {
                tmplist.add(0, nums[index]);
                llist.add(tmplist);
            }
            swap(nums, index, i);
        }
        return llist;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        Permutations2 obj = new Permutations2();
        int[] nums = {1, 1, 2, 1,2};
        List<List<Integer>> llist = obj.permute(nums);
        for(List<Integer> list : llist) {
            System.out.println(list.toString());
        }
    }
}

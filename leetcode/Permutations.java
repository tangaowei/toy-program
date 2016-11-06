import java.util.List;
import java.util.LinkedList;

/**
No 46
Given a collection of distinct numbers, return all possible permutations.
**/

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
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
        Permutations obj = new Permutations();
        int[] nums = {4, 1, 2, 3};
        List<List<Integer>> llist = obj.permute(nums);
        for(List<Integer> list : llist) {
            System.out.println(list.toString());
        }
    }
}

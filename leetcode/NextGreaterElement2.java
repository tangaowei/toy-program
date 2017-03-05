/**
Given a circular array, print the next greater number for every element,
if it doesn't exist, output -1 for this number;
2017-3-5
**/
import java.util.Arrays;
import java.util.Stack;
public class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        if(length == 0) return new int[0];
        int[] ret = new int[length];
        Stack<Integer> stack = new Stack<Integer>();
        Arrays.fill(ret, -1);
        int count = 0;
        while(count < length * 2) {
            int index = count % length; 
            while(!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                ret[stack.pop()] = nums[index];
            }
            if(count < length) {
                stack.push(index);
            }
            ++count;
        }
        return ret;
    }

    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("usage NextGreaterElement2 1 2 3");
            return;
        }
        int[] nums = new int[args.length];
        for(int i = 0; i < args.length; ++i) {
            nums[i] = Integer.parseInt(args[i]);
        }

        NextGreaterElement2 obj = new NextGreaterElement2();
        int[] ret = obj.nextGreaterElements(nums);
        for(int i = 0; i < ret.length; ++i) {
            System.out.print(ret[i] + " ");
        }
        System.out.println();
    }
}

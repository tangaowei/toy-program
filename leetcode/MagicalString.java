/**
    leet code 481. Magical String
    2017-03-12
    **/

public class MagicalString {
    public int magicalString(int n) {
        if(n <= 0) return 0;
        if(n <= 3) return 1;
        int[] array = new int[n];
        array[0] = 1; array[1] = 2; array[2] = 2;
        int count = 1, pindex = 2;
        for(int i = 3; i < n; ++i) {
            array[i] = 3 - array[i-1];
            if(array[i] == 1) ++count;
            
            if(array[pindex] == 2 && i + 1 < n) {
                array[i + 1] = array[i];
                if(array[++i] == 1) ++count;
            }
            ++pindex;
        }
        return count;
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage MagicalString digital");
            return;
        }
        MagicalString obj = new MagicalString();
        int ret = obj.magicalString(Integer.parseInt(args[0]));
        System.out.println("ret = " + ret);
    }
}

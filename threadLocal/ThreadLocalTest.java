public class ThreadLocalTest {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage ThreadLocalTest [1|2|3]");
            return;
        }
        ISequence seq = initSeq(args[0]);
        for(int i = 0; i < 10; ++i) {
            MyThread th1 = new MyThread(seq);
            th1.start();
        }
    }

    private static ISequence initSeq(String no) {
        int i = Integer.parseInt(no);
        System.out.println("Init sequence" + i);
        if(i == 1) {
            return new Sequence1();
        } else if(i == 2) {
            return new Sequence2();
        } else {
            return new Sequence3();
        }
    }
}

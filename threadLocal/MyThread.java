public class MyThread extends Thread {
    private ISequence mSeq;
    public MyThread(ISequence seq) {
        mSeq = seq;
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; ++i) {
            try {
                Thread.currentThread().sleep(100);
            } catch(InterruptedException e) {
            }
            System.out.println("thread " + getName() + ", seq " + mSeq.next());
        }
    }
}

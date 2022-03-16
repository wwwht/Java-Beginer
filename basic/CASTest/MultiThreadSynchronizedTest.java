package CASTest;

public class MultiThreadSynchronizedTest {
    private static int count = 0;
    public static void main(String[] args) {
        for (int i = 0; i <2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                        synchronized (Thread.class){
                            count ++;
                        }
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}

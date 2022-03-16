package CASTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS自旋锁产生了ABA问题
 */
public class ABADemo {
    static AtomicReference<String> atomicReference = new AtomicReference<>("A");

    public static void main(String[] args) {
        new Thread(()-> {
            atomicReference.compareAndSet("A", "B");
            atomicReference.compareAndSet("B", "A");
        }, "t2").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet("A", "C")
            + "\t" + atomicReference.get());
        }, "t1").start();
    }
}

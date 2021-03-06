package CASTest;

import java.util.concurrent.atomic.AtomicBoolean;

public class CASPrinciple implements Runnable {
    private static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) {
        CASPrinciple pri = new CASPrinciple();
        Thread thread1 = new Thread(pri);
        Thread thread2 = new Thread(pri);
        thread1.start();
        thread2.start();
    }
    @Override
    public void run() {
        System.out.println("thread:"+Thread.currentThread().getName()+";flag:"+flag.get());
        if(flag.compareAndSet(true, false)){
            System.out.println(Thread.currentThread().getName()+""+flag.get());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.set(true);
        }else {
            System.out.println("重试机制thread:"+Thread.currentThread().getName()+";flag:"+flag.get());
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            run();
        }
    }
}

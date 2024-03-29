package thread.并发工具类.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FruitCake implements Runnable {

    private List<String> cakesList;
    private CountDownLatch countDownLatch;

    private Object bridge2;

    public FruitCake(List<String> cakesList, CountDownLatch countDownLatch, Object bridge2) {
        this.cakesList = cakesList;
        this.countDownLatch = countDownLatch;
        this.bridge2 = bridge2;
    }

    @Override
    public void run() {

        // 等待上一个生产者完成，来通知还在等待（阻塞）的bridge2对象继续。
        // 一定要注意！要在上一个生产者前获取到bridge2，并进入等待状态，否则上一个生产者在通知时，并没有已等待的对象收到消息。
        synchronized (bridge2) {
            try {
                bridge2.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 上一个生产者已完成，自己开始生产
        System.out.println("Start make fruit");
        for (int i = 0; i < cakesList.size(); i++) {
            System.out.print(" Make fruit " + i);
            cakesList.set(i, "Fruit " + i);
        }
        System.out.println("\nEnd make fruit");

        // 自己生产结束，计数栅栏减一
        countDownLatch.countDown();
    }

}

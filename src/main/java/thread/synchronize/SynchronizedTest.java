package thread.synchronize;

/**
 *
 * synchronized实现同步的基础： Java中的每一个对象都可以作为锁！
 *      普通方法  锁当前实例 this
 *      静态方法  锁类的Class对象
 *      方法块    锁synchronized(monitor)括号内的对象
 *
 * 每一个对象都可以作为锁： 锁信息存在Java对象头里。
 *       数组对象    对象头3个字宽 （64位JVM里 1字宽=8字节 即64bit）
 *       非数组对象  对象头2个字宽 （32位JVM里 1字宽=4字节 即32bit）
 */
public class SynchronizedTest {

    public synchronized void method1(String name) {
        System.out.println(name + "  call method 1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}
        System.out.println(name + "  call method 1 over");
    }

    public synchronized void method2(String name) {
        System.out.println(name + "  call method 2 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}
        System.out.println(name + "  call method 2 over");
    }

    /**
     * 两个不同的对象，方法上加锁，锁住了对象实例，即使是同一个方法，也不会产生资源的抢占。
     *
     * 关键在于 上锁的对象是否被抢占
     *
     */
    public static void main(String[] args) {
        new Thread(() -> {
            SynchronizedTest test = new SynchronizedTest();
            test.method1("a");
        }).start();

        new Thread(() -> {
            SynchronizedTest test = new SynchronizedTest();
            test.method1("b");
        }).start();
    }

    /**
     * 同一个对象，方法上加锁，锁住了对象实例，即使是不同的方法，也会产生资源的抢占。
     *
     * 关键在于 上锁的对象是否被抢占
     *
     */
    public static void main1(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        new Thread(() -> {
            test.method1("a");
        }).start();

        new Thread(() -> {
            test.method2("b");
        }).start();
    }

}

package design.pattern.创建型.单例;

/**
 *
 * 内部类模式
 *
 *  1.从外部无法访问静态内部类LazyHolder，只有当调用Singleton.getInstance方法的时候，才能得到单例对象INSTANCE。
 *
 *  2.INSTANCE对象初始化的时机并不是在单例类Singleton被加载的时候，而是在调用getInstance方法，使得静态内部类LazyHolder被加载的时候。
 *    因此这种实现方式是利用 classloader 的加载机制来实现懒加载，并保证构建单例的线程安全。
 *
 */
public class Singleton4 {

    private static class LazyHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    private Singleton4 (){}

    public static Singleton4 getInstance() {
        return LazyHolder.INSTANCE;
    }

}

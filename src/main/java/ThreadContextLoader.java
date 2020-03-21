import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class ThreadContextLoader {
    public static void main(String[] args) {
        // SPI作为一个上层标准，他是如何加载下层Classpath中的类的呢？
        //
        // 类加载器主要有两种：
        //   * 当前类加载器（默认选项） --- 每个类如果没有特别指定，默认都会尝试着去使用它自己的类加载器来去加载它依赖的其他的类
        //   * 线程上下文类加载器
        //         * 每个线程都有默认的线程上下文加载器，也可以通过setContextClassLoader设置成任何的类加载器，设置完以后
        //         * 当线程运行到上层加载器加载的类环境中时，就可以去使用当前的线程上下文加载器去随意加载，从而打破了默认规则（上层加载器加载）。
        //           父ClassLoader可以使用当前线程Thread.currentThread().getContextClassLoader()所指定的classloader来加载类。这就改变了父Classloader不能使用子ClassLoader加载的类的情况

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();

        while(iterator.hasNext()){
            Driver driver = iterator.next();
            System.out.println("driver:" + driver.getClass() + ", loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("当前线程上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器" + ServiceLoader.class.getClassLoader());
    }
}

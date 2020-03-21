package cglib;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

// -XX:MaxMetaspaceSize=10m

public class CglibTest {
    public static void main(String[] args) {
        for(;;){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(BaseClass.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor)(obj,method,args1,proxy)-> proxy.invokeSuper(obj, args1));
            System.out.println("hello world");
            enhancer.create();
        }
    }
}

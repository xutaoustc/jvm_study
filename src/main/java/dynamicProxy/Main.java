package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 定义了动态代理类的行为
class ProxyClass implements InvocationHandler{
    private Object realObject;
    public void setRealObject(Object realObject){
        this.realObject = realObject;
    }

    // 当代理接口中的方法被调用时的具体执行的动作
    // 可以写的很灵活，甚至不调用真正的代理类也可以
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeInvoke();
        Object obj = method.invoke(realObject, args);
        afterInvoke();

        return obj;
    }

    private void beforeInvoke(){
        System.out.println("before invoke");
    }
    private void afterInvoke(){
        System.out.println("after invoke");
    }
}

interface InterA{
    String getValue();
}
class A implements InterA{
    @Override
    public String getValue() {
        String value = "get Value";
        System.out.println(value);
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        ProxyClass proxyInstance = new ProxyClass();
        proxyInstance.setRealObject( new A() );

        // 生成真正的代理对象
        InterA proxy = (InterA)Proxy.newProxyInstance(Main.class.getClassLoader(),new Class[]{InterA.class}, proxyInstance);
        String value = proxy.getValue();
    }
}

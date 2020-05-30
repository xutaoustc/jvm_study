package singleton;

public class InstanceHolderSingleton {
    public static void main(String[] args) {

    }
}

// 既能懒加载，而且效率高（不加锁），保证线程安全性
class Instance{
    private Instance(){}

    private static class InstanceHolder{
        private static final Instance instance = new Instance();
    }

    public static Instance getInstance(){
        return InstanceHolder.instance;
    }
}

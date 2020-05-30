package singleton;

public class EnumSingleton {
    public static void main(String[] args) {
        EasySingleton singleton = EasySingleton.INSTANCE;
    }
}


enum EasySingleton{
    INSTANCE;
}
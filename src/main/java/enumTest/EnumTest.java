package enumTest;

public class EnumTest {
    public static void main(String[] args) {

    }
}


// 普通类
// 无法继承
// 实例仅能内部定义
enum Weekday {
    // 实例定义部分，分别为实例名和传入实例的参数
    MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六"), SUN(0, "星期日");


    // 和普通类没有区别的部分
    public final int dayValue;
    private final String chinese;

    Weekday(int dayValue, String chinese) {
        this.dayValue = dayValue;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return this.chinese;
    }
}


//public enum Color {
//    RED, GREEN, BLUE;
//}

//编译器编译出的class大概就像这样：
//public final class Color extends Enum { // 继承自Enum，标记为final class
//    // 每个实例均为全局唯一:
//    public static final Color RED = new Color();
//    public static final Color GREEN = new Color();
//    public static final Color BLUE = new Color();
//    // private构造方法，确保外部无法调用new操作符:
//    private Color() {}
//}
package gc;

// -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
// 2,2,3
// [GC (Allocation Failure) [PSYoungGen: 5949K->576K(9216K)] 5949K->4680K(19456K), 0.0032247 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
//                                       执行前 执行后 新生代总 总堆前  总堆后 总堆
// 新生代减少了5949-576  ， 总堆减少了5959-4680， 两者相减就是进入到老年代的对象


public class GcTest {
    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[2*size];
        byte[] myAlloc2 = new byte[2*size];
        byte[] myAlloc3 = new byte[3*size];

        System.out.println("hello world");
    }
}

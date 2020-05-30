package shutdownHook;

// kill -9 不会响应
// kill 会响应
// 一般做管理的时候不要用kill -9
public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(
                new Thread(()->{
                    System.out.println("The application will exit");
                    notifyAndRelease();
                })
        );

        int i = 0;
        while(true){
            try {
                System.out.println("Program run...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
            if(i>10){
                throw new RuntimeException();
            }
        }
    }

    private static void notifyAndRelease(){
        System.out.println("will release the resource");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("resource release finished");
    }
}

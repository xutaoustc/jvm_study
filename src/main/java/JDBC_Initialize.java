import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Initialize {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载并初始化com.mysql.jdbc.Driver，进入到了com.mysql.jdbc.Driver的静态代码块
        // static {
        //     try {
        //         java.sql.DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //     } catch (SQLException E) {
        //         throw new RuntimeException("Can't register driver!");
        //     }
        // }

        // 初始化java.sql.DriverManager时又调用了java.sql.DriverManager的静态代码块
        // static {
        //     loadInitialDrivers();
        //     println("JDBC DriverManager initialized");
        // }
        // 在其中使用SPI，加载并实例化了每个Driver类（除了com.mysql.jdbc.Driver因为已经在最开始初始化过了）
        // 又调用对应Driver的静态代码块，在静态代码块中将自己注册到DriverManager中，DriverManager.registerDriver(new FabricMySQLDriver());

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("ss","ss","ss");
    }
}

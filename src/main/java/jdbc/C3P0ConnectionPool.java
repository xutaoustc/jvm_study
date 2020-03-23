package jdbc;

// 传统的模式有以下3个缺点：
// 1. 每次向数据库建立连接的时候都需要花费时间，数据库的连接资源并没有得到很好的重复利用
// 2. 对于每一次数据库连接，使用完都得断开
// 3. 不能控制被创建的连接对象数

// 数据库连接池的基本思想：就是为数据库连接建立一个缓冲池，预先在缓冲池中放入一定数量的连接，当需要建立数据库连接时，只需从缓冲池中取出一个，使用完毕后再放回去
// 数据库连接池负责分配、管理和释放数据库连接，它允许应用程序重复使用一个现有的数据库连接，而不是重新建立一个

// JDBC中连接池使用javax.sql.DataSource来表示

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0ConnectionPool {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        // 也可以使用配置文件来进行初始化
        // DBCP, Druid之类的都类似
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/testDB");
        cpds.setUser("xu1");
        cpds.setPassword("123456");

        cpds.setInitialPoolSize(10);

        Connection connection = cpds.getConnection();
        System.out.println(connection);

        DataSources.destroy(cpds);
    }






}

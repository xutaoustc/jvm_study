package jdbc;

// 提交事务的方式
// DDL  --- TRUNCATE is a DDL, but DELETE is a DML
// DML  --- 默认情况下会自动提交，autocommit
// Connection close


// 脏读 --- T1可以读到T2改变了但是没有提交的数据
// 不可重复读 --- T1读取了字段的值， T2更新字段的值并提交，T1再来读发现值不一样了
// 幻读 --- T1读取了表，T2插入了一些新行，T1再次读取表就会多出几行

// READ COMMITED --- 解决了脏读
// REPEATABLE READ --- 解决了脏读、不可重复读
// SERIALIZABLE --- 解决了脏读、不可重复读、幻读

import java.sql.Connection;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) throws SQLException {
        Connection connection = DbOperation.INSTANCE.getConnection();
        connection.setAutoCommit(false);

        try {
            DbOperation.INSTANCE.updateActionWithoutCloseConnection(connection, "update testTable set name=? where id=?", "xu5", "600003");
            int a = 1 / 0;
            DbOperation.INSTANCE.updateActionWithoutCloseConnection(connection, "update testTable set name=? where id=?", "xu6", "600004");

            connection.commit();
        }catch(Exception e){
            connection.rollback();
        }
    }
}

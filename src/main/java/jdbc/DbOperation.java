package jdbc;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum DbOperation{
    INSTANCE;

    public void testInsert() throws SQLException {
        updateAction("insert into testTable(name,email) values (?,?)", "xu","xu@163.com");
    }
    public void testUpdate() throws SQLException {
        updateAction("update testTable set name=? where id=?", "xu1",1);
    }
    public void testDelete() throws SQLException {
        updateAction("delete from testTable where id=?", 1);
    }

    private void updateAction(String sql, Object... params) throws SQLException {
        // 1. get connection
        Connection connection = getConnection();

        // 2. get statement, set sql and params
        // do not use Statement, use PreparedStatement instead
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = 0;
        for(Object param : params){
            ps.setObject(++i, param);
        }

        // 3. execute
        ps.execute();

        // 4. close
        ps.close();
        connection.close();
    }

    public <T> List<T> testQuery(Class<T> objClass, String sql, Object... params) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        // 1. get connection
        Connection connection = getConnection();

        // 2. get statement, set sql and params
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = 0;
        for(Object param : params){
            ps.setObject(++i, param);
        }

        // 3. execute
        ResultSet rs = ps.executeQuery();

        // 4. parse result
        int columnCount = rs.getMetaData().getColumnCount();
        ResultSetMetaData metaData = rs.getMetaData();

        List<T> retList = new ArrayList<>();
        while(rs.next()){
            T eachValue = objClass.newInstance();

            for(int j = 1; j<= columnCount; j++){
                // 不推荐使用metaData.getColumnName，因为其返回用的永远是列原始的名字
                Field field = objClass.getDeclaredField( metaData.getColumnLabel(j) );
                field.setAccessible(true);
                field.set(eachValue, rs.getObject(j));
            }
            retList.add(eachValue);
        }

        // 5. close
        rs.close();
        ps.close();
        connection.close();

        return retList;
    }

    public void insertBatch(String sql, Object... params) throws SQLException {
        // 1. get connection
        Connection connection = getConnection();
        connection.setAutoCommit(false);   // 默认是每次DML后都会执行提交，设置autoCommit为false最后统一提交可以提高执行效率

        // 2. get statement, set sql and params
        // do not use Statement, use PreparedStatement instead
        PreparedStatement ps = connection.prepareStatement(sql);
        for(int count=0;count<100000;count++){
            int i = 0;
            for(Object param : params){
                ps.setObject(++i, param);
            }
            ps.addBatch();

            if(count % 500 == 0){
                // 3. execute
                ps.executeBatch();
                ps.clearBatch();
            }
        }
        ps.executeBatch();
        ps.clearBatch();
        connection.commit();

        // 4. close
        ps.close();
        connection.close();
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testDB";
        String user = "xu1";
        String password = "123456";

//        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url,user,password);

        return connection;
    }
}

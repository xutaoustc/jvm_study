package jdbc;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main<T> {
    public static void main(String[] args) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
//        DbOperation.INSTANCE.insert();

        // 可以通过在select语句中给列起别名来适配数据库和Java之间字段名字的不匹配问题
//        List<TestDBEntity> getResult = DbOperation.INSTANCE.testQuery(TestDBEntity.class,"select * from testTable");

        DaoImpl daoImpl = new DaoImpl();
        System.out.println("s");
    }
}



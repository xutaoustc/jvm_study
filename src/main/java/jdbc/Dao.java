package jdbc;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class BaseDao<T>{
    private Class<T> clazz = null;

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = paramType.getActualTypeArguments();
        clazz = (Class<T>) typeArguments[0];
    }
}

class DaoImpl extends BaseDao<TestDBEntity>{


}

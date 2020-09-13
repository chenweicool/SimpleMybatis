package chen.proxey;

import chen.sqlsession.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @Author Chen
 * @Date 2020/9/7 23:38
 * 动态代理，主要代理mapper的接口。实现mysql的执行。
 **/
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * 代理sqlsession的实现
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getDeclaringClass().getName()+"."+method.getName());
        if(Collection.class.isAssignableFrom(method.getReturnType())){
            return sqlSession.selectList(method.getDeclaringClass().getName()+"."+method.getName(),args==null?null:args[0]);
        }else{
            return sqlSession.selectOne(method.getDeclaringClass().getName()+"."+method.getName(),args==null?null:args[0]);
        }
    }
}

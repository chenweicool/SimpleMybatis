package chen.sqlsession;

import chen.proxey.MapperProxy;
import chen.configration.Configuration;
import chen.configration.MappedStatement;
import chen.executor.Executor;
import chen.executor.SimpleExecutor;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Author Chen
 * @Date 2020/9/7 23:35
 **/
public class DefaultSqlSession implements SqlSession {
    private  Configuration configuration = null;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
//        super();
        this.configuration = configuration;
        executor = new SimpleExecutor(configuration);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<T> selectList = this.selectList(statement,parameter);
        if(selectList == null || selectList.size() == 0){
            return null;
        }
        if(selectList.size() == 1){
            return (T) selectList.get(0);
        }else{
            throw new RuntimeException("too many result");
        }
    }


    @Override
    public <T> List<T> selectList(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement().get(statement);
        return executor.query(ms,parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        MapperProxy mp = new MapperProxy(this);
        //给我一个接口，还你一个实现类
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},mp);
    }
}

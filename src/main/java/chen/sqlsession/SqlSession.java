package chen.sqlsession;

import java.util.List;

/**
 * @Author Chen
 * @Date 2020/9/7 22:20
 * 定义一个sqlsession这个类的实现
 **/
public interface SqlSession {

    /**
     * 根据查询的结果查询的一个结果。
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T>  T selectOne(String statement,Object  parameter);

    <T> List<T> selectList(String statement,Object parameter);

    <T> T getMapper(Class<T> type);
}

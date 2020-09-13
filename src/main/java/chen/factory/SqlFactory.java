package chen.factory;

import chen.sqlsession.SqlSession;

/**
 * @Author Chen
 * @Date 2020/9/7 22:19
 * 通过这个工厂类，来获取sqlSession这个实现类
 **/
public interface SqlFactory {
    SqlSession openSqlSession();
}

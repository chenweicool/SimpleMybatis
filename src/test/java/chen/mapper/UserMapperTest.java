package chen.mapper;


import chen.entity.User;
import chen.factory.DefaultSqlSessionFactory;
import chen.factory.SqlFactory;
import chen.sqlsession.SqlSession;
import org.junit.Test;

public class UserMapperTest {

   @Test
   public   void testQuery() {
        SqlFactory sqlFactory =  new DefaultSqlSessionFactory();
        SqlSession sqlSession = sqlFactory.openSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByPrimaryKey(1L);
        System.out.println(user.getName());
    }
}
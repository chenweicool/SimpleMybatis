package chen.executor;

import chen.configration.Configuration;
import chen.configration.MappedStatement;
import chen.utils.ReflectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Chen
 * @Date 2020/9/7 21:36
 *
 * 主要执行器的类的实现。
 * 1.从congfigration的对象中读取
 **/
public class SimpleExecutor implements  Executor {


    private Configuration configuration;
    public SimpleExecutor(Configuration configuration){
        this.configuration = configuration;
    }

    /**
     * 主要实现查询功能的实现
     * @param ms 对应的xml文件的解析对象
     * @param parameter  传入的sql参数
     * @param <E>
     * @return
     */
    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter) {

        System.out.println(ms.getSql().toString());
        List<E> ret = new ArrayList<E>();

        try {
            Class.forName(configuration.getJdbcDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(configuration.getJdbcUrl(),configuration.getJdbcUsername(),configuration.getJdbcPassword());

            // 这个正则有点问题，后续再改
            //String regex ="#\\{([^])}*\\}";
            // String sql = ms.getSql().replace(regex,"?");

            //将数字的字符替换为占位符
            String sql = ms.getSql().replace("#{userId}","?");

            /**
             * 到数据库查询数据的过程
             */
            preparedStatement = connection.prepareStatement(sql);
            paraedStatersize(preparedStatement,parameter);
            resultSet = preparedStatement.executeQuery();

            // 处理查询出的数据，并进行映射到对应的实体类中
            handleResultSet(resultSet,ret,ms.getResultType());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return ret;
    }

    /**
     * 利用反射将查询到的数据映射到实体的类信息中。
     * @param resultSet 从数据库拿到的结果集
     * @param ret  将要返回的果集
     * @param resultType  对应数据库的实体类
     * @param <E>
     */
    private <E> void handleResultSet(ResultSet resultSet, List<E> ret, String resultType){
        // 通过反射类获取对象
        Class<E> clazz = null;
        try {
            clazz = (Class<E>) Class.forName(resultType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while(resultSet.next()) {
                Object entity = clazz.newInstance();

                // 通过反射工具将查询到的数据填充到entity中去。
                ReflectionUtil.setPropToBeanFromResultSet(entity,resultSet);
                ret.add((E)entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 一个类型的转换
     * @param preparedStatement
     * @param parameter
     * @throws SQLException
     */
    private void paraedStatersize(PreparedStatement preparedStatement, Object parameter) throws SQLException {
        if(parameter instanceof Integer){
            preparedStatement.setInt(1,(Integer) parameter);
        }else if(parameter instanceof  Long){
            preparedStatement.setLong(1, (Long) parameter);
        }else if(parameter instanceof String){
            preparedStatement.setString(1, (String) parameter);
        }
    }
}

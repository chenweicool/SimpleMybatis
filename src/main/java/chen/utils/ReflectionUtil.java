package chen.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Chen
 * @Date 2020/9/7 22:03
 * 反射的工具类。
 * 主要作用是将数据库中查询出的数据填充到指定的实体中去。
 **/
public class ReflectionUtil {

    /**
     * 实现字段的一个映射
     * @param bean  实体
     * @param propName
     * @param value
     */
    public static void setPropToBean(Object bean,String propName,Object value){
        Field field;
        try {
            field = bean.getClass().getDeclaredField(propName);
            field.setAccessible(true);
            field.set(bean,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现字段的映射
     * @param entity 实体类
     * @param resultSet  mapper文件中声明的实体类型
     * @throws SQLException
     */
    public static void setPropToBeanFromResultSet(Object entity, ResultSet resultSet) throws SQLException {
        // 通过反射字段获取所有的字段
        Field[] declaredFields = entity.getClass().getDeclaredFields();

        for (int i = 0; i < declaredFields.length ; i++) {
            if(declaredFields[i].getType().getSimpleName().equals("String")){
                setPropToBean(entity,declaredFields[i].getName(),resultSet.getString(declaredFields[i].getName()));
            }else if(declaredFields[i].getType().getSimpleName().equals("Integer")){
                setPropToBean(entity,declaredFields[i].getName(),resultSet.getInt(declaredFields[i].getName()));
            }else if(declaredFields[i].getType().getSimpleName().equals("Long")){
                setPropToBean(entity,declaredFields[i].getName(),resultSet.getLong(declaredFields[i].getName()));
            }
        }
    }
}

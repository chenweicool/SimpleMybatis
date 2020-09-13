package chen.executor;

import chen.configration.MappedStatement;

import java.util.List;

/**
 * @Author Chen
 * @Date 2020/9/7 21:34
 * 执行类的接接口，核心的接口，jdbc，sqlSession的所有功能都是它实现的。
 **/
public interface Executor {
    /**
     * 查询的方法
     * @param ms 对应的xml文件的解析对象
     * @param parameter  传入的sql参数
     * @param <E> 返回结果的对象
     * @return
     */
    <E> List<E> query(MappedStatement ms,Object parameter);
}

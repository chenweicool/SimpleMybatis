package chen.mapper;

import chen.entity.User;

import java.util.List;

/**
 * @Author Chen
 * @Date 2020/9/7 18:30
 * 一个查询的接口
 **/
public interface UserMapper {
    User selectByPrimaryKey(long UserId);
    List<User>  selectAll();
}

package chen.entity;


/**
 * @Author Chen
 * @Date 2020/9/7 18:25
 *
 *实体类
 **/
public class User {

    private Long userId;

    private String userName;

    private int sex;

    private String role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long serId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}



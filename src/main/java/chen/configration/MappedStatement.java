package chen.configration;

/**
 * @Author Chen
 * @Date 2020/9/7 20:43
 *
 * @UserMapper.xml
 * mybaties的配置类,mapper.xml的文件解析的结果使用这个类进行封装。
 **/
public class MappedStatement {

    // 命名空间
    private String namespace;

     // 方法的名
    private String id;

    // sql的执行的放回值
    private String resultType;

    // 具体的sql语句
    private String sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}

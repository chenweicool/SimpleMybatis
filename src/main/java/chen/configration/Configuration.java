package chen.configration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Chen
 * @Date 2020/9/7 18:58
 * 配置文件的类，主要的信息
 * 1， 封装数据库的连接的一些信息
 * 2.封装mybaties的mapper的xml文件的内容
 *
 **/
public class Configuration {

   private String jdbcDriver;

   private String jdbcUrl;

   private String jdbcPassword;

   private String jdbcUsername;

    private Map<String,MappedStatement> mappedStatement = new HashMap<String, MappedStatement>();

    public Map<String, MappedStatement> getMappedStatement() {
        return mappedStatement;
    }

    public void setMappedStatement(Map<String, MappedStatement> mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }
}

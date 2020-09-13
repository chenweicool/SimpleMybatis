package chen.factory;

import chen.configration.Configuration;
import chen.configration.MappedStatement;
import chen.sqlsession.DefaultSqlSession;
import chen.sqlsession.SqlSession;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author Chen
 * @Date 2020/9/7 22:27
 *
 * 这个类的主要作用就是：
 * 1.初始化configration,将数据库连接以及mapper.xml的一些信息封装到configration中去。
 * 2.返回一个具有数据的sqlsession这个类的实现。
 **/
public class DefaultSqlSessionFactory implements SqlFactory {

    private  final Configuration configuration = new Configuration();

    private static final String MAPPER_CONTENT_PATH = "mappers";

    private static final String DB_CONFIG = "db.properties";

    public DefaultSqlSessionFactory(){
        loadDBInfo();
        loadMapperInfo();
    }

    private void loadMapperInfo() {
        URL resours = null;
        resours = this.getClass().getClassLoader().getResource(MAPPER_CONTENT_PATH);
        File file = new File(resours.getFile());

        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                loadMapperInfo(file1);
            }
        }
    }

    private void   loadMapperInfo(File file1) {
        SAXReader reader = new SAXReader();

        // 获取一个document的对象
        Document document = null;
        try {
            document = reader.read(file1);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 获取根节点mapper
        Element e = document.getRootElement();
        // 获取命名空间
        String namespace = e.attribute("namespace").getData().toString() ;
        List<Element> selects =  e.elements("select");
        List<Element> inserts = e.elements("select");
        List<Element> updates = e.elements("select");
        List<Element> deletes = e.elements("select");

        List<Element> all = new ArrayList<Element>();
        all.addAll(selects);
        all.addAll(inserts);
        all.addAll(updates);
        all.addAll(deletes);

        // 遍历节点，放入到configuration
        for (Element element : all) {
            MappedStatement mappedStatement = new MappedStatement();
            String id = element.attribute("id").getData().toString();
            String resultType = element.attribute("resultType").getData().toString();
            String sql = element.getData().toString();
           mappedStatement.setId(id);
           mappedStatement.setResultType(resultType);
           mappedStatement.setSql(sql);
          configuration.getMappedStatement().put(namespace+"."+id,mappedStatement);
        }

    }

    private void loadDBInfo()  {
        InputStream db =  this.getClass().getClassLoader().getResourceAsStream(DB_CONFIG);

        Properties properties = new Properties();
        try {
            properties.load(db);

        } catch (IOException e) {
            e.printStackTrace();
        }
       // 将配置信息写入到configation中去。
        configuration.setJdbcDriver(properties.get("jdbc.driver").toString());
        configuration.setJdbcUrl(properties.get("jdbc.url").toString());
        configuration.setJdbcUsername(properties.get("jdbc.username").toString());
        configuration.setJdbcPassword(properties.get("jdbc.password").toString());
    }

    /**
     * 返回一个实体的sql对象
     * @return
     */
    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }

}

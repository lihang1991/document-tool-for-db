import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * freemarker导出测试
 * @author lih
 * @create 2019-05-21-16:40.
 */
public class WordDocExportTest {

    public static void main(String[] args) throws IOException, TemplateException {
        /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        /** 设置编码 **/
        configuration.setDefaultEncoding("utf-8");
        /** 我的ftl文件是放在D盘的**/
        String fileDirectory = WordDocExportTest.class.getResource("/").getPath();
        /** 加载文件 **/
        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
        /** 加载模板 **/
        Template template = configuration.getTemplate("table.ftl");
        /** 准备数据 **/
        Map<String,Object> tableMap = new HashMap<>(16);

        Column column = null;
        List<Column> list = new ArrayList<Column>(5);
        for (int i = 0; i < 5; i++) {
            column = new Column();
            column.setComment("comment" + i);
            column.setEmpt(null);
            column.setKey("key");
            column.setType("int(11)");
            column.setName("aaaa");
            list.add(column);
        }

        tableMap.put("table1", list);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMap", tableMap);


        /** 指定输出word文件的路径 **/
        String outFilePath = "D:\\myFreeMarker.doc";
        File docFile = new File(outFilePath);
        FileOutputStream fos = new FileOutputStream(docFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
        if(out != null){
            out.close();
        }
    }
}

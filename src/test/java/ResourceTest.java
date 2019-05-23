import freemarker.template.Configuration;

import java.net.URL;

/**
 * @author lih
 * @create 2019-05-22-9:47.
 */
public class ResourceTest {

    public static void main(String[] args) {
        URL resource = ResourceTest.class.getResource("/");
        System.out.println(resource.getPath());
    }
}

import lombok.Data;

import java.io.Serializable;

/**
 * 列
 *
 * @author lih
 * @create 2019-05-22-10:01.
 */
@Data
public class Column implements Serializable {

    /** 列名 **/
    String name;

    String type;

    String key;

    String empt;

    String comment;

}

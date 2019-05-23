package com.github.lihang1991.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 数据库连接属下
 *
 * @author lih
 * @create 2019-05-22-11:36.
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class Parameters {

    private String host = "localhost";
    private String user;
    private String port = "3306";// 默认值
    private String password;
    private String database;
    private String table = "_NULL_";
    private String path = "";

    /** 数据库类型 备用 **/
    private String dbType;

    public Parameters() {
    }

    public Parameters(String host, String user, String port, String password,
                      String database, String table, String path) {
        this.host = host;
        this.user = user;
        this.port = port;
        this.password = password;
        this.database = database;
        this.table = table;
        this.path = path;
    }
}

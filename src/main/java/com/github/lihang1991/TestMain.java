package com.github.lihang1991;

import com.github.lihang1991.db.DBUtil;
import com.github.lihang1991.entity.Column;
import com.github.lihang1991.entity.Parameters;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author lih
 * @create 2019-05-22-14:08.
 */
public class TestMain {

    public static void main(String[] args) throws SQLException {
        Parameters parameters = new Parameters();

        parameters.setDatabase("jczl2.0").setHost("20.21.1.127").setPassword("Mysql123456.")
                .setPath("D:/").setPort("3306").setUser("root");

        DBUtil dbUtil = new DBUtil(parameters);
        Map<String, List<Column>> databaseInfo = dbUtil.getDatabaseInfo();
        databaseInfo.forEach((k, v) -> System.out.println(k));
    }
}

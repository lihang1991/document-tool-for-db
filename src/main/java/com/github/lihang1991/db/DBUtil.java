package com.github.lihang1991.db;

import com.github.lihang1991.entity.Column;
import com.github.lihang1991.entity.Parameters;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;

/**
 * 数据库连接工具
 *
 * @author lih
 * @create 2019-05-22-11:27.
 */
public class DBUtil {

    private static Parameters parameters = null;

    private static Connection conn = null;

    private DBUtil(){}

    public DBUtil(Parameters parameters) throws SQLException {
        loadDriver();
        this.parameters = parameters;
        initConn();
    }

    private static void loadDriver() {
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void initConn() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + parameters.getHost() + ":" + parameters.getPort()
                + "/information_schema?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false", parameters.getUser(), parameters.getPassword());
    }

    public Map<String, List<Column>> getDatabaseInfo() throws SQLException {
        String databaseName = parameters.getDatabase();
        String tableName = parameters.getTable();
        String columnInfoSql = "select table_name, column_name, column_type, column_key, extra, is_nullable,column_comment from columns where table_schema=''{0}''";
        String tableInfoSql = "select TABLE_SCHEMA, TABLE_NAME, TABLE_COMMENT from `TABLES` where TABLE_SCHEMA = ''{0}'' ORDER BY TABLE_NAME";
        if (!"_NULL_".equals(tableName) && !"".equals(tableName)) {
            tableInfoSql = "select TABLE_SCHEMA, TABLE_NAME, TABLE_COMMENT from `TABLES` where TABLE_SCHEMA = ''{0}'' and table_name = ''{1}'' ORDER BY TABLE_NAME";
            columnInfoSql = "select table_name, column_name, column_type, column_key, extra, is_nullable,column_comment from columns where table_schema=''{0}'' and table_name = ''{1}''";
            tableInfoSql = MessageFormat.format(tableInfoSql, databaseName, tableName);
            columnInfoSql = MessageFormat.format(columnInfoSql,databaseName, tableName);
        } else {
            tableInfoSql = MessageFormat.format(tableInfoSql, databaseName);
            columnInfoSql = MessageFormat.format(columnInfoSql,databaseName);
        }

        // 表信息
        Statement tableSt = conn.createStatement();
        ResultSet tableInfoRs = tableSt.executeQuery(tableInfoSql);

        Map<String, String> tableCommentMap = new HashMap<>();
        while (tableInfoRs.next()) {
            String table_name = tableInfoRs.getString("table_name");
            String table_comment = tableInfoRs.getString("TABLE_COMMENT");
            tableCommentMap.put(table_name, table_comment);
        }

        Map<String, List<Column>> info = new HashMap<>();
        Column column = null;
        // 列信息
        Statement columnSt = conn.createStatement();
        ResultSet columnInfoRs = tableSt.executeQuery(columnInfoSql);
        while (columnInfoRs.next()) {
            String table_name = columnInfoRs.getString("table_name");
            table_name = table_name + tableCommentMap.get(table_name);

            List<Column> columnList = info.get(table_name);
            if (columnList == null) {
                columnList = new LinkedList<>();
            }

            // 列属性
            column = new Column();
            String column_name = columnInfoRs.getString("column_name");
            String column_type = columnInfoRs.getString("column_type");
            String column_key = columnInfoRs.getString("column_key");
            String is_nullable = columnInfoRs.getString("is_nullable");
            String column_comment = columnInfoRs.getString("column_comment");

            column.setColumnName(column_name).setColumnType(column_type).setColumnKey("PRI".equals(column_key) ? "是":"否")
                .setColumnComment(column_comment).setNullable(is_nullable);

            columnList.add(column);
            info.put(table_name, columnList);
        }
        return info;
    }
}

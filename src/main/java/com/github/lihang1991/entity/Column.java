package com.github.lihang1991.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 表中的直段信息
 *
 * @author lih
 * @create 2019-05-22-10:01.
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class Column implements Serializable {

    /** 所属表名 **/
    String tableName;

    /** 列名 **/
    String columnName;

    /** 数据类型 **/
    String columnType;

    /** 默认值 **/
    String columnDefault;

    /** 索引 **/
    String columnKey;

    /** 是否为空 **/
    String nullable;

    /** 注释 **/
    String columnComment;

}

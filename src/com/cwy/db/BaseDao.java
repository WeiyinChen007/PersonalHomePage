package com.cwy.db;

/**
 * 用于定义数据操作对象的  存取标准
 * @param <T> 操作的数据类型：用户基本信息、学历信息、工作经验、特长、技能列表。。。
 */
public interface BaseDao<T> {

    /**
     * 用于规范存储
     * @param t 要存储的数据
     * @return 存储的结果，大于0表示成功
     */
    int insert(T t);

    /**
     * 用于规范获取
     * @param userId 基于用户的编号，查询数据
     * @return 基于用户的编号，查询到的：用户基本信息、学历信息、工作经验、特长、技能列表。。。
     */
    T findByUserId(int userId);
}

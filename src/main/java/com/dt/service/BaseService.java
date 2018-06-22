package com.dt.service;


import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;

import java.util.List;

/**
 * Service接口，定义了常用的Service方法，实现业务逻辑操作并调用DAO<br />
 * 由Controller调用Service时，需要传递DTO对象进行，并返回DTO对象到Controller<br />
 * 创建于2017-08-24
 *
 * @author 陈飞龙
 * @version 1.0
 * @param <T> Service操作的数据传输对象，具体的DTO类
 */
public interface BaseService<T> {

    /**
     * 添加数据到数据库中
     * @param t DTO数据传输对象
     */
    void save(T t);

    /**
     * 根据DTO实体类从数据库中删除数据
     * @param t DTO数据传输对象
     */
    void remove(T t);

    /**
     * 根据主键从数据库中删除数据
     * @param id 主键ID
     */
    void removeById(Long id);

    /**
     * 根据对象更新数据库中的数据
     * @param t DTO数据传输对象
     */
    void update(T t);

    /**
     * 根据StatusQuery查询对象更新状态值
     * @param statusQuery 状态查询对象，包括id和status
     */
    void updateStatus(StatusQuery statusQuery);

    /**
     * 根据主键id查找数据
     * @param id 主键字段值
     * @return DTO数据传输对象
     */
    T getById(Long id);

    /**
     * 查找所有记录数据
     * @return DTO数据传输对象组成的List列表
     */
    List<T> listAll();

    /**
     * 根据PageQuery分页查询对象分页查找记录数据
     * @param pageQuery 分页查询对象
     * @return 分页数据DTO对象所组成的List列表
     */
    List<T> listPage(PageQuery pageQuery);

    long count(PageQuery pageQuery);
}

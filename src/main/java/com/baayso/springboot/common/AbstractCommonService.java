package com.baayso.springboot.common;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 通用业务逻辑。
 *
 * @author ChenFangjie (2015/12/5 13:02)
 * @since 1.0.0
 */
@Service
public abstract class AbstractCommonService<T, ID extends Serializable> {

    @Inject
    protected CommonMapper<T> dao;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值。
     *
     * @param entity 需要保存的实体
     *
     * @return 受影响的行数
     *
     * @since 1.0.0
     */
    public <S extends T> int save(S entity) {
        return this.save(entity, false);
    }

    /**
     * 保存一个实体。
     *
     * @param entity     需要保存的实体
     * @param isSaveNull 是否保存为null的属性
     *
     * @return 受影响的行数
     *
     * @since 1.0.0
     */
    public <S extends T> int save(S entity, boolean isSaveNull) {
        int count;

        if (isSaveNull) {
            count = this.dao.insert(entity);
        }
        else {
            count = this.dao.insertSelective(entity);
        }

        return count;
    }

    /**
     * 批量插入，该接口限制实体包含`id`属性并且必须为自增列。 <br>
     * 注：null的属性也会保存，不会使用数据库默认值。
     *
     * @param list 实体集合
     *
     * @return 受影响的行数
     *
     * @since 1.0.0
     */
    public int saves(List<T> list) {
        return this.dao.insertList(list);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性。
     *
     * @param key 主键
     *
     * @return 受影响的行数
     *
     * @since 1.0.0
     */
    public int remove(ID key) {
        return this.dao.deleteByPrimaryKey(key);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号。
     *
     * @param entity 封装了查询条件的实体
     *
     * @return 受影响的行数
     *
     * @since 1.0.0
     */
    public int remove(T entity) {
        return this.dao.delete(entity);
    }

    /**
     * 根据主键更新实体属性不为null的值。
     *
     * @param entity 封装了更新条件和更新内容的实体
     *
     * @return 受影响的行数
     *
     * @since 1.0.0
     */
    public int update(T entity) {
        return this.update(entity, false);
    }

    /**
     * 根据主键更新实体。
     *
     * @param entity       封装了更新条件和更新内容的实体
     * @param isUpdateNull 是否更新为null的属性
     *
     * @return 受影响的行数
     *
     * @since 1.0.0
     */
    public int update(T entity, boolean isUpdateNull) {
        int count;

        if (isUpdateNull) {
            count = this.dao.updateByPrimaryKey(entity);
        }
        else {
            count = this.dao.updateByPrimaryKeySelective(entity);
        }

        return count;
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号。
     *
     * @param entity 封装了查询条件的实体
     *
     * @return 查询总数
     *
     * @since 1.0.0
     */
    public int count(T entity) {
        return this.dao.selectCount(entity);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号。
     *
     * @param key 主键
     *
     * @return 查询到的实体
     *
     * @since 1.0.0
     */
    public T get(ID key) {
        return this.dao.selectByPrimaryKey(key);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号。
     *
     * @param entity 封装了查询条件的实体
     *
     * @return 查询到的实体
     *
     * @since 1.0.0
     */
    public T get(T entity) {
        return this.dao.selectOne(entity);
    }

    /**
     * 查询全部数据。
     *
     * @return 查询到的全部数据
     *
     * @since 1.0.0
     */
    public List<T> list() {
        return this.dao.selectAll();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号。
     *
     * @param entity 封装了查询条件的实体
     *
     * @return 查询到的数据列表
     *
     * @since 1.0.0
     */
    public List<T> list(T entity) {
        return this.dao.select(entity);
    }

    /**
     * 单表分页查询。
     *
     * @param pageNum  页码
     * @param pageSize 每页行数
     *
     * @return 查询到的数据列表
     *
     * @since 1.0.0
     */
    public PageInfo<T> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = this.dao.select(null);

        return new PageInfo<>(list);
    }

}

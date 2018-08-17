package cn.luv2code.sample.userprovider.core;

import java.io.Serializable;

public interface BaseService<T,PK extends Serializable> {
    /**
     * 查
     *
     * @param id 主键
     * @return 实体
     */
    T findById(PK id);

    /**
     * 增
     *
     * @param t 实体
     */
    void save(T t);

    /**
     * 删
     *
     * @param id 主键
     */
    void deleteById(PK id);
    // TODO: 2018/8/16 分页
}

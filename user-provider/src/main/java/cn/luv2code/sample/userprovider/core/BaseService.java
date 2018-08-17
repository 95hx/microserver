package cn.luv2code.sample.userprovider.core;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T,PK extends Serializable> {
    /**
     * 查
     *
     * @param id 主键
     * @return 实体
     */
    T findById(PK id);

    /**
     * 查
     * @return list<T>
     */
    List<T> findAll();

    /**
     * 查
     * @param pageable
     * @return Page<T>
     */
    List<T> findAll(Pageable pageable);

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
}

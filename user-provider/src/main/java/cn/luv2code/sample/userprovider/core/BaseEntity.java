package cn.luv2code.sample.userprovider.core;


import java.time.LocalDateTime;

public abstract class BaseEntity implements java.io.Serializable {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract LocalDateTime getCreateTime();

    public abstract void setCreateTime(LocalDateTime createTime);

    public abstract LocalDateTime getUpdateTime();

    public abstract void setUpdateTime(LocalDateTime updateTime);
}

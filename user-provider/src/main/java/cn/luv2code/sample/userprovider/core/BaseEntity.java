package cn.luv2code.sample.userprovider.core;


import java.util.Date;

public abstract class BaseEntity implements java.io.Serializable {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract Date getCreateTime();

    public abstract void setCreateTime(Date createTime);

    public abstract Date getUpdateTime();

    public abstract void setUpdateTime(Date updateTime);
}

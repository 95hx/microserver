package cn.luv2code.sample.userprovider.entity;

import cn.luv2code.sample.userprovider.core.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
//in redis need Serializable
public class User extends BaseEntity {

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private BigDecimal balance;
    @Column
    @OneToMany(targetEntity = Article.class)
    private List<Article> articles;

    @Id
    @Max(1000)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, columnDefinition = "timestamp default current_timestamp", updatable = false)
    @CreationTimestamp
    private Date createTime;
    @Column(insertable = false, columnDefinition = "timestamp default current_timestamp")
    @UpdateTimestamp
    private Date updateTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = Optional.ofNullable(age).orElse(new Integer(0));

    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }
}

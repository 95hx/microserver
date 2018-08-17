package cn.luv2code.sample.userprovider.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
//in redis need Serializable
public class User implements Serializable{
    @Id
    @Max(1000)
    private Long id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

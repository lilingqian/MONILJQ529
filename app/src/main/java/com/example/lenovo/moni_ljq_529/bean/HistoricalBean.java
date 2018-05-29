package com.example.lenovo.moni_ljq_529.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HistoricalBean {
    @Id
    private Long id;
    @Unique
    private String name;
    @Generated(hash = 1063713278)
    public HistoricalBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 633459717)
    public HistoricalBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
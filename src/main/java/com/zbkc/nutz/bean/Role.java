package com.zbkc.nutz.bean;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;

/**
 * Created by momo on 2017/4/14.
 */
@Table("角色")
public class Role {
    @Id
    private Long id;
    @Name
    @Column("角色名称")
    private String name;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

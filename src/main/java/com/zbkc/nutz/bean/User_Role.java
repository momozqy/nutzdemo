package com.zbkc.nutz.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by momo on 2017/4/14.
 */
@Table("用户_角色")
public class User_Role {

    @Column("用户ID")
    private Long userID;


    @Column("角色ID")
    private Long roleID;


    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }





}

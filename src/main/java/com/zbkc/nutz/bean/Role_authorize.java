package com.zbkc.nutz.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by momo on 2017/4/14.
 */
@Table("角色_权限")
public class Role_authorize {
    @Column("角色ID")
    private Long RoleID;

    @Column("权限ID")
    private Long authorizeID;

    //只读  或者 读写
    @Column("权限")
    private String type;

}

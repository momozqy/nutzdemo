package com.zbkc.nutz.controller;

import com.zbkc.nutz.bean.Role;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpSession;

/**
 * Created by momo on 2017/4/14.
 * 角色类
 */
@IocBean
@At("/role")
@Ok("json")
@Filters(@By(type=CheckSession.class, args={"UserID", "/"}))
@Fail("http:500")
public class RoleModule {
    @Inject
    protected Dao dao; // 就这么注入了,有@IocBean它才会生效

    @At
    public int count() {
        return dao.count(Role.class);
    }

    @At
    public Object add(@Param("..")Role Role) {// 两个点号是按对象属性一一设置
        NutMap re = new NutMap();
        String msg = checkRole(Role, true);
        if (msg != null){
            return re.setv("ok", false).setv("msg", msg);
        }
        role = dao.insert(Role);
        return re.setv("ok", true).setv("data", role);
    }
    protected String checkRole(Role role, boolean create) {
        if (role == null) {
            return "空对象";
        }
        if (role.getId() < 1) {
            return "Id非法";
        }

        if (create) {
            if (Strings.isBlank(role.getName()))
                return "名称不能为空";
            if (0 >= dao.count(Role.class, Cnd.where("name", "=", role.getName()))) {
                return "角色名已经存在";
            }
        }

        if (role.getName() != null)
            role.setName(role.getName().trim());

        return null;
    }
    @At
    public Object update(@Param("..")Role role) {
        NutMap re = new NutMap();
        String msg = checkRole(role, false);
        if (msg != null){
            return re.setv("ok", false).setv("msg", msg);
        }
        dao.updateIgnoreNull(role);//
        return re.setv("ok", true);
    }
    @At
    public Object delete(@Param("id")int id) {
        dao.delete(Role.class, id);
        return new NutMap().setv("ok", true);
    }
    @At
    public Object query(@Param("name")String name, @Param("..")Pager pager) {
        Cnd cnd = Strings.isBlank(name)? null : Cnd.where("name", "like", "%"+name+"%");
        QueryResult qr = new QueryResult();
        qr.setList(dao.query(Role.class, cnd, pager));
        pager.setRecordCount(dao.count(Role.class, cnd));
        qr.setPager(pager);
        return qr; //默认分页是第1页,每页20条
    }
}

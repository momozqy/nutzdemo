package com.zbkc.nutz.controller;

import com.zbkc.nutz.bean.Authorize;
import com.zbkc.nutz.controller.base.BaseModule;
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

/**
 * Created by momo on 2017/4/14.
 * 权限类
 */
@IocBean
@At("/Authorize")
@Ok("json")
@Fail("http:500")
public class AuthorizeModule extends BaseModule{

    @At
    public int count() {
        return dao.count(Authorize.class);
    }

    @At
    public Object add(@Param("..")Authorize authorize) {// 两个点号是按对象属性一一设置
        NutMap re = new NutMap();
        String msg = checkAuthorize(authorize, true);
        if (msg != null){
            return re.setv("ok", false).setv("msg", msg);
        }
        authorize = dao.insert(authorize);
        return re.setv("ok", true).setv("data", authorize);
    }
    protected String checkAuthorize(Authorize authorize, boolean create) {
        if (authorize == null) {
            return "空对象";
        }
        if (authorize.getId() < 1) {
            return "Id非法";
        }

        if (create) {
            if (Strings.isBlank(authorize.getName()))
                return "名称不能为空";
        }

        if (authorize.getName() != null)
            authorize.setName(authorize.getName().trim());

        return null;
    }
    @At
    public Object update(@Param("..")Authorize authorize) {
        NutMap re = new NutMap();
        String msg = checkAuthorize(authorize, false);
        if (msg != null){
            return re.setv("ok", false).setv("msg", msg);
        }
        dao.updateIgnoreNull(authorize);//
        return re.setv("ok", true);
    }
    @At
    public Object delete(@Param("id")int id) {
        dao.delete(Authorize.class, id);
        return new NutMap().setv("ok", true);
    }
    @At
    public Object query(@Param("name")String name, @Param("..")Pager pager) {
        Cnd cnd = Strings.isBlank(name)? null : Cnd.where("name", "like", "%"+name+"%");
        QueryResult qr = new QueryResult();
        qr.setList(dao.query(Authorize.class, cnd, pager));
        pager.setRecordCount(dao.count(Authorize.class, cnd));
        qr.setPager(pager);
        return qr; //默认分页是第1页,每页20条
    }
}

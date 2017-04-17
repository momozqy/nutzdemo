package com.zbkc.nutz.core;

import com.zbkc.nutz.bean.*;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.util.Date;

/**
 * Created by momo on 2017/4/14.
 */
public class MainSetup implements Setup{
    @Override
    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);

        dao.create(User.class,false);
        dao.create(Role.class,false);
        dao.create(Authorize.class,false);
        dao.create(Role_authorize.class,false);
        dao.create(User_Role.class,false);


        // 如果没有createTablesInPackage,请检查nutz版本
        Daos.createTablesInPackage(dao, "com.zbkc.nutz", false);
    }

    @Override
    public void destroy(NutConfig nutConfig) {

    }
}

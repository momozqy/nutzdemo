package com.zbkc.nutz.controller;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

/**
 * Created by momo on 2017/4/14.
 */
@IocBean
@At("/user")
@Ok("json")
@Fail("http:500")
public class UserModule {
    @Inject
    protected Dao dao; // 就这么注入了,有@IocBean它才会生效


}

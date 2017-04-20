package com.zbkc.nutz.controller;

import com.zbkc.nutz.core.MainSetup;
import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * Created by momo on 2017/4/13.
 */
@SetupBy(MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "conf/ioc/",
        // 这个package下所有带@IocBean注解的类,都会登记上
        "*anno", "com.zbkc.nutz",
        "*tx", // 事务拦截 aop
        "*async"}) // 异步执行aop
@ChainBy(args="conf/mvc/nutzdemo-mvc-chain.js")
@SessionBy(ShiroSessionProvider.class)
@Modules(scanPackage = true)
public class MainController {
}

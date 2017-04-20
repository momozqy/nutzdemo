package com.zbkc.nutz.controller.base;

/**
 * Created by momo on 2017/4/18.
 */
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

public abstract class BaseModule {

    /** 注入同名的一个ioc对象 */
    @Inject protected Dao dao;

}

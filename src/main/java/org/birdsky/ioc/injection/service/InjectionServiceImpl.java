package org.birdsky.ioc.injection.service;

import org.birdsky.ioc.injection.dao.InjectionDAO;

public class InjectionServiceImpl implements InjectionService {
    private InjectionDAO injectionDAO;
    public InjectionServiceImpl(InjectionDAO injectionDAO2){
        this.injectionDAO= injectionDAO2;
    }
    // 设值注入
    public void setInjectionDAO(InjectionDAO injectionDAO) {
        this.injectionDAO = injectionDAO;
    }

    @Override
    public void save(String arg) {
        System.out.println("服务器接收参数："+arg);
        arg = arg+":"+this.hashCode();
        injectionDAO.save(arg);
    }
}

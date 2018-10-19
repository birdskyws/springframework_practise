package org.birdsky.ioc.injection.dao;

public class InjectionDAOImpl implements InjectionDAO{
    @Override
    public void save(String arg) {
        System.out.println("保存数据库"+arg);
    }
}

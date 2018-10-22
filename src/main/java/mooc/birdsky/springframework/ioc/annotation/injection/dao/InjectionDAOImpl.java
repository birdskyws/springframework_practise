package mooc.birdsky.springframework.ioc.annotation.injection.dao;

import org.springframework.stereotype.Repository;

@Repository
public class InjectionDAOImpl implements InjectionDAO {
    @Override
    public void save(String arg) {
        System.out.println("保存数据库"+arg);
    }
}

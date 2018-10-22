package mooc.birdsky.springframework.ioc.annotation.injection.service;

import mooc.birdsky.springframework.ioc.annotation.injection.dao.InjectionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectionServiceImpl implements InjectionService {
    /**
     * InjectionDao 依靠 byType实现自动装配
     */
    @Autowired
    private InjectionDAO injectionDAO;

    public InjectionDAO getInjectionDAO() {
        return injectionDAO;
    }

    @Override
    public void save(String arg) {
        System.out.println("服务器接收参数："+arg);
        arg = arg+":"+this.hashCode();
        injectionDAO.save(arg);
    }
}

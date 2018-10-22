package mooc.birdsky.springframework.ioc.annotation.multibean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BeanList {
    /**
     * @Autowired 集合对象 list set map，会把容器环境中的对象全部获取。
     */
    @Autowired
    private List<BeanInterface> list;
    @Autowired
    private Map<String,BeanInterface> map;

    @Autowired
    @Qualifier("beanInterfaceOne")
    private BeanInterface beanInterface;

    public void printList(){
        if(list!=null && list.size()!=0){
            for(BeanInterface bean :list){
                System.out.println(bean.getClass().getName());
            }
        }
        else {
            System.out.println("list in null");
        }
    }

    public void printMap(){
        if(map!=null && map.size()!=0){
            for(Map.Entry<String,BeanInterface> entry :map.entrySet()){
                System.out.println("Name:"+entry.getKey());
                System.out.println("value type："
                        +entry.getValue().getClass().getName());
            }
        }
        else {
            System.out.println("map in empty");
        }


        System.out.println(beanInterface.getClass().getName());
    }
}

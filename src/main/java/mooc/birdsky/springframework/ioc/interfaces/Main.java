package mooc.birdsky.springframework.ioc.interfaces;

/**
 * 时间 2018 10.18
 * 函数作用：功能测试
 */
public class Main {
    public static void main(String[] args) {
        OneInterface oif = new OneInterfaceImpl();
        System.out.println(oif.hello("青岛"));
    }
}

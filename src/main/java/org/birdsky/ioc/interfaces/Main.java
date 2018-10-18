package org.birdsky.ioc.interfaces;

/**
 * 时间 2018 10.18
 * 函数作用：功能测试
 */
public class Main {
    public static void main(String[] args) {

        OneInterface oif = new OneInterfaceImpl();
        String newWord = oif.hello("qidao");
        System.out.println(newWord);
    }
}

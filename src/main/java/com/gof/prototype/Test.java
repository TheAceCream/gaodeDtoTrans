package com.gof.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式
 *
 * 用原型实例制定创建对象的种类，并通过拷贝这些原型创建新对象
 *
 * 实例来源：http://blog.csdn.net/u013916933/article/details/51584913
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        List<String> famMem = new ArrayList<>(); // 家庭成员名单
        famMem.add("Papa");
        famMem.add("Mama");

        // 创建初始简历
        Resume resume1 = new Resume("Jobs");
        resume1.setPersonal("Male", 26, famMem);
        resume1.setWork("2013/8/1 - 2015/6/30", "Huawei");

        // 通过简历1复制出简历2，并对家庭成员和工作经验进行修改
        Resume resume2 = resume1.clone();
        resume2.setName("Tom");
        resume2.famMem.add("Brother");
        resume2.setWork("2015/7/1 - 2016/6/30", "Baidu");

        resume1.display();
        resume2.display();
    }
}

package com.gof.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式 产品类
 */
public class Product {
    List<String> parts = new ArrayList<>();

    public void add(String part){
        parts.add(part);
    }

    public void show(){
        System.out.println("产品创建---");
        for(String str:parts){
            System.out.println(str);
        }
    }
}

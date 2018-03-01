package com.gof.iterator;

/**
 * 迭代器模式 迭代元素类
 */
public class Item {
    /**商品名称*/
    private String name;

    /**价格*/
    private double price;

    /**描述*/
    private String desc;

    /**数量*/
    private int count;

    public Item(String name, double price, String desc, int count) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", price=" + price + ", desc=" + desc
                + ", count=" + count + "]";
    }

}

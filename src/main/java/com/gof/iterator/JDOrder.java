package com.gof.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器模式 具体聚合类
 */
public class JDOrder implements Order {

    /**
     * 京东用集合装订单项
     */
    private List<Item> list = new ArrayList<Item>();

    public JDOrder() {
        add("iphone6", 5000.00, "一部手机", 2);
        add("mbp", 16000.00, "一台电脑", 1);
        add("西门子洗衣机", 3000.00, "一台洗衣机", 3);
    }

    /**
     * 添加订单条目
     */
    public void add(String name, double price, String desc, int count) {
        list.add(new Item(name, price, desc, count));
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item> {

        private Iterator<Item> it = list.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Item next() {
            return it.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("目前不支持删除操作");
        }

    }

}


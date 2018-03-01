package com.gof.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 迭代器模式 具体聚合类
 */
public class TBOrder implements Order{
    private int size=3;

    private Item[] orders=new Item[size];

    private int index=0;

    public TBOrder(){
        add("天猫1", 1111, "天猫活动1", 1);
        add("天猫2", 1111, "天猫活动1", 1);
        add("天猫3", 1111, "天猫活动1", 1);
        add("天猫4", 1111, "天猫活动1", 1);
        add("天猫5", 1111, "天猫活动1", 1);
        add("天猫6", 1111, "天猫活动1", 1);
        add("天猫7", 1111, "天猫活动1", 1);
        add("天猫8", 1111, "天猫活动1", 1);
    }

    /**添加订单条目*/
    public void add(String name, double price, String desc, int count) {

        //如果超过数组大小，就扩容
        if(index>=size-1){
            resize();
        }

        orders[index++]=new Item(name, price, desc, count);
    }

    /**扩容*/
    private void resize() {
        size=size<<1;//移位运算符--相当于size=size*2
        Item[] newItems=new Item[size];
        //将原始数组内容拷贝到新数组中去
        for(int i=0;i<orders.length;i++){
            newItems[i]=orders[i];
        }
        orders=newItems;
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item>{

        private int curr=0;

        @Override
        public boolean hasNext() {
            return orders[curr]!=null;
        }

        @Override
        public Item next() {
            if(hasNext()){
                return orders[curr++];
            }else{
                throw new NoSuchElementException("没有这个元素");
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("目前不支持删除操作");
        }

    }

}

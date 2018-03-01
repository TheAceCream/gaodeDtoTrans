package com.gof.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 复合享元模式的具体享元实现
 *
 * 复合式享元相当于多个单纯享元的集合。也就是说复合享元相当于对单纯享元进行了一个再分组，在复合享元的每个分组里又是一个独立的单纯享元模式。
 */
public class ConcreteCompositeFlyweight implements Flyweight {

    private Map<Integer, Flyweight> labels = new HashMap<Integer, Flyweight>();

    public void add(int key, Flyweight flyweight) {
        labels.put(key, flyweight);
    }

    @Override
    public void operation(String extrinsicState) {
        Flyweight flyweight = null;
        for (Object key : labels.keySet()) {
            flyweight = labels.get(key);
            flyweight.operation(extrinsicState);
        }
    }
}

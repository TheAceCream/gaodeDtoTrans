package com.gof.flyweight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 享元工厂
 *
 * 在享元模式中，有一个至关重要的模块就是工厂模块了。在 Flyweight Factory 里维护了一个 Flyweight 池(存放内部状态)，
 * Flyweight Factory 就是通过这个 Flyweight 池对整个享元模式进行控制。
 */
public class FlyweightFactory {
    private Map<Integer, Flyweight> labels = new HashMap<Integer, Flyweight>();

    /**
     * 单纯享元工厂
     */
    public Flyweight factory(String intrinsicState) {

        int hashCode = intrinsicState.hashCode();

        Flyweight fly = labels.get(hashCode);

        if (fly == null) {
            fly = new ConcreteFlyweight(intrinsicState);
            labels.put(hashCode, fly);
        }

        return fly;
    }

    /**
     * 复合享元工厂
     */
    public Flyweight compositeFactory(List<String> intrinsicStates) {
        ConcreteCompositeFlyweight flyweight = new ConcreteCompositeFlyweight();

        for (String intrinsicState : intrinsicStates) {
            flyweight.add(intrinsicState.hashCode(), factory(intrinsicState));
        }

        return flyweight;
    }
}

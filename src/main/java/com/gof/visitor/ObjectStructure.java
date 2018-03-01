package com.gof.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * ObjectStructure类，能枚举它的元素，可以提供一个高层的接口以允许访问者访问他的元素
 */
public class ObjectStructure {
    private List<Element> list = new ArrayList<Element>();

    public void attach(Element element) {
        list.add(element);
    }

    public void detach(Element element) {
        list.remove(element);
    }

    public void accpet(Visitor visitor) {
        for (Element element : list) {
            element.accept(visitor);
        }
    }
}

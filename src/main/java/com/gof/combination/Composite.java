package com.gof.combination;

import java.util.LinkedList;
import java.util.List;

/**
 * 组合模式
 * Composite 定义有枝节点行为，用来存储子部件，在Component接口中实现与子部件有关的操作，比如添加add和删除remove
 */
public class Composite extends Component{
    private List<Component> children = new LinkedList<>();

    public Composite(String name){
        super(name);
    }

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public void displaty(int depth) {
        char[] c = new char[depth];
        for(int i = 0;i<depth;i++){
            c[i] = '-';
        }
        System.out.println(new String(c)+name);
        for(Component component:children){
            component.displaty(depth+2);
        }
    }
}

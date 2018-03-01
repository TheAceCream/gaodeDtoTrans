package com.gof.combination;

/**
 * 组合模式
 *
 * Leaf 在组合中表示叶节点对象，叶节点没有子节点
 */
public class Leaf extends Component{

    public Leaf(String name){
        super(name);
    }
    @Override
    public void add(Component c) {
        System.out.println("不能添加叶节点");
    }

    @Override
    public void remove(Component c) {
        System.out.println("不能移除叶节点");
    }

    @Override
    public void displaty(int depth) {
        char[] c = new char[depth];
        for(int i = 0;i<depth;i++){
            c[i] = '-';
        }
        System.out.println(new String(c)+name);
    }
}

package com.gof.combination;

/**
 * 组合模式
 * 将对象组合成属性接口以表示‘部分-整体’的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性
 *
 * 组合模式定义了包含基本对象和组合对象的类层次结构。基本对象可以被组合成更复杂的组合对象，儿这个组合对象又可以被组合，这样不断地递归下去，
 * 客户端代码中，任何用到基本对象的地方都可以使用组合对象了。用户不关心到底是处理一个叶节点还是处理一个组合组件，也就用不着定义组合而写一些判断语句了。
 * 组合模式让客户可以一致地使用组合结构的单个对象
 *
 * 组合模式分为 透明方式 与 安全方式
 * 透明方式
 * 在Component中声明所有用来管理子对象的方法，其中包括add（）和remove（）等。
 * 这样实现Component接口的所有子类都具备了add（）和remove（）。
 * 好处就是叶节点和枝节点对于外界没有区别，它们具备了完全一致的行为接口。但是问题也很名此案，因为Leaf类本身不具备add（）和remove（）方法的功能，所以实现他们是没有意义的。
 *
 * 安全方式
 * 在Component接口中不回去声明add（）和remove（）方法，那么子类的Leaf也就不需要去实现它，而是在Component声明所有用来管理子类对象的方法，不过由于不够透明，
 * 所以输液和树枝类将不具有相同的接口，客户端的调用需要做相应的判断，带来了不便。
 */
public class Test {
    public static void main(String[] args) {
        Composite root = new Composite("root");
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        Composite comp = new Composite("Composite X");
        comp.add(new Leaf("Leaf XA"));
        comp.add(new Leaf("Leaf XB"));

        root.add(comp);

        Composite comp2 = new Composite("Composite XY");
        comp2.add(new Leaf("Leaf XYA"));
        comp2.add(new Leaf("Leaf XYB"));
        comp.add(comp2);

        Leaf leaf = new Leaf("Leaf D");
        root.add(leaf);
        //root.remove(leaf);

        root.displaty(1);
    }
}

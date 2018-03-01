package com.gof.factoryabstract;

/**
 * 抽象工厂模式
 *
 * 抽象工厂模式的优缺点
 * 最大的好处就是易于产品系列，由于具体工厂类 IFactory factoryI =  new ProductFactoryI()
 * 在一个应用中只需要实例化一次，这就使得改变一个应用的具体工厂变得非常容易，他只需要改变具体工厂即可使用不同的产品配置
 * 他让具体的创建实力过程与客户端分离，客户端是通过他们的抽象接口操纵示例，产品的具体类名也被集体工厂实现分离，不会出现在客户端代码中
 */
public class Test {
    public static void main(String[] args) throws Exception {
        /*IFactory factoryI =  new ProductFactoryI();
        IFactory factoryII =  new ProductFactoryII();

        IProductA pa = factoryI.createProductA();
        IProductB pb = factoryI.createProductB();

        IProductA paa = factoryII.createProductA();
        IProductB pbb = factoryII.createProductB();

        pa.productMethod();
        pb.productMethod();

        paa.productMethod();
        pbb.productMethod();*/

        /***********************************************/

        /*
        * 通过反射方式 简化工厂代码
        */
        IProductA product = (IProductA)Class.forName("com.gof.factoryabstract.ProductA").newInstance();
        product.productMethod();

        //System.out.println(ProductA.class.getName());
    }
}

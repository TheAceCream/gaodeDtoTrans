package com.gof.factoryabstract;

/**
 * 抽象工厂的改进
 *
 * 去除 IFactory、ProductFactoryI、ProductFactoryII 用简单工厂的思想改进抽象工厂
 *
 */
public class Access {

    public IProductA createProductA(String productAName) throws Exception {
        return (IProductA)Class.forName(productAName).newInstance();
    }

    public IProductB createProductB(String productBName) throws Exception{
        return (IProductB)Class.forName(productBName).newInstance();
    }

}

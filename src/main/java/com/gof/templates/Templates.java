package com.gof.templates;

/**
 * 模板方法模式 模板类
 */
public abstract class Templates {

    public abstract void primitiveOperationA();

    public abstract void primitiveOperationB();

    public void TemplateMethod(){
        primitiveOperationA();
        System.out.println("模板方法");
        primitiveOperationB();
    }
}

package com.gof.templates;

/**
 * 模板方法模式 测试
 *
 * 模板方法模式是通过吧不变的行为搬移到超类，去除子类中的重复代码来体现他的优势。
 *
 * 模板方法模式就是提供了一个很好的代码服用平台
 *
 * 当不变的和可变的行为在方法的子类实现中混合在一起的时候，不变的行为就会在子类中重复出现。
 * 我们通过模板方法模式吧这些行为搬移到单一的地方，这样就帮助子类摆脱重复的不变行为的纠缠
 */
public class Test {
    public static void main(String[] args) {
        Templates tempA = new ConcreteClassA();
        Templates tempB = new ConcreteClassB();
        tempA.TemplateMethod();
        tempB.TemplateMethod();
    }
}

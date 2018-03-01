package com.gof.interpreter;

/**
 * NonterminalExpression（非终结表达式）为文法中的非终结符实现解释操作，对文法中的每一条规则都需要一个具体的非终结表达式类，
 * 通过实现抽象表达式的interpret()方法实现解释操作，解释操作以递归的方式调用每条规则所代表符号的实例变量
 */
public class NonterminalExpression extends AbstractExpression {
    @Override
    public void interpret(Context context) {
        System.out.println("非終結符解释器");
    }
}

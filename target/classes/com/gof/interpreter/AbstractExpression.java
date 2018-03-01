package com.gof.interpreter;

/**
 * AbstractExpression(抽象表达式)，声明一个抽象的解释操作，这个接口作为抽象语法树中所有节点（即终结符表达式和非终结符表达式）所共享
 */
public abstract class AbstractExpression {
    public abstract void interpret(Context context);
}

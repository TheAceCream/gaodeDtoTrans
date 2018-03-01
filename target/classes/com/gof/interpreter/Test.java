package com.gof.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * 解释器模式
 * 如果特定类型的问题发生的频率足够高，那么可能就值得将该问题的各个实力表述为一个简单语言中的句子。这样就可以构建一个解释器，
 * 该解释器通过解释这些句子来解决该问题。
 *
 * 使用解释器模式可以很容易的改变和扩展文法，因为该模式使用类来表示文法规则，你可以使用继承来改变或扩展文法，页比较容易实现文法，
 * 因为定义抽象语法树种各个节点的实现大体类似，这些类都易于直接编写。
 *
 * 解释器模式也有不足，解释器模式为文法中的每一条规则至少定义了一个类，因此包含许多规则的文法可能难以管理和维护。
 * 建议当文法非常复杂时，使用其他的技术如语法分析器或者编译生成器来处理
 *
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        List<AbstractExpression> list = new ArrayList<AbstractExpression>();
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());
        for (AbstractExpression abstractExpression : list) {
            abstractExpression.interpret(context);
        }

    }
}

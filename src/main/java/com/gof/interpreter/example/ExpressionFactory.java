package com.gof.interpreter.example;

import java.util.Hashtable;

/**
 * 因为解释器会不断重复使用，所以这里用到了享元模式来生成各种具体的解释器
 */
public class ExpressionFactory {
    //使用hashtable变量储存编译器列表
    private static Hashtable<Object, AbstractExpression> expressions = new Hashtable<Object, AbstractExpression>();
    public static AbstractExpression createExpression(String text) {
        String key = text.substring(0,1);
        AbstractExpression expression = null;
        switch (key) {
            case "C":
            case "D":
            case "E":
            case "F":
            case "G":
            case "A":
            case "B":
                expression = expressions.get("note");
                if (expression == null) {
                    expression = new NoteExpression();
                    expressions.put("note", expression);
                } break;
            case "O":
                expression = expressions.get("scale");
                if (expression == null) {
                    expression = new ScaleExpression();
                    expressions.put("scale", expression);
                } break;
            case "S":
                expression = expressions.get("speed");
                if (expression == null) {
                    expression = new SpeedExpression();
                    expressions.put("speed", expression);
                }
                break;
        }
        //System.out.println(expressions.size());
        return expression;
    }

}

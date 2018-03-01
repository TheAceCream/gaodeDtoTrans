package com.gof.interpreter.example;

/**
 * 例子：
 * 以手机编辑铃声为例：
 * 首先制定规则，S代表速度，500低速，1000中速，1500高速，O代表音阶，‘O 1’代表低音阶，‘O 2’代表中音阶，‘O 3’代表高音阶，‘P’代表休止符，
 * ‘CDEFGAB’代表音调，1表示一拍，0.5表示半拍，依此类推。
 * 所有的字母和数字使用空格分隔。例：“O 2 E 0.5 G 0.5 A 3 E 0.5”
 *
 * 实例参考：https://www.imooc.com/article/16230
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context() ;
        //设置演奏文本
        context.setPlayText("S 1500 O 2 E 0.5 G 0.5 A 3 E 0.5 G 0.5 D 3 E 0.5 " + "G 0.5 A 0.5 O 3 C 1 O 2 A 0.5 G 1 C 0.5 E 0.5 D 3 ");
        AbstractExpression expression = null;
        try {
            while (context.getPlayText().length()>0)
            {
                //使用享元模式
                expression = ExpressionFactory.createExpression(context.getPlayText());
                expression.interpret(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

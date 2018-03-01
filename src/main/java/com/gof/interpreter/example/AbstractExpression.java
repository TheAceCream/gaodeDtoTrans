package com.gof.interpreter.example;

/**
 * 表达式类AbstractExpression
 */
public abstract class AbstractExpression {
    //将演奏文本解析为对应的key和value，根据key值指定解释器
    public void interpret(Context context){
        if(context.getPlayText().length()==0){
            return;
        }
        //获取文本第一个字符作为key值
        String playText = context.getPlayText();
        String key = playText.substring(0,1);
        //获取key值后截取文本
        playText = playText.substring(2);
        //获取截取后文本第一个字段作为value值
        Double value = Double.parseDouble(playText.substring(0,playText.indexOf(" ")));
        //获取value值后截取文本
        playText = playText.substring(playText.indexOf(" ")+1);
        context.setPlayText(playText);
        //解释器执行
        excute(key, value);
    };

    public abstract void excute(String key, Double value);
}

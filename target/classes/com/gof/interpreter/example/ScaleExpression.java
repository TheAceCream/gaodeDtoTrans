package com.gof.interpreter.example;

/**
 * 音调类ScaleExpression
 */
public class ScaleExpression extends AbstractExpression {
    @Override
    public void excute(String key, Double value) {
        String scale = "";
        switch (value.intValue()) {
            case 1:
                scale = "low";
                break;
            case 2:
                scale = "middle";
                break;
            case 3:
                scale = "high";
                break;
        }
        System.out.print(scale+" ");
    }
}

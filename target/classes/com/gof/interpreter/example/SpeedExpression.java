package com.gof.interpreter.example;

/**
 * 音速类SpeedExpression
 */
public class SpeedExpression extends AbstractExpression {
    @Override
    public void excute(String key, Double value) {
        String speed = "";
        switch (value.intValue()) {
            case 500:
                speed = "slow";
                break;
            case 1000:
                speed = "middle";
                break;
            case 1500:
                speed = "fast";
                break;
        }
        System.out.print(speed+" ");
    }
}

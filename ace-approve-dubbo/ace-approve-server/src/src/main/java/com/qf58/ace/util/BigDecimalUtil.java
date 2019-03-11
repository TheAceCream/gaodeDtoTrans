package com.qf58.ace.util;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/17 11:49
 * Time: 14:15
 */
public final class BigDecimalUtil {

    private BigDecimalUtil(){}

    /**
     * 整形相除保留两位小数
     * 舍入：银行家舍入法
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public static String stringDiv(Integer dividend, Integer divisor){
        BigDecimal bigDecimalDivisor = new BigDecimal(divisor);
        return new BigDecimal(dividend).divide(bigDecimalDivisor,2,BigDecimal.ROUND_HALF_EVEN).toString();
    }

    /**
     * 整形相除保留两位小数
     * 舍入：银行家舍入法
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public static String stringDiv(Long dividend, Long divisor){
        BigDecimal bigDecimalDivisor = new BigDecimal(divisor);
        return new BigDecimal(dividend).divide(bigDecimalDivisor,2,BigDecimal.ROUND_HALF_EVEN).toString();
    }

    /**
     * 整形相除保留两位小数
     * 舍入：银行家舍入法
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public static Double doubleDiv(Integer dividend, Integer divisor){
        BigDecimal bigDecimalDivisor = new BigDecimal(divisor);
        String result = new BigDecimal(dividend).divide(bigDecimalDivisor,2,BigDecimal.ROUND_HALF_EVEN).toString();
        return Double.parseDouble(result);
    }

    /**
     * 整形相除保留两位小数
     * 舍入：银行家舍入法
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public static Double doubleDiv(Long dividend, Long divisor){
        BigDecimal bigDecimalDivisor = new BigDecimal(divisor);
        String result = new BigDecimal(dividend).divide(bigDecimalDivisor,2,BigDecimal.ROUND_HALF_EVEN).toString();
        return Double.parseDouble(result);
    }

    /**
     * 乘法
     * @param bigDecimal 数
     * @param multiplier 乘数
     * @return
     */
    public static Long longMul(BigDecimal bigDecimal,Long multiplier){
        BigDecimal bigDecimalMul = bigDecimal.multiply(new BigDecimal(multiplier));
        return bigDecimalMul.longValue();
    }

    /**
     * 乘法
     * @param bigDecimal 数
     * @param multiplier 乘数
     * @return
     */
    public static Long longMul(BigDecimal bigDecimal,Integer multiplier){
        BigDecimal bigDecimalMul = bigDecimal.multiply(new BigDecimal(multiplier));
        return bigDecimalMul.longValue();
    }


}

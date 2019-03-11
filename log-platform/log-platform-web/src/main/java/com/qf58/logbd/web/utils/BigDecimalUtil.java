package com.qf58.bd.web.utils;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/8/9 16:54
 * Time: 14:15
 */
public class BigDecimalUtil {


    /**
     * 整形相除保留两位小数
     * 舍入：银行家舍入法
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public static String stringDiv(Integer dividend, Integer divisor){
        BigDecimal bigDecimalDivisor = new BigDecimal(divisor);
        String result = new BigDecimal(dividend).divide(bigDecimalDivisor,2,BigDecimal.ROUND_HALF_EVEN).toString();
        return result;
    }

    /**
     * 整形相除保留两位小数
     * 舍入：银行家舍入法
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public static String stringDiv(Double dividend, Double divisor){
        BigDecimal bigDecimalDivisor = new BigDecimal(divisor);
        String result = new BigDecimal(dividend).divide(bigDecimalDivisor,2,BigDecimal.ROUND_HALF_EVEN).toString();
        return result;
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
        String result = new BigDecimal(dividend).divide(bigDecimalDivisor,2,BigDecimal.ROUND_HALF_EVEN).toString();
        return result;
    }


}

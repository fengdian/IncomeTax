package com.wxf.incometax.utils;

/**
 * 计算个人应缴保险钱数
 */
public class InsuranceUtils {
    public   static  double getInsurance(double base){

        return  base*0.102+3;
    }
}

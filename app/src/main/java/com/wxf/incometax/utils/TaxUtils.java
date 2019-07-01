package com.wxf.incometax.utils;

public class TaxUtils {

    public static  double getTax(double base,int[] levels, int[] rates){
        double tax =0;
        for(int i =0;i<levels.length;i++){
           if(base>=levels[i]){
               tax =tax+levels[i]*rates[i]*1.0/100;
               base = base -levels[i];
           }else {
               tax =tax+base*rates[i]*1.0/100;
               break;
           }

        }

        return  tax;

    }
}

package com.ibeetl.utils;

/**
 * 比例计算
 */
public class RateUtils {


    /**
     * 获取比例金额
     *
     * @param rate
     * @return
     */
    public static long getRateAmount(long amount, int rate) {
        return amount * rate / Const.RATE_BASE;
    }


}

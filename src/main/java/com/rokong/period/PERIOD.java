package com.rokong.period;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 주기에 관한 enum이다.
 * 월, 분기, 반기, 연도 4가지 주기가 있으며
 * 주기에 따른 세부주기로 해당하는 월을 구할 수 있다.
 *
 * @author rokong
 * @since 2021.03.22
 */
public enum PERIOD {
    MONTH("09", 1),
    QUARTER("11", 3),
    SEMIANNUAL("12", 6),
    ANNUAL("13", 12);

    private String codeValue;
    private int monthCnt;

    PERIOD(String codeValue, int monthCnt){
        this.codeValue = codeValue;
        this.monthCnt = monthCnt;
    }

    private static String defaultString(Object obj){
        return StringUtils.defaultString((String) obj);
    }

    private static String defaultString(Object obj, String str){
        return StringUtils.defaultString((String) obj, str);
    }

    public boolean codeEquals(Object obj){
        return this.codeValue.equals(defaultString(obj));
    }

    public static String format(int month){
        return StringUtils.leftPad(defaultString(month, "1"), 2, "0");
    }

    public int startMonthOf(Object periodDtl){
        if(this.equals(PERIOD.ANNUAL)){
            periodDtl = null;
        }

        int month = Integer.parseInt(defaultString(periodDtl, "1"));
        month = ((month-1) * monthCnt) + 1;

        return month;
    }

    public int endMonthOf(Object periodDtl){
        int month = startMonthOf(periodDtl) + monthCnt - 1;
        return month;
    }

    public List<String> getMonthList(Object periodDtl){
        List<String> monthList = new ArrayList<>();

        if(this.equals(PERIOD.ANNUAL)){
            periodDtl = null;
        }

        int month = startMonthOf(periodDtl);
        int endMonth = endMonthOf(periodDtl);

        while(month <= endMonth){
            monthList.add(format(month));
            month++;
        }

        return monthList;
    }

    public static PERIOD getObject(Object codeValue){
        for(PERIOD period : PERIOD.values()){
            if(period.codeEquals(codeValue)){
                return period;
            }
        }

        return PERIOD.MONTH;
    }
}

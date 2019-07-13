package com.cwh.myspringbootutils.help;

import java.util.Date;

public class DateUtil {

    private DateUtil() {
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurDate(){
        Date d = new Date();
        return d;
    }

}

package com.ischoolbar.programmer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static String getFormatDate(Date date,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}

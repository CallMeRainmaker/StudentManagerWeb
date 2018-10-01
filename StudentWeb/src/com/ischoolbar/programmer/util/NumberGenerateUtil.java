package com.ischoolbar.programmer.util;

import java.util.Date;

public class NumberGenerateUtil {
    public static String NumberGenerate(int classid){
        String number = "";
        number = "S" + classid + System.currentTimeMillis();
        return number;
    }

    public static String TeacherNumberGenerate(int classid){
        String number = "";
        number = "T" + classid + System.currentTimeMillis();
        return number;
    }
}

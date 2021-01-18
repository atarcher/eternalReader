package com.gbh.eternalreader.utils;

/**
 * Created by gbh
 * Date 2021/1/18
 * Description
 */
public class TextUtil {

    public static boolean isEmpty(String str){
        if (str != null){
            str = str.replace(" ","");
        }
        return str == null || str.equals("");
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}

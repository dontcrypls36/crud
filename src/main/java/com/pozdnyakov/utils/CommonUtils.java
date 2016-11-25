package com.pozdnyakov.utils;

import com.pozdnyakov.model.Role;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class CommonUtils {
    public static String roles(Set<Role> roleSet){
        String result = "";
        for (Role str : roleSet){
            result += str.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

    public static String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String result = sdf.format(date);
        return result;
    }
}

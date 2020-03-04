package com.ifchange.regressiontest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    public static String typeToClassName(String typeName) {
        if (typeName.contains("byte") || typeName.contains("Byte")) {
            return "java.lang.Byte";
        } else if (typeName.contains("short") || typeName.contains("Short")) {
            return "java.lang.Short";
        } else if (typeName.contains("int") || typeName.contains("Int")) {
            return "java.lang.Int";
        } else if (typeName.contains("long") || typeName.contains("Long")) {
            return "java.lang.Long";
        } else if (typeName.contains("float") || typeName.contains("Float")) {
            return "java.lang.Float";
        } else if (typeName.contains("double") || typeName.contains("Double")) {
            return "java.lang.Double";
        } else if (typeName.contains("char") || typeName.contains("Char")) {
            return "java.lang.Char";
        } else if (typeName.contains("boolean") || typeName.contains("Boolean")) {
            return "java.lang.Boolean";
        } else if (typeName.contains("string") || typeName.contains("String")) {
            return "java.lang.String";
        } else {
            return null;
        }
    }

}

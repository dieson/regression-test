package com.ifchange.regressiontest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @ClassName: JsonUtil
 * @Description: Json工具类
 * @author: Dieson Zuo
 * @date: Created in 16:28 2018/12/13
 */
public class JsonUtil {
    /***
     * 字符串类型转JSONObject
     * @param jsonStr
     * @return
     */
    public static JSONObject strToJson(String jsonStr) {
        return JSONObject.parseObject(jsonStr);
    }

    public static String objToJson(Object object) {
        return JSONObject.toJSONString(object);
    }

    public static <T> T jsonToObject(String jsonStr, Class<T> classOfT) {
        return JSON.parseObject(jsonStr, classOfT);
    }

    public static <T> T mapToObj(Object object, Class<T> classOfT) {
        String jsonStr = JSONObject.toJSONString(object);
        return JSON.parseObject(jsonStr, classOfT);
    }

    public static <T> List<T> jsonToList(String jsonStr, Class<T> classOfT) {
        return JSON.parseArray(jsonStr, classOfT);
    }
}

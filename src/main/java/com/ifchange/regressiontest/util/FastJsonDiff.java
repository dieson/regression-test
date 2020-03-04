package com.ifchange.regressiontest.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

/**
 * @ClassName: FastJsonDiff
 * @Description: JSON数据比较
 * @author: Dieson Zuo
 * @date: Created in 11:10 2018/12/21
 */
public class FastJsonDiff {
    @SuppressWarnings("unchecked")
    public static boolean compareJson(JSONObject expect, JSONObject actual) {
        for (String key : expect.keySet()) {
            if (!compareJson(expect.get(key), actual.get(key))) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareJson(Object expect, Object actual) {
        if (expect instanceof JSONObject) {
            return compareJson((JSONObject) expect, (JSONObject) actual);
        } else if (expect instanceof JSONArray) {
            return compareJson((JSONArray) expect, (JSONArray) actual);
        } else if (expect instanceof String) {
            try {
                return compareJson(expect.toString(), actual.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return compareJson(expect.toString(), actual.toString());
        }
    }

    public static boolean compareJson(JSONArray expect, JSONArray actual) {
        if (expect == null || actual == null) {
            return false;
        } else if (expect.size() > actual.size()) {
            return false;
        }

        Iterator i1 = expect.iterator();
        Iterator i2 = actual.iterator();
        while (i1.hasNext()) {
            if (!compareJson(i1.next(), i2.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareJson(String expect, String actual) {
        return expect.equals(actual);
    }

}

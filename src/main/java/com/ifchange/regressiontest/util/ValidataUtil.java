package com.ifchange.regressiontest.util;

import com.ifchange.regressiontest.constant.ResponseCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @ClassName: ValidataUtil
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:19 2019/4/2
 */
public class ValidataUtil {
    public static String judgeValidata(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError error : list) {
                stringBuilder.append(error.getDefaultMessage()).append(";");
            }
            return stringBuilder.toString();
        }
        return ResponseCode.PARAM_CORRECT.getDesc();
    }
}

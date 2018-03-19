package com.neighbor.unitl;

import com.neighbor.exception.NeiException;
import org.apache.commons.lang3.StringUtils;

/**
 * 工具类
 */
public class Utils {

    /**
     * 字符串空值校验
     * @param str
     * @param message
     */
    public static void checkString(String str ,String message){
        if(StringUtils.isBlank(str)){
            throw new NeiException(message);
        }
    }
}

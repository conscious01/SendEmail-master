package utils;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Created by wison on 2017/3/8.
 */

public class JsonUtil {

    //判断字符串是否json 是返回true
    public static boolean isJson(String json) {
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

}

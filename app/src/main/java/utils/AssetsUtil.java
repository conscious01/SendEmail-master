package utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Assets 工具类
 * Created by wison on 2017/7/4.
 */
public class AssetsUtil {

    //读取assets中的文件
    public static String readFromAssets(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);//此处为要加载的json文件名称
            return readTextFromSDCard(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //将传入的is一行一行解析读取出来出来
    public static String readTextFromSDCard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder buffer = new StringBuilder("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }

}

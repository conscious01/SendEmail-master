package com.example.sendemail.mail;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import net.api.ApiHelper;
import net.api.ApiResultSubscriber;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.leancloud.AVObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * +----------------------------------------------------------------------
 * | 59197696@qq.com
 * +----------------------------------------------------------------------
 * | 功能描述:
 * +----------------------------------------------------------------------
 * | 时　　间: 2019/3/5.
 * +----------------------------------------------------------------------
 * | 代码创建: 张云鹏
 * +----------------------------------------------------------------------
 * | 版本信息: V1.0.0
 * +----------------------------------------------------------------------
 **/

public class MailUtil {

    public static String TAG = "MailUtil";
    //QQ邮箱

    private static final String HOST = "smtp.qq.com";
    private static final String PORT = "587";
    //发送方邮箱
    private static final String FROM_ADD = "769757583@qq.com";
    //发送方邮箱授权码
    private static final String FROM_PSW = "anwdirbkofcdbbie";


    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void send(String toAdd, String title, String content) {
//        final MailInfo mailInfo = creatMail(toAdd, content);
//        final MailSender sms = new MailSender();
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                sms.sendTextMail(mailInfo);
//            }
//        });


        HashMap<String, String> map = new HashMap<>();
        map.put("phone", title);
        map.put("content", content);
        System.out.println("map-->"+map);

        String json = "{\"phone\":\"" + title + "\",\"content\":\"" + content + "\"}";

        ApiHelper.doPost2("exe", ApiHelper.CreateBody(map), new ApiResultSubscriber() {
            @Override
            public void onResponse(JsonElement json) {
                System.out.println("onResponse-->"+json.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
                System.out.println("onError-->"+throwable.toString());

            }
        });


        // 构建对象
        AVObject avObject = new AVObject("SMS");
        // 为属性赋值
        avObject.put("title", title);
        avObject.put("content", content);
        // 将对象保存到云端
        avObject.saveInBackground().subscribe(new Observer<AVObject>() {
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(AVObject todo) {
                // 成功保存之后，执行其他逻辑
                System.out.println("保存成功。objectId：" + todo.getObjectId());
            }

            public void onError(Throwable throwable) {
                System.out.println("onError-->" + throwable.toString());
                // 异常处理
            }

            public void onComplete() {
            }
        });

    }


    @NonNull
    private static MailInfo creatMail(String toAdd, String content) {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost(HOST);
        mailInfo.setMailServerPort(PORT);
        mailInfo.setValidate(true);
        mailInfo.setUserName(FROM_ADD);
        mailInfo.setPassword(FROM_PSW);
        mailInfo.setFromAddress(FROM_ADD);
        mailInfo.setToAddress(toAdd);
        mailInfo.setSubject("收到新短信");
        mailInfo.setContent(content);
        return mailInfo;
    }
}

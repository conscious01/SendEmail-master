package com.example.sendemail

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cn.leancloud.AVObject
import com.example.sendemail.mail.MailUtil
import com.google.gson.JsonElement
import com.xdandroid.hellodaemon.DaemonEnv
import com.xdandroid.hellodaemon.IntentWrapper
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import net.api.ApiConstants
import net.api.ApiHelper
import net.api.ApiResultSubscriber
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rx.Observable
import rx.Observer
import sample.TraceServiceImpl
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    companion object {
        val SP_ADDRESS = "sp_email"
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission()
        }

        //稍微做一下保活
//        val component = ComponentName(packageName, "com.example.sendemail.MailService")
//        packageManager.setComponentEnabledSetting(
//            component,
//            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//            PackageManager.DONT_KILL_APP
//        )

        initView()
        initListener()

        //需要在 Application 的 onCreate() 中调用一次 DaemonEnv.initialize()
        DaemonEnv.initialize(this, TraceServiceImpl::class.java, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL)
        TraceServiceImpl.sShouldStopService = false
        DaemonEnv.startServiceMayBind(TraceServiceImpl::class.java)
    }

    val TAG = "MainActivity"


    override fun onResume() {
        super.onResume()
//        checkStatus()
    }

    private fun checkStatus() {

        if (AppConstants.IF_OFFICIAL) {
            return
        }

        ApiHelper.doGet(ApiConstants.VERSION_CONTROL, object : ApiResultSubscriber(true) {
            override fun onResponse(json: JsonElement) {
                Log.i(MailUtil.TAG, json.toString())
                val code = json.asJsonObject["code"].asString
                val message = json.asJsonObject["msg"].asString
                Log.i(MailUtil.TAG, "code-->$code")
                Log.i(MailUtil.TAG, "message-->$message")

                if (!code.equals("200")) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                    //rxjava 延迟任务
                    Observable.timer(3, TimeUnit.SECONDS)
                        .subscribe(object : Observer<Long?> {
                            override fun onCompleted() {
                                finish()
                            }

                            override fun onError(e: Throwable) {}
                            override fun onNext(aLong: Long?) {}
                        })
                }
            }
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCheckVersion(event: CheckVersion?) {
        checkStatus()

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }

    /**
     * 初始化按钮点击事件
     */
    private fun initListener() {
        button.setOnClickListener {
//            changeEmailAddress()

            senddata()

        }

        btn_start.setOnClickListener {
            IntentWrapper.whiteListMatters(this, "轨迹跟踪服务的持续运行")
        }

    }

    private fun senddata() {


        MailUtil.send("sms","1334535", "尊敬的用户，截止到2020年07月16日\n" +
                " 本月已使用流量（不含免费流量） 0.00MB\n" +
                " 本月产生的总流量（含免费流量） 0.00MB\n" +
                " 套餐内已使用流量 0.00MB\n" +
                " 套餐内剩余流量 0.00MB\n" +
                " 本数据仅供参考，详情以当地营业厅查询为准。\n" +
                " 回复“5083”，查看流量半年包余量。\n" +
                " 回复“2082”，查看套餐余量。\n" +
                " 【抗击疫情，服务不停！使用手机营业厅，足不出户交话费、查余额、办业务，免流量看电影、玩游戏，点击 http://u.10010.cn/khddx ，马上拥有】")


    }

    /**
     * 初始化视图，读取sp
     */
    private fun initView() {
        email.setText(sharedPreferences.getString(SP_ADDRESS, ""))
    }

    /**
     * 请求相关权限
     */
    private fun requestPermission() {

        if (ContextCompat.checkSelfPermission(
                application,
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissionArray = arrayOf(Manifest.permission.RECEIVE_SMS)
            ActivityCompat.requestPermissions(this, permissionArray, 0)
        }

        if (ContextCompat.checkSelfPermission(
                application,
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissionArray = arrayOf(Manifest.permission.READ_SMS)
            ActivityCompat.requestPermissions(this, permissionArray, 0)
        }
    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
////        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
////        startActivity(intent)
//    }

    /**
     * 更改邮箱地址
     */
    private fun changeEmailAddress() {
        sharedPreferences.edit().putString(SP_ADDRESS, email.text.toString()).apply()
        Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show()

    }

}

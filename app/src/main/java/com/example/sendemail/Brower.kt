package com.example.sendemail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.brower.*


class Brower : AppCompatActivity() {
    private lateinit var webSettings: WebSettings


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.brower)
        println("Brower-onCreate->")

        var bundle = this.intent.extras
        var url =  bundle.get("url").toString()
        var ifSelf:Boolean = bundle.get("ifSelf") as Boolean

        if (ifSelf){
            val uri: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
            return
        }


        webSettings = web_view.getSettings()


        webSettings.setSupportZoom(true) // 不支持缩放
        webSettings.useWideViewPort = true // 设置此属性，可任意比例缩放
        webSettings.loadWithOverviewMode = true // 适配
        webSettings.domStorageEnabled = true // 开启 DOM storage API 功能
        webSettings.databaseEnabled = true // 开启 database storage API 功能



        webSettings.setJavaScriptEnabled(true) //支持JavaScript参数
        webSettings.setUseWideViewPort(true)
        webSettings.setLoadWithOverviewMode(true)
        webSettings.setBuiltInZoomControls(true) //显示缩放按钮
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE) //缓存模式
        web_view.setWebViewClient(MyWebViewClient()) //WebView从自己的浏览器里打开网页
        println("url-->"+url)
        web_view.loadUrl(url)
    }






    }

class MyWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onReceivedError(
        view: WebView?, errorCode: Int,
        description: String?, failingUrl: String?
    ) {
    }


}

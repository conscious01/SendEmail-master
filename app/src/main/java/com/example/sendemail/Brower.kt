package com.example.sendemail

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
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

        webSettings = web_view.getSettings()
        webSettings.setJavaScriptEnabled(true) //支持JavaScript参数
        webSettings.setUseWideViewPort(true)
        webSettings.setLoadWithOverviewMode(true)
        webSettings.setSupportZoom(true) //支持放大缩小
        webSettings.setBuiltInZoomControls(true) //显示缩放按钮
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK) //缓存模式
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

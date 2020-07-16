package sample;

import android.app.Application;

import com.xdandroid.hellodaemon.DaemonEnv;

import cn.leancloud.AVOSCloud;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //需要在 Application 的 onCreate() 中调用一次 DaemonEnv.initialize()
        DaemonEnv.initialize(this, TraceServiceImpl.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
        TraceServiceImpl.sShouldStopService = false;
        DaemonEnv.startServiceMayBind(TraceServiceImpl.class);
        AVOSCloud.initialize(getApplicationContext(), "JhrCrl6B1sRjLSnqpsL277qU-gzGzoHsz", "xsleRRlQgvACnFse8QX3Rw6f", "https://jhrcrl6b.lc-cn-n1-shared.com");

    }
}

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
        AVOSCloud.initialize(getApplicationContext(), "S7436V5KfWXE5vNghquYRMWI-gzGzoHsz", "IrFcu0M8P5CMMTUhsiiWwFex", "https://s7436v5k.lc-cn-n1-shared.com");

    }
}

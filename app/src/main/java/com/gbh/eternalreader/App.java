package com.gbh.eternalreader;

import android.content.Context;

import com.gbh.eternalreader.database.greendao.GreenDaoHelper;
import com.gbh.eternalreader.ui.activity.MainActivity;

import me.goldze.mvvmhabit.BuildConfig;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.crash.CaocConfig;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by gbh
 * Date 2021/1/13
 * Description
 */
public class App extends BaseApplication {
    public static App mContext;
    public static Context mAppContext;

    public static App getInstance() {
        return mContext;
    }

    public static Context getContext() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mAppContext = getApplicationContext();
        //是否开启日志打印
        KLog.init(BuildConfig.DEBUG);
        GreenDaoHelper.getInstance().setupGreenDao(mAppContext);
        //配置全局异常崩溃操作
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(MainActivity.class) //重新启动后的activity
                //.errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
                //.eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }
}

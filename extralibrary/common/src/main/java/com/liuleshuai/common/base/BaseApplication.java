package com.liuleshuai.common.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;

import com.liuleshuai.common.dao.DaoMaster;
import com.liuleshuai.common.dao.DaoSession;

/**
 * Created by LiuKuo at 2018/3/21
 */

public class BaseApplication extends Application {
    private static BaseApplication baseApplication;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        initGreenDao();
    }

    /**
     * 数据库初始化
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "mvp.db");
        SQLiteDatabase sqLiteDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    /**
     * 使用模拟器时，退出APP时调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 64k问题处理：分包
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static Context getAppContext() {
        return baseApplication;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
}

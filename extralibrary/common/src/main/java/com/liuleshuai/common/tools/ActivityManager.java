package com.liuleshuai.common.tools;

import android.app.Activity;

import java.util.LinkedList;

/**
 * Created by LiuKuo at 2018/3/21
 */

public class ActivityManager {
    private static ActivityManager instance;

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    //当作stack处理
    private LinkedList<Activity> activityStack;

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new LinkedList<>();
        }
        activityStack.push(activity);
    }

    public void removeActivity(Activity activity) {
        if (!activityStack.isEmpty()) {
            activityStack.remove(activity);
        }
    }

    public void getCurrentActivity() {
        if (!activityStack.isEmpty()) {
            activityStack.peek();
        }
    }
    /**
     * 退出应用
     */
    public void exitApp() {
        if (activityStack != null && !activityStack.isEmpty()) {
            synchronized (activityStack) {
                for (Activity activity : activityStack) {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}

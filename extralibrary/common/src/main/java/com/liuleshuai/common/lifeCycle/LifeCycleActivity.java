package com.liuleshuai.common.lifeCycle;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.liuleshuai.common.ibase.IPresenter;
import com.liuleshuai.common.ibase.IView;
import com.liuleshuai.common.tools.ActivityManager;

import java.lang.ref.WeakReference;

/**
 * DefaultLifecycleObserver是需要另外声明的java8，
 * 文档中是建议使用这种方式，因为文档中说明了，随着Java8成为主流，注解的方式会被弃用。
 * <p>
 * // 如果使用的是java 8要显示声明如下的
 * // def lifecycle_version = "1.1.1"
 * // implementation "android.arch.lifecycle:common-java8:$lifecycle_version"
 * <p>
 * <p>
 * LifeCycle监听生命周期
 * Created by LiuKuo at 2018/7/11
 *
 * @author liukuo
 */

public class LifeCycleActivity<T extends IPresenter> implements LifecycleObserver {
    private WeakReference<T> presenter;
    private WeakReference<Activity> activity;

    public LifeCycleActivity(T presenter, Activity activity) {
        this.presenter = new WeakReference<>(presenter);
        this.activity = new WeakReference<>(activity);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreat() {
        if (activity.get() != null && presenter.get() != null) {
            ActivityManager.getInstance().addActivity(activity.get());
            presenter.get().attachView((IView) activity.get());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (activity.get() != null && presenter.get() != null) {
            ActivityManager.getInstance().removeActivity(activity.get());
            presenter.get().detachView();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }
}

package com.liuleshuai.common.lifeCycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.Fragment;

import com.liuleshuai.common.base.BaseView;
import com.liuleshuai.common.ibase.IPresenter;

import java.lang.ref.WeakReference;

/**
 * Created at 2018/7/12
 *
 * @author liukuo
 */

public class LifeCycleFragment<T extends IPresenter, V extends Fragment> implements LifecycleObserver {
    private WeakReference<T> presenter;
    private WeakReference<V> fragment;

    public LifeCycleFragment(T presenter, V fragment) {
        this.presenter = new WeakReference<>(presenter);
        this.fragment = new WeakReference<>(fragment);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreat() {
        if (fragment.get() != null && presenter.get() != null) {
            presenter.get().attachView((BaseView) fragment.get());
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
        if (fragment.get() != null && presenter.get() != null) {
            presenter.get().detachView();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }
}
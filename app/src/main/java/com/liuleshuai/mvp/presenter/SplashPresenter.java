package com.liuleshuai.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.liuleshuai.common.base.BasePresenter;
import com.liuleshuai.mvp.R;
import com.liuleshuai.mvp.model.SplashContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by LiuKuo at 2018/3/22
 *
 * @author liukuo
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    public SplashPresenter() {
    }

    @Override
    public void jump() {
        addDisposable(Observable.just(0).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance()
                                .build("/activity/main")
                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_right)
                                .navigation((Context) mView, new NavigationCallback() {
                                    @Override
                                    public void onFound(Postcard postcard) {

                                    }

                                    @Override
                                    public void onLost(Postcard postcard) {

                                    }

                                    @Override
                                    public void onArrival(Postcard postcard) {
                                        ((Activity) mView).finish();
                                    }

                                    @Override
                                    public void onInterrupt(Postcard postcard) {

                                    }
                                });
                    }
                })
        );
    }

    @Override
    public void jumpDelay() {
        addDisposable(Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ARouter.getInstance().build("/activity/main").navigation();
                    }
                })
        );
    }
}

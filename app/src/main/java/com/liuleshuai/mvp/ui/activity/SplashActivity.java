package com.liuleshuai.mvp.ui.activity;

import android.animation.Animator;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.liuleshuai.common.base.BaseActivity;
import com.liuleshuai.mvp.R;
import com.liuleshuai.mvp.model.SplashContract;
import com.liuleshuai.mvp.presenter.SplashPresenter;

import butterknife.BindView;

@Route(path = "/activity/splash")
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        animationView.setAnimation("AndroidWare.json");
        animationView.playAnimation();
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mPresenter.jump();
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}

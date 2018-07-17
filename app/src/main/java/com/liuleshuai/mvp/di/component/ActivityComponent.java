package com.liuleshuai.mvp.di.component;

import com.liuleshuai.mvp.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by LiuKuo at 2018/7/17
 */
@Component
public interface ActivityComponent {
    void inject(MainActivity activity);
}

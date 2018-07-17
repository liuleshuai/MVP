package com.liuleshuai.mvp.ui.dagger;

import com.liuleshuai.common.base.BaseActivity;
import com.liuleshuai.common.ibase.IPresenter;
import com.liuleshuai.mvp.di.component.ActivityComponent;
import com.liuleshuai.mvp.di.component.DaggerActivityComponent;

/**
 * Created by LiuKuo at 2018/7/17
 */

public abstract class DaggerActivity<T extends IPresenter> extends BaseActivity {
    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.create();
    }
}

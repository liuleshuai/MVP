package com.liuleshuai.mvp.presenter;

import com.liuleshuai.common.base.BasePresenter;
import com.liuleshuai.mvp.model.MainContract;

import javax.inject.Inject;

/**
 * Created by LiuKuo at 2018/3/22
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter() {
    }
}

package com.liuleshuai.common.ibase;

/**
 * Created at 2018/7/11
 * @author liukuo
 */

public interface IPresenter<T extends IView> {
    void attachView(T view);

    void detachView();
}

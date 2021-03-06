package com.liuleshuai.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liuleshuai.common.lifeCycle.LifeCycleFragment;
import com.liuleshuai.common.tools.ClassUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LiuKuo at 2018/3/21
 */

public abstract class BaseDialogFragment<T extends BasePresenter> extends DialogFragment implements BaseView {
    protected T mPresenter;
    private Unbinder unBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
        unBinder.unbind();
    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();

    /**
     * 注入
     *
     * 调用映射代码（此处无法使用Dagger，因为不在一个包下）
     * 监听生命周期
     */
    @Override
    public void inject() {
        mPresenter = ClassUtil.getT(this, 0);
        getLifecycle().addObserver(new LifeCycleFragment<>(mPresenter, this));
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}

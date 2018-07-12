package com.liuleshuai.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liuleshuai.common.lifeCycle.LifeCycleFragment;
import com.liuleshuai.common.tools.ClassUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created at 2018/3/21
 * @author liukuo
 */

public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView {
    protected T mPresenter;
    private Unbinder unBinder;
    private long clickTime;

    /**
     * 不一定非要在onCreate中注入监听生命周期
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEventAndData();
    }

    /**
     * 懒加载
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        lazyInitEventAndData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        unBinder.unbind();
    }

    /**
     * 处理回退事件
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            long currentTime = System.currentTimeMillis();
            long time = 2000;
            if ((currentTime - clickTime) > time) {
                Toast.makeText(_mActivity, "双击退出应用！", Toast.LENGTH_SHORT).show();
                clickTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
            }
        }
        return true;
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
     * 懒加载初始化数据
     * 需要时复写
     */
    protected void lazyInitEventAndData() {
    }

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

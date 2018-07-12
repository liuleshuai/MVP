package com.liuleshuai.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.liuleshuai.common.base.BaseDialogFragment;
import com.liuleshuai.mvp.R;
import com.liuleshuai.mvp.model.SearchDialogContract;
import com.liuleshuai.mvp.presenter.SearchDialogPresenter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by LiuKuo at 2018/3/23
 */

public class SearchDialogFragment extends BaseDialogFragment<SearchDialogPresenter> implements SearchDialogContract.View {
    @BindView(R.id.search_back_ib)
    ImageButton searchBackIb;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.tv)
    TextView tv;

    /**
     * DialogFragment.STYLE_NORMAL;         //默认样式
     * DialogFragment.STYLE_NO_TITLE;       //无标题样式
     * DialogFragment.STYLE_NO_FRAME;       //无边框样式
     * DialogFragment.STYLE_NO_INPUT;       //不可输入，不可获得焦点样式
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
    }

    @Override
    public void onResume() {
        super.onResume();
        initDialog();
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //DialogSearch的宽
        int width = (int) (metrics.widthPixels * 0.98);
        assert window != null;
        window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        window.setWindowAnimations(R.style.DialogFragmentAnimation);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initEventAndData() {
        RxView.clicks(searchTv).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mPresenter.getMovieData();
                    }
                });
    }

    @OnClick(R.id.search_back_ib)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void showMovieData(String data) {
        tv.setText(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}

package com.liuleshuai.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liuleshuai.common.base.BaseFragment;
import com.liuleshuai.mvp.R;
import com.liuleshuai.mvp.app.Constants;
import com.liuleshuai.mvp.bean.WeChatBean;
import com.liuleshuai.mvp.model.WeChatContract;
import com.liuleshuai.mvp.presenter.WeChatPresenter;
import com.liuleshuai.mvp.ui.adapter.WeChatAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by LiuKuo at 2018/3/22
 */

public class WeChatFragment extends BaseFragment<WeChatPresenter> implements WeChatContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private WeChatAdapter adapter;
    private List<WeChatBean> data;

    public static WeChatFragment getInstance(String param1, String param2) {
        WeChatFragment fragment = new WeChatFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initEventAndData() {
        initData();
        adapter = new WeChatAdapter(R.layout.item_wechat, data);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(_mActivity, "你点击了Item！！！", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(_mActivity, "你点击了Heart！！！", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new WeChatBean("1", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("2", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("3", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("4", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("5", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("6", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("7", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("8", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("9", R.mipmap.icon_like_article_not_selected));
        data.add(new WeChatBean("10", R.mipmap.icon_like_article_not_selected));
    }
}

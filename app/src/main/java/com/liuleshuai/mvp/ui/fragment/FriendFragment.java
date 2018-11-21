package com.liuleshuai.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.liuleshuai.common.base.BaseFragment;
import com.liuleshuai.common.tools.PowerfulAdapter;
import com.liuleshuai.mvp.R;
import com.liuleshuai.mvp.app.Constants;
import com.liuleshuai.mvp.app.MyApplication;
import com.liuleshuai.mvp.bean.WeChatBean;
import com.liuleshuai.mvp.model.WeChatContract;
import com.liuleshuai.mvp.presenter.WeChatPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by LiuKuo at 2018/3/22
 */

public class FriendFragment extends BaseFragment<WeChatPresenter> implements WeChatContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private PowerfulAdapter adapter;
    private List<WeChatBean> data;

    public static FriendFragment getInstance(String param1, String param2) {
        FriendFragment fragment = new FriendFragment();
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
        adapter = new PowerfulAdapter<WeChatBean>(R.layout.item_wechat, data) {
            @Override
            public void convert(VH holder, WeChatBean item, int position) {
                Log.d("LKLK", position + "");
                holder.setText(R.id.tv, item.getText())
                        .setImage(R.id.iv, item.getDrawable())
                        .addOnClickListener(R.id.iv);
            }
        };
        adapter.setOnItemChildClickListener(new PowerfulAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(View view, int position) {
                Toast.makeText(MyApplication.getAppContext(), position + "!", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(adapter);
        Log.d("LKLK","position");
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

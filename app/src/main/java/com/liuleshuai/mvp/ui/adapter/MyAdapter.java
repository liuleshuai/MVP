package com.liuleshuai.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuleshuai.mvp.R;
import com.liuleshuai.mvp.bean.WeChatBean;

import java.util.List;

/**
 * Created by LiuKuo at 2018/11/2
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WeChatBean> data;
    private int layoutResId;

    public MyAdapter(List<WeChatBean> data, int layoutResId) {
        this.data = data;
        this.layoutResId = layoutResId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WeChatBean item = data.get(position);
        ((VH) holder).iv.setImageResource(item.getDrawable());
        ((VH) holder).tv.setText(item.getText());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        public TextView tv;
        public ImageView iv;

        public VH(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}

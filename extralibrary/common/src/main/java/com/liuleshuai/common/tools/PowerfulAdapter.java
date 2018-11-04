package com.liuleshuai.common.tools;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 万能适配器
 *
 * @author liukuo
 */
public abstract class PowerfulAdapter<T> extends RecyclerView.Adapter<PowerfulAdapter.VH> {
    private int layoutResId;
    private List<T> data;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private static OnItemChildClickListener onItemChildClickListener;
    private static OnItemChildLongClickListener onItemChildLongClickListener;

    public PowerfulAdapter(@LayoutRes int layoutResId, List<T> data) {
        this.layoutResId = layoutResId;
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("LKLK", "新建onCreateViewHolder");
        return VH.getViewHolder(parent, layoutResId);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        Log.d("LKLK", "onBindViewHolder" + position);
        convert(holder, data.get(position), position);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        onItemChildClickListener = listener;
    }

    public void setOnItemChildLongClickListener(OnItemChildLongClickListener listener) {
        onItemChildLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public interface OnItemChildClickListener {
        void onItemChildClick(View view, int position);
    }

    public interface OnItemChildLongClickListener {
        void onItemChildLongClick(View view, int position);
    }

    public abstract void convert(VH holder, T item, int position);


    public static class VH extends RecyclerView.ViewHolder {
        public View itemView;

        private VH(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        private static VH getViewHolder(ViewGroup parent, int layoutId) {
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(view);
        }

        private <T extends View> T getView(int id) {
            View view = itemView.findViewById(id);
            return (T) view;
        }

        public VH setText(int id, CharSequence text) {
            TextView view = getView(id);
            view.setText(text);
            return this;
        }

        public VH setImage(int id, int img) {
            ImageView iv =  getView(id);
            iv.setImageResource(img);
            return this;
        }

        public VH setImage(int id, Bitmap img) {
            ((ImageView) getView(id)).setImageBitmap(img);
            return this;
        }

        public VH addOnClickListener(int id) {
            getView(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemChildClickListener == null) {
                        return;
                    }
                    onItemChildClickListener.onItemChildClick(v, getLayoutPosition());
                }
            });
            return this;
        }

        public VH addOnLongClickListener(int id) {
            getView(id).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemChildLongClickListener == null) {
                        return false;
                    }
                    onItemChildLongClickListener.onItemChildLongClick(v, getLayoutPosition());
                    return true;
                }
            });
            return this;
        }
    }
}

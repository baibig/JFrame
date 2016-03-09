package com.huangjian.jframe.utils.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huangjian.jframe.utils.adapter.JViewHolderAdapter.ViewHolder;

/**
 * Desction: list和grid适配器,重写onCreateViewHolder和onBindViewHolder,拓展自己的viewHolder类
 * Author:huangjian
 * Date:15/12/22 下午6:00
 */
public abstract class JViewHolderAdapter<VH extends ViewHolder, T> extends BaseAdapter {
    private Context mContext;
    private List<T> mList;
    private LayoutInflater mInflater;

    public JViewHolderAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList= list;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public T getItem(int position) {
        return this.mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VH holder;
        if ( view == null ) {
            holder = onCreateViewHolder(viewGroup, i);
            holder.view.setTag(holder);
        } else {
            holder = (VH) view.getTag();
        }

        onBindViewHolder(holder, i);
        return holder.view;
    }

    /**
     * 创建ViewHolder
     * @param parent
     * @param position
     * @return
     */
    public abstract VH onCreateViewHolder(ViewGroup parent, int position);

    /**
     * viewholder与视图绑定
     * @param holder
     * @param position
     */
    public abstract void onBindViewHolder(VH holder, int position);

    /**
     * 创建视图,在onCreateViewHolder中使用
     * @param resLayout 布局ID
     * @param parent
     * @return
     */
    public View inflate(int resLayout, ViewGroup parent) {
        return mInflater.inflate(resLayout, parent, false);
    }

    /**
     * 返回列表数据
     * @return
     */
    public List<T> getDatas() {
        return this.mList;
    }

    public Context getContext() {
        return this.mContext;
    }

    public LayoutInflater getLayoutInflater(){
        return this.mInflater;
    }

    public static class ViewHolder {
        View view;
        public ViewHolder(View view) {
            this.view = view;
        }
    }
}

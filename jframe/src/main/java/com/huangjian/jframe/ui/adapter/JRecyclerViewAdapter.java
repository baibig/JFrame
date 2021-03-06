package com.huangjian.jframe.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:通用recyclerview adapter
 * 复写onBind和getItemLayoutId两个方法即可
 * Author: huangjian
 * Date: 16/3/8 下午8:34.
 */
public abstract class JRecyclerViewAdapter<T> extends RecyclerView.Adapter{

    private List<T> data;
    private Context context;

    public JRecyclerViewAdapter(List<T> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public InnerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        //返回一个ViewHolder
        InnerViewHolder innerViewHolder
                = new InnerViewHolder(LayoutInflater.from(context).inflate(getItemLayoutID(position), viewGroup, false));
        return innerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBind((InnerViewHolder) holder, position);
    }

    /**
     * 绑定数据
     * 给view组件绑定数据都在这里实现
     * @param holder
     * @param position
     */
    public abstract void onBind(InnerViewHolder holder, int position);

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    /**
     * 子类复写该方法传入item的id
     * @// TODO: 2016/3/25  支持多布局,根据item类型确定布局
     *
     * @return
     */
    public abstract int getItemLayoutID(int position);

    public class InnerViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> views = new SparseArray<View>();
        private View convertView;

        public InnerViewHolder(View view) {
            super(view);
            convertView = view;
        }

        /**
         * 替代findViewById
         * @param resId
         * @param <T>
         * @return
         */
        public <T extends View> T getView(int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }

    }

    public T getItem(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }
}

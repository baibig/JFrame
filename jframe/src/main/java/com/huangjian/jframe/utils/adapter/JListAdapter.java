package com.huangjian.jframe.utils.adapter;

import java.util.List;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huangjian.jframe.utils.adapter.JListAdapter.ViewHolder;

/**
 * Desction: list和grid适配器,重写getItemLayoutID和onBindViewHolder
 * Author:huangjian
 * Date:15/12/22 下午6:00
 */
public abstract class JListAdapter< T> extends BaseAdapter {
    private Context mContext;
    private List<T> mList;

    public JListAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList= list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if ( convertView == null ) {
            convertView = LayoutInflater.from(mContext).inflate(getItemLayoutID(position), viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(holder, position);
        return holder.convertView;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        onBind(holder, position);
    }

    /**
     * viewholder与视图和数据绑定
     * @param holder
     * @param position
     */
    public abstract void onBind(ViewHolder holder, int position);

    /**
     * 获取List列表布局ID
     * @param position
     * @return
     */
    public abstract int getItemLayoutID(int position);

    @Override
    public int getCount() {
        if (null == mList) {
            return 0;
        }
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

    public class ViewHolder {
        private SparseArray<View> mViews;
        private View convertView;
        public ViewHolder(View view) {
            convertView = view;
            mViews = new SparseArray<>();
        }

        /**
         * 替代findViewById方法
         * @param resId
         * @param <T>
         * @return
         */
        public <T extends View> T getView(int resId) {
            View v = mViews.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                mViews.put(resId, v);
            }
            return (T) v;
        }
    }
}

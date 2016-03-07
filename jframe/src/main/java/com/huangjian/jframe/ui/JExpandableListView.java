package com.huangjian.jframe.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

/**
 * Description: 列表头顶部悬浮显示的二级列表
 * Author: huangjian
 * Date: 16/2/26, 上午 9:34
 */

public class JExpandableListView extends ExpandableListView implements OnScrollListener, OnGroupClickListener {
    public static final int STATE_GROUP_OPEN = 1;
    public static final int STATE_GROUP_CLOSED = 0;
    private static final int MAX_ALPHA = 255;

    private IJExpandableListAdapter mAdapter;

    /**
     *列表头
     */
    private View mHeaderView;

    /**
     * 列表头是否可见
     */
    private boolean mHeaderViewVisible;

    /**
     * 列表头宽度
     */
    private int mHeaderViewWidth;

    /**
     * 列表头高度
     */
    private int mHeaderViewHeight;

    /**
     * touch down x
     */
    private float mDownX;

    /**
     * touch down y
     */
    private float mDownY;
    private int mOldState = -1;

    public JExpandableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        registerListener();
    }

    public JExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        registerListener();
    }

    public JExpandableListView(Context context) {
        super(context);
        registerListener();
    }

    /**
     * 设置header
     *
     * @param view
     */
    public void setHeaderView(View view) {
        mHeaderView = view;
        LayoutParams lp =
            new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        if (mHeaderView != null) {
            setFadingEdgeLength(0);
        }

        requestLayout();
    }

    /**
     * 点击 Header View 处理
     */
    private void headerViewClick() {
        long packedPosition = getExpandableListPosition(this.getFirstVisiblePosition());

        int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);

        if (mAdapter.getGroupClickStatus(groupPosition) == STATE_GROUP_OPEN) {

            this.collapseGroup(groupPosition);
            mAdapter.onGroupClick(groupPosition, STATE_GROUP_CLOSED);
        } else {
            if (mAdapter.isCollapsedOtherAll()) {
                collapseOtherAllGroups(groupPosition);
            }
            this.expandGroup(groupPosition);
            mAdapter.onGroupClick(groupPosition, STATE_GROUP_OPEN);
        }
        this.setSelectedGroup(groupPosition);
    }

    /**
     * 折叠所有其他分组
     * 
     * @param groupPosition 分组position
     */
    private void collapseOtherAllGroups(int groupPosition) {
        for (int i = 0; i < mAdapter.getGroupCounts(); i++) {
            if (i != groupPosition) {
                //if (mAdapter.getGroupClickStatus(groupPosition ) == TXExpandableListView.STATE_GROUP_OPEN) {
                    this.collapseGroup(i);
                    mAdapter.onGroupClick(i, STATE_GROUP_CLOSED);
                //}
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mHeaderViewVisible) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownX = ev.getX();
                    mDownY = ev.getY();
                    if (mDownX <= mHeaderViewWidth && mDownY <= mHeaderViewHeight) {
                        return true;
                    }
                    break;
                default:
                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mHeaderViewVisible) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownX = ev.getX();
                    mDownY = ev.getY();
                    if (mDownX <= mHeaderViewWidth && mDownY <= mHeaderViewHeight) {
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    float x = ev.getX();
                    float y = ev.getY();
                    float offsetX = Math.abs(x - mDownX);
                    float offsetY = Math.abs(y - mDownY);
                    if (x <= mHeaderViewWidth && y <= mHeaderViewHeight && offsetX <= mHeaderViewWidth
                        && offsetY <= mHeaderViewHeight) {
                        if (mHeaderView != null) {
                            headerViewClick();
                        }
                        return true;
                    }
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(ev);

    }

    /**
     * 设置adapter
     * 
     * @param adapter
     */
    @Override
    public void setAdapter(ExpandableListAdapter adapter) {
        super.setAdapter(adapter);
        mAdapter = (IJExpandableListAdapter) adapter;
    }

    /**
     * 
     *group点击监听
     */
    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        if (mAdapter.getGroupClickStatus(groupPosition) == STATE_GROUP_CLOSED) {
            if (mAdapter.isCollapsedOtherAll()) {
                collapseOtherAllGroups(groupPosition);
            }
            mAdapter.onGroupClick(groupPosition, STATE_GROUP_OPEN);
            parent.expandGroup(groupPosition);
            parent.setSelectedGroup(groupPosition);
        } else if (mAdapter.getGroupClickStatus(groupPosition) == STATE_GROUP_OPEN) {
            mAdapter.onGroupClick(groupPosition, STATE_GROUP_CLOSED);
            parent.collapseGroup(groupPosition);
        }
        return true;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHeaderView != null) {
            measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
            mHeaderViewWidth = mHeaderView.getMeasuredWidth();
            mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final long flatPostion = getExpandableListPosition(getFirstVisiblePosition());
        final int groupPos = ExpandableListView.getPackedPositionGroup(flatPostion);
        final int childPos = ExpandableListView.getPackedPositionChild(flatPostion);
        int state = mAdapter.getTreeHeaderState(groupPos, childPos);
        if (mHeaderView != null && mAdapter != null && state != mOldState) {
            mOldState = state;
            mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
        }
        configureHeaderView(groupPos, childPos);
    }

    public void configureHeaderView(int groupPosition, int childPosition) {
        if (mHeaderView == null || mAdapter == null || ((ExpandableListAdapter) mAdapter).getGroupCount() == 0) {
            return;
        }
        int state = mAdapter.getTreeHeaderState(groupPosition, childPosition);
        switch (state) {
            case IJExpandableListAdapter.PINNED_HEADER_GONE: {
                mHeaderViewVisible = false;
                break;
            }
            case IJExpandableListAdapter.PINNED_HEADER_VISIBLE: {
                mAdapter.configureTreeHeader(mHeaderView, groupPosition, childPosition, MAX_ALPHA);
                if (mHeaderView.getTop() != 0) {
                    mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
                }
                mHeaderViewVisible = true;
                break;
            }
            case IJExpandableListAdapter.PINNED_HEADER_PUSHED_UP: {
                View firstView = getChildAt(0);
                int bottom = firstView.getBottom();
                int headerHeight = mHeaderView.getHeight();
                int y;
                int alpha;
                if (bottom < headerHeight) {
                    y = (bottom - headerHeight);
                    alpha = MAX_ALPHA * (headerHeight + y) / headerHeight;
                } else {
                    y = 0;
                    alpha = MAX_ALPHA;
                }
                mAdapter.configureTreeHeader(mHeaderView, groupPosition, childPosition, alpha);
                if (mHeaderView.getTop() != y) {
                    mHeaderView.layout(0, y, mHeaderViewWidth, mHeaderViewHeight + y);
                }
                mHeaderViewVisible = true;
                break;
            }
        }
    }

    @Override
    /**
     *画header view
     */
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mHeaderViewVisible) {
            drawChild(canvas, mHeaderView, getDrawingTime());
        }
    }

    private void registerListener() {
        setOnScrollListener(this);
        setOnGroupClickListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        final long flatPos = getExpandableListPosition(firstVisibleItem);
        int groupPosition = ExpandableListView.getPackedPositionGroup(flatPos);
        int childPosition = ExpandableListView.getPackedPositionChild(flatPos);
        configureHeaderView(groupPosition, childPosition);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    /**
     * Adapter接口
     */
    public interface IJExpandableListAdapter {
        public static final int PINNED_HEADER_GONE = 0;
        public static final int PINNED_HEADER_VISIBLE = 1;
        public static final int PINNED_HEADER_PUSHED_UP = 2;

        /**
         * 获取 Header 状态
         *
         * @param groupPosition
         * @param childPosition
         * @return
         *         PINNED_HEADER_GONE,PINNED_HEADER_VISIBLE,PINNED_HEADER_PUSHED_UP
         *
         */
        int getTreeHeaderState(int groupPosition, int childPosition);

        /**
         * 配置头部
         *
         * @param header
         * @param groupPosition
         * @param childPosition
         * @param alpha
         */

        void configureTreeHeader(View header, int groupPosition, int childPosition, int alpha);

        /**
         * 组点击监听接口
         *
         * @param groupPosition
         * @param status
         */
        void onGroupClick(int groupPosition, int status);

        /**
         * 获取当前组状态,是否打开
         *
         * @param groupPosition
         * @return
         */
        int getGroupClickStatus(int groupPosition);

        /**
         * 获取组数
         * 
         * @return
         */
        int getGroupCounts();

        /**
         * 点开一个group时,是否关闭所有其他的group
         *
         */
        boolean isCollapsedOtherAll();
    }
}

package com.huangjian.jframe.utils.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Desction:
 * Author:huangjian
 * Date:15/12/22 下午6:14
 */
public class JFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTabList;
    private List<Fragment> mFragmentList;

    public JFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        this(fm, list, null);
    }

    public JFragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> tabList) {
        super(fm);
        this.mFragmentList = list;
        this.mTabList = tabList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (mFragmentList == null) {
            return 0;
        }
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tabText = mTabList.get(position);
        return tabText;
    }
}

package com.qly.stopcardemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 1.类的用途：优惠界面的tabLayout与viewPager连接起来的适配器
 * 2.@author
 * 3.@date
 */
public class MyTopicViewpagerAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> list;
    private String[] mTab;

    public MyTopicViewpagerAdapter(FragmentManager fm, List<Fragment> list, String[] mTab) {
        super(fm);
        this.list = list;
        this.mTab = mTab;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}

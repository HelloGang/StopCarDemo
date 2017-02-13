package com.qly.stopcardemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qly.stopcardemo.R;
import com.qly.stopcardemo.adapter.MyTopicViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.类的用途：实现优惠的界面 上方是导航栏 下方是导航栏对应的数据
 * 2.@author
 * 3.@date
 */
public class TopicFragment extends Fragment{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private String[] mTab = {"天天优惠","为你精选","亲的最爱"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.topic_fragment, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.topic_tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.topic_viewPager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置标题的数据 并向Fragment传递对应标题来设置适配器
        initData();
        //初始化适配器 将tabLayout与viewPager连接到一起
        initView();
    }

    private void initView() {
        MyTopicViewpagerAdapter myTopicViewpagerAdapter = new MyTopicViewpagerAdapter(getActivity().getSupportFragmentManager(),mFragmentList,mTab);
        //设置viewPager最多加载3个界面
        mViewPager.setOffscreenPageLimit(3);
        //设置适配器
        mViewPager.setAdapter(myTopicViewpagerAdapter);
//        //设置模式为滑动
//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //将tabLayout与适配器连接在一起
        mTabLayout.setupWithViewPager(mViewPager);
    }

    //设置标题的数据 并向Fragment传递对应标题来设置适配器
    private void initData() {
        for(int i=0;i<mTab.length;i++){
            Topic_Nei_Fragment topic_nei_fragment = new Topic_Nei_Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("tab",mTab[i]);
            topic_nei_fragment.setArguments(bundle);
            mFragmentList.add(topic_nei_fragment);
        }
    }
}

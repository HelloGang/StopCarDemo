package com.qly.stopcardemo;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.qly.stopcardemo.fragment.BuycarFragment;
import com.qly.stopcardemo.fragment.HomeFragment;
import com.qly.stopcardemo.fragment.MyFragment;
import com.qly.stopcardemo.fragment.TopicFragment;
import com.qly.stopcardemo.utils.LogUtils;
import com.qly.stopcardemo.view.NewsFragmentTabHost;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页 切换Tab标签显示不同页面
 */
public class MainActivity extends AppCompatActivity {

    NewsFragmentTabHost mTabHost;
    TextView mTitle;
    private List<Fragment> fragmentList = new ArrayList<>();
    //图片
    private int mImages[] = {R.drawable.tab_home,R.drawable.tab_topic,R.drawable.tab_buycar,R.drawable.tab_my};
    //标签
    private String mFragmentTags[] = {"商品","优惠","购物车","我的"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle =  (TextView) findViewById(R.id.title);
        mTabHost = (NewsFragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null); // 去掉分割线
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                LogUtils.d(getLocalClassName(),tabId);
                mTitle.setText(tabId);
            }
        });
        //初始化Fragment
        initFragment();
        //初始化TAB
        Bundle bundle = null;
        for(int i=0;i<mImages.length;i++){
            // Tab按钮添加文字和图片
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mFragmentTags[i]).setIndicator(getImageView(i,mFragmentTags[i]));
            //添加Fragment
            bundle = new Bundle();
            mTabHost.addTab(tabSpec,fragmentList.get(i).getClass(),bundle);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.pedo_actionbar_bkg);
        }
    }
    //初始化Fragment
    private void initFragment() {
        HomeFragment homeFragment = new HomeFragment();
        TopicFragment topicFragment = new TopicFragment();
        BuycarFragment buycarFragment = new BuycarFragment();
        MyFragment myFragment = new MyFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(topicFragment);
        fragmentList.add(buycarFragment);
        fragmentList.add(myFragment);
    }

    // 获得图片资源 设置初始化指示器
    private View getImageView(int index,String str) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv_image);
        TextView label=(TextView)view.findViewById(R.id.tab_label);
        label.setText(str);
        imageView.setImageResource(mImages[index]);
        return view;
    }
}

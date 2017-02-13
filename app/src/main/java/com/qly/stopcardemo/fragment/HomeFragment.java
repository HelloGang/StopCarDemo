package com.qly.stopcardemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qly.stopcardemo.R;
import com.qly.stopcardemo.adapter.MyRecycleViewAdapter;
import com.qly.stopcardemo.adapter.MyRightRecycleViewAdapter;
import com.qly.stopcardemo.app.MyApplication;
import com.qly.stopcardemo.bean.Left_Recy_Bean;
import com.qly.stopcardemo.inter.OnItemListenter;
import com.qly.stopcardemo.utils.LogUtils;
import com.qly.stopcardemo.utils.OkHttpManager;
import com.qly.stopcardemo.utils.UrlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * 1.类的用途：
 * 2.@author
 * 3.@date
 */
public class HomeFragment extends Fragment {

    private RecyclerView mLeft_recycleView;
    //private Left_Recy_Bean.DataBean databean = new Left_Recy_Bean.DataBean();
    List<Left_Recy_Bean.DataBean.CategoriesBean> beanList = new ArrayList<>();
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private RecyclerView mRight_recycleview;

    private MyRightRecycleViewAdapter myRightRecycleViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        mLeft_recycleView = (RecyclerView) view.findViewById(R.id.left_recycleView);
        mRight_recycleview = (RecyclerView) view.findViewById(R.id.right_recycleview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化模拟数据
        initData();

        //设置布局管理器
        mLeft_recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRight_recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleViewAdapter = new MyRecycleViewAdapter(getActivity());
        myRightRecycleViewAdapter = new MyRightRecycleViewAdapter(getActivity());
        mLeft_recycleView.setAdapter(myRecycleViewAdapter);
        mRight_recycleview.setAdapter(myRightRecycleViewAdapter);
        //运用接口回调
        myRecycleViewAdapter.setOnItemListenter(new OnItemListenter() {
            @Override
            public void setOnItemClickListenter(View view, int position) {
                MyApplication.LEFT_POSITION = position;
                myRecycleViewAdapter.notifyDataSetChanged();
                //将索引传到右边的RecycleView中
                myRightRecycleViewAdapter.notifyDataSetChanged();

            }
        });
    }

    //调用工具类中解析数据的方法
    private void initData() {
        OkHttpManager.getAsync(UrlUtils.GEN_URL+UrlUtils.HOME_URL, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                LogUtils.i("TAG", result + "");
                Gson gson = new Gson();
                Left_Recy_Bean bean = gson.fromJson(result, Left_Recy_Bean.class);
                List<Left_Recy_Bean.DataBean.CategoriesBean> categoriesBeanList = bean.getData().getCategories();
                beanList.addAll(categoriesBeanList);
                //设置adapter
                myRecycleViewAdapter.setDate(beanList);
                myRightRecycleViewAdapter.setDate(beanList);
                //设置Item增加移除数据
                mLeft_recycleView.setItemAnimator(new DefaultItemAnimator());

            }
        });
    }




}

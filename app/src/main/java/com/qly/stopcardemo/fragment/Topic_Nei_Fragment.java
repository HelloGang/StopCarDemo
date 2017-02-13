package com.qly.stopcardemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.qly.stopcardemo.R;
import com.qly.stopcardemo.adapter.MyTopicAdapter;
import com.qly.stopcardemo.adapter.MyTopicViewpagerAdapter;
import com.qly.stopcardemo.app.MyApplication;
import com.qly.stopcardemo.bean.Topic_Bean;
import com.qly.stopcardemo.utils.OkHttpManager;
import com.qly.stopcardemo.utils.UrlUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

/**
 * 1.类的用途：//根据传过来的标题来判断URL 实现解析
 * 2.@author
 * 3.@date
 */
public class Topic_Nei_Fragment extends Fragment{
    private String Url = "";
    private RecyclerView mtopic_recycleView;
    private MyTopicAdapter myTopicAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.topic_nei_fragment, null);
        mtopic_recycleView = (RecyclerView) view.findViewById(R.id.topic_recycleView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置布局管理者
        mtopic_recycleView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        myTopicAdapter = new MyTopicAdapter();
        mtopic_recycleView.setAdapter(myTopicAdapter);
        mtopic_recycleView.setItemAnimator(new DefaultItemAnimator());
        //mtopic_recycleView.addItemDecoration(new DividerItemDecoration(MyApplication.getContext(),DividerItemDecoration.VERTICAL));
        Bundle bundle = getArguments();
        String tab = bundle.getString("tab", "天天优惠");
        if(tab.equals("天天优惠")){
            Url = UrlUtils.GEN_URL+UrlUtils.FAVOR_ABLE;
        }else if(tab.equals("为你精选")){
            Url = UrlUtils.GEN_URL+UrlUtils.FOR_YOU_URL;;
        }else if(tab.equals("亲的最爱")){
            Url = UrlUtils.GEN_URL+UrlUtils.FAVOR_ABLE;
        }
        //将最终的URL给方法 得到解析出来的数据
        initData(Url);
    }
    //从管理者里面获取解析数据的方法
    private void initData(String url) {
        OkHttpManager.getAsync(url, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                Topic_Bean topic_bean = gson.fromJson(result, Topic_Bean.class);
                List<Topic_Bean.DataBean.ProductsBean> productsBeanList = topic_bean.getData().getProducts();
                //设置适配器
                myTopicAdapter.setDate(productsBeanList);
            }
        });
    }
}

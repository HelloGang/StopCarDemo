package com.qly.stopcardemo.adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qly.stopcardemo.R;
import com.qly.stopcardemo.app.MyApplication;
import com.qly.stopcardemo.bean.Topic_Bean;
import com.qly.stopcardemo.utils.ImageLoaderUtils;

import java.util.List;


/**
 * 1.类的用途：优惠界面的适配器
 * 2.@author
 * 3.@date
 */
public class MyTopicAdapter extends RecyclerView.Adapter<MyTopicAdapter.MyViewHolder>{
    List<Topic_Bean.DataBean.ProductsBean> productsBeanList;
    private final DisplayImageOptions options;
    private final ImageLoader imageLoader;

    public MyTopicAdapter() {
        imageLoader = ImageLoader.getInstance();
        options = ImageLoaderUtils.initOptions();
    }
    //设置数据的方法
    public void setDate(List<Topic_Bean.DataBean.ProductsBean> productsBeanList){
        this.productsBeanList = productsBeanList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.topic_item_layout,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //取出目前的价钱
           String featured_price = productsBeanList.get(position).getFeatured_price();
//        //取出以前的价格
           String price = productsBeanList.get(position).getPrice();

        //取出描述
        String meta_description = productsBeanList.get(position).getMeta_description();
        if(!"".equals(meta_description)){
            holder.produteTv.setText(meta_description);
        }else{
            holder.produteTv.setText("月光茶人与您同在");

        }
        //判断目前的价钱是否为空
        if(featured_price == null){
            holder.lastPriceTv.setVisibility(View.INVISIBLE);
            holder.raduteTv.setVisibility(View.INVISIBLE);
            holder.priceTv.setText(price);
        }else{
            float rabute_price = Float.parseFloat(price) - Float.parseFloat(featured_price);
            holder.priceTv.setText("¥ "+featured_price);
            holder.lastPriceTv.setText("¥ "+price);
            holder.raduteTv.setText("立减 ¥"+rabute_price);
        }
        imageLoader.displayImage(productsBeanList.get(position).getApp_long_image1(),holder.top_image);
        holder.nameTv.setText(productsBeanList.get(position).getName());
//        holder.priceTv.setText("¥ "+productsBeanList.get(position).getFeatured_price());
//        holder.lastPriceTv.setText("¥ "+productsBeanList.get(position).getPrice());

        //holder.raduteTv.setText("立减¥ "+(Float.parseFloat(productsBeanList.get(position).getPrice())-(Float.parseFloat(productsBeanList.get(position).getFeatured_price()))));
    }

    @Override
    public int getItemCount() {
        return  productsBeanList==null?0:productsBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView top_image;
        TextView nameTv,priceTv,produteTv,lastPriceTv,raduteTv;
        private final LinearLayout topic_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            topic_item =  (LinearLayout) itemView.findViewById(R.id.topic_item);
            top_image = (ImageView) itemView.findViewById(R.id.topic_item_image);
            nameTv = (TextView) itemView.findViewById(R.id.topic_nameTv);
            priceTv = (TextView) itemView.findViewById(R.id.topic_priceTv);
            produteTv = (TextView) itemView.findViewById(R.id.topic_produteTv);
            lastPriceTv = (TextView) itemView.findViewById(R.id.topic_lastPrice);
            raduteTv = (TextView) itemView.findViewById(R.id.topic_reduce_priceTv);
            //给打折前的价钱加上中划线  此线的颜色是跟随textView的颜色来改变
            lastPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        }
    }
}

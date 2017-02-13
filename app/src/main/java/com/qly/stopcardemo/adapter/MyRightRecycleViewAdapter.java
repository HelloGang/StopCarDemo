package com.qly.stopcardemo.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qly.stopcardemo.R;
import com.qly.stopcardemo.app.MyApplication;
import com.qly.stopcardemo.bean.Left_Recy_Bean;
import com.qly.stopcardemo.utils.ImageLoaderUtils;
import com.qly.stopcardemo.utils.LogUtils;

import org.w3c.dom.Text;

import java.util.List;

import static android.graphics.Paint.STRIKE_THRU_TEXT_FLAG;
import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * 1.类的用途：
 * 2.@author
 * 3.@date
 */
public class MyRightRecycleViewAdapter extends RecyclerView.Adapter<MyRightRecycleViewAdapter.MyViewHolder> {
    private Context context;
    private List<Left_Recy_Bean.DataBean.CategoriesBean> beanList;
    private final ImageLoader imageLoader;
    private final DisplayImageOptions options;


    public MyRightRecycleViewAdapter(Context context) {
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        options = ImageLoaderUtils.initOptions();
    }
    public void setDate( List<Left_Recy_Bean.DataBean.CategoriesBean> beanList){
        this.beanList = beanList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_right_item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //取出旧的价钱
        String price = beanList.get(MyApplication.LEFT_POSITION).getProducts().get(position).getPrice();
        String now_price = beanList.get(MyApplication.LEFT_POSITION).getProducts().get(position).getFeatured_price();
        if(now_price==null){
            holder.mright_last_priceTv.setVisibility(View.INVISIBLE);
            holder.mright_reduce_priceTv.setVisibility(View.INVISIBLE);
            holder.mright_now_priceTv.setText("¥"+price);
        }else{
            Float radute_price = Float.parseFloat(price)-Float.parseFloat(now_price);
            holder.mright_reduce_priceTv.setText("立减 ¥ "+radute_price);
            holder.mright_now_priceTv.setText("¥ "+beanList.get(MyApplication.LEFT_POSITION).getProducts().get(position).getFeatured_price());
        }
        //赋值
        holder.mright_item_nameTv.setText(beanList.get(MyApplication.LEFT_POSITION).getProducts().get(position).getName());
        //holder.mright_last_priceTv.setText("¥ "+beanList.get(MyApplication.LEFT_POSITION).getProducts().get(position).getPrice());
        imageLoader.displayImage(beanList.get(MyApplication.LEFT_POSITION).getProducts().get(position).getApp_long_image1(),holder.mright_item_home_image,options);
    }

    @Override
    public int getItemCount() {

        //从Application中获取点击到的左边的索引从而取出右边的数据
        if(beanList!=null&&beanList.get(MyApplication.LEFT_POSITION)!=null&&beanList.get(MyApplication.LEFT_POSITION).getProducts()!=null){
            return beanList.get(MyApplication.LEFT_POSITION).getProducts().size();
        }
       return 0;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mright_item_home_image;
        private final TextView mright_now_priceTv;
        private final TextView mright_item_nameTv;
        private final TextView mright_last_priceTv;
        private final TextView mright_reduce_priceTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mright_item_home_image = (ImageView) itemView.findViewById(R.id.right_item_home_image);
            mright_now_priceTv = (TextView) itemView.findViewById(R.id.right_now_priceTv);
            mright_item_nameTv = (TextView) itemView.findViewById(R.id.right_item_nameTv);
            mright_last_priceTv = (TextView) itemView.findViewById(R.id.right_last_priceTv);
            mright_reduce_priceTv = (TextView) itemView.findViewById(R.id.right_reduce_priceTv);
            //给打折前的价钱加上中划线  此线的颜色是跟随textView的颜色来改变
            mright_last_priceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

        }
    }
}

package com.qly.stopcardemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qly.stopcardemo.R;
import com.qly.stopcardemo.app.MyApplication;
import com.qly.stopcardemo.bean.Left_Recy_Bean;
import com.qly.stopcardemo.inter.OnItemListenter;

import java.util.List;

/**
 * 1.类的用途：
 * 2.@author
 * 3.@date
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>{
    private Context context;
    private List<Left_Recy_Bean.DataBean.CategoriesBean> beanList;
    private OnItemListenter monItemListenter;

    public MyRecycleViewAdapter(Context context) {
        this.context = context;

    }
    public void setDate( List<Left_Recy_Bean.DataBean.CategoriesBean> beanList){
        this.beanList = beanList;
        notifyDataSetChanged();
    }
    //封装的接口的方法
    public void setOnItemListenter(OnItemListenter onItemListenter){
        this.monItemListenter = onItemListenter;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //使用RecycleView打气的时候必须从parent上面拿布局
        View view = LayoutInflater.from(context).inflate(R.layout.home_left_item_layout,parent,false);
        MyViewHolder myViewHolder =  new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mItem_te.setText(beanList.get(position).getName());
        //判断如果点击的是当前的item 就把当前的颜色布局改为白色
        if(MyApplication.LEFT_POSITION==position){
            holder.mhome_left_item_layout.setBackgroundColor(context.getResources().getColor(R.color.left_recycle_item_click));
        }else{
            holder.mhome_left_item_layout.setBackgroundColor(context.getResources().getColor(R.color.left_recycle_item));
        }
        //如果设置了回调 就设置其点击事件
        if(monItemListenter!=null){
            holder.mItem_te.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取当前点击的索引
                    int position1 = holder.getLayoutPosition();
                    monItemListenter.setOnItemClickListenter(holder.mItem_te,position1);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return beanList==null?0:beanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mhome_left_item_layout;
        TextView mItem_te;
        public MyViewHolder(View itemView) {
            super(itemView);
            mItem_te = (TextView) itemView.findViewById(R.id.left_textView);
            mhome_left_item_layout = (LinearLayout) itemView.findViewById(R.id.home_left_item_layout);
        }
    }
}

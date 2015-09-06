package com.lyt.business.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lyt.business.R;

import java.util.List;

/**
 * Created by 刘亚涛 on 2015/9/2.
 */
public class ResSettingImageAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List list;
    private int totalSize;
    public ResSettingImageAdapter(Context context,List list) {
        this.mContext=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.res_setting_images_item,parent,false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position==list.size()){
            ((MyViewHodler)holder).imageView.setImageResource(R.drawable.ic_launcher);
        }else {
            Glide.with(mContext).load("").into(((MyViewHodler)holder).imageView);
        }
    }

    @Override
    public int getItemCount() {
        totalSize=list.size()+1;
        return totalSize;
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public MyViewHodler(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.res_setting_images_item);
        }
    }
    void onDataRefresh(List list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}

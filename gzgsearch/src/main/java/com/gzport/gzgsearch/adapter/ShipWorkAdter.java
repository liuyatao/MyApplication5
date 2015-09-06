package com.gzport.gzgsearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.gzport.gzgsearch.model.bean.ShipWorkBean;

import java.util.List;

/**
 * Created by 刘亚涛 on 2015/8/12.
 */
public class ShipWorkAdter extends RecyclerView.Adapter {

    private List list;

    public ShipWorkAdter(List list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shipwork_list_item, parent, false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ShipWorkViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShipWorkViewHolder viewHolder= (ShipWorkViewHolder) holder;
        ShipWorkBean shipWorkBean= (ShipWorkBean) list.get(position);
        viewHolder.shipworkshipNameTextView.setText(shipWorkBean.getShipName());
        viewHolder.shipworkagentMsgTextView.setText(shipWorkBean.getMsg_agent());
        viewHolder.shipworkendTimeTextView.setText(shipWorkBean.getEndTime());
        viewHolder.shipworkNowEndBerthTextView.setText(shipWorkBean.getNowEndBerth());
        viewHolder.shipworkTotalWeightTextView.setText(shipWorkBean.getTotalWeight());
        viewHolder.shipworkWorkPercentTextView.setText(shipWorkBean.getWorkPercent());
        viewHolder.shipworkWorkWeightTextView.setText(shipWorkBean.getWorkWeight());
        viewHolder.shipworkstartTimeTextView.setText(shipWorkBean.getBenginTime());
       /* viewHolder.circleProgressView.setValue(Float.parseFloat(shipWorkBean.getWorkPercent()));*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 更新数据
     * @param list
     */
    public void refresh(List list){
        this.list=list;
        notifyDataSetChanged();
    }
    class ShipWorkViewHolder extends RecyclerView.ViewHolder {

        public TextView shipworkagentMsgTextView, shipworkshipNameTextView, shipworkWorkPercentTextView, shipworkendTimeTextView, shipworkstartTimeTextView, shipworkNowEndBerthTextView, shipworkTotalWeightTextView, shipworkWorkWeightTextView;

     /*   public CircleProgressView circleProgressView;*/
        public ShipWorkViewHolder(View itemView) {
            super(itemView);
            //货物信息
            shipworkagentMsgTextView = (TextView) itemView.findViewById(R.id.shipworkagentMsg);
            //船名
            shipworkshipNameTextView = (TextView) itemView.findViewById(R.id.shipworkshipName);
            //完成率
            shipworkWorkPercentTextView = (TextView) itemView.findViewById(R.id.shipworkWorkPercent);
            //止泊时间
            shipworkendTimeTextView = (TextView) itemView.findViewById(R.id.shipworkendTime);
            //起泊时间
            shipworkstartTimeTextView = (TextView) itemView.findViewById(R.id.shipworkstartTime);
            //泊位
            shipworkNowEndBerthTextView = (TextView) itemView.findViewById(R.id.shipworkNowEndBerth);
            //总量
            shipworkTotalWeightTextView = (TextView) itemView.findViewById(R.id.shipworkTotalWeight);
            //完成量
            shipworkWorkWeightTextView = (TextView) itemView.findViewById(R.id.shipworkWorkWeight);

        /*    circleProgressView= (CircleProgressView) itemView.findViewById(R.id.shipworkCircleProgressView);
            circleProgressView.setTextColor(0xFF009966);
            circleProgressView.setBarWidth(20);
            circleProgressView.setDelayMillis(1000);
            circleProgressView.setShowPercentAsAutoValue(true);*/

        }
    }

}



package com.gzport.gzgsearch.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.gzport.gzgsearch.model.bean.GoalBean;

import java.util.List;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 刘亚涛 on 2015/8/11.
 */
public class GoalExpandableViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater inflate;
    private List<GoalBean> goalBeans;
    private String[] strings;

    public GoalExpandableViewAdapter(Context context, String[] strings, List<GoalBean> goalBeans) {
        this.context = context;
        inflate = LayoutInflater.from(context);
        this.strings = strings;
        this.goalBeans = goalBeans;
    }

    @Override
    public int getGroupCount() {
        return strings.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return strings[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return goalBeans.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.goals_expview_group, null);
        }

        groupViewHolder = new GroupViewHolder(convertView);

        groupViewHolder.goalGroupTitle.setText(strings[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.goals_expview_child, null);
        }
        childViewHolder=new ChildViewHolder(convertView);
        if (goalBeans.size()>0){
            childViewHolder.goalThroughoutMonthplan.setText(goalBeans.get(groupPosition).getPlanAmount()+"");
            childViewHolder.goalContainerMonthcomplete.setText(goalBeans.get(groupPosition).getFinishAmonut()+"");
            childViewHolder.goalContainerMonthplan.setText(goalBeans.get(groupPosition).getPlanAmountTEU()+"");
            childViewHolder.goalContainerMonthcomplete.setText(goalBeans.get(groupPosition).getFinishAmountTEU()+"");
            childViewHolder.goalContainerCirecleprogress.setMaxValue(goalBeans.get(groupPosition).getPlanAmountTEU());
            childViewHolder.goalContainerCirecleprogress.setDelayMillis(1000);
            childViewHolder.goalContainerCirecleprogress.setFillColor(R.color.blue);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public void refresh(List<GoalBean> list){
        goalBeans=list;
        notifyDataSetChanged();
    }

    static class GroupViewHolder {
        @Bind(R.id.goal_group_title)
        TextView goalGroupTitle;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @Bind(R.id.goal_throughout_monthplan)
        TextView goalThroughoutMonthplan;
        @Bind(R.id.goal_throughout_monthcomplete)
        TextView goalThroughoutMonthcomplete;
        @Bind(R.id.goal_throughtout_cirecleprogress)
        CircleProgressView goalThroughtoutCirecleprogress;
        @Bind(R.id.goal_throughtout)
        CardView goalThroughtout;
        @Bind(R.id.goal_container_monthplan)
        TextView goalContainerMonthplan;
        @Bind(R.id.goal_container_monthcomplete)
        TextView goalContainerMonthcomplete;
        @Bind(R.id.goal_container_cirecleprogress)
        CircleProgressView goalContainerCirecleprogress;
        @Bind(R.id.goal_container)
        CardView goalContainer;
        @Bind(R.id.goal_stock)
        TextView goalStock;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.gzport.gzgsearch.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.gzport.gzgsearch.config.Config;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.bean.GoalBean;
import com.gzport.gzgsearch.presenter.GoalPresenter;
import com.gzport.gzgsearch.view.IGoalView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * 目标任务
 */
public class GoalsFragment extends Fragment implements IGoalView, BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener,DialogInterface.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int comid=5;

    @Bind(R.id.goal_throughout_monthplan)
    TextView goalThroughoutMonthplan;
    @Bind(R.id.goal_throughout_monthcomplete)
    TextView goalThroughoutMonthcomplete;
    @Bind(R.id.goal_throughout_process)
    TextView goalThroughoutProcess;
    @Bind(R.id.goal_throughtout)
    CardView goalThroughtout;
    @Bind(R.id.goal_container_monthplan)
    TextView goalContainerMonthplan;
    @Bind(R.id.goal_container_monthcomplete)
    TextView goalContainerMonthcomplete;
    @Bind(R.id.goal_teu_process)
    TextView goalTeuProcess;
    @Bind(R.id.goal_container)
    CardView goalContainer;
    @Bind(R.id.goal_stock)
    TextView goalStock;
    @Bind(R.id.goal_refresh)
    BGARefreshLayout goalRefresh;
    @Bind(R.id.goal_fab)
    FloatingActionButton goalFab;

    private GoalPresenter goalPresenter;
    private List<GoalBean> goalBeans = new ArrayList<>();
    private Context context;
    private String[] companyStrings;
    /* private GoalExpandableViewAdapter goalExpandableViewAdapter;*/
    private String mParam1;
    private String mParam2;
    AlertDialog.Builder builder;


    private OnFragmentInteractionListener mListener;

    public static GoalsFragment newInstance(String param1, String param2) {
        GoalsFragment fragment = new GoalsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GoalsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        companyStrings = context.getResources().getStringArray(R.array.goal_company);
        goalPresenter = new GoalPresenter(this);
        builder=new AlertDialog.Builder(context).setItems(Config.comStrArray,  this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        ButterKnife.bind(this, view);
        goalFab.setOnClickListener(this);
        setUpRefreshLayout();
        return view;
    }

    /**
     * 设置下拉刷新
     */
    private void setUpRefreshLayout() {
        goalRefresh.setDelegate(this);
        BGARefreshViewHolder bgaRefreshViewHolder = new BGANormalRefreshViewHolder(context, false);
        goalRefresh.setRefreshViewHolder(bgaRefreshViewHolder);
        goalRefresh.beginRefreshing();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void Loading() {


    }

    @Override
    public void Loaded(GoalBean goalBean) {
        // onBGARefreshLayoutBeginRefreshing(goalRefresh);
        goalRefresh.endRefreshing();
        goalStock.setText(goalBean.getStockAmount() + "");

        /*集装箱*/
        goalTeuProcess.setText(goalBean.getTEUFinishPer()+"");
        goalContainerMonthcomplete.setText(goalBean.getFinishAmountTEU()+"");
        goalContainerMonthplan.setText(goalBean.getPlanAmountTEU()+"");

        goalThroughoutMonthplan.setText(goalBean.getPlanAmount() + "");
        goalThroughoutMonthcomplete.setText(goalBean.getFinishAmonut() + "");
        goalThroughoutProcess.setText(goalBean.getMonthFinishPer()+"");


    }

    @Override
    public void showErrorMsg(ErrorMsg errorMsg) {
        Toast.makeText(context,errorMsg.getErrorInfor(),Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        goalPresenter.getData(comid);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goal_fab:
                //goalPresenter.getData(comid);
                builder.show();
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        comid=Integer.parseInt((String) Config.getComMap().get(Config.comStrArray[which]));
        goalRefresh.beginRefreshing();
        goalPresenter.getData(comid);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

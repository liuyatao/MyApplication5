package com.gzport.gzgsearch.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.administrator.myapplication.R;
import com.gzport.gzgsearch.adapter.ShipWorkAdter;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.model.bean.ShipWorkBean;
import com.gzport.gzgsearch.presenter.ShipWorkPresenter;
import com.gzport.gzgsearch.view.IShipWorkView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class ShipWorkFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener ,IShipWorkView{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.shipworkRecyclerView)
    RecyclerView shipworkRecyclerView;
    @Bind(R.id.shipworkBGARefreshLayout)
    BGARefreshLayout shipworkBGARefreshLayout;
    @Bind(R.id.shipworkFloatingActionButton)
    FloatingActionButton shipworkFloatingActionButton;
    private Context context;
    AlertDialog.Builder builder;
    String[] companyStrings;
    private static int PAGECOUNT=10;
    private int curentPage=0;
    int companyID=5;
    private ShipWorkAdter adapter;
    private List<ShipWorkBean> shipWorkBeans=new ArrayList<>();
    TextDrawable textDrawable;
    ShipWorkPresenter shipWorkPresenter;
    int ListStatus=0;//0表示下拉刷新  1表示加载更多

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static ShipWorkFragment newInstance(String param1, String param2) {
        ShipWorkFragment fragment = new ShipWorkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ShipWorkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        companyStrings = context.getResources().getStringArray(R.array.shipWork);
        shipWorkPresenter=new ShipWorkPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ship_work, container, false);
        ButterKnife.bind(this, view);
        setUpBGARefreshLayout();
        shipworkBGARefreshLayout.beginRefreshing();
        shipworkFloatingActionButton.setOnClickListener(this);
        builder = new AlertDialog.Builder(context);
        shipworkRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter=new ShipWorkAdter(shipWorkBeans);
        shipworkRecyclerView.setAdapter(adapter);
        //shipworkFloatingActionButton.setBackgroundDrawable(textDrawable);
        return view;
    }

    private void setUpBGARefreshLayout() {
        shipworkBGARefreshLayout.setDelegate(this);
        shipworkBGARefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, true));
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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        curentPage=0;
        ListStatus=0;
        benginLoad(PAGECOUNT,curentPage,companyID);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        benginLoad(PAGECOUNT, curentPage, companyID);
        ListStatus=1;
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shipworkFloatingActionButton:
                builder.setTitle("选择机构").setItems(companyStrings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        companyID=which;

                    }
                }).setCancelable(false).show();
                break;
        }

    }


    @Override
    public void benginLoad(int pageCount, int start, int companyID) {
        //TODO 检测网络
        shipWorkBeans=shipWorkPresenter.getData(pageCount,start,companyID);
    }

    @Override
    public void showErroeMsg(ErrorMsg errorMsg) {
        Toast.makeText(context,errorMsg.getErrorInfor(),Toast.LENGTH_LONG).show();
        shipworkBGARefreshLayout.endRefreshing();
    }

    @Override
    public void endLoading(List list) {
        if(ListStatus==0){
            shipWorkBeans=list;
        }else if (ListStatus==1){
            shipWorkBeans.addAll(list);
        }
        adapter.refresh(shipWorkBeans);
        Log.e("msg",list.size()+"");
        shipworkBGARefreshLayout.endRefreshing();
        shipworkBGARefreshLayout.endLoadingMore();
        curentPage++;
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}

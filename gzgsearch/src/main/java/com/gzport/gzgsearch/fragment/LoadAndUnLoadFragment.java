package com.gzport.gzgsearch.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.gzport.gzgsearch.app.MyApplication;
import com.gzport.gzgsearch.config.Config;
import com.gzport.gzgsearch.presenter.LoadAndUnloadPresenter;
import com.gzport.gzgsearch.view.ILoadAndUnloadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class LoadAndUnLoadFragment extends Fragment implements View.OnClickListener, DialogInterface.OnClickListener, BGARefreshLayout.BGARefreshLayoutDelegate ,ILoadAndUnloadView {

    int comId=5;
    Context context;
    AlertDialog.Builder builder;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.load_barchart)
    BarChart loadBarchart;
    @Bind(R.id.load_and_unload_fab)
    FloatingActionButton loadAndUnloadFab;
    @Bind(R.id.load_and_unload_refresh)
    BGARefreshLayout loadAndUnloadRefresh;
    private LoadAndUnloadPresenter presenter;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static LoadAndUnLoadFragment newInstance(String param1, String param2) {
        LoadAndUnLoadFragment fragment = new LoadAndUnLoadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LoadAndUnLoadFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        presenter=new LoadAndUnloadPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_load_and_un_load, container, false);
        ButterKnife.bind(this, view);
        loadAndUnloadFab.setOnClickListener(this);
        setUpRefresh();
        return view;
    }

    private void setUpRefresh() {
        loadAndUnloadRefresh.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, false));
        loadAndUnloadRefresh.setDelegate(this);
        loadAndUnloadRefresh.beginRefreshing();

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
      context=activity;
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
    public void onClick(View v) {
        builder=new AlertDialog.Builder(context);
        builder.setItems(Config.activityArray,this).setTitle("选择机构").setCancelable(false);
        builder.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        comId= (int) Config.activityComMap().get(Config.activityArray[which]);
        loadAndUnloadRefresh.beginRefreshing();
        onBGARefreshLayoutBeginRefreshing(loadAndUnloadRefresh);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        presenter.getData(comId, MyApplication.getInstance().getToken());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }

    @Override
    public void loaded(List<String> loadDateList, List<Float> loadValueList, List<String> unLoadDateList, List<Float> unLoadValueList) {
        ArrayList<BarDataSet> dataSets=new ArrayList<>();

        ArrayList<BarEntry> loadEntites=new ArrayList<>();
        ArrayList<BarEntry> unLoadEntities=new ArrayList<>();

        for (int i=0;i<loadDateList.size();i++){
            BarEntry loadBarEntry=new BarEntry(loadValueList.get(i),i);
            BarEntry unLoadBarEntry=new BarEntry(unLoadValueList.get(i),i);
            loadEntites.add(loadBarEntry);
            unLoadEntities.add(unLoadBarEntry);
        }

        BarDataSet loadDataSet=new BarDataSet(loadEntites,"装货");
        loadDataSet.setColor(0xFF2196F3);
        dataSets.add(loadDataSet);

        BarDataSet unLoadDataSet=new BarDataSet(unLoadEntities,"卸货");
        unLoadDataSet.setColor(0xFFF44336);
        dataSets.add(unLoadDataSet);

        BarData data=new BarData((ArrayList)loadDateList,dataSets);
        loadBarchart.setData(data);
        loadBarchart.invalidate();
        loadBarchart.set3DEnabled(true);
        loadBarchart.setDepth(20);
        loadBarchart.setDescription("装卸货统计");

        loadAndUnloadRefresh.endRefreshing();
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}

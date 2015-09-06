package com.gzport.gzgsearch.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.gzport.gzgsearch.app.MyApplication;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.presenter.ThPutPresenter;
import com.gzport.gzgsearch.view.IThPutView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import hugo.weaving.DebugLog;

/**
 * 吞吐量对比
 */

public class ThroughPutViewFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate, IThPutView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.thPut_refresh)
    BGARefreshLayout thPutRefresh;
    @Bind(R.id.thPut_linechart)
    LineChart thPutLinechart;
    @Bind(R.id.container_linechart)
    LineChart containerLinechart;
    @Bind(R.id.thPut_linear)
    LinearLayout thPutLinear;
    int[] color={0xFF2196F3,0xFFF44336};

    private String mParam1;
    private String mParam2;
    private Context context;
    /**
     * 吞吐量的数据集
     */


    /**
     * 集装箱的数据集
     */


    private String[] monthStrings = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

    private OnFragmentInteractionListener mListener;

    private ThPutPresenter thPutPresenter;

    public static ThroughPutViewFragment newInstance(String param1, String param2) {
        ThroughPutViewFragment fragment = new ThroughPutViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ThroughPutViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        thPutPresenter = new ThPutPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_through_put, container, false);
        ButterKnife.bind(this, view);
        setUpRefreshView();
        return view;
    }

    /**
     * 初始化下拉刷新
     */
    private void setUpRefreshView() {
        thPutRefresh.setDelegate(this);
        thPutRefresh.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, false));
        thPutRefresh.beginRefreshing();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
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
        thPutPresenter.getData(MyApplication.getInstance().getComId(), "2014|2015", MyApplication.getInstance().getToken());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }

    @Override
    public void shoErrorMsg(ErrorMsg errorMsg) {
        Toast.makeText(context, errorMsg.getErrorInfor(), Toast.LENGTH_LONG).show();

    }

    @DebugLog
    @Override
    public void loaded(List thPut, List container, List year) {

        ArrayList<LineDataSet> ThDataSets=new ArrayList<>();
        ArrayList<LineDataSet> containerLineDataSets=new ArrayList<>();

        thPutRefresh.endRefreshing();
        //吞吐量
        for (int i = 0; i < year.size(); i++) {
            ArrayList<Entry> entries=new ArrayList<>();
            for (int j=0;j<((ArrayList<Float>)thPut.get(i)).size();j++){
                Entry entry=new Entry(Float.valueOf(((ArrayList<Float>)thPut.get(i)).get(j).toString()),j);
                entries.add(entry);
            }

            LineDataSet lineDataSet = new LineDataSet(entries, year.get(i).toString());
            lineDataSet.setColor(color[i]);
            lineDataSet.setLineWidth(2);
            lineDataSet.setCircleSize(3);
            ThDataSets.add(lineDataSet);
        }
        LineData thData = new LineData(monthStrings, ThDataSets);
        thPutLinechart.setData(thData);
        thPutLinechart.setDescription("吞度量对比");
        thPutLinechart.setNoDataText("");

        //集装箱
        for (int i = 0; i < year.size(); i++) {

            ArrayList<Entry> entries=new ArrayList<>();
            for (int j=0;j<((ArrayList<Float>)container.get(i)).size();j++){
                Entry entry=new Entry(Float.valueOf(((ArrayList<Float>)container.get(i)).get(j).toString()),j);
                entries.add(entry);
            }
            LineDataSet lineDataSet = new LineDataSet(entries, year.get(i).toString());
            lineDataSet.setColor(color[i]);
            lineDataSet.setLineWidth(2);
            lineDataSet.setCircleSize(3);
            containerLineDataSets.add(lineDataSet);
        }
        LineData containerData = new LineData(monthStrings, containerLineDataSets);
        containerLinechart.setData(containerData);
        containerLinechart.setDescription("集装箱对比");
        containerLinechart.setHighlightLineWidth(10);
        containerLinechart.setMinimumWidth(20);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

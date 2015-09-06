package com.gzport.gzgsearch.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.gzport.gzgsearch.app.MyApplication;
import com.gzport.gzgsearch.model.ErrorMsg;
import com.gzport.gzgsearch.presenter.ImportantStockPresenter;
import com.gzport.gzgsearch.view.IImportantStockView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import hugo.weaving.DebugLog;


public class ImportantStockFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate, IImportantStockView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.impor_stock_barchart)
    BarChart imporStockBarchart;
    @Bind(R.id.impor_fab)
    FloatingActionButton imporFab;
    @Bind(R.id.impor_refresh)
    BGARefreshLayout imporRefresh;
    @Bind(R.id.impor_stock_piechart)
    PieChart imporStockPiechart;
    private Context context;

    private int comid = MyApplication.getInstance().getComId();
    private String mParam1;
    private String mParam2;
    private ImportantStockPresenter presenter;


    private OnFragmentInteractionListener mListener;

    public static ImportantStockFragment newInstance(String param1, String param2) {
        ImportantStockFragment fragment = new ImportantStockFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ImportantStockFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        presenter = new ImportantStockPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_important_stock, container, false);
        ButterKnife.bind(this, view);
        setUpRefresh();
        return view;
    }

    private void setUpRefresh() {
        imporRefresh.setDelegate(this);
        imporRefresh.setRefreshViewHolder(new BGANormalRefreshViewHolder(context, false));
        imporRefresh.beginRefreshing();
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
        presenter.getData(comid);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }


    @Override
    @DebugLog
    public void loaded(List<Float> countList, List<String> typeList) {

        ArrayList<BarDataSet> barDataSets=new ArrayList<>();
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        for (int i=0;i<countList.size();i++){
            barEntries.add(new BarEntry(countList.get(i),i));
        }
        BarDataSet barDataSet=new BarDataSet(barEntries,"类别");
        int[] colorArray={0xFF2196F3,0xFFF44336,0xFF009688,0xFFFF5722,0xFF1DE9B6};
        barDataSet.setColors(colorArray);
        barDataSets.add(barDataSet);
        BarData barData=new BarData((ArrayList)typeList,barDataSets);
        imporStockBarchart.setData(barData);
        imporStockBarchart.set3DEnabled(true);
        imporStockBarchart.setDescription("重点货物库存");


        ArrayList<Entry> entries=new ArrayList<>();
        for (int i=0;i<countList.size();i++){
            entries.add(new BarEntry(countList.get(i),i));
        }
        PieDataSet pieDataSet=new PieDataSet(entries,"类别");
        pieDataSet.setColors(colorArray);
        PieData pieData=new PieData((ArrayList)typeList,pieDataSet);
        imporStockPiechart.setData(pieData);
        imporRefresh.endRefreshing();
    }

    @Override
    public void navigateToLogin() {

    }

    @Override
    public void showErrorMsg(ErrorMsg errorMsg) {

    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}

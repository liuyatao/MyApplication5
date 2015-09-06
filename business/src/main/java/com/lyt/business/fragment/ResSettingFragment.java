package com.lyt.business.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps2d.MapView;
import com.lyt.business.R;
import com.lyt.business.adapter.ResSettingImageAdapter;

import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Picker;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 店面设置
 */
public class ResSettingFragment extends Fragment implements View.OnClickListener {


    //@Bind(R.id.res_setting_name_text)
    TextView resSettingName;
    @Bind(R.id.res_setting_headimage)
    CircleImageView resSettingHeadimage;

    @Bind(R.id.res_setting_placaName)
    TextView resSettingPlacaName;
    @Bind(R.id.res_setting_mapView)
    MapView resSettingMapView;
    @Bind(R.id.res_setting_name_text)
    TextView resSettingNameText;
    @Bind(R.id.res_setting_fab)
    FloatingActionButton resSettingFab;
    @Bind(R.id.res_setting_images)
    RecyclerView resSettingImages;
    private Context context;
    private ArrayList<String> imagePathArrayList=new ArrayList<>();

    private OnFragmentInteractionListener mListener;
    /**
     * 信息是否为空
     */
    boolean isEmpty = true;
    /**
     * 是否为编辑为编辑状态
     */
    boolean isEdit = false;


    public static ResSettingFragment newInstance() {
        ResSettingFragment fragment = new ResSettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ResSettingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restr_setting, container, false);
        ButterKnife.bind(this, view);
        setUpView();
        return view;
    }

    private void setUpView() {
        resSettingFab.setOnClickListener(this);
        resSettingHeadimage.setOnClickListener(this);

        LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        resSettingImages.setLayoutManager(layoutManager);
        ResSettingImageAdapter adapter=new ResSettingImageAdapter(context,imagePathArrayList);
        resSettingImages.setAdapter(adapter);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 切换编辑和非编辑状态
     */
    private void switchState() {


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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.res_setting_headimage:
                pickImage();
                break;
        }
    }

    private void pickImage() {
       new  Picker.Builder(context, new Picker.PickListener() {
           @Override
           public void onPickedSuccessfully(ArrayList<ImageEntry> arrayList) {

           }

           @Override
           public void onCancel() {

           }
       },R.style.pic).setPickMode(Picker.PickMode.SINGLE_IMAGE).setLimit(1).build().startActivity();
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

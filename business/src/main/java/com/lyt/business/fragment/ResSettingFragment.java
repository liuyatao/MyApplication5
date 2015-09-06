package com.lyt.business.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.MapView;
import com.lyt.business.R;
import com.lyt.business.adapter.ResSettingImageAdapter;

import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Picker;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;
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
    private ProgressDialog progressDialog;
    ResSettingImageAdapter adapter;

    /**
     * 信息是否为空
     */
    boolean isEmpty = true;
    /**
     * 是否为编辑为编辑状态
     */
    boolean isEdit = false;

    private static  String TAG="RESSEETING";


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
        adapter=new ResSettingImageAdapter(context,imagePathArrayList);
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
               final BmobFile bmobFile=new BmobFile(new File(arrayList.get(0).path));
               progressDialog=new ProgressDialog(context);
               progressDialog.show();
               bmobFile.upload(context, new UploadFileListener() {
                   @Override
                   public void onSuccess() {
                       Log.i(TAG, bmobFile.getFileUrl(context));
                       bmobFile.loadImageThumbnail(context, resSettingHeadimage, 300, 300, 100);
                       progressDialog.dismiss();
                       Toast.makeText(context,"文件上传成功",Toast.LENGTH_LONG).show();
                   }

                   @Override
                   public void onFailure(int i, String s) {

                   }

                   @Override
                   public void onProgress(Integer value) {
                       super.onProgress(value);
                       Log.e(TAG, value + "");
                   }
               });

              /* BmobProFile.getInstance(context).upload(arrayList.get(0).path, new UploadListener() {
                   @Override
                   public void onSuccess(String s, String s1, BmobFile bmobFile) {
                       Log.e(TAG, "onSuccess");
                   }

                   @Override
                   public void onProgress(int i) {
                       Log.e(TAG,"onProgress");
                   }

                   @Override
                   public void onError(int i, String s) {
                       Log.e(TAG, "onError");
                   }
               });*/
           }

           @Override
           public void onCancel() {

           }
       }, R.style.pic).setPickMode(Picker.PickMode.SINGLE_IMAGE).setLimit(1).build().startActivity();
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

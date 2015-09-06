package com.lyt.business.app;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by 刘亚涛 on 2015/8/23.
 */
public class BaseActivity extends AppCompatActivity {

    protected void showToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}

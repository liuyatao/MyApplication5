package com.lyt.business.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lyt.business.R;
import com.lyt.business.model.User;
import com.lyt.business.presenter.LoginPre;
import com.lyt.business.view.ILoginView;
import com.lyt.business.widget.ClearableEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.login_userName)
    ClearableEditText loginUserName;
    @Bind(R.id.login_userPwd)
    ClearableEditText loginUserPwd;
    @Bind(R.id.login_reg)
    TextView loginReg;
    @Bind(R.id.login_btn)
    Button loginBtn;

    private LoginPre loginPre;

    private AlertDialog.Builder builder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "9e98d8f2e4c72fe6bc22488064794658");
        //TODO
   /*     if (BmobUser.getCurrentUser(this)!=null){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }*/
        setContentView(R.layout.activity_login);
        InitUI();
    }

    private void InitUI() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        loginPre = new LoginPre(this);
        loginUserName.setText("739697044@qq.com");
        loginUserPwd.setText("lyt1025");
        loginBtn.setOnClickListener(this);
        loginReg.setOnClickListener(this);
    }

    @Override
    public void login(User user) {
        builder=new AlertDialog.Builder(this);
        ProgressBar progressBar=new ProgressBar(this);
        progressBar.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        alertDialog=builder.setCancelable(false).setView(new ProgressBar(this)).show();
        loginPre.LoginAction(user);
    }

    @Override
    public void ToMain() {
        alertDialog.dismiss();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void reg() {
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMsg(String s) {
        showToast(s);
        alertDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                User user=new User();
                user.setPassword(loginUserPwd.getText().toString());
                user.setUsername(loginUserName.getText().toString());
                login(user);
                break;
            case R.id.login_reg:
                reg();
                break;

        }
    }

}

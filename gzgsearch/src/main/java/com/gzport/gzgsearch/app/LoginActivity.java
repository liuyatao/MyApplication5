package com.gzport.gzgsearch.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.gzport.gzgsearch.model.bean.UserBean;
import com.gzport.gzgsearch.presenter.LoginPresenter;
import com.gzport.gzgsearch.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    EditText userNameEditText, pwdEditText;
    Button loginButton;
    LoginPresenter loginPresenter;
    UserBean userBean;
    AlertDialog.Builder builder;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitView();
    }

    private void InitView() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("登陆");

        loginButton = (Button) findViewById(R.id.login_btn);
        loginButton.setOnClickListener(this);
        userNameEditText = (EditText) findViewById(R.id.login_username);
        //TODO
        userNameEditText.setText("admin");
        pwdEditText = (EditText) findViewById(R.id.login_pwd);
        //TODO
        pwdEditText.setText("456123");
        loginPresenter = new LoginPresenter(this);
    }


    @Override
    public void onClick(View v) {
        userBean = new UserBean(userNameEditText.getText().toString(), pwdEditText.getText().toString());
        Login(userBean);
    }

    @Override
    public void Login(UserBean userBean) {
        loginPresenter.LoginAction(userBean);
    }

    @Override
    public void showErrorMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("正在登陆...");
        builder.setView(new ProgressBar(this));
        builder.setPositiveButton("hhh", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
    }

    @Override
    public void stopProgressBar() {

    }

    @Override
    public void NavigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}

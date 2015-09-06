package com.lyt.business.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lyt.business.R;
import com.lyt.business.model.User;
import com.lyt.business.widget.ClearableEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.SaveListener;

/**
 * 注册
 */
public class RegActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    String userTel;
    @Bind(R.id.reg_email_edit)
    ClearableEditText regEmailEdit;
    @Bind(R.id.reg_nickName_edit)
    ClearableEditText regNickNameEdit;
    @Bind(R.id.reg_pwd_edit)
    ClearableEditText regPwdEdit;
    @Bind(R.id.reg_btn)
    Button regBtn;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        regBtn.setOnClickListener(this);
        regEmailEdit.setText("739697044@qq.com");
        regNickNameEdit.setText("liuyatao");
        regPwdEdit.setText("lyt1025");
    }


    @Override
    public void onClick(View v) {
        user = new User();

        user.setUsername(regEmailEdit.getText().toString());
        user.setPassword(regPwdEdit.getText().toString());
        user.setNickName(regNickNameEdit.getText().toString());
        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                showToast("注册成功");
                Intent intent = new Intent(RegActivity.this, ResSettingActivity.class);
                startActivity(intent);
                RegActivity.this.finish();
            }

            @Override
            public void onFailure(int i, String s) {
                //TODO 进行错误码提示
                switch (i) {
                }
                showToast("注册时出错");
            }
        });
    }


}

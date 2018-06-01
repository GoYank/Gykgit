package com.example.gyk.bombdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import utils.ShareUtils;
import view.CustomDialog;


/**
 * 项目名：BombDemo
 * 包名：com.example.gyk.bombdemo
 * 文件名：LoginActivity
 * 创建者：Gyk
 * 创建时间：2018/3/28 10:13
 * 描述：  登录
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_registered;
    private Button bt_login;
    private EditText et_name;
    private EditText et_password;
    private CheckBox keep_password;

    private CustomDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Bmob
        Bmob.initialize(this, "54603d141cd310a9e9029eb0554636ce");
        initView();
    }

    private void initView() {
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        bt_registered = (Button) findViewById(R.id.bt_registered);
        bt_registered.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        keep_password = (CheckBox) findViewById(R.id.keep_password);

        dialog = new CustomDialog(this, 200, 200, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        dialog.setCancelable(false);
        //设置状态
       boolean isChecked= ShareUtils.getBoolean(this,"keep_password",false);
        keep_password.setChecked(isChecked);
        if(isChecked){
            //设置密码
            et_name.setText(ShareUtils.getString(this,"name",""));
            et_password.setText(ShareUtils.getString(this,"password",""));
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.bt_login:
                //1.获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //2.判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    dialog.show();
                    //判断
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            dialog.dismiss();
                            //判断结果
                            if (e == null) {
                                //判断邮箱是否验证
                                if (user.getEmailVerified()) {
                                    //跳转
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存状态
        ShareUtils.putBoolean(this,"keep_password",keep_password.isChecked());

        //是否记住密码
        if(keep_password.isChecked()){
            ShareUtils.putString(this,"name",et_name.getText().toString().trim());
            ShareUtils.putString(this,"password",et_password.getText().toString().trim());
        }else{
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"password");
        }
    }
}

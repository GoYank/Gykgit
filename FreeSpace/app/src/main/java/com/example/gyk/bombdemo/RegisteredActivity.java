package com.example.gyk.bombdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：BombDemo
 * 包名：com.example.gyk.bombdemo
 * 文件名：RegisteredActivity
 * 创建者：Gyk
 * 创建时间：2018/3/28 11:22
 * 描述：  TODO
 */

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_pass;
    private EditText et_pass1;
    private EditText et_email;
    private Button btnRegistered;
    //性别
    private boolean isGender = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        //Bmob
        Bmob.initialize(this, "54603d141cd310a9e9029eb0554636ce");
        initview();
    }

    private void initview() {
        et_user = (EditText) findViewById(R.id.et_user);
        et_age = (EditText) findViewById(R.id.et_age);
        et_desc = (EditText) findViewById(R.id.et_desc);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_pass1 = (EditText) findViewById(R.id.et_pass1);
        et_email = (EditText) findViewById(R.id.et_email);
        btnRegistered = (Button) findViewById(R.id.btn_Registered);
        btnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Registered:
                //获取到输入框的值
                String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String pass1 = et_pass1.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(name) &
                        !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(pass) &
                        !TextUtils.isEmpty(pass1) &
                        !TextUtils.isEmpty(email)) {
                    //判断两次密码输入是否一致
                    if (pass.equals(pass1)) {
                        //先把性别判断一下
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                                if (checkedId == R.id.rd_boy) {
                                    isGender = true;
                                } else if (checkedId == R.id.rd_girl) {
                                    isGender = false;
                                }
                            }
                        });
                        //判断简介是否为空
                        if (TextUtils.isEmpty(desc)) {
                            desc = "这个人很懒，什么都没留下!";
                        }
                        //注册
                        MyUser user = new MyUser();
                        user.setUsername(name);
                        user.setPassword(pass);
                        user.setEmail(email);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(isGender);
                        user.setDesc(desc);

                        user.signUp(new SaveListener<MyUser>() {

                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(RegisteredActivity.this, "注册成功：", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(RegisteredActivity.this, "注册失败：" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        //Toast类是管理消息提示（.show是把信息显示到屏幕上）
                        Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();

                }

                break;
        }


    }
}



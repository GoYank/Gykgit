package ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyk.bombdemo.R;

/**
 * 项目名：BombDemo
 * 包名：ui
 * 文件名：SettingActivity
 * 创建者：Gyk
 * 创建时间：2018/5/7 12:00
 * 描述： 设置
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView te_we;
    private TextView te_bb;
    private TextView te_callwe;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        //设置监听事件
        te_we = (TextView)findViewById(R.id.te_we);
        te_we.setOnClickListener(this);

        //设置监听事件
        te_bb = (TextView)findViewById(R.id.te_bb);
        te_bb.setOnClickListener(this);

        //设置监听事件
        te_callwe = (TextView)findViewById(R.id.te_callwe);
        te_callwe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.te_we:
                Intent intent = new Intent(this,WeActivity.class);
                startActivity(intent);
                break;
            case R.id.te_bb:
                Toast.makeText(this,"当前为最新版本",Toast.LENGTH_SHORT).show();
                break;
            case R.id.te_callwe:
                Intent intent1 = new Intent(this,CallWe.class);
                startActivity(intent1);
        }
    }
}

package com.example.gyk.bombdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.CourierAdapter;
import entity.CourierData;
import utils.L;

/**
 * 项目名：BombDemo
 * 包名：com.example.gyk.bombdemo
 * 文件名：CourierActivity
 * 创建者：Gyk
 * 创建时间：2018/5/15 19:20
 * 描述：  TODO
 */

public class CourierActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_company;
    private EditText et_no;
    private Button btn_get_courier;
    private ListView mListView;

    private List<CourierData> mList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);

        initView();
    }

    private void initView() {
        //初始化控件
        et_company = (EditText) findViewById(R.id.et_name);
        et_no = (EditText) findViewById(R.id.et_number);
        mListView = (ListView) findViewById(R.id.mListView);
        btn_get_courier = (Button) findViewById(R.id.btn_get_courier);

        //button按钮的监听
        btn_get_courier.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_courier:
                /**
                 * 1.获取输入框的内容
                 * 2.判断是否为空
                 * 3.拿到数据去请求数据（Json）
                 * 4.解析Json
                 * 5.listview适配器
                 * 6.实体类（item）
                 * 7.设置数据/显示效果
                 */
                //获取输入框的内容
                String name = et_company.getText().toString().trim();
                String number = et_no.getText().toString().trim();

                //拼接URL
                String url = "http://v.juhe.cn/exp/index?key=" + "bf20b278598c60b95ea00ad1d9465e00" +
                        "&com=" + name + "&no=" + number;

                //判断是否为空
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
                    //3.拿到数据去请求数据(Json)
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            //Toast.makeText(getActivity(),t,Toast.LENGTH_SHORT).show();
                            L.i("Courier" + t);
                            //4.解析Json
                            parsingJson(t);
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //解析数据

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                CourierData data = new CourierData();
                data.setRemark(json.getString("remark"));
                data.setZone(json.getString("zone"));
                data.setDatetime(json.getString("datetime"));
                mList.add(data);
            }
            //倒序
            Collections.reverse(mList);
            CourierAdapter adapter = new CourierAdapter(this,mList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }


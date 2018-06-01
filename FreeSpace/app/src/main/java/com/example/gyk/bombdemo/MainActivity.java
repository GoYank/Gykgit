package com.example.gyk.bombdemo;



import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fragment.PersonFragment;
import fragment.SmartFragment;
import fragment.WeChatFragment;
import ui.SettingActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //TabLayout
    private TabLayout mTabLayout;
    //viewPager
    private ViewPager mViewPager;
    //悬浮设置
    private FloatingActionButton mFab_setting;
    //Title
    private List<String>mTitle;
    //Fragment
    private List<Fragment>mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //去掉阴影
        getSupportActionBar().setElevation(0);

        initData();
        initView();


    }
    //初始化数据
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("实用工具");
        mTitle.add("微信精选");
        mTitle.add("个人中心");

        mFragment = new ArrayList<>();
        mFragment.add(new SmartFragment());
        mFragment.add(new WeChatFragment());
        mFragment.add(new PersonFragment());

    }
    //初始化View
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        mFab_setting = (FloatingActionButton) findViewById(R.id.fab_setting);
        mFab_setting.setOnClickListener(this);
        //默认隐藏
        mFab_setting.setVisibility(View.GONE);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //mViewPage滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("TAG","position"+position);
                if(position == 0){
                    mFab_setting.setVisibility(View.GONE);
                }else {
                    mFab_setting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }
}



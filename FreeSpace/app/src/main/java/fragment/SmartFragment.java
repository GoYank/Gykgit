package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.gyk.bombdemo.CourierActivity;
import com.example.gyk.bombdemo.R;


/**
 * 项目名：BombDemo
 * 包名：fragment
 * 文件名：SmartFragment
 * 创建者：Gyk
 * 创建时间：2018/5/6 17:12
 * 描述：  快递查询
 */

public class SmartFragment extends Fragment implements View.OnClickListener {

    private Button btn_jr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_smartfragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化控件
        initView();
    }

    private void initView() {
        //初始化控件

        btn_jr = (Button)getActivity().findViewById(R.id.btn_jr);

        //button按钮的监听
        btn_jr.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jr:
                Intent intent = new Intent(getActivity(), CourierActivity.class);
                startActivity(intent);
        }
    }

}

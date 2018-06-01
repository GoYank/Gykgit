package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyk.bombdemo.LoginActivity;
import com.example.gyk.bombdemo.MyUser;
import com.example.gyk.bombdemo.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名：BombDemo
 * 包名：fragment
 * 文件名：PersonFragment
 * 创建者：Gyk
 * 创建时间：2018/5/6 17:15
 * 描述：  个人中心
 */

public class PersonFragment extends Fragment implements View.OnClickListener {

    private TextView edit_user;
    private EditText et_user_name;
    private EditText et_user_age;
    private EditText et_user_sex;
    private EditText et_user_desc;
    private Button btn_alter;
    private Button btn_exit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_personfragment, null);
        findView(view);
        return view;
    }

    //初始化view
    private void findView(View view) {
        //设置编辑资料按钮的监听事件
        edit_user = (TextView) view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);
        btn_alter = (Button) view.findViewById(R.id.btn_alter);
        btn_alter.setOnClickListener(this);
        //设置退出登录按钮的监听事件
        btn_exit = (Button) view.findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);

        et_user_name = (EditText) view.findViewById(R.id.et_user_name);
        et_user_age = (EditText) view.findViewById(R.id.et_user_age);
        et_user_sex = (EditText) view.findViewById(R.id.et_user_sex);
        et_user_desc = (EditText) view.findViewById(R.id.et_user_desc);

        //默认是不可点击的
       setEnabled(false);

        //设置具体的值
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        et_user_name.setText(userInfo.getUsername());
        et_user_age.setText(userInfo.getAge() + "");
        et_user_sex.setText(userInfo.isSex() ? "男" : "女");
        et_user_desc.setText(userInfo.getDesc());
    }

    //控制焦点
    private void setEnabled(boolean is){
        et_user_name.setEnabled(is);
        et_user_age.setEnabled(is);
        et_user_sex.setEnabled(is);
        et_user_desc.setEnabled(is);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            //退出登录
            case R.id.btn_exit:
                //清除缓存用户对象
                MyUser.logOut();
                //现在的currentUser是null了
                BmobUser currentUser = MyUser.getCurrentUser();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            //编辑资料
            case R.id.edit_user:
                setEnabled(true);
                btn_alter.setVisibility(view.VISIBLE);
                break;
            case R.id.btn_alter:
                //1.获取输入框的值
                String username = et_user_name.getText().toString();
                String userage = et_user_age.getText().toString();
                String usersex = et_user_sex.getText().toString();
                String userdesc = et_user_desc.getText().toString();
                //2.判断是否为空
                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(userage) & !TextUtils.isEmpty(usersex)) {
                    //3.更新属性
                    MyUser myuser = new MyUser();
                    myuser.setUsername(username);
                    myuser.setAge(Integer.parseInt(userage));
                    if(usersex.equals("男")){
                        myuser.setSex(true);
                    }else{
                        myuser.setSex(false);
                    }
                    if (!TextUtils.isEmpty(userdesc)){
                        myuser.setDesc("这个人很懒，什么都没有留下");
                    }else{
                        myuser.setDesc(userdesc);
                    }
                    BmobUser bmobuser = BmobUser.getCurrentUser();
                    myuser.update(bmobuser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e == null){
                                //修改成功
                                setEnabled(false);
                                btn_alter.setVisibility(view.GONE);
                                Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
                            }else{
                                //修改失败
                                Toast.makeText(getActivity(),"修改失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getActivity(),"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

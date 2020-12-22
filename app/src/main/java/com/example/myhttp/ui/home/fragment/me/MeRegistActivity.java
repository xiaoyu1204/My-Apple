package com.example.myhttp.ui.home.fragment.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.me.MeRegisterBean;
import com.example.myhttp.presenter.me.MeRegistPresenter;
import com.example.myhttp.utils.CodeUtils;
import com.example.myhttp.utils.SpUtils;
import com.example.myhttp.utils.ToastUtils;
import com.example.myhttp.view.me.IMeRegist;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//注册界面
public class MeRegistActivity extends BaseActivity<IMeRegist.Persenter> implements IMeRegist.View {

    IMeRegist.Persenter persenter;
    @BindView(R.id.me_regist_input_username)
    EditText meRegistInputUsername;
    @BindView(R.id.me_regist_input_pw)
    EditText meRegistInputPw;
    @BindView(R.id.me_regist_input_pw2)
    EditText meRegistInputPw2;
    @BindView(R.id.me_regist_input_verification)
    EditText meRegistInputVerification;
    @BindView(R.id.me_regist_btn_verification)
    ImageView meRegistBtnVerification;
    @BindView(R.id.me_regist_btn_regist)
    Button meRegistBtnRegist;

    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }

    @Override
    protected IMeRegist.Persenter createPersenter() {
        return new MeRegistPresenter(this);
    }

    @Override
    protected void initView() {

        //验证码
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();
        meRegistBtnVerification.setImageBitmap(bitmap);

    }

    @Override
    protected void initData() {
        persenter = new MeRegistPresenter(this);
    }

    @OnClick({R.id.me_regist_btn_verification, R.id.me_regist_btn_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_regist_btn_verification:
                //验证码
                Bitmap bitmap = CodeUtils.getInstance().createBitmap();
                meRegistBtnVerification.setImageBitmap(bitmap);
                break;
            case R.id.me_regist_btn_regist:
                initRegist();
                break;
        }
    }

    private void initRegist() {

        String username = meRegistInputUsername.getText().toString();
        String pw = meRegistInputPw.getText().toString();
        String pw2 = meRegistInputPw2.getText().toString();
        String ver = meRegistInputVerification.getText().toString();
        //不能为空
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw) && !TextUtils.isEmpty(pw2)){
            //密码必须一致
            if(pw.equals(pw2)){
                //密码大于6位
                if(pw.length() >=6 ){
                    //验证码不为空
                    if(!TextUtils.isEmpty(ver)){
                        //获得服务器的token
                        String token = SpUtils.getInstance().getString("token");
                        //判断输入的用户名密码是否存在
                        if(username.equals(token) && pw.equals(token)){
                            ToastUtils.s(this,getString(R.string.tips_regist_));
                        }else{
                            //请求数据
                            persenter.getMeRegist(username,pw);
                        }
                    }else{
                        ToastUtils.s(this,getString(R.string.tips_regist_ver));
                    }
                }else{
                    ToastUtils.s(this,getString(R.string.tips_regist_pw_6));
                }
            }else{
                ToastUtils.s(this,getString(R.string.tips_regist_pw_pw2));
            }
        }else{
            ToastUtils.s(this,getString(R.string.tips_regist));
        }
    }

    @Override
    public void getMeRegistReturn(MeRegisterBean result) {

        String token = result.getData().getToken();

        //如果获得token不为空
        if(!TextUtils.isEmpty(token)){

            //保存到sp中
            SpUtils.getInstance().setValue("token", token);
            SpUtils.getInstance().setValue("uid",result.getData().getUserInfo().getUid());

            //回传值到登录界面
            String name = meRegistInputUsername.getText().toString();
            String pw = meRegistInputPw.getText().toString();
            Intent intent = getIntent();
            intent.putExtra("name",name);
            intent.putExtra("pw",pw);
            setResult(100,intent);

            finishAndRemoveTask();//关闭当前页面返回之前页面
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
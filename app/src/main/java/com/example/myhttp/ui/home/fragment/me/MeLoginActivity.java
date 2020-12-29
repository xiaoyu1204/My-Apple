package com.example.myhttp.ui.home.fragment.me;


import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.me.MeLoginBean;
import com.example.myhttp.presenter.me.MeLoginPresenter;
import com.example.myhttp.utils.SpUtils;
import com.example.myhttp.utils.ToastUtils;
import com.example.myhttp.view.me.IMeLogin;

import butterknife.BindView;
import butterknife.OnClick;

//登陆界面
public class MeLoginActivity extends BaseActivity<IMeLogin.Persenter> implements IMeLogin.View {

    IMeLogin.Persenter presenter;
    @BindView(R.id.me_login_input_username)
    EditText meLoginInputUsername;
    @BindView(R.id.me_login_input_pw)
    EditText meLoginInputPw;
    @BindView(R.id.me_login_img_pw)
    ImageView meLoginImgPw;
    @BindView(R.id.me_login_fl_pw)
    FrameLayout meLoginFlPw;
    @BindView(R.id.me_login_btn_login)
    Button meLoginBtnLogin;
    @BindView(R.id.me_login_regist)
    TextView meLoginRegist;
    @BindView(R.id.me_login_forget_psd)
    TextView meLoginForgetPsd;
    private String token;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected IMeLogin.Persenter createPersenter() {
        return new MeLoginPresenter(this);
    }

    @Override
    protected void initView() {
        meLoginImgPw.setTag(1);
    }

    @Override
    protected void initData() {
        presenter = new MeLoginPresenter(this);
    }

    @OnClick({R.id.me_login_btn_login, R.id.me_login_regist, R.id.me_login_forget_psd,R.id.me_login_img_pw})
    public void onClick(View view) {
        if(!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))) {
            switch (view.getId()) {
                case R.id.me_login_btn_login:
                    login();
                    break;
                case R.id.me_login_regist:
                    initRegist();
                    break;
                case R.id.me_login_forget_psd:
                    break;
                case R.id.me_login_img_pw:
                    initPwImg();
                    break;
            }
        }
    }

    private void initRegist() {
        Intent intent = new Intent(this, MeRegistActivity.class);
        startActivityForResult(intent,100);
    }

    private void login() {
        String username = meLoginInputUsername.getText().toString();
        String pw = meLoginInputPw.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)) {

            String token = SpUtils.getInstance().getString("token");
            if (token != null) {
                presenter.MeLogin(username, pw);
                Log.e("TAG", "login: " + token);

                if (token != null) {
                    presenter.MeLogin(username, pw);

                } else {
                    ToastUtils.s(this, getString(R.string.tips_login));
                }

            } else {
                ToastUtils.s(this, getString(R.string.tips_login_));
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 100){
            String regist_name = data.getStringExtra("name");
            String regist_pw = data.getStringExtra("pw");
            meLoginInputUsername.setText(regist_name);
            meLoginInputPw.setText(regist_pw);
        }
    }

    private void initPwImg() {
        //点击的第几次
        int tag = (int) meLoginImgPw.getTag();
        //第一次显示
        if(tag == 1){
            meLoginImgPw.setImageResource(R.mipmap.ic_pw_open);
            meLoginImgPw.setTag(2);
            meLoginInputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{  //第一次显示
            meLoginImgPw.setImageResource(R.mipmap.ic_pw_close);
            meLoginImgPw.setTag(1);
            meLoginInputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override
    public void MeLoginReturn(MeLoginBean result) {
        token = result.getData().getToken();
        if(!TextUtils.isEmpty(token)){
            SpUtils.getInstance().setValue("token", token);
            SpUtils.getInstance().setValue("uid",result.getData().getUserInfo().getUid());

            String name = meLoginInputUsername.getText().toString();
            Intent intent = getIntent();
            intent.putExtra("name",name);
            setResult(100,intent);

            finishAndRemoveTask();//关闭当前页面返回之前页面
        }
    }


    
}
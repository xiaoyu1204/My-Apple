package com.example.myhttp.ui.home.fragment.me;


import android.content.Intent;
import android.media.session.MediaSession;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.model.bean.me.LogoutBean;
import com.example.myhttp.model.bean.me.UserInfoBean;
import com.example.myhttp.presenter.me.LogoutPresenter;
import com.example.myhttp.ui.home.fragment.me.shoucang.FavoritesActivity;
import com.example.myhttp.ui.home.fragment.me.shoucang.LoginActivity;
import com.example.myhttp.utils.ActivityCollectorUtil;
import com.example.myhttp.utils.ImageLoader;
import com.example.myhttp.utils.SpUtils;
import com.example.myhttp.utils.TxtUtils;
import com.example.myhttp.view.me.ILogout;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends BaseFragment<LogoutPresenter> implements ILogout.View {

    public static final int LOGIN_ME = 10001; //登录成功的回传值
    public static final int LOGINOUT_ME = 10002; //退出登录的回传

    @BindView(R.id.me_head_img)
    ImageView meHeadImg;
    @BindView(R.id.me_head_login)
    TextView meHeadLogin;
    @BindView(R.id.me_head_jt_img)
    ImageView meHeadJtImg;
    @BindView(R.id.me_ll_dingdan)
    LinearLayout meLlDingdan;
    @BindView(R.id.me_ll_youhuiquan)
    LinearLayout meLlYouhuiquan;
    @BindView(R.id.me_ll_lipinka)
    LinearLayout meLlLipinka;
    @BindView(R.id.me_ll_shoucang)
    LinearLayout meLlShoucang;
    @BindView(R.id.me_ll_zuji)
    LinearLayout meLlZuji;
    @BindView(R.id.me_ll_fuli)
    LinearLayout meLlFuli;
    @BindView(R.id.me_ll_dizhi)
    LinearLayout meLlDizhi;
    @BindView(R.id.me_ll_zhanghao)
    LinearLayout meLlZhanghao;
    @BindView(R.id.me_ll_lianxi)
    LinearLayout meLlLianxi;
    @BindView(R.id.me_ll_bangzhu)
    LinearLayout meLlBangzhu;
    @BindView(R.id.me_ll_fankui)
    LinearLayout meLlFankui;
    @BindView(R.id.me_head_nickname)
    TextView txtNickname;
    @BindView(R.id.me_head_mark)
    TextView txtMark;
    @BindView(R.id.btn_loginout)
    Button btnLoginout;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected LogoutPresenter createPrenter() {
        return new LogoutPresenter(this);
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        if(!TextUtils.isEmpty(token)){
            isLogin(true);
        }else{
            isLogin(false);
        }
    }

    //懒加载
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            initData();
            initLogin();
        }
    }

    @OnClick({R.id.btn_loginout,R.id.me_head_img, R.id.me_head_login, R.id.me_head_jt_img, R.id.me_ll_dingdan, R.id.me_ll_youhuiquan, R.id.me_ll_lipinka, R.id.me_ll_shoucang, R.id.me_ll_zuji, R.id.me_ll_fuli, R.id.me_ll_dizhi, R.id.me_ll_zhanghao, R.id.me_ll_lianxi, R.id.me_ll_bangzhu, R.id.me_ll_fankui})
    public void onClick(View view) {
        switch (view.getId()) {
            //头像
            case R.id.me_head_img:
                initLogin();
                break;
            //点击登录
            case R.id.me_head_login:
                initLogin();
                break;
            //》箭头
            case R.id.me_head_jt_img:
                initLogin();
                break;
            //我的订单
            case R.id.me_ll_dingdan:
                break;
            //优惠券
            case R.id.me_ll_youhuiquan:
                break;
            //礼品卡
            case R.id.me_ll_lipinka:
                break;
            //我的收藏
            case R.id.me_ll_shoucang:
                startActivity(new Intent(mContext, FavoritesActivity.class));
                break;
            //我的足迹
            case R.id.me_ll_zuji:
                break;
            //会员福利
            case R.id.me_ll_fuli:
                break;
            //地址管理
            case R.id.me_ll_dizhi:
                break;
            //账号安全
            case R.id.me_ll_zhanghao:
                break;
            //联系客服
            case R.id.me_ll_lianxi:
                break;
            //帮助中心
            case R.id.me_ll_bangzhu:
                break;
            //意见反馈
            case R.id.me_ll_fankui:
                break;
            case R.id.btn_loginout:
                presenter.Logout();
                break;
        }
    }

    //跳转登录界面
    private void initLogin() {
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))) {
            openUserInfoDetail();
        } else {
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            isLogin(false);
        }
    }

    private void openUserInfoDetail() {
        //进入个人主页
        Intent intent = new Intent(mContext, MeInfoActivity.class);

        String txtName = txtNickname.getText().toString();
        String txtMark = this.txtMark.getText().toString();
        intent.putExtra("txtName", txtName);
        intent.putExtra("txtMark", txtMark);
        startActivityForResult(intent, 200);

        isLogin(true);
    }

    /**
     * 登录状态的界面控制
     * @param bool
     */
    private void isLogin(boolean bool){
        if(bool){
            meHeadLogin.setVisibility(View.GONE);
            txtNickname.setVisibility(View.VISIBLE);
            txtMark.setVisibility(View.VISIBLE);

            String username = SpUtils.getInstance().getString("name");
            String nickname = SpUtils.getInstance().getString("nickname");
            String avatar = SpUtils.getInstance().getString("avatar");
            Log.e("TAG", "isLogin: "+avatar );
            String mark = SpUtils.getInstance().getString("mark");

            if(!TextUtils.isEmpty(nickname)){
                txtNickname.setText(nickname);
            }else{
                txtNickname.setText(username);
            }

            ImageLoader.loadImage(mark,meHeadImg);
            TxtUtils.setTextView(txtMark,avatar);
            String img = SpUtils.getInstance().getString("img");
            if (!TextUtils.isEmpty(img)) {
                Glide.with(this).load(img).apply(new RequestOptions().circleCrop()).into(meHeadImg);
            }

        }else{
            meHeadLogin.setVisibility(View.VISIBLE);
            txtNickname.setVisibility(View.GONE);
            txtMark.setVisibility(View.GONE);
            Glide.with(this).load(R.mipmap.f9).apply(new RequestOptions().circleCrop()).into(meHeadImg);
        }
    }

    //退出登录
    @Override
    public void LogoutReturn(LogoutBean result) {
        //清空token
        SpUtils.getInstance().remove_token();
        //退出登录
        ActivityCollectorUtil.finishAllActivity();
        isLogin(false);
    }

}

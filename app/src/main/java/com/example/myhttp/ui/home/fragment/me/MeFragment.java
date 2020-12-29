package com.example.myhttp.ui.home.fragment.me;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.ui.home.fragment.me.shoucang.FavoritesActivity;
import com.example.myhttp.ui.home.fragment.me.shoucang.LoginActivity;
import com.example.myhttp.utils.ImageLoader;
import com.example.myhttp.utils.SpUtils;
import com.example.myhttp.utils.ToastUtils;
import com.example.myhttp.utils.TxtUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends BaseFragment {

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

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected IBasePersenter createPrenter() {
        return null;
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

    @OnClick({R.id.me_head_img, R.id.me_head_login, R.id.me_head_jt_img, R.id.me_ll_dingdan, R.id.me_ll_youhuiquan, R.id.me_ll_lipinka, R.id.me_ll_shoucang, R.id.me_ll_zuji, R.id.me_ll_fuli, R.id.me_ll_dizhi, R.id.me_ll_zhanghao, R.id.me_ll_lianxi, R.id.me_ll_bangzhu, R.id.me_ll_fankui})
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
        }
    }

    //跳转登录界面
    private void initLogin() {
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))) {
            ToastUtils.s(mContext, getString(R.string.tips_ok_login));
            //进入个人主页
            Intent intent = new Intent(mContext, MeInfoActivity.class);
            startActivity(intent);
            isLogin(true);
        } else {
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            isLogin(false);
        }
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
            String mark = SpUtils.getInstance().getString("mark");
            Log.e("TAG", "isLogin: "+nickname);
            if(!TextUtils.isEmpty(nickname)){
                txtNickname.setText(nickname);
            }else{
                txtNickname.setText(username);
            }
            ImageLoader.loadImage(avatar,meHeadImg);
            TxtUtils.setTextView(txtMark,mark);
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

}

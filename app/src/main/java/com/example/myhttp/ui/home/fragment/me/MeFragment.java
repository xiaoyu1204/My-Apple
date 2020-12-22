package com.example.myhttp.ui.home.fragment.me;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.utils.SpUtils;
import com.example.myhttp.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends BaseFragment {

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
        if(!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))){
            Intent intent = new Intent(mContext, MeLoginActivity.class);
            startActivityForResult(intent,100);
        }else{
            ToastUtils.s(mContext,getString(R.string.tips_ok_login));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 100){
            String name = data.getStringExtra("name");
            meHeadLogin.setText(name);
        }
    }

}

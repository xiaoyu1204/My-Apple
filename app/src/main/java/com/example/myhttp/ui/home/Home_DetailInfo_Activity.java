package com.example.myhttp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.presenter.home.Home_DetailInfo_Presenter;
import com.example.myhttp.presenter.home.Home_NewGoods_Presenter;
import com.example.myhttp.view.home.IHome;
import com.example.myhttp.view.home.IHomeDetailInfo;
import com.example.myhttp.view.home.IHomeNewGoods;

public class Home_DetailInfo_Activity extends BaseActivity<Home_DetailInfo_Presenter> implements IHomeDetailInfo.View {

    //home__detail_info_nsc_category

    IHomeDetailInfo.Persenter presenter;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_home__detail_info;
    }

    @Override
    protected Home_DetailInfo_Presenter createPersenter() {
        return new Home_DetailInfo_Presenter(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        initBanner();

    }

    private void initBanner() {



    }

    @Override
    protected void initData() {
        presenter = new Home_DetailInfo_Presenter(this);
        presenter.getHomeDetailInfo(id);



    }

    @Override
    public void getHomeDetailInfoReturn(Home_DetailInfo_Bean result) {
        Home_DetailInfo_Bean.DataBeanX.InfoBean info = result.getData().getInfo();
        Log.e("TAG", "getHomeDetailInfoReturn: "+info.getName());
    }
    
}
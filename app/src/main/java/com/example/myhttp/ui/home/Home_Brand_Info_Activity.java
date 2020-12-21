package com.example.myhttp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.adapter.home.brand.HomeBrandInfoBelowAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Below_Bean;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Top_Bean;
import com.example.myhttp.presenter.home.Home_Brand_Info_Top_Presenter;
import com.example.myhttp.utils.ItemDecoration;
import com.example.myhttp.view.home.IHomeBrandInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home_Brand_Info_Activity extends BaseActivity<Home_Brand_Info_Top_Presenter> implements IHomeBrandInfo.View {

    IHomeBrandInfo.Persenter presenter;
    @BindView(R.id.fr_topic_info_img)
    ImageView frTopicInfoImg;
    @BindView(R.id.fr_topic_info_tv_title)
    TextView frTopicInfoTvTitle;
    @BindView(R.id.fr_topic_info_tv_desc)
    TextView frTopicInfoTvDesc;
    @BindView(R.id.fr_topic_info_rlv)
    RecyclerView frTopicInfoRlv;
    private int id;
    private int page = 1;
    private int size = 1000;
    private HomeBrandInfoBelowAdapter homeBrandInfoBelowAdapter;
    private ArrayList<Home_Brand_Info_Below_Bean.DataBeanX.DataBean> belowBeans;

    @Override
    protected int getLayout() {
        return R.layout.activity_home__brand__info;
    }

    @Override
    protected Home_Brand_Info_Top_Presenter createPersenter() {
        return new Home_Brand_Info_Top_Presenter(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        frTopicInfoRlv.setLayoutManager(new GridLayoutManager(this,2));
        frTopicInfoRlv.addItemDecoration(new ItemDecoration(this));
        belowBeans = new ArrayList<>();
        homeBrandInfoBelowAdapter = new HomeBrandInfoBelowAdapter(this,belowBeans);
        frTopicInfoRlv.setAdapter(homeBrandInfoBelowAdapter);

    }

    @Override
    protected void initData() {
        presenter = new Home_Brand_Info_Top_Presenter(this);
        presenter.getHomeInfoTop(id);
        presenter.getHomeInfoBelow(id,page,size);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getHomeInfoTopReturn(Home_Brand_Info_Top_Bean result) {

        Home_Brand_Info_Top_Bean.DataBean.BrandBean brand = result.getData().getBrand();

        if(brand != null){
            frTopicInfoTvTitle.setText(brand.getName());
            frTopicInfoTvDesc.setText(brand.getSimple_desc());
            Glide.with(this).load(brand.getList_pic_url()).into(frTopicInfoImg);
        }

    }

    @Override
    public void getHomeInfoBelowReturn(Home_Brand_Info_Below_Bean result) {

        List<Home_Brand_Info_Below_Bean.DataBeanX.DataBean> data = result.getData().getData();
        belowBeans.addAll(data);
        homeBrandInfoBelowAdapter.notifyDataSetChanged();

    }

}
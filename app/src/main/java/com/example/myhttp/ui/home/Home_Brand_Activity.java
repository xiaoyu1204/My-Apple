package com.example.myhttp.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.home.brand.HomeBrandAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_Brand_Bean;
import com.example.myhttp.presenter.home.Home_Brand_Presenter;
import com.example.myhttp.view.home.IHomeBrand;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home_Brand_Activity extends BaseActivity<Home_Brand_Presenter> implements IHomeBrand.View {

    IHomeBrand.Persenter persenter;
    @BindView(R.id.fr_topic_rlv)
    RecyclerView frTopicRlv;
    private int page = 1;
    private int size = 100;
    private List<Home_Brand_Bean.DataBeanX.DataBean> dataBeans;
    private HomeBrandAdapter homeBrandAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_home__brand;
    }

    @Override
    protected Home_Brand_Presenter createPersenter() {
        return new Home_Brand_Presenter(this);
    }

    @Override
    protected void initView() {
        frTopicRlv.setLayoutManager(new LinearLayoutManager(this));

        //自定义分割线    白色
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_rlv_line));
        frTopicRlv.addItemDecoration(dividerItemDecoration);

        dataBeans = new ArrayList<>();
        homeBrandAdapter = new HomeBrandAdapter(this,dataBeans);
        frTopicRlv.setAdapter(homeBrandAdapter);

        //点击监听
        Intent intent = new Intent(this, Home_Brand_Info_Activity.class);
        homeBrandAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = dataBeans.get(pos).getId();
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        persenter = new Home_Brand_Presenter(this);
        persenter.getHomeBrand(page,size);
    }

    @Override
    public void getHomeBrandReturn(Home_Brand_Bean result) {
        List<Home_Brand_Bean.DataBeanX.DataBean> data = result.getData().getData();
        dataBeans.addAll(data);
        homeBrandAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
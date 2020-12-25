package com.example.myhttp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.home.Home_Channel_Type_Bean;
import com.example.myhttp.presenter.home.Home_Type_Presenter;
import com.example.myhttp.ui.home.fragment.home.HomeTreeFragment;
import com.example.myhttp.utils.viewpager.CustomViewPager;
import com.example.myhttp.view.home.IHomeType;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Home_Type_Activity extends BaseActivity<Home_Type_Presenter> implements IHomeType.View {

    IHomeType.Persenter persenter;
    @BindView(R.id.home_type_tab)
    TabLayout homeTypeTab;
    @BindView(R.id.home_type_vp_no)
    CustomViewPager homeTypeVpNo;
    private Unbinder bind;
    private String murl;
    private String name;

    @Override
    protected int getLayout() {
        return R.layout.activity_home__type;
    }

    @Override
    protected void initView() {

        //获取传来的值
        Intent intent = getIntent();
        murl = intent.getStringExtra("murl");
        //收name
        name = intent.getStringExtra("name");

        //禁止滑动
        homeTypeVpNo.setScanScroll(false);

    }

    @Override
    protected Home_Type_Presenter createPersenter() {
        return new Home_Type_Presenter(this);
    }

    @Override
    protected void initData() {
        persenter = new Home_Type_Presenter(this);
        persenter.getHomeType(murl);
    }

    @Override
    public void getHomeTypeReturn(Home_Channel_Type_Bean result) {

        List<Home_Channel_Type_Bean.DataBean.CategoryListBean> categoryList = result.getData().getCategoryList();

        List<HomeTreeFragment> fs = new ArrayList<>();
        for (int i = 0; i <categoryList.size() ; i++) {

            int id = categoryList.get(i).getId();
            String name = categoryList.get(i).getName();
            String front_name = categoryList.get(i).getFront_name();

            HomeTreeFragment f = new HomeTreeFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("id",id);
            bundle.putString("name",name);
            bundle.putString("front_name",front_name);
            f.setArguments(bundle);

            fs.add(f);

        }

        //创建适配器
        homeTypeVpNo.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fs.get(position);
            }

            @Override
            public int getCount() {
                return fs.size();
            }
        });

        homeTypeTab.setupWithViewPager(homeTypeVpNo);

        //点一个变一个
        //遍历集合
        for (int i = 0; i < categoryList.size(); i++) {
            //给tablayout赋值
            homeTypeTab.getTabAt(i).setText(categoryList.get(i).getName());

        }
        //遍历集合
        for (int i = 0; i < categoryList.size(); i++) {
            //如果获取的name == 集合的name
            if(name.equals(categoryList.get(i).getName())){
                //被选中   select
                homeTypeTab.getTabAt(i).select();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
    }

}
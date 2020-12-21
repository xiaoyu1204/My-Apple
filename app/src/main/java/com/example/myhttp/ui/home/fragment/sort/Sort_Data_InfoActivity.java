package com.example.myhttp.ui.home.fragment.sort;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.sort.Sort_Data_InfoBean;
import com.example.myhttp.presenter.sort.SortDataInfoPresenter;
import com.example.myhttp.ui.home.fragment.home.HomeTreeFragment;
import com.example.myhttp.utils.viewpager.CustomViewPager;
import com.example.myhttp.view.sort.ISortDataInfo;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Sort_Data_InfoActivity extends BaseActivity<SortDataInfoPresenter> implements ISortDataInfo.View {

    ISortDataInfo.Persenter presenter;
    @BindView(R.id.sort_data_info_tab)
    TabLayout sortDataInfoTab;
    @BindView(R.id.sort_data_info_vp)
    CustomViewPager sortDataInfoVp;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_sort__data__info;
    }

    @Override
    protected SortDataInfoPresenter createPersenter() {
        return new SortDataInfoPresenter(this);
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        //禁止滑动
        sortDataInfoVp.setScanScroll(false);
    }

    @Override
    protected void initData() {
        presenter = new SortDataInfoPresenter(this);
        presenter.getSortDataInfo(id);
    }

    @Override
    public void getSortDataInfoReturn(Sort_Data_InfoBean result) {
        List<Sort_Data_InfoBean.DataBean.BrotherCategoryBean> brotherCategory = result.getData().getBrotherCategory();

        List<SortDataInfoTreeFragment> fs = new ArrayList<>();
        for (int i = 0; i <brotherCategory.size() ; i++) {

            int id = brotherCategory.get(i).getId();
            String name = brotherCategory.get(i).getName();
            String front_name = brotherCategory.get(i).getFront_name();

            SortDataInfoTreeFragment f = new SortDataInfoTreeFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("id",id);
            bundle.putString("name",name);
            bundle.putString("front_name",front_name);
            f.setArguments(bundle);

            fs.add(f);

        }
        //创建适配器
        sortDataInfoVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fs.get(position);
            }

            @Override
            public int getCount() {
                return fs.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return brotherCategory.get(position).getName();
            }
        });

        sortDataInfoTab.setupWithViewPager(sortDataInfoVp);

    }

}
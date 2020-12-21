package com.example.myhttp.ui.home.fragment.sort;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myhttp.MainActivity;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.model.bean.sort.SortBean;
import com.example.myhttp.model.bean.sort.SortDataBean;
import com.example.myhttp.presenter.sort.SortPresenter;
import com.example.myhttp.utils.viewpager.CustomViewPager;
import com.example.myhttp.view.sort.ISort;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class SortFragment extends BaseFragment<SortPresenter> implements ISort.View {

    ISort.Persenter persenter;
    @BindView(R.id.sort_tab)
    VerticalTabLayout sortTab;
    @BindView(R.id.mVp_type)
    CustomViewPager mVpType;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected SortPresenter createPrenter() {
        return new SortPresenter(this);
    }

    @Override
    protected void initView() {
        //禁止滑动
    }

    @Override
    protected void initData() {
        persenter = new SortPresenter(this);
        persenter.getSort();
    }



    @Override
    public void getSortReturn(SortBean result) {
        List<SortBean.DataBean.CategoryListBean> categoryList = result.getData().getCategoryList();

        List<SortDataFragment> fs = new ArrayList<>();
        for (int i = 0; i <categoryList.size() ; i++) {

            int id = categoryList.get(i).getId();

            SortDataFragment f = new SortDataFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("id",id);
            f.setArguments(bundle);

            fs.add(f);
        }

        //创建适配器
        mVpType.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
                return categoryList.get(position).getName();
            }

        });
        sortTab.setupWithViewPager(mVpType);

        sortTab.setTabAdapter(new TabAdapter() {

            @Override
            public int getCount() {
                return categoryList.size();
            }

            @Override
            public TabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public QTabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public QTabView.TabTitle getTitle(int position) {
                return new QTabView.TabTitle.Builder()
                        .setContent(categoryList.get(position).getName())
                        //改变字体颜色
                        .setTextColor(Color.BLUE, Color.BLACK)
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }

        });


    }

    @Override
    public void getSortDataReturn(SortDataBean result) {

    }

}

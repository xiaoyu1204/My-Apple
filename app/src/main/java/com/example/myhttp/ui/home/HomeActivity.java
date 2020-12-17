package com.example.myhttp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myhttp.R;
import com.example.myhttp.ui.home.fragment.home.HomeFragment;
import com.example.myhttp.ui.home.fragment.me.MeFragment;
import com.example.myhttp.ui.home.fragment.shop.ShopFragment;
import com.example.myhttp.ui.home.fragment.sort.SortFragment;
import com.example.myhttp.ui.home.fragment.topic.TopicFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private TabLayout mTab;

    private FragmentManager mFm;
    private HomeFragment homeFragment;
    private SortFragment sortFragment;
    private TopicFragment topicFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initFragment();//碎片
        initTab();//Tab添加
    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.mTab_shop);
    }

    private void initFragment() {
        //准备fragment
        homeFragment = new HomeFragment();
        sortFragment = new SortFragment();
        topicFragment = new TopicFragment();
        shopFragment = new ShopFragment();
        meFragment = new MeFragment();

        //得到fragment管理器
        mFm = getSupportFragmentManager();
        //放入布局管理器
        mFm.beginTransaction()
                .add(R.id.mRl_shop, homeFragment)//添加
                .add(R.id.mRl_shop, sortFragment)
                .add(R.id.mRl_shop, topicFragment)
                .add(R.id.mRl_shop, shopFragment)
                .add(R.id.mRl_shop, meFragment)
                .hide(sortFragment)//隐藏
                .hide(topicFragment)
                .hide(shopFragment)
                .hide(meFragment)
                .commit();//提交事物
    }

    private void initTab() {
        //添加Tab页
        mTab.addTab(mTab.newTab().setText("首页").setIcon(R.drawable.home_selector));
        mTab.addTab(mTab.newTab().setText("专题").setIcon(R.drawable.sort_selector));
        mTab.addTab(mTab.newTab().setText("分类").setIcon(R.drawable.topic_selector));
        mTab.addTab(mTab.newTab().setText("购物车").setIcon(R.drawable.shop_selector));
        mTab.addTab(mTab.newTab().setText("我的").setIcon(R.drawable.me_selector));

        //通过tablayout的监听器，实现和fragment的联动
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //开启事物
                FragmentTransaction t1 = mFm.beginTransaction();
                if (tab.getPosition() == 0) {
                    t1.show(homeFragment)
                            .hide(sortFragment)
                            .hide(topicFragment)
                            .hide(shopFragment)
                            .hide(meFragment);
                } else if (tab.getPosition() == 1) {
                    t1.show(sortFragment)
                            .hide(homeFragment)
                            .hide(topicFragment)
                            .hide(shopFragment)
                            .hide(meFragment);
                } else if (tab.getPosition() == 2) {
                    t1.show(topicFragment)
                            .hide(homeFragment)
                            .hide(sortFragment)
                            .hide(shopFragment)
                            .hide(meFragment);
                } else if (tab.getPosition() == 3) {
                    t1.show(shopFragment)
                            .hide(homeFragment)
                            .hide(sortFragment)
                            .hide(topicFragment)
                            .hide(meFragment);
                } else {
                    t1.show(meFragment)
                            .hide(homeFragment)
                            .hide(sortFragment)
                            .hide(topicFragment)
                            .hide(shopFragment);
                }
                t1.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
package com.example.myhttp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.home.ShopAdapter;
import com.example.myhttp.ui.home.fragment.home.HomeFragment;
import com.example.myhttp.ui.home.fragment.me.MeFragment;
import com.example.myhttp.ui.home.fragment.shop.ShopFragment;
import com.example.myhttp.ui.home.fragment.sort.SortFragment;
import com.example.myhttp.ui.home.fragment.topic.TopicFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    private TabLayout mTab;

    private FragmentManager mFm;
    private HomeFragment homeFragment;
    private SortFragment sortFragment;
    private TopicFragment topicFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;

    private Disposable disposable;
    private PopupWindow window;
    private TextView tv_time;
    private ViewPager mVp;
    private boolean aBoolean=true;

    //onCreat开始获取视图
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        initView();
//        initFragment();//碎片
//        initTab();//Tab添加
    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.mTab_shop);
    }

    private void initFragment() {
        //准备fragment
        homeFragment = new HomeFragment();
        topicFragment = new TopicFragment();
        sortFragment = new SortFragment();
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
                    t1.show(topicFragment)
                            .hide(homeFragment)
                            .hide(sortFragment)
                            .hide(shopFragment)
                            .hide(meFragment);
                } else if (tab.getPosition() == 2) {
                    t1.show(sortFragment)
                            .hide(homeFragment)
                            .hide(topicFragment)
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

    //onWindowFocusChanged 已经获取到视图 展示视图内容
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (aBoolean){
            initPop();//先弹出来popu
            initView();
            initFragment();//碎片
            initTab();//Tab添加
            aBoolean=false;
        }
        //initVp();
    }
    private void initPop() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(R.mipmap.h);
        integerList.add(R.mipmap.j);
        integerList.add(R.mipmap.p);

        View view = View.inflate(this,R.layout.layout_shop_popu,null);
        window = new PopupWindow(view, -1,-1);
        tv_time = view.findViewById(R.id.tv_dao);
        mVp = view.findViewById(R.id.mVp_shop);
        ShopAdapter vpAdapter = new ShopAdapter(this,integerList, window);
        mVp.setAdapter(vpAdapter);
        //展示完Popu
        popupVpCli();
        //进行显示
        window.showAsDropDown(this.findViewById(R.id.mRl_shop));
    }
    //TODO 页码的点击监听
    private void popupVpCli() {
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){//在最后一页执行倒计时
                    tv_time.setVisibility(View.VISIBLE);
                    //TODO       Interval操作符(有范围)：创建一个按照固定时间发射整数序列的Observable
                    disposable = Observable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS) //起始值，发送总数量，初始延迟，固定延迟
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    long time = 3 - aLong;
                                    tv_time.setText(time + "s");
                                    if (time == 0) {
                                        window.dismiss();
                                    }
                                }
                            });
                }else{
                    tv_time.setVisibility(View.GONE);//隐藏视图
                    cancelCallback();//取消订阅的方法
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //TODO 取消订阅的方法
    private void cancelCallback() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


}
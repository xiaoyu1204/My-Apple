package com.example.myhttp.ui.home.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.adapter.home.CategoryAdapter;
import com.example.myhttp.adapter.home.RecyBrandAdapter;
import com.example.myhttp.adapter.home.RecyHotgoodAdapter;
import com.example.myhttp.adapter.home.RecyNewgoodAdapter;
import com.example.myhttp.adapter.home.RecyTopicAdapter;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.presenter.home.HomePresenter;
import com.example.myhttp.ui.home.Home_Type_Activity;
import com.example.myhttp.utils.ImageLoader;
import com.example.myhttp.utils.TxtUtils;
import com.example.myhttp.view.home.IHome;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<IHome.Persenter> implements IHome.View {

    @BindView(R.id.home_banner)
    Banner homeBanner;

    @BindView(R.id.recy_brand)
    RecyclerView recyBrand;
    @BindView(R.id.recy_newgood)
    RecyclerView recyNewgood;
    @BindView(R.id.recy_hotgoods)
    RecyclerView recyHotgoods;
    @BindView(R.id.mRlv_home_topic)
    RecyclerView mRlvHomeTopic;
    @BindView(R.id.mLl_category)
    LinearLayout mLlCategory;
    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    private RecyBrandAdapter recyBrandAdapter;
    private List<HomeBean.DataBean.BrandListBean> brandListBeans;
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> newGoodsListBeans;
    private RecyNewgoodAdapter recyNewgoodAdapter;
    private ArrayList<HomeBean.DataBean.HotGoodsListBean> hotGoodsListBeans;
    private RecyHotgoodAdapter recyHotgoodAdapter;
    private ArrayList<HomeBean.DataBean.TopicListBean> topicListBeans;
    private RecyTopicAdapter recyTopicAdapter;
    private CategoryAdapter categoryAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPrenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initView() {
        initBrand();        //品牌制造商
        initNewGoods();     //新品首发
        initHotGoods();     //人气推荐
        initTopic();        //专题精选
    }

    //品牌制造商直供
    private void initBrand() {

        recyBrand.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        recyBrand.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyBrand.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.HORIZONTAL));

        brandListBeans = new ArrayList<>();
        recyBrandAdapter = new RecyBrandAdapter(getActivity(), brandListBeans);
        recyBrand.setAdapter(recyBrandAdapter);

    }

    //新品首发
    private void initNewGoods() {

        recyNewgood.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        newGoodsListBeans = new ArrayList<>();
        recyNewgoodAdapter = new RecyNewgoodAdapter(getActivity(), newGoodsListBeans);
        recyNewgood.setAdapter(recyNewgoodAdapter);

    }

    //人气推荐
    private void initHotGoods() {

        recyHotgoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyHotgoods.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        hotGoodsListBeans = new ArrayList<>();
        recyHotgoodAdapter = new RecyHotgoodAdapter(getActivity(), hotGoodsListBeans);
        recyHotgoods.setAdapter(recyHotgoodAdapter);

    }

    //专题精选
    private void initTopic() {

        mRlvHomeTopic.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRlvHomeTopic.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.HORIZONTAL));

        topicListBeans = new ArrayList<>();
        recyTopicAdapter = new RecyTopicAdapter(getActivity(), topicListBeans);
        mRlvHomeTopic.setAdapter(recyTopicAdapter);

    }

    @Override
    protected void initData() {
        presenter.getHome(); //初始化加载数据
    }

    @Override
    public void getHomeReturn(HomeBean result) {

        if (result.getData() != null) {
            //初始化Banner
            initBanner(result.getData().getBanner());
            //动态栏
            initChannel(result.getData().getChannel());
            //品牌制造商直供
            initBrand(result.getData().getBrandList());
            //新品首发
            initNewGoods(result.getData().getNewGoodsList());
            //人气推荐
            initHotGoods(result.getData().getHotGoodsList());
            //专题精选
            initTopic(result.getData().getTopicList());
            //居家
            initCategory(result.getData().getCategoryList());
        }

    }

    /**
     * 初始化banner
     *
     * @param list
     */
    private void initBanner(List<HomeBean.DataBean.BannerBean> list) {

        homeBanner.setImages(list).setImageLoader(new com.youth.banner.loader.ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean bannerBean = (HomeBean.DataBean.BannerBean) path;
                Glide.with(getActivity()).load(bannerBean.getImage_url()).into(imageView);
            }
        }).start();

    }

    /**
     * 初始化channel
     */
    private void initChannel(List<HomeBean.DataBean.ChannelBean> list) {

        layoutTab.removeAllViews();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        for (HomeBean.DataBean.ChannelBean item : list) {

            View channel = LayoutInflater.from(getContext()).inflate(R.layout.layout_channel_item, layoutTab, false);

            ImageView img = channel.findViewById(R.id.img_channel);
            TextView txtChannel = channel.findViewById(R.id.txt_channel);

            Glide.with(getActivity()).load(item.getIcon_url()).into(img);
            TxtUtils.setTextView(txtChannel, item.getName());
            txtChannel.setGravity(Gravity.CENTER);
            channel.setLayoutParams(params);

            layoutTab.addView(channel);

            Intent intent = new Intent(getActivity(), Home_Type_Activity.class);

        }

//        tv1.setText(list.get(0).getName());
//        tv2.setText(list.get(1).getName());
//        tv3.setText(list.get(2).getName());
//        tv4.setText(list.get(3).getName());
//        tv5.setText(list.get(4).getName());
//        Glide.with(getActivity()).load(list.get(0).getIcon_url()).into(iv1);
//        Glide.with(getActivity()).load(list.get(1).getIcon_url()).into(iv2);
//        Glide.with(getActivity()).load(list.get(2).getIcon_url()).into(iv3);
//        Glide.with(getActivity()).load(list.get(3).getIcon_url()).into(iv4);
//        Glide.with(getActivity()).load(list.get(4).getIcon_url()).into(iv5);

    }

    /**
     * 初始化brandList
     */
    private void initBrand(List<HomeBean.DataBean.BrandListBean> brandList) {
        brandListBeans.addAll(brandList);
        //刷新适配器
        recyBrandAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化newGoodsList
     */
    private void initNewGoods(List<HomeBean.DataBean.NewGoodsListBean> newGoodsList) {
        newGoodsListBeans.addAll(newGoodsList);
        //刷新适配器
        recyNewgoodAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化hotGoodsList
     */
    private void initHotGoods(List<HomeBean.DataBean.HotGoodsListBean> hotGoodsList) {
        hotGoodsListBeans.addAll(hotGoodsList);
        //刷新适配器
        recyHotgoodAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化topicList
     */
    private void initTopic(List<HomeBean.DataBean.TopicListBean> topicList) {
        topicListBeans.addAll(topicList);
        //刷新适配器
        recyTopicAdapter.notifyDataSetChanged();
    }

    /**
     * //居家
     */
    private void initCategory(List<HomeBean.DataBean.CategoryListBean> categoryList) {
        //循环放入布局 布局嵌套布局
        for (int i = 0; i < categoryList.size(); i++) {
            View view = View.inflate(getActivity(), R.layout.fragment_home_item, null);

            TextView title = view.findViewById(R.id.txt_home_title);
            RecyclerView recyhome = view.findViewById(R.id.mRlv_home);

            title.setText(categoryList.get(i).getName());//给上面的标题赋值
            List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList = categoryList.get(i).getGoodsList();

            recyhome.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            categoryAdapter = new CategoryAdapter(getActivity(), goodsList);
            recyhome.setAdapter(categoryAdapter);
            mLlCategory.addView(view);

        }
    }

}

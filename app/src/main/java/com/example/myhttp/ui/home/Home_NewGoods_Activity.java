package com.example.myhttp.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.adapter.home.HomeNewGoodsBelowAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.home.Home_NewGoods_Below_Bean;
import com.example.myhttp.model.bean.home.Home_NewGoods_Top_Bean;
import com.example.myhttp.presenter.home.Home_NewGoods_Presenter;
import com.example.myhttp.utils.ItemDecoration;
import com.example.myhttp.view.home.IHomeNewGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home_NewGoods_Activity extends BaseActivity<Home_NewGoods_Presenter> implements IHomeNewGoods.View {

    IHomeNewGoods.Persenter presenter;
    @BindView(R.id.iv_newgoods_list_img)
    ImageView ivNewgoodsListImg;
    @BindView(R.id.tv_newgoods_list_text)
    TextView tvNewgoodsListText;
    @BindView(R.id.tv_newgoods_list_all)
    TextView tvNewgoodsListAll;
    @BindView(R.id.tv_newgoods_list_price)
    TextView tvNewgoodsListPrice;
    @BindView(R.id.img_arrow_up)
    ImageView imgArrowUp;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrowDown;
    @BindView(R.id.tv_newgoods_list_sort)
    TextView tvNewgoodsListSort;
    @BindView(R.id.mRlv_NewGoodList)
    RecyclerView mRlvNewGoodList;
    private ArrayList<Home_NewGoods_Below_Bean.DataBeanX.DataBean> belowBeans;
    private HomeNewGoodsBelowAdapter homeNewGoodsBelowAdapter;

    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order;
    private String sort;
    private int categoryId = 0;

    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";

    @Override
    protected int getLayout() {
        return R.layout.activity_home__new_goods;
    }

    @Override
    protected Home_NewGoods_Presenter createPersenter() {
        return new Home_NewGoods_Presenter(this);
    }

    @Override
    @SuppressLint("ResourceType")
    protected void initView() {

        order = ASC;
        sort = DEFAULT;
        categoryId = 0;
        tvNewgoodsListPrice.setTag(0);
        tvNewgoodsListAll.setTextColor(Color.parseColor(this.getString(R.color.red)));

        mRlvNewGoodList.setLayoutManager(new GridLayoutManager(this, 2));
        mRlvNewGoodList.addItemDecoration(new ItemDecoration(this));

        belowBeans = new ArrayList<>();
        homeNewGoodsBelowAdapter = new HomeNewGoodsBelowAdapter(this, belowBeans);
        mRlvNewGoodList.setAdapter(homeNewGoodsBelowAdapter);

    }

    @Override
    protected void initData() {
        presenter = new Home_NewGoods_Presenter(this);
        presenter.getHomeNewGoodsTop();
        presenter.getHomeNewGoodsBelow(isNew, page, size, order, sort, categoryId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getHomeNewGoodsTopReturn(Home_NewGoods_Top_Bean result) {

        Home_NewGoods_Top_Bean.DataBean.BannerInfoBean bannerInfo = result.getData().getBannerInfo();

        if (bannerInfo != null) {
            tvNewgoodsListText.setText(bannerInfo.getName());
            Glide.with(this).load(bannerInfo.getImg_url()).into(ivNewgoodsListImg);
        }

    }

    @Override
    public void getHomeNewGoodsBelowReturn(Home_NewGoods_Below_Bean result) {
        //清空集合
        belowBeans.clear();
        List<Home_NewGoods_Below_Bean.DataBeanX.DataBean> data = result.getData().getData();
        belowBeans.addAll(data);
        homeNewGoodsBelowAdapter.notifyDataSetChanged();
    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.tv_newgoods_list_all, R.id.tv_newgoods_list_price, R.id.tv_newgoods_list_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_newgoods_list_all:
                resetPriceState();
                tvNewgoodsListAll.setTextColor(Color.parseColor(this.getString(R.color.red)));
                sort = DEFAULT;
                //点击给值
                presenter.getHomeNewGoodsBelow(isNew, page, size, order, sort, categoryId);
                break;
            case R.id.tv_newgoods_list_price:
                int tag = (int) tvNewgoodsListPrice.getTag();
                if(tag == 0){
                    resetPriceState();
                    priceStateUp();
                    tvNewgoodsListPrice.setTag(1);
                    order = ASC;
                }else if(tag == 1){
                    resetPriceState();
                    priceStateDown();
                    tvNewgoodsListPrice.setTag(0);
                    order = DESC;
                }
                sort = PRICE;
                //点击给值
                presenter.getHomeNewGoodsBelow(isNew, page, size, order, sort, categoryId);
                break;
            case R.id.tv_newgoods_list_sort:
                resetPriceState();
                tvNewgoodsListSort.setTextColor(Color.parseColor(this.getString(R.color.red)));
                sort = CATEGORY;
                //点击给值
                presenter.getHomeNewGoodsBelow(isNew, page, size, order, sort, categoryId);
                initPw();
                break;
        }

    }

    //TODO 重置条件选择的所有状态
    @SuppressLint("ResourceType")
    private void resetPriceState() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        //价格颜色
        tvNewgoodsListPrice.setTextColor(Color.parseColor(getString(R.color.black)));
        //综合颜色
        tvNewgoodsListAll.setTextColor(Color.parseColor(getString(R.color.black)));
        //分类颜色
        tvNewgoodsListSort.setTextColor(Color.parseColor(getString(R.color.black)));
        //价格
        tvNewgoodsListPrice.setTag(0);
    }

    //向上
    //TODO 按价格升序排序
    @SuppressLint("ResourceType")
    private void priceStateUp() {
        //上面图标变红
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select);
        //下面图标变黑
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        //价格颜色
        tvNewgoodsListPrice.setTextColor(Color.parseColor(getString(R.color.red)));

    }

    //向下
    //TODO 价格的降序排列
    @SuppressLint("ResourceType")
    private void priceStateDown() {
        //上面图标变黑
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        //下面图标变红
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
        //给文字价格颜色
        tvNewgoodsListPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    //分类 弹出pw   流布局
    private void initPw() {

        View inflate = View.inflate(this, R.layout.layout_home_newgoods_pw, null);
        new PopupWindow(inflate, RecyclerView.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.WRAP_CONTENT);

    }

}
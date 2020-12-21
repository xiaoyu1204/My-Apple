package com.example.myhttp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.adapter.home.detail.HomeDetailInfoButtomAdapter;
import com.example.myhttp.adapter.home.detail.HomeDetailInfoIssueAdapter;
import com.example.myhttp.adapter.home.detail.HomeDetailInfoparamenterAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bottom_Bean;
import com.example.myhttp.presenter.home.Home_DetailInfo_Presenter;
import com.example.myhttp.utils.ImageLoaderUtils;
import com.example.myhttp.utils.ItemDecoration;
import com.example.myhttp.view.home.IHomeDetailInfo;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Home_DetailInfo_Activity extends BaseActivity<Home_DetailInfo_Presenter> implements IHomeDetailInfo.View {

    IHomeDetailInfo.Persenter presenter;
    private int id;

    //详情上面
    private Banner home_detail_info_banner_category;
    private TextView home_detail_info_title;        //沙发
    private TextView home_detail_info_desc;         //沙发下面那行字
    private TextView home__detail_info_price;       //价格

    //中间
    private TextView home_detail_info_comment;      //评论
    private TextView home_detail_info_all;          //查看全部  //底部列表数据

    //评论
    private ImageView home_detail_info_comment_head_img;    //头像
    private TextView home_detail_info_comment_head_name;    //名称
    private TextView home_detail_info_comment_head_date;    //日期
    private TextView home_detail_info_comment_head_desc;    //内容
    private ImageView home_detail_info_comment_img;         //评论图片

    //webview
    private WebView webView_category;

    //rlv
    private RecyclerView mRlv_category_issue;         //常见问题
    private RecyclerView mRlv_category_all;           //大家都爱看
    private RecyclerView mRlv_category_parameter;     //商品参数

    //底部
    private ImageView img_collect;              //收藏
    private ImageView iv_category_car;          //购物车
    private TextView tv_category_number;        //购物车上面红字
    private TextView tv_category_buy;           //立即购买
    private TextView tv_category_addCar;        //加入购物车

    //评论con
    private ConstraintLayout home_detail_info_comment_con1;        //加入购物车
    private ConstraintLayout home_detail_info_comment_con2;        //加入购物车

    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";

    //集合
    private ArrayList<Home_DetailInfo_Bottom_Bean.DataBean.GoodsListBean> goodsList;//底部列表集合
    private ArrayList<Home_DetailInfo_Bean.DataBeanX.IssueBean> issuelist;//常见问题集合
    private ArrayList<Home_DetailInfo_Bean.DataBeanX.AttributeBean> attributeList;//商品参数集合
    //底部列表适配器
    private HomeDetailInfoButtomAdapter categoryButtomInfoAdapter;
    private HomeDetailInfoIssueAdapter categoryIssueAdapter;  //常见问题
    private HomeDetailInfoparamenterAdapter categoryParameterAdapter;  //商品参数

    @Override
    protected int getLayout() {
        return R.layout.activity_home_detail_info;
    }

    @Override
    protected Home_DetailInfo_Presenter createPersenter() {
        return new Home_DetailInfo_Presenter(this);
    }

    @Override
    protected void initView() {

        home_detail_info_banner_category = (Banner) findViewById(R.id.home_detail_info_banner_category);
        home_detail_info_title = (TextView) findViewById(R.id.home_detail_info_title);
        home_detail_info_desc = (TextView) findViewById(R.id.home_detail_info_desc);
        home__detail_info_price = (TextView) findViewById(R.id.home__detail_info_price);
        home_detail_info_comment = (TextView) findViewById(R.id.home_detail_info_comment);
        home_detail_info_all = (TextView) findViewById(R.id.home_detail_info_all);
        home_detail_info_comment_head_img = (ImageView) findViewById(R.id.home_detail_info_comment_head_img);
        home_detail_info_comment_head_name = (TextView) findViewById(R.id.home_detail_info_comment_head_name);
        home_detail_info_comment_head_date = (TextView) findViewById(R.id.home_detail_info_comment_head_date);
        home_detail_info_comment_head_desc = (TextView) findViewById(R.id.home_detail_info_comment_head_desc);
        home_detail_info_comment_img = (ImageView) findViewById(R.id.home_detail_info_comment_img);
        webView_category = (WebView) findViewById(R.id.webView_category);
        mRlv_category_issue = (RecyclerView) findViewById(R.id.mRlv_category_issue);
        mRlv_category_all = (RecyclerView) findViewById(R.id.mRlv_category_all);
        mRlv_category_parameter = (RecyclerView) findViewById(R.id.mRlv_category_parameter);
        img_collect = (ImageView) findViewById(R.id.img_collect);
        iv_category_car = (ImageView) findViewById(R.id.iv_category_car);
        tv_category_number = (TextView) findViewById(R.id.tv_category_number);
        tv_category_buy = (TextView) findViewById(R.id.tv_category_buy);
        tv_category_addCar = (TextView) findViewById(R.id.tv_category_addCar);

        home_detail_info_comment_con1 = (ConstraintLayout) findViewById(R.id.home_detail_info_comment_con1);
        home_detail_info_comment_con2 = (ConstraintLayout) findViewById(R.id.home_detail_info_comment_con2);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        initViewIssue();//常见问题布局
        initBottomInfo();//底部列表数据
        initViewParameter();//商品参数

    }

    @Override
    protected void initData() {
        presenter = new Home_DetailInfo_Presenter(this);
        presenter.getHomeDetailInfo(id);
        presenter.getHomeDetailInfoBottom(id);
    }


    //TODO 商品参数布局
    private void initViewParameter() {
        attributeList = new ArrayList<>();
        mRlv_category_parameter.setLayoutManager(new LinearLayoutManager(this));
        categoryParameterAdapter = new HomeDetailInfoparamenterAdapter(this, attributeList);
        mRlv_category_parameter.setAdapter(categoryParameterAdapter);
    }

    //TODO 常见问题布局
    private void initViewIssue() {
        issuelist = new ArrayList<>();
        mRlv_category_issue.setLayoutManager(new LinearLayoutManager(this));
        categoryIssueAdapter = new HomeDetailInfoIssueAdapter(this, issuelist);
        mRlv_category_issue.setAdapter(categoryIssueAdapter);
    }

    //TODO 底部列表数据
    private void initBottomInfo() {
        goodsList = new ArrayList<>();
        mRlv_category_all.setLayoutManager(new GridLayoutManager(this, 2));
        mRlv_category_all.addItemDecoration(new ItemDecoration(this));
        categoryButtomInfoAdapter = new HomeDetailInfoButtomAdapter(this, goodsList);
        mRlv_category_all.setAdapter(categoryButtomInfoAdapter);
    }

    //TODO 居家 商品详情购买页
    @Override
    public void getHomeDetailInfoReturn(Home_DetailInfo_Bean result) {
        if (result != null) {
            //Banner
            initBanner(result.getData());
            //Banner下面的展示数据
            initInfo(result.getData().getInfo());
            //评论
            initComment(result.getData().getComment().getData());
            //常见问题数据
            initIssue(result.getData().getIssue());
            //h5 商品详情
            initGoodDetail(result.getData().getInfo().getGoods_desc());
            //商品参数
            initParameter(result.getData().getAttribute());
        }
    }

    //TODO Banner数据
    private void initBanner(Home_DetailInfo_Bean.DataBeanX data) {
        home_detail_info_banner_category.setImages(data.getGallery()).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Home_DetailInfo_Bean.DataBeanX.GalleryBean bean = (Home_DetailInfo_Bean.DataBeanX.GalleryBean) path;
                Glide.with(context).load(bean.getImg_url()).into(imageView);
            }
        }).start();
    }

    //TODO Banner下面的展示数据
    private void initInfo(Home_DetailInfo_Bean.DataBeanX.InfoBean info) {
        home_detail_info_title.setText(info.getName());
        home_detail_info_desc.setText(info.getGoods_brief());
        home__detail_info_price.setText("￥"+info.getRetail_price());
    }

    //TODO h5 商品详情数据
    private void initGoodDetail(String webData) {
        String content = h5.replace("word", webData);
        Log.e("TAG", content);
        webView_category.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    //TODO 评论
    private void initComment(Home_DetailInfo_Bean.DataBeanX.CommentBean.DataBean data) {

        if(data!=null && data.getAdd_time() != null && data.getNickname() != null && data.getContent() != null && data.getPic_list()!= null){
            home_detail_info_comment_con1.setVisibility(View.VISIBLE);
            home_detail_info_comment_con2.setVisibility(View.VISIBLE);

            //时间
            home_detail_info_comment_head_date.setText(data.getAdd_time());
            //名字
            home_detail_info_comment_head_name.setText(data.getNickname());
            //评论内容
            home_detail_info_comment_head_desc.setText(data.getContent());
            //底部图片
            ImageLoaderUtils.loadImage(data.getPic_list().get(0).getPic_url(),home_detail_info_comment_img);

        }

    }

    //TODO 常见问题数据
    private void initIssue(List<Home_DetailInfo_Bean.DataBeanX.IssueBean> issue) {
        issuelist.addAll(issue);
        categoryIssueAdapter.notifyDataSetChanged();
    }

    //TODO 商品参数数据
    private void initParameter(List<Home_DetailInfo_Bean.DataBeanX.AttributeBean> attribute) {
        attributeList.addAll(attribute);
        categoryParameterAdapter.notifyDataSetChanged();
    }

    //TODO 商品 详情购买页 底部数据列表
    @Override
    public void getHomeDetailInfoBottomReturn(Home_DetailInfo_Bottom_Bean result) {
        List<Home_DetailInfo_Bottom_Bean.DataBean.GoodsListBean> data = result.getData().getGoodsList();
        goodsList.addAll(data);
        categoryButtomInfoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

}
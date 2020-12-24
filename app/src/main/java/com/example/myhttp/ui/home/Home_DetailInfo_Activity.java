package com.example.myhttp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.adapter.home.bigimg.HomeDetailBigImageAdapter;
import com.example.myhttp.adapter.home.detail.HomeDetailInfoButtomAdapter;
import com.example.myhttp.adapter.home.detail.HomeDetailInfoIssueAdapter;
import com.example.myhttp.adapter.home.detail.HomeDetailInfoparamenterAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bottom_Bean;
import com.example.myhttp.model.bean.shop.AddCarBean;
import com.example.myhttp.presenter.home.Home_DetailInfo_Presenter;
import com.example.myhttp.ui.home.fragment.home.HomeBigimageActivity;
import com.example.myhttp.ui.home.fragment.me.MeLoginActivity;
import com.example.myhttp.utils.ImageLoaderUtils;
import com.example.myhttp.utils.ItemDecoration;
import com.example.myhttp.utils.SpUtils;
import com.example.myhttp.utils.ToastUtils;
import com.example.myhttp.view.home.IHomeDetailInfo;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Home_DetailInfo_Activity extends BaseActivity<IHomeDetailInfo.Persenter> implements IHomeDetailInfo.View {

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
    private RecyclerView home_detail_info_bigimage;     //商品详情

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

    //添加购物车
    private Button btn_jia;
    private Button btn_jian;
    private TextView tv_shu;
    public static final int RECOMMEND_CAR = 1000; //打开购物车的指令
    private int shu;    //购买的数量
    private boolean isSelect = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_detail_info;
    }

    @Override
    protected IHomeDetailInfo.Persenter createPersenter() {
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
//        webView_category = (WebView) findViewById(R.id.webView_category);
        mRlv_category_issue = (RecyclerView) findViewById(R.id.mRlv_category_issue);
        mRlv_category_all = (RecyclerView) findViewById(R.id.mRlv_category_all);
        mRlv_category_parameter = (RecyclerView) findViewById(R.id.mRlv_category_parameter);
        home_detail_info_bigimage = (RecyclerView) findViewById(R.id.home_detail_info_bigimage);
        img_collect = (ImageView) findViewById(R.id.img_collect);
        iv_category_car = (ImageView) findViewById(R.id.iv_category_car);
        tv_category_number = (TextView) findViewById(R.id.tv_category_number);
        tv_category_buy = (TextView) findViewById(R.id.tv_category_buy);
        tv_category_addCar = (TextView) findViewById(R.id.tv_category_addCar);

        home_detail_info_comment_con1 = (ConstraintLayout) findViewById(R.id.home_detail_info_comment_con1);
        home_detail_info_comment_con2 = (ConstraintLayout) findViewById(R.id.home_detail_info_comment_con2);

        initViewIssue();//常见问题布局
        initBottomInfo();//底部列表数据
        initViewParameter();//商品参数

    }

    @Override
    protected void initData() {
        //接受值 进行判断
        presenter = new Home_DetailInfo_Presenter(this);
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            id = intent.getIntExtra("id", 0);
            if (id > 0) {
                presenter.getHomeDetailInfo(id);
                presenter.getHomeDetailInfoBottom(id);
            } else {
                ToastUtils.s(this, getString(R.string.tips_error_goodid));
            }
        }
    }

    //TODO 居家 商品详情购买页
    @Override
    public void getHomeDetailInfoReturn(Home_DetailInfo_Bean result) {
        if (result != null) {
            //Banner
            initBanner(result.getData());
            //Banner下面的展示数据
            initInfo(result.getData().getInfo(),result.getData().getProductList());
            //评论
            initComment(result.getData().getComment().getData());
            //常见问题数据
            initIssue(result.getData().getIssue());
            //h5 商品详情
//            initGoodDetail(result.getData().getInfo().getGoods_desc());
            //商品参数
            initParameter(result.getData().getAttribute());

            //展示goods_desc  大图
            showImage(result.getData().getInfo().getGoods_desc());

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

    //TODO Banner下面的展示数据 商品信息
    private void initInfo(Home_DetailInfo_Bean.DataBeanX.InfoBean info, List<Home_DetailInfo_Bean.DataBeanX.ProductListBean> productList) {
        home_detail_info_title.setText(info.getName());
        home_detail_info_desc.setText(info.getGoods_brief());
        home__detail_info_price.setText("￥" + info.getRetail_price());

        //加入购物车
        initaddCar(info,productList);

    }

    //TODO 评论
    private void initComment(Home_DetailInfo_Bean.DataBeanX.CommentBean.DataBean data) {

        if (data != null && data.getAdd_time() != null && data.getNickname() != null && data.getContent() != null && data.getPic_list() != null) {
            home_detail_info_comment_con1.setVisibility(View.VISIBLE);  //进行显示评论
            home_detail_info_comment_con2.setVisibility(View.VISIBLE);      //显示商品文字

            //时间
            home_detail_info_comment_head_date.setText(data.getAdd_time());
            //名字
            home_detail_info_comment_head_name.setText(data.getNickname());
            //评论内容
            home_detail_info_comment_head_desc.setText(data.getContent());
            //底部图片
            ImageLoaderUtils.loadImage(data.getPic_list().get(0).getPic_url(), home_detail_info_comment_img);

        }

    }

    //TODO 商品参数布局
    private void initViewParameter() {
        attributeList = new ArrayList<>();
        mRlv_category_parameter.setLayoutManager(new LinearLayoutManager(this));
        categoryParameterAdapter = new HomeDetailInfoparamenterAdapter(this, attributeList);
        mRlv_category_parameter.setAdapter(categoryParameterAdapter);
    }

    //TODO 商品参数数据
    private void initParameter(List<Home_DetailInfo_Bean.DataBeanX.AttributeBean> attribute) {
        attributeList.addAll(attribute);
        categoryParameterAdapter.notifyDataSetChanged();
    }

    //TODO h5 商品详情数据
    private void initGoodDetail(String webData) {
        String content = h5.replace("word", webData);
        Log.e("TAG", content);
        webView_category.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    //TODO 显示大图
    private void showImage(String goods_desc) {

        ArrayList<String> listUrl = new ArrayList<>();
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(goods_desc);

        while (matcher.find()) {
            String word = matcher.group();

            int start = word.indexOf("\"") + 1;
            //截取.jpg
            int end = word.indexOf(".jpg");     //截取到就有.jpg
            if (end > 0) {  //如果是jpg格式的就截取jpg       有的话就 >0
                String url = word.substring(start, end);     //截取 start 到 .jpg   中间的网址
                if (url != null) {      //如果 截取的网址 不等于null
                    url = url + ".jpg";      //就在尾部加.jpg
                    listUrl.add(url);      //添加到集合
                } else {
                    return;     //结束
                }
            } else {
                int end1 = word.indexOf(".png");    //如果是png格式的就截取png
                String url = word.substring(start, end1);    //截取start 到     .png
                if (url != null) {       //如果 截取的网址 不等于null
                    url = url + ".png";      //就在尾部加.png
                    listUrl.add(url);    //添加到集合
                } else {
                    return; //结束
                }
            }
        }

        home_detail_info_bigimage.setLayoutManager(new LinearLayoutManager(this));
        HomeDetailBigImageAdapter homeDetailBigImageAdapter = new HomeDetailBigImageAdapter(this, listUrl);
        home_detail_info_bigimage.setAdapter(homeDetailBigImageAdapter);

        //点击条目跳转
        homeDetailBigImageAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("image", listUrl);
                bundle.putInt("position", pos);  //点那个就传那个下标

                Intent intent = new Intent(Home_DetailInfo_Activity.this, HomeBigimageActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);

            }
        });

    }

    //TODO 常见问题布局
    private void initViewIssue() {
        issuelist = new ArrayList<>();
        mRlv_category_issue.setLayoutManager(new LinearLayoutManager(this));
        categoryIssueAdapter = new HomeDetailInfoIssueAdapter(this, issuelist);
        mRlv_category_issue.setAdapter(categoryIssueAdapter);
    }

    //TODO 常见问题数据
    private void initIssue(List<Home_DetailInfo_Bean.DataBeanX.IssueBean> issue) {
        issuelist.addAll(issue);
        categoryIssueAdapter.notifyDataSetChanged();
    }

    //TODO 底部列表数据
    private void initBottomInfo() {
        goodsList = new ArrayList<>();
        mRlv_category_all.setLayoutManager(new GridLayoutManager(this, 2));
        mRlv_category_all.addItemDecoration(new ItemDecoration(this));
        categoryButtomInfoAdapter = new HomeDetailInfoButtomAdapter(this, goodsList);
        mRlv_category_all.setAdapter(categoryButtomInfoAdapter);

        iv_category_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开购物车
                setResult(RECOMMEND_CAR);
                finish();
            }
        });

    }

    //TODO 商品 详情购买页 底部数据列表
    @Override
    public void getHomeDetailInfoBottomReturn(Home_DetailInfo_Bottom_Bean result) {
        List<Home_DetailInfo_Bottom_Bean.DataBean.GoodsListBean> data = result.getData().getGoodsList();
        goodsList.addAll(data);
        categoryButtomInfoAdapter.notifyDataSetChanged();
    }

    //添加购物车返回
    @Override
    public void addGoodCarReturn(AddCarBean addCarBean) {
        //添加成功以后跟新数量显示
        int number = addCarBean.getData().getCartTotal().getGoodsCount();
        tv_category_number.setText(String.valueOf(number));
        tv_category_number.setVisibility(View.VISIBLE);
    }

    //TODO 加入购物车
    private void initaddCar(Home_DetailInfo_Bean.DataBeanX.InfoBean info, List<Home_DetailInfo_Bean.DataBeanX.ProductListBean> productList) {
        //判断选中状态
        if (isSelect == false) {
            isSelect = true;
        } else {
            isSelect = false;
        }

        tv_category_addCar.setOnClickListener(new View.OnClickListener() {

            private PopupWindow popupWindow;

            @Override
            public void onClick(View v) {
                //显示隐藏
                if (isSelect) {
                    View join_view = LayoutInflater.from(Home_DetailInfo_Activity.this).inflate(R.layout.layout_detail_pop, null);
                    popupWindow = new PopupWindow(join_view, GridLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow.showAtLocation(tv_category_addCar, Gravity.BOTTOM, 0, 150);

                    ImageView image_pop = join_view.findViewById(R.id.detail_image_pop);
                    TextView price_pop = join_view.findViewById(R.id.detail_tv_price_pop);
                    btn_jia = join_view.findViewById(R.id.detail_btn_jia);
                    btn_jian = join_view.findViewById(R.id.detail_btn_jian);
                    tv_shu = join_view.findViewById(R.id.detail_tv_shu);
                    TextView tv_back = join_view.findViewById(R.id.detail_tv_back);

                    Glide.with(Home_DetailInfo_Activity.this).load(info.getList_pic_url()).into(image_pop);
                    price_pop.setText("价格:  ￥" + info.getRetail_price() + "");
                    shu = 1;

                    ClickListener clickListener = new ClickListener();
                    btn_jia.setOnClickListener(clickListener);
                    btn_jian.setOnClickListener(clickListener);

                    tv_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                            isSelect = false;
                        }
                    });

                    isSelect = false;
                } else {

                    popupWindow.dismiss();

                    if(shu <= 0){
                        ToastUtils.s(Home_DetailInfo_Activity.this,getString(R.string.tips_shu));
                        return;
                    }
                    if(productList.size() > 0){
                        int goodsId = info.getId();
                        String number = tv_shu.getText().toString();
                        int productid = productList.get(0).getId();
                        Map<String,String> map = new HashMap<>();
                        map.put("goodsId",String.valueOf(goodsId));
                        map.put("number",number);
                        map.put("productId",String.valueOf(productid));
                        presenter.addGoodCar(map);
                    }

                    View join_view = LayoutInflater.from(Home_DetailInfo_Activity.this).inflate(R.layout.layout_detail_pop_ok, null);
                    PopupWindow popupWindow1 = new PopupWindow(join_view, 200, 200);

                    WindowManager.LayoutParams attributes = getWindow().getAttributes();
                    attributes.alpha = 0.5f;
                    getWindow().setAttributes(attributes);

                    popupWindow1.showAtLocation(tv_category_addCar, Gravity.CENTER, 0, 0);

                    //两秒自动关闭
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    popupWindow1.dismiss();
                                    WindowManager.LayoutParams attributes = getWindow().getAttributes();
                                    attributes.alpha = 1f;
                                    getWindow().setAttributes(attributes);
                                }
                            });
                        }
                    },1000,3000);

                    isSelect = true;

                }
            }
        });
    }

    //TODO 购物车的点击
    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.detail_btn_jia:
                    shu++;
                    if (shu > 0) {
                        tv_shu.setText(shu + "");
                    }
                    break;
                case R.id.detail_btn_jian:
                    shu--;
                    if (shu > 0) {
                        tv_shu.setText(shu + "");
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
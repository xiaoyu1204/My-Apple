package com.example.myhttp.model.api;

import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.model.bean.home.Home_Brand_Bean;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Below_Bean;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Top_Bean;
import com.example.myhttp.model.bean.home.Home_Channel_Tree_Bean;
import com.example.myhttp.model.bean.home.Home_Channel_Type_Bean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bottom_Bean;
import com.example.myhttp.model.bean.home.Home_NewGoods_Below_Bean;
import com.example.myhttp.model.bean.home.Home_NewGoods_Top_Bean;
import com.example.myhttp.model.bean.me.MeLoginBean;
import com.example.myhttp.model.bean.me.MeRegisterBean;
import com.example.myhttp.model.bean.sort.SortBean;
import com.example.myhttp.model.bean.sort.SortDataBean;
import com.example.myhttp.model.bean.sort.Sort_Data_InfoBean;
import com.example.myhttp.model.bean.topic.TopicBean;
import com.example.myhttp.model.bean.topic.TopicCommentBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiShop {

    String BASE_URL = "http://cdplay.cn/api/";

    @GET("index")
    Flowable<HomeBean> getHome();

    //分类居家    /pages/category/category?id=1005000
    @GET("catalog/index")
    Flowable<Home_Channel_Type_Bean> getChannelType(@Query("murl")String murl);

    //分类数据居家  1005007 1 100
    @GET("goods/list?page=1&size=100&")
    Flowable<Home_Channel_Tree_Bean> getChannelTree(@Query("categoryId")int categoryId);

    //专题
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page")int page);

    //专题评论    valueId=314&typeId=1&size=5
    @GET("comment/list")
    Flowable<TopicCommentBean> getTopicComment(@Query("valueId")int valueId,@Query("typeId")int typeId,@Query("size")int size);

   //首页制造商     page=1&size=1000
    @GET("brand/list")
    Flowable<Home_Brand_Bean> getHomeBrand(@Query("page")int page, @Query("size")int size);

    //首页制造商点击进去 上面图片和文字     id=1001000
    @GET("brand/detail")
    Flowable<Home_Brand_Info_Top_Bean> getHomeBrandInfoTop(@Query("id")int id);

    //首页制造商点击进去 下面的数据       brandId=1001000&page=1&size=1000
    @GET("goods/list")
    Flowable<Home_Brand_Info_Below_Bean> getHomeBrandInfoBelow(@Query("brandId")int brandId,@Query("page")int page,@Query("size")int size);

    //首页新品首发    点进去
    @GET("goods/hot")
    Flowable<Home_NewGoods_Top_Bean> getHomeNewBrandsTop();

    //首页新品首发    点进去     下面的数据       isNew=1  &  page=1  &  size =1000  &  order=asc  &  sort=default  &  categoryId=0
    @GET("goods/list")
    Flowable<Home_NewGoods_Below_Bean> getHomeNewGoodsBelow(@Query("isNew")int isNew,@Query("page")int page,@Query("size")int size,@Query("order")String order,@Query("sort")String sort,@Query("categoryId")int categoryId);

    //商品详情  id=1155000
    @GET("goods/detail")
    Flowable<Home_DetailInfo_Bean> getDetailInfo(@Query("id")int id);

    //商品详情底部数据  id=1155000
    @GET("api/goods/related")
    Flowable<Home_DetailInfo_Bottom_Bean> getDetailInfoBottom(@Query("id") int id);

    //分类竖着导航
    @GET("catalog/index")
    Flowable<SortBean> getHomeSort();

    //分类右边数据    id=1005000
    @GET("catalog/current")
    Flowable<SortDataBean> getSortData(@Query("id") int id);

    //分类右边数据点击详情
    @GET("goods/category")
    Flowable<Sort_Data_InfoBean> getSortDataInfo(@Query("id") int id);

    //登录接口
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<MeLoginBean> getMeLogin(@Field("username") String username, @Field("password") String password);

    //注册接口
    @POST("auth/register")
    @FormUrlEncoded
    Flowable<MeRegisterBean> getMeRegist(@Field("username") String username, @Field("password") String password);

    //添加到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<MeLoginBean> getAddCar(@FieldMap HashMap<String,String> map);

}

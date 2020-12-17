package com.example.myhttp.model.api;

import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.model.bean.home.Home_Tree_Bean;
import com.example.myhttp.model.bean.home.Home_Type_Bean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiShop {

    String BASE_URL = "http://cdplay.cn/api/";

    @GET("index")
    Flowable<HomeBean> getHome();

    //分类
    @GET("api/goods/list")
    Flowable<Home_Type_Bean> getChannelType(@Query("categoryId")String id);

    //分类数据
    @GET("api/catalog/index/pages/category/category")
    Flowable<Home_Tree_Bean> getChannel(@Query("id")String id);


}

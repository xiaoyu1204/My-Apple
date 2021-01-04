package com.example.myhttp.model.api;

import com.example.myhttp.model.bean.app.AppBean;
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
import com.example.myhttp.model.bean.me.LogoutBean;
import com.example.myhttp.model.bean.me.MeLoginBean;
import com.example.myhttp.model.bean.me.MeRegisterBean;
import com.example.myhttp.model.bean.me.UserInfoBean;
import com.live.model.bean.CreateRoomBean;
import com.live.model.bean.RoomBean;
import com.example.myhttp.model.bean.shop.AddCarBean;
import com.example.myhttp.model.bean.shop.AddressAddProvinceBean;
import com.example.myhttp.model.bean.shop.AddressBean;
import com.example.myhttp.model.bean.shop.CarBean;
import com.example.myhttp.model.bean.shop.DeleteCarBean;
import com.example.myhttp.model.bean.shop.UpdateCarBean;
import com.example.myhttp.model.bean.sort.SortBean;
import com.example.myhttp.model.bean.sort.SortDataBean;
import com.example.myhttp.model.bean.sort.Sort_Data_InfoBean;
import com.example.myhttp.model.bean.topic.TopicBean;
import com.example.myhttp.model.bean.topic.TopicCommentBean;
import com.example.myhttp.model.bean.topic.TopicRelatedBean;
import com.example.myhttp.model.bean.topic.TopicdeBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiShop {

    String BASE_URL = "https://cdplay.cn/api/";

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

    //专题详情页评论评论数据    valueId=314&typeId=1&size=5
    @GET("comment/list")
    Flowable<TopicCommentBean> getTopicComment(@Query("valueId")int valueId,@Query("typeId")int typeId,@Query("size")int size);

    //专题详情数据        id=314
    @GET("topic/detail")
    Flowable<TopicdeBean> getTopicde(@Query("id") int id);

    //专题详情页相关的专题推荐数据        id=314
    @GET("topic/related")
    Flowable<TopicRelatedBean> getTopicRela(@Query("id") int id);

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
    Flowable<MeLoginBean> MeLogin(@Field("username") String username, @Field("password") String password);

    //注册接口
    @POST("auth/registernew")
    @FormUrlEncoded
    Flowable<MeRegisterBean> MeRegist(@Field("username") String username, @Field("password") String password);

    //添加到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap Map<String,String> map);

    //更新购物车的数据
    @POST("cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);

    //删除购物车数据
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);

    //购物车列表
    @GET("cart/index")
    Flowable<CarBean> getCarList();

    //收获地址列表
    @GET("address/list")
    Flowable<AddressBean> getAddress();

    //添加地址
    @POST("address/save")
    @FormUrlEncoded
    Flowable<DeleteCarBean> AddressAdd(@Field("productIds") String productIds);

    //获得省市接口数据
    @GET("region/list") //parentId  1
    Flowable<AddressAddProvinceBean> getAddressAddProvince(@Query("parentId")int parentId);

    //退出登录
    @POST("auth/logout")
    Flowable<LogoutBean> Logout();

    //用户信息更新
    @POST("user/updateUserInfo")
    Flowable<UserInfoBean> updateUserInfo(@FieldMap Map<String,String> map);

    //版本更新
    @GET("apk/appinfo")
    Flowable<AppBean> getAppInfo();

    //创建直播间
    @POST("live/createRoom") //room_name 小白的直播间  房间名      room_icon https://shop-app1.oss-cn-beijing.aliyuncs.com/live/1/room.jpg    房间列表默认的背景图
    @FormUrlEncoded     //isopen    1   1公开  2密码
    Flowable<CreateRoomBean> CreateRoom(@FieldMap Map<String,String> map);

    //获取房间列表
    @GET("live/getRoomList")
    Flowable<RoomBean> getRoom();

}

package com.live.model.api;
import com.live.model.bean.CreateRoomBean;
import com.live.model.bean.RoomBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;;

public interface ApiShop {

    String BASE_URL = "https://cdplay.cn/";

    //创建直播间
    @POST("api/live/createRoom") //room_name 小白的直播间  房间名      room_icon https://shop-app1.oss-cn-beijing.aliyuncs.com/live/1/room.jpg    房间列表默认的背景图
    @FormUrlEncoded     //isopen    1   1公开  2密码
    Flowable<CreateRoomBean> CreateRoom(@FieldMap Map<String, String> map);

    //获取房间列表
    @GET("api/live/getRoomList")
    Flowable<RoomBean> getRoom();

}

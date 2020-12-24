package com.example.myhttp.view.home;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bottom_Bean;
import com.example.myhttp.model.bean.shop.AddCarBean;
import com.example.myhttp.model.callback.Callback;

import java.util.Map;

//接口契约类
public interface IHomeDetailInfo {

    interface View extends IBaseView {
        //定义一个被实现的View层接口方法
        void getHomeDetailInfoReturn(Home_DetailInfo_Bean result);
        void getHomeDetailInfoBottomReturn(Home_DetailInfo_Bottom_Bean result);
        void addGoodCarReturn(AddCarBean addCarBean);
    }

    interface Persenter extends IBasePersenter<View> {
        //定义一个V层调用的接口
        void getHomeDetailInfo(int id);
        void getHomeDetailInfoBottom(int id);
        //添加进购物车
        void addGoodCar(Map<String,String> map);
    }

    interface Model extends IBaseModel {
        //定义一个加载数据的接口方法   被P层
        void getHomeDetailInfo(int id,Callback callback);
        void getHomeDetailInfoBottom(int id,Callback callback);
        //添加进购物车
        void addGoodCar(Map<String,String> map,Callback callback);
    }

}

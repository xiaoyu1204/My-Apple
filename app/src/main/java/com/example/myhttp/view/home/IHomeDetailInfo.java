package com.example.myhttp.view.home;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.callback.Callback;

//接口契约类
public interface IHomeDetailInfo {

    interface View extends IBaseView {
        //定义一个被实现的View层接口方法
        void getHomeDetailInfoReturn(Home_DetailInfo_Bean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //定义一个V层调用的接口
        void getHomeDetailInfo(int id);
    }

    interface Model extends IBaseModel {
        //定义一个加载数据的接口方法   被P层
        void getHomeDetailInfo(int id,Callback callback);
    }

}

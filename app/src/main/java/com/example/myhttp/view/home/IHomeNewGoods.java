package com.example.myhttp.view.home;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.home.Home_NewGoods_Below_Bean;
import com.example.myhttp.model.bean.home.Home_NewGoods_Top_Bean;
import com.example.myhttp.model.callback.Callback;

//接口契约类
public interface IHomeNewGoods {

    interface View extends IBaseView {
        //定义一个被实现的View层接口方法
        void getHomeNewGoodsTopReturn(Home_NewGoods_Top_Bean result);
        void getHomeNewGoodsBelowReturn(Home_NewGoods_Below_Bean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //定义一个V层调用的接口
        void getHomeNewGoodsTop();
        void getHomeNewGoodsBelow(int isNew,int page,int size,String order,String sort,int categoryId);
    }

    interface Model extends IBaseModel {
        //定义一个加载数据的接口方法   被P层
        void getHomeNewGoodsTop(Callback callback);
        void getHomeNewGoodsBelow(int isNew,int page,int size,String order,String sort,int categoryId,Callback callback);
    }

}

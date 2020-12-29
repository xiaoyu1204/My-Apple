package com.example.myhttp.view.shop;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.shop.CarBean;
import com.example.myhttp.model.callback.Callback;

//接口契约类
public interface IShop {

    interface View extends IBaseView {
        //定义一个被实现的View层接口方法
        //购物车列表
        void getShopReturn(CarBean result);
        //添加购物车
        void ShopAddCarReturn(CarBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //定义一个V层调用的接口
        void getShop();
        void ShopAdd(int goodsId,String number,int productId);
    }

    interface Model extends IBaseModel {
        //定义一个加载数据的接口方法   被P层
        void getShop(Callback callback);
        void ShopAdd(int goodsId,String number,int productId,Callback callback);
    }

}

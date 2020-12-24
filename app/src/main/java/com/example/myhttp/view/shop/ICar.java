package com.example.myhttp.view.shop;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.shop.CarBean;
import com.example.myhttp.model.bean.shop.DeleteCarBean;
import com.example.myhttp.model.bean.shop.UpdateCarBean;
import com.example.myhttp.model.callback.Callback;

import java.util.Map;

public interface ICar {
    interface View extends IBaseView {
        void getCarListReturn(CarBean carBean);

        //更新 购物车
        void updateCarReturn(UpdateCarBean result);

        //删除购物车
        void deleteCarReturn(DeleteCarBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void getCarList();
        //更新购物车的数据
        void  updateCar(Map<String, String> map);

        //删除购物车列表
        void deleteCar(String pIds);
    }


    interface Model extends IBaseModel {
        void getCarList(Callback callback);

        void updateCar(Map<String, String> map, Callback callback);

        void deleteCar(String pIds, Callback callback);
    }

}

package com.example.myhttp.presenter.shop;

import android.util.Log;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.shop.CarBean;
import com.example.myhttp.model.bean.shop.DeleteCarBean;
import com.example.myhttp.model.bean.shop.UpdateCarBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.shop.CarModel;
import com.example.myhttp.view.shop.ICar;

import java.util.Map;

public class CarPresenter extends BasePresenter<ICar.View> implements ICar.Presenter {

    ICar.Model model;
    public CarPresenter(){
        model = new CarModel();
    }
    @Override
    public void getCarList() {
        model.getCarList(new Callback<CarBean>() {
            @Override
            public void success(CarBean data) {
                if(mView != null){
                    mView.getCarListReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    //更新购物车
    @Override
    public void updateCar(Map<String, String> map) {
        model.updateCar(map,new Callback<UpdateCarBean>() {
            @Override
            public void success(UpdateCarBean data) {
                if(mView != null){
                    mView.updateCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    // 删除购物车列表
    @Override
    public void deleteCar(String pIds) {
        model.deleteCar(pIds,new Callback<DeleteCarBean>() {
            @Override
            public void success(DeleteCarBean data) {
                if(mView != null){
                    Log.i("TAG","CarPresenter delete return:");
                    mView.deleteCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}

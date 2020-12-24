package com.example.myhttp.presenter.home;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bottom_Bean;
import com.example.myhttp.model.bean.shop.AddCarBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.home.HomeDetailInfoModel;
import com.example.myhttp.view.home.IHomeDetailInfo;
import com.example.myhttp.view.home.IHomeNewGoods;
import com.example.myhttp.view.home.IHomeTree;

import java.util.Map;

public class Home_DetailInfo_Presenter extends BasePresenter<IHomeDetailInfo.View> implements IHomeDetailInfo.Persenter {

    IHomeDetailInfo.View mView;
    IHomeDetailInfo.Model model;

    public Home_DetailInfo_Presenter(IHomeDetailInfo.View mView) {
        this.mView = mView;
        model = new HomeDetailInfoModel();
    }

    @Override
    public void getHomeDetailInfo(int id) {
        model.getHomeDetailInfo(id, new Callback() {
            @Override
            public void success(Object o) {
                if(mView != null){
                    mView.getHomeDetailInfoReturn((Home_DetailInfo_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    @Override
    public void getHomeDetailInfoBottom(int id) {
        model.getHomeDetailInfoBottom(id, new Callback() {
            @Override
            public void success(Object o) {
                if(mView != null){
                    mView.getHomeDetailInfoBottomReturn((Home_DetailInfo_Bottom_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    // 添加到购物车
    @Override
    public void addGoodCar(Map<String, String> map) {
        model.addGoodCar(map, new Callback<AddCarBean>() {
            @Override
            public void success(AddCarBean data) {
                if(mView != null){
                    mView.addGoodCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}

package com.example.myhttp.presenter.home;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.home.Home_NewGoods_Below_Bean;
import com.example.myhttp.model.bean.home.Home_NewGoods_Top_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.home.Home_NewGoods_Model;
import com.example.myhttp.view.home.IHomeNewGoods;

public class Home_NewGoods_Presenter extends BasePresenter<IHomeNewGoods.View> implements IHomeNewGoods.Persenter {

    IHomeNewGoods.View view;
    IHomeNewGoods.Model model;

    public Home_NewGoods_Presenter(IHomeNewGoods.View view) {
        this.view = view;
        model = new Home_NewGoods_Model();
    }

    @Override
    public void getHomeNewGoodsTop() {
        model.getHomeNewGoodsTop(new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getHomeNewGoodsTopReturn((Home_NewGoods_Top_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    @Override
    public void getHomeNewGoodsBelow(int isNew, int page, int size, String order, String sort, int categoryId) {
        model.getHomeNewGoodsBelow(isNew, page, size, order, sort, categoryId, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getHomeNewGoodsBelowReturn((Home_NewGoods_Below_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
}

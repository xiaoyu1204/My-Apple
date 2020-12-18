package com.example.myhttp.presenter.home;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Below_Bean;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Top_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.home.HomeBrandInfoTopModel;
import com.example.myhttp.view.home.IHomeBrandInfo;

public class Home_Brand_Info_Top_Presenter extends BasePresenter<IHomeBrandInfo.View> implements IHomeBrandInfo.Persenter {

    IHomeBrandInfo.View view;
    IHomeBrandInfo.Model model;

    public Home_Brand_Info_Top_Presenter(IHomeBrandInfo.View view) {
        this.view = view;
        model = new HomeBrandInfoTopModel();
    }

    @Override
    public void getHomeInfoTop(int id) {
        model.getHomeInfoTop(id, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getHomeInfoTopReturn((Home_Brand_Info_Top_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    @Override
    public void getHomeInfoBelow(int brandId, int page, int size) {
        model.getHomeInfoBelow(brandId, page, size, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getHomeInfoBelowReturn((Home_Brand_Info_Below_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

}

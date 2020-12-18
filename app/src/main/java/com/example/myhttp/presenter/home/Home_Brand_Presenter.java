package com.example.myhttp.presenter.home;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.home.Home_Brand_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.home.HomeBrandModel;
import com.example.myhttp.view.home.IHomeBrand;

public class Home_Brand_Presenter extends BasePresenter<IHomeBrand.View> implements IHomeBrand.Persenter{

    IHomeBrand.View view;
    IHomeBrand.Model model;

    public Home_Brand_Presenter(IHomeBrand.View view) {
        this.view = view;
        model = new HomeBrandModel();
    }

    @Override
    public void getHomeBrand(int page, int size) {
        model.getHomeBrand(page, size, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getHomeBrandReturn((Home_Brand_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

}

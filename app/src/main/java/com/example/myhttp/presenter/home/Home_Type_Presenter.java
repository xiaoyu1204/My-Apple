package com.example.myhttp.presenter.home;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.home.Home_Channel_Type_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.home.HomeTypeModel;
import com.example.myhttp.view.home.IHome;
import com.example.myhttp.view.home.IHomeType;

public class Home_Type_Presenter extends BasePresenter<IHomeType.View> implements IHomeType.Persenter{

    IHomeType.Model model;
    IHomeType.View mView;

    public Home_Type_Presenter(IHomeType.View mView) {
        this.mView = mView;
        model = new HomeTypeModel();
    }

    @Override
    public void getHomeType(String murl) {
        model.getHomeType(murl, new Callback() {
            @Override
            public void success(Object o) {
                if(mView != null){
                    mView.getHomeTypeReturn((Home_Channel_Type_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if(mView != null){
                    mView.tips(msg);
                }
            }
        });
    }

}

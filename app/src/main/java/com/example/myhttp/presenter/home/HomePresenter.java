package com.example.myhttp.presenter.home;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.checkbox.CheckBoxModel;
import com.example.myhttp.model.home.HomeModel;
import com.example.myhttp.view.checkbox.IChexkBox;
import com.example.myhttp.view.home.IHome;

public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Persenter  {

    IHome.Model model;
    IHome.View mView;

    public HomePresenter(IHome.View mView) {
        this.mView = mView;
        model = new HomeModel();
    }

    @Override
    public void getHome() {
        model.getHome(new Callback() {
            @Override
            public void success(Object o) {
                if(mView != null){
                    mView.getHomeReturn((HomeBean) o);
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

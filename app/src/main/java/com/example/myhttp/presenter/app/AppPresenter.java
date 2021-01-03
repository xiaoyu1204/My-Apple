package com.example.myhttp.presenter.app;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.app.AppBean;
import com.example.myhttp.model.bean.app.AppModel;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.view.app.IApp;

public class AppPresenter extends BasePresenter<IApp.View> implements IApp.Presenter {

    IApp.Model model;

    public AppPresenter(){
        model = new AppModel();
    }

    @Override
    public void getAppInfo() {
        model.getAppInfo(new Callback<AppBean>() {
            @Override
            public void success(AppBean data) {
                if(mView != null){
                    mView.getAppInfoReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}

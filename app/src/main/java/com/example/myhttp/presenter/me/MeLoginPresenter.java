package com.example.myhttp.presenter.me;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.me.MeLoginBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.me.MeLoginModel;
import com.example.myhttp.view.me.IMeLogin;
import com.example.myhttp.view.topic.ITopic;

public class MeLoginPresenter extends BasePresenter<IMeLogin.View> implements IMeLogin.Persenter {

    IMeLogin.View view;
    IMeLogin.Model model;

    public MeLoginPresenter(IMeLogin.View view) {
        this.view = view;
        model = new MeLoginModel();
    }

    @Override
    public void MeLogin(String username, String password) {
        model.MeLogin(username, password, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.MeLoginReturn((MeLoginBean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if(view != null){
                    view.tips(msg);
                }
            }
        });
    }

}

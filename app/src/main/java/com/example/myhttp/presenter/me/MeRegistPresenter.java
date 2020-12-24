package com.example.myhttp.presenter.me;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.me.MeRegisterBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.me.MeRegistModel;
import com.example.myhttp.view.me.IMeLogin;
import com.example.myhttp.view.me.IMeRegist;

public class MeRegistPresenter extends BasePresenter<IMeRegist.View> implements IMeRegist.Persenter {

    IMeRegist.View view;
    IMeRegist.Model model;

    public MeRegistPresenter(IMeRegist.View view) {
        this.view = view;
        model = new MeRegistModel();
    }

    @Override
    public void MeRegist(String username, String password) {
        model.MeRegist(username, password, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.MeRegistReturn((MeRegisterBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
}

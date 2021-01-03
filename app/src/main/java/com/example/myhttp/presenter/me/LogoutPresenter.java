package com.example.myhttp.presenter.me;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.me.LogoutBean;
import com.example.myhttp.model.bean.me.UserInfoBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.me.LogoutModel;
import com.example.myhttp.view.me.ILogout;
import com.example.myhttp.view.me.IMeLogin;

public class LogoutPresenter extends BasePresenter<ILogout.View> implements ILogout.Presenter {

    ILogout.View view;
    ILogout.Model model;

    public LogoutPresenter(ILogout.View view) {
        this.view = view;
        model = new LogoutModel();
    }

    @Override
    public void Logout() {
        model.Logout(new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.LogoutReturn((LogoutBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
}

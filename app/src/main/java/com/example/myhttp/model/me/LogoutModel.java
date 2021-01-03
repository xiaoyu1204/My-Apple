package com.example.myhttp.model.me;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.me.LogoutBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.me.ILogout;
import com.example.myhttp.view.me.IMeLogin;

public class LogoutModel extends BaseModel implements ILogout.Model {
    @Override
    public void Logout(Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().Logout()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<LogoutBean>(callback) {
            @Override
            public void onNext(LogoutBean logoutBean) {
                callback.success(logoutBean);
            }
        }));
    }
}

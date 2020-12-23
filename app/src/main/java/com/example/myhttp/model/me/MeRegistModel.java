package com.example.myhttp.model.me;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.me.MeRegisterBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.me.IMeLogin;
import com.example.myhttp.view.me.IMeRegist;

public class MeRegistModel extends BaseModel implements IMeRegist.Model {
    @Override
    public void MeRegist(String username, String password, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().MeRegist(username,password)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<MeRegisterBean>(callback) {
            @Override
            public void onNext(MeRegisterBean meRegisterBean) {
                callback.success(meRegisterBean);
            }
        }));
    }
}

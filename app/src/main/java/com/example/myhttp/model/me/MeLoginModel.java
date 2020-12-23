package com.example.myhttp.model.me;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.me.MeLoginBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.me.IMeLogin;
import com.example.myhttp.view.sort.ISortDataInfo;

public class MeLoginModel extends BaseModel implements IMeLogin.Model  {
    @Override
    public void MeLogin(String username, String password, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().MeLogin(username,password)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<MeLoginBean>(callback) {
            @Override
            public void onNext(MeLoginBean meLoginBean) {
                callback.success(meLoginBean);
            }
        }));
    }
}

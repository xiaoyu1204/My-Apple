package com.example.myhttp.model.bean.app;

import android.util.Log;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.app.IApp;


public class AppModel extends BaseModel implements IApp.Model {
    @Override
    public void getAppInfo(Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getAppInfo().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AppBean>(callback) {
                    @Override
                    public void onNext(AppBean appBean) {
                        Log.i("TAG","model onNext register");
                        callback.success(appBean);
                    }
                }));
    }
}

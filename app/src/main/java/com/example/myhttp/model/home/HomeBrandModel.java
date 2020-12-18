package com.example.myhttp.model.home;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.Home_Brand_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.home.IHomeBrand;

public class HomeBrandModel extends BaseModel implements IHomeBrand.Model  {
    @Override
    public void getHomeBrand(int page, int size, Callback callback) {
        addDisposable(
                HttpManager.getInstance().getApiShop().getHomeBrand(page,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<Home_Brand_Bean>(callback) {
                    @Override
                    public void onNext(Home_Brand_Bean home_brand_bean) {
                        callback.success(home_brand_bean);
                    }
                })
        );
    }
}

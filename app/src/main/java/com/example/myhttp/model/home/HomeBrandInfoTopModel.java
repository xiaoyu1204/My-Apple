package com.example.myhttp.model.home;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Below_Bean;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Top_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.home.IHomeBrandInfo;

public class HomeBrandInfoTopModel extends BaseModel implements IHomeBrandInfo.Model {
    @Override
    public void getHomeInfoTop(int id, Callback callback) {
        addDisposable(
                HttpManager.getInstance().getApiShop().getHomeBrandInfoTop(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<Home_Brand_Info_Top_Bean>(callback) {
                            @Override
                            public void onNext(Home_Brand_Info_Top_Bean home_brand_info_Top_bean) {
                                callback.success(home_brand_info_Top_bean);
                            }
                        })
        );
    }

    @Override
    public void getHomeInfoBelow(int brandId, int page, int size, Callback callback) {
        addDisposable(
                HttpManager.getInstance().getApiShop().getHomeBrandInfoBelow(brandId,page,size)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<Home_Brand_Info_Below_Bean>(callback) {
                            @Override
                            public void onNext(Home_Brand_Info_Below_Bean home_brand_info_below_bean) {
                                callback.success(home_brand_info_below_bean);
                            }
                        })
        );
    }

}

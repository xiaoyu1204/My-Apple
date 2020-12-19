package com.example.myhttp.model.home;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.home.IHome;
import com.example.myhttp.view.home.IHomeDetailInfo;

public class HomeDetailInfoModel extends BaseModel implements IHomeDetailInfo.Model {
    @Override
    public void getHomeDetailInfo(int id, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getDetailInfo(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<Home_DetailInfo_Bean>(callback) {
            @Override
            public void onNext(Home_DetailInfo_Bean home_detailInfo_bean) {
                callback.success(home_detailInfo_bean);
            }
        }));
    }
}

package com.example.myhttp.model.home;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bottom_Bean;
import com.example.myhttp.model.bean.shop.AddCarBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;

import com.example.myhttp.view.home.IHome;import com.example.myhttp.view.home.IHomeDetailInfo;

import java.util.Map;

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

    @Override
    public void getHomeDetailInfoBottom(int id, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getDetailInfoBottom(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<Home_DetailInfo_Bottom_Bean>(callback) {
            @Override
            public void onNext(Home_DetailInfo_Bottom_Bean home_detailInfo_bottom_bean) {
                callback.success(home_detailInfo_bottom_bean);
            }
        }));
    }

    // 添加进购物车
    @Override
    public void addGoodCar(Map<String, String> map, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().addCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        callback.success(addCarBean);
                    }
                }));
    }

}

package com.example.myhttp.model.home;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.Home_Channel_Type_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.home.IHomeType;

public class HomeTypeModel extends BaseModel implements IHomeType.Model {
    @Override
    public void getHomeType(String murl, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getChannelType(murl)
                .compose(RxUtils.rxScheduler()) //线程切换
                .subscribeWith(new CommonSubscriber<Home_Channel_Type_Bean>(callback){
                    @Override
                    public void onNext(Home_Channel_Type_Bean home_channel_type_bean) {
                        callback.success(home_channel_type_bean);
                    }
                })
        );
    }
}

package com.example.myhttp.model.home;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.checkbox.IChexkBox;
import com.example.myhttp.view.home.IHome;

import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel implements IHome.Model  {
    @Override
    public void getHome(Callback callback) {
        addDisposable(
                HttpManager.getInstance().getApiShop().getHome()
                        .compose(RxUtils.rxScheduler()) //线程切换
                        .subscribeWith(new CommonSubscriber<HomeBean>(callback){
                            @Override
                            public void onNext(HomeBean homeBean) {
                                callback.success(homeBean);
                            }
                        }));
    }

}

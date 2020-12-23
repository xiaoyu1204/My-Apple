package com.example.myhttp.model.shop;


import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.shop.ShopBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.shop.IShop;

public class ShopModel extends BaseModel implements IShop.Model {

    @Override
    public void getShop(Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getShop()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ShopBean>(callback) {
                    @Override
                    public void onNext(ShopBean shopBean) {
                        callback.success(shopBean);
                    }
                }));
    }
}

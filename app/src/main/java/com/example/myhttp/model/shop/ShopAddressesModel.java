package com.example.myhttp.model.shop;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.shop.AddressAddProvinceBean;
import com.example.myhttp.model.bean.shop.AddressBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.shop.ICar;
import com.example.myhttp.view.shop.IShopAddress;

public class ShopAddressesModel extends BaseModel implements IShopAddress.Model {
    @Override
    public void getAddressList(Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getAddress()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<AddressBean>(callback) {
            @Override
            public void onNext(AddressBean addressBean) {
                callback.success(addressBean);
            }
        }));
    }

    //省市地址
    @Override
    public void getAddressAddProvince(int parentId, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getAddressAddProvince(parentId)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<AddressAddProvinceBean>(callback) {
            @Override
            public void onNext(AddressAddProvinceBean addressAddProvinceBean) {
                callback.success(addressAddProvinceBean);
            }
        }));
    }

}

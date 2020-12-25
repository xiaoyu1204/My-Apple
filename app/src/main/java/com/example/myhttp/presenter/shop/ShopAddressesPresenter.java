package com.example.myhttp.presenter.shop;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.shop.AddressAddProvinceBean;
import com.example.myhttp.model.bean.shop.AddressBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.shop.ShopAddressesModel;
import com.example.myhttp.view.shop.ICar;
import com.example.myhttp.view.shop.IShopAddress;

public class ShopAddressesPresenter extends BasePresenter<IShopAddress.View> implements IShopAddress.Presenter {

    IShopAddress.View view;
    IShopAddress.Model model;

    public ShopAddressesPresenter(IShopAddress.View view) {
        this.view = view;
        model = new ShopAddressesModel();
    }

    @Override
    public void getAddressList() {
        model.getAddressList(new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getAddressReturn((AddressBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    //省市地址
    @Override
    public void getAddressAddProvince(int parentId) {
        model.getAddressAddProvince(parentId, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getAddressAddProvinceReturn((AddressAddProvinceBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

}

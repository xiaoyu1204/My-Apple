package com.example.myhttp.presenter.shop;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.shop.ShopBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.shop.ShopModel;
import com.example.myhttp.view.shop.IShop;

public class ShopPresenter extends BasePresenter<IShop.View> implements IShop.Persenter {

    IShop.View view;
    IShop.Model model;

    public ShopPresenter(IShop.View view) {
        this.view = view;
        model=new ShopModel();
    }

    @Override
    public void getShop() {
        model.getShop(new Callback() {
            @Override
            public void success(Object o) {
                if(view!=null){
                    view.getShopReturn((ShopBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    @Override
    public void ShopAdd(int goodsId, String number, int productId) {
        model.ShopAdd(goodsId, number, productId, new Callback() {
            @Override
            public void success(Object o) {
                if(view!=null){
                    view.ShopAddCarReturn((ShopBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
}

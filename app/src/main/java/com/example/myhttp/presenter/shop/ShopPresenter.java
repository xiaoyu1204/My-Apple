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
        if(view!=null){
           model.getShop(new Callback() {
               @Override
               public void fail(String msg) {
                   view.tips(msg);
               }

               @Override
               public void success(Object o) {
                   view.getShopReturn((ShopBean) o);
               }
           });
        }
    }
}

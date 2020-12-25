package com.example.myhttp.view.shop;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.shop.AddressAddProvinceBean;
import com.example.myhttp.model.bean.shop.AddressBean;
import com.example.myhttp.model.bean.shop.CarBean;
import com.example.myhttp.model.bean.shop.DeleteCarBean;
import com.example.myhttp.model.bean.shop.UpdateCarBean;
import com.example.myhttp.model.callback.Callback;

import java.util.Map;

public interface IShopAddress {

    interface View extends IBaseView {
        //地址列表
        void getAddressReturn(AddressBean result);
        //添加地址
        //省市区县数据
        void getAddressAddProvinceReturn(AddressAddProvinceBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        //地址列表
        void getAddressList();
        //添加地址
        //省市区县数据
        void getAddressAddProvince(int parentId);
    }

    interface Model extends IBaseModel {
        //地址列表
        void getAddressList(Callback callback);
        //添加地址
        //省市区县数据
        void getAddressAddProvince(int parentId,Callback callback);
    }

}

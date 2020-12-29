package com.example.myhttp.view.me;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.me.UserInfoBean;
import com.example.myhttp.model.callback.Callback;

import java.util.Map;

public interface IUser {
    interface View extends IBaseView {
        void updateUserInfoReturn(UserInfoBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void updateUserInfo(Map<String,String> map);
    }


    interface Model extends IBaseModel {
        void updateUserInfo(Map<String,String> map, Callback callback);
    }
}
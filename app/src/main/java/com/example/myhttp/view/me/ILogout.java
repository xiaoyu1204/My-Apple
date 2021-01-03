package com.example.myhttp.view.me;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.me.LogoutBean;
import com.example.myhttp.model.bean.me.UserInfoBean;
import com.example.myhttp.model.callback.Callback;

import java.util.Map;

public interface ILogout {

    interface View extends IBaseView {
        void LogoutReturn(LogoutBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void Logout();
    }


    interface Model extends IBaseModel {
        void Logout(Callback callback);
    }

}
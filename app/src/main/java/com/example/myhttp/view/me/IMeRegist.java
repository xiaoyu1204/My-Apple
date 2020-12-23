package com.example.myhttp.view.me;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.me.MeLoginBean;
import com.example.myhttp.model.bean.me.MeRegisterBean;
import com.example.myhttp.model.callback.Callback;

//接口契约类
public interface IMeRegist {

    interface View extends IBaseView {
        //定义一个被实现的View层接口方法
        void MeRegistReturn(MeRegisterBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //定义一个V层调用的接口
        void MeRegist(String username, String password);
    }

    interface Model extends IBaseModel {
        //定义一个加载数据的接口方法   被P层
        void MeRegist(String username, String password, Callback callback);
    }

}

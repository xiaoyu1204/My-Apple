package com.example.myhttp.view;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePresenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.checkbox.CheckBox_Bean;
import com.example.myhttp.model.callback.Callback;


//接口契约类
public interface IChexkBox {

    interface View extends IBaseView{
        //定义一个被实现的View层接口方法
        void getChexkBoxReturn(CheckBox_Bean checkBoxBean);
    }

    interface Persenter extends IBasePresenter<View>{
        //定义一个V层调用的接口
        void getChexkBox();
    }

    interface Model extends IBaseModel{
        //定义一个加载数据的接口方法   被P层
        void getChexkBox(Callback callback);
    }

}

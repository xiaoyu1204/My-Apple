package com.example.myhttp.view.sort;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.sort.SortBean;
import com.example.myhttp.model.bean.sort.SortDataBean;
import com.example.myhttp.model.bean.topic.TopicBean;
import com.example.myhttp.model.callback.Callback;

//接口契约类
public interface ISort {

    interface View extends IBaseView {
        //定义一个被实现的View层接口方法
        void getSortReturn(SortBean result);
        void getSortDataReturn(SortDataBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //定义一个V层调用的接口
        void getSort();
        void getSortData(int id);
    }

    interface Model extends IBaseModel {
        //定义一个加载数据的接口方法   被P层
        void getSort(Callback callback);
        void getSortData(int id,Callback callback);
    }

}

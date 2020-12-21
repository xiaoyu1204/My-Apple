package com.example.myhttp.presenter.sort;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.sort.Sort_Data_InfoBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.sort.SortDataInfoModel;
import com.example.myhttp.view.sort.ISort;
import com.example.myhttp.view.sort.ISortDataInfo;

public class SortDataInfoPresenter extends BasePresenter<ISortDataInfo.View> implements ISortDataInfo.Persenter {

    ISortDataInfo.View view;
    ISortDataInfo.Model model;

    public SortDataInfoPresenter(ISortDataInfo.View view) {
        this.view = view;
        model = new SortDataInfoModel();
    }

    @Override
    public void getSortDataInfo(int id) {
        model.getSortDataInfo(id, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getSortDataInfoReturn((Sort_Data_InfoBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
}

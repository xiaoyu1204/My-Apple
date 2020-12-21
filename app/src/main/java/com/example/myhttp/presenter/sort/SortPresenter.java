package com.example.myhttp.presenter.sort;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.sort.SortBean;
import com.example.myhttp.model.bean.sort.SortDataBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.sort.SortModel;
import com.example.myhttp.view.sort.ISort;
import com.example.myhttp.view.topic.ITopic;

public class SortPresenter extends BasePresenter<ISort.View> implements ISort.Persenter {

    ISort.View view;
    ISort.Model model;

    public SortPresenter(ISort.View view) {
        this.view = view;
        model = new SortModel();
    }

    @Override
    public void getSort() {
        model.getSort(new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getSortReturn((SortBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    @Override
    public void getSortData(int id) {
        model.getSortData(id, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getSortDataReturn((SortDataBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

}

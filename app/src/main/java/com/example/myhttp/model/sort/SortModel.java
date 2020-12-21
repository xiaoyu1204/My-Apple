package com.example.myhttp.model.sort;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.sort.SortBean;
import com.example.myhttp.model.bean.sort.SortDataBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.sort.ISort;
import com.example.myhttp.view.topic.ITopic;

public class SortModel extends BaseModel implements ISort.Model {
    @Override
    public void getSort(Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getHomeSort()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<SortBean>(callback) {
            @Override
            public void onNext(SortBean sortBean) {
                callback.success(sortBean);
            }
        }));
    }

    @Override
    public void getSortData(int id, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getSortData(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<SortDataBean>(callback) {
            @Override
            public void onNext(SortDataBean sortDataBean) {
                callback.success(sortDataBean);
            }
        }));
    }
}

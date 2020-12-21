package com.example.myhttp.model.sort;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.sort.Sort_Data_InfoBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.sort.ISort;
import com.example.myhttp.view.sort.ISortDataInfo;

public class SortDataInfoModel extends BaseModel implements ISortDataInfo.Model {
    @Override
    public void getSortDataInfo(int id, Callback callback) {
        addDisposable(
                HttpManager.getInstance().getApiShop().getSortDataInfo(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<Sort_Data_InfoBean>(callback) {
                    @Override
                    public void onNext(Sort_Data_InfoBean sort_data_infoBean) {
                        callback.success(sort_data_infoBean);
                    }
                })
        );
    }
}

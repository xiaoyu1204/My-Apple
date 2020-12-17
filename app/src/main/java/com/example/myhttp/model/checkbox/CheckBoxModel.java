package com.example.myhttp.model.checkbox;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.checkbox.CheckBox_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.checkbox.IChexkBox;

public class CheckBoxModel extends BaseModel implements IChexkBox.Model {
    @Override
    public void getChexkBox(Callback callback) {
        addDisposable(
                HttpManager.getInstance().getService().getCheckBox()
                    .compose(RxUtils.rxScheduler()) //线程切换
                    .subscribeWith(new CommonSubscriber<CheckBox_Bean>(callback){
                        @Override
                        public void onNext(CheckBox_Bean checkBox_bean) {
                            callback.success(checkBox_bean);
                        }
                    })
        );
    }

}

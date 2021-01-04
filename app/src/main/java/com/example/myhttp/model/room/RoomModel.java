package com.example.myhttp.model.room;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.room.RoomBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.room.IRoom;

public class RoomModel extends BaseModel implements IRoom.Model {
    @Override
    public void room(Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getRoom()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<RoomBean>(callback) {
            @Override
            public void onNext(RoomBean roomBean) {
                callback.success(roomBean);
            }
        }));
    }
}

package com.live.model;

import com.live.base.BaseModel;
import com.live.base.Callback;
import com.live.model.bean.RoomBean;
import com.live.net.CommonSubscriber;
import com.live.net.HttpManager;
import com.live.utils.RxUtils;
import com.live.view.IRoom;

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

package com.example.myhttp.view.room;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.room.RoomBean;
import com.example.myhttp.model.callback.Callback;

public interface IRoom {

    interface View extends IBaseView {
        void roomreturn(RoomBean result);
    }

    interface Presenter extends IBasePersenter<View> {
        void room();
    }


    interface Model extends IBaseModel {
        void room(Callback callback);
    }

}

package com.live.view;

import com.live.base.Callback;
import com.live.base.IBaseModel;
import com.live.base.IBasePersenter;
import com.live.base.IBaseView;
import com.live.model.bean.RoomBean;

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

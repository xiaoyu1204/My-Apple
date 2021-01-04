package com.live.presenter;

import com.live.base.BasePresenter;
import com.live.base.Callback;
import com.live.model.RoomModel;
import com.live.model.bean.RoomBean;
import com.live.view.IRoom;

public class RoomPresenter extends BasePresenter<IRoom.View> implements IRoom.Presenter {

    IRoom.View view;
    IRoom.Model model;

    public RoomPresenter(IRoom.View view) {
        this.view = view;
        model = new RoomModel();
    }

    @Override
    public void room() {
        model.room(new Callback() {
            @Override
            public void success(Object data) {
                if(view != null){
                    view.roomreturn((RoomBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}

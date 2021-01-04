package com.example.myhttp.presenter.room;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.room.RoomBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.room.RoomModel;
import com.example.myhttp.view.room.IRoom;

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
                if(mView != null){
                    mView.roomreturn((RoomBean) data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}

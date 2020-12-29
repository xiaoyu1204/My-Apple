package com.example.myhttp.presenter.me;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.me.UserInfoBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.me.UserModel;
import com.example.myhttp.view.me.IUser;

import java.util.Map;

public class UserPresenter extends BasePresenter<IUser.View> implements IUser.Presenter {

    IUser.Model model;

    public UserPresenter(){
        model = new UserModel();
    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
        model.updateUserInfo(map,new Callback<UserInfoBean>() {
            @Override
            public void success(UserInfoBean data) {
                if(mView != null){
                    mView.updateUserInfoReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}

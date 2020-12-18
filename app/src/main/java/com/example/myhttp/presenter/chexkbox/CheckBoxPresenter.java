package com.example.myhttp.presenter.chexkbox;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.checkbox.CheckBox_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.checkbox.CheckBoxModel;
import com.example.myhttp.view.checkbox.IChexkBox;

public class CheckBoxPresenter extends BasePresenter<IChexkBox.View> implements IChexkBox.Persenter {

    IChexkBox.Model model;
    IChexkBox.View view;

    public CheckBoxPresenter(IChexkBox.View view) {
        this.view = view;
        model = new CheckBoxModel();
    }

    @Override
    public void getChexkBox() {
        model.getChexkBox(new Callback<CheckBox_Bean>() {
            @Override
            public void success(CheckBox_Bean o) {
                if(view != null){
                    view.getChexkBoxReturn(o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }

}

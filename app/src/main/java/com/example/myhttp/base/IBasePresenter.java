package com.example.myhttp.base;

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();

}

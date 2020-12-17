package com.example.myhttp.base;

public interface IBasePersenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();

}

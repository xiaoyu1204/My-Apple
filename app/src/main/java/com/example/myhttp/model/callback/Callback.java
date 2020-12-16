package com.example.myhttp.model.callback;

public interface Callback<T> {

    void success(T t);

    void fail(String msg);

}

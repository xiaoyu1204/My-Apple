package com.example.myhttp.base;

public interface Callback<T> {

    void success(T data);

    void fail(String err);

}

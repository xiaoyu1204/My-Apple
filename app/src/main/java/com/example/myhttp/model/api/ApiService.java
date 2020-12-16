package com.example.myhttp.model.api;

import com.example.myhttp.model.bean.checkbox.CheckBox_Bean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "http://cdwan.cn:7000/";

    @GET("tongpao/list.json")
    Flowable<CheckBox_Bean> getCheckBox();
}

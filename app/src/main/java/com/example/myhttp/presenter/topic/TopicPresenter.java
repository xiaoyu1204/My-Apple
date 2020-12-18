package com.example.myhttp.presenter.topic;

import android.util.Log;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.topic.TopicBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.topic.TopicModel;
import com.example.myhttp.view.home.IHomeTree;
import com.example.myhttp.view.topic.ITopic;

public class TopicPresenter extends BasePresenter<ITopic.View> implements ITopic.Persenter  {

    ITopic.Model model;
    ITopic.View view;

    public TopicPresenter(ITopic.View view) {
        this.view = view;
        model = new TopicModel();
    }

    @Override
    public void getTopic(int page) {
        model.getTopic(page, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null) {
                    view.getTopicReturn((TopicBean) o);
                }
            }
            @Override
            public void fail(String msg) {

            }
        });
    }
}

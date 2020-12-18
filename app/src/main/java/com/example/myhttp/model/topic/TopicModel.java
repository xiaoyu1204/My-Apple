package com.example.myhttp.model.topic;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.Home_Channel_Tree_Bean;
import com.example.myhttp.model.bean.topic.TopicBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.home.IHomeTree;
import com.example.myhttp.view.topic.ITopic;

public class TopicModel extends BaseModel implements ITopic.Model {
    @Override
    public void getTopic(int page, Callback callback) {
        addDisposable(
                HttpManager.getInstance().getApiShop().getTopic(page)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicBean>(callback) {
                    @Override
                    public void onNext(TopicBean topicBean) {
                        callback.success(topicBean);
                    }
                })
        );
    }
}

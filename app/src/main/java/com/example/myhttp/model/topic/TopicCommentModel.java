package com.example.myhttp.model.topic;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.topic.TopicCommentBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.topic.ITopic;
import com.example.myhttp.view.topic.ITopicCommennt;

public class TopicCommentModel extends BaseModel implements ITopicCommennt.Model {
    @Override
    public void getTopicComment(int valueId, int typeId, int size, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getTopicComment(valueId,typeId,size)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TopicCommentBean>(callback) {
            @Override
            public void onNext(TopicCommentBean topicCommentBean) {
                callback.success(topicCommentBean);
            }
        }));
    }
}

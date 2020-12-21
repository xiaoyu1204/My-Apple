package com.example.myhttp.presenter.topic;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.topic.TopicCommentBean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.topic.TopicCommentModel;
import com.example.myhttp.view.topic.ITopic;
import com.example.myhttp.view.topic.ITopicCommennt;

public class TopicCommentPresenter extends BasePresenter<ITopicCommennt.View> implements ITopicCommennt.Persenter {

    ITopicCommennt.View view;
    ITopicCommennt.Model model;

    public TopicCommentPresenter(ITopicCommennt.View view) {
        this.view = view;
        model = new TopicCommentModel();
    }

    @Override
    public void getTopicComment(int valueId, int typeId, int size) {
        model.getTopicComment(valueId, typeId, size, new Callback() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.getTopicCommentReturn((TopicCommentBean) o);
                }
            }

            @Override
            public void fail(String msg) {

            }
        });
    }
}

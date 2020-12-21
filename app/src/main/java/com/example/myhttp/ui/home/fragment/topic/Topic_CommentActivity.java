package com.example.myhttp.ui.home.fragment.topic;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.topic.TopicCommentBean;
import com.example.myhttp.presenter.topic.TopicCommentPresenter;
import com.example.myhttp.view.topic.ITopicCommennt;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Topic_CommentActivity extends BaseActivity<TopicCommentPresenter> implements ITopicCommennt.View {

    ITopicCommennt.Persenter persenter;
    @BindView(R.id.topic_comment_rlv)
    RecyclerView topicCommentRlv;

    private int valueId = 314;
    private int typeId = 1;
    private int size = 5;

    @Override
    protected int getLayout() {
        return R.layout.activity_topic__comment;
    }

    @Override
    protected TopicCommentPresenter createPersenter() {
        return new TopicCommentPresenter(this);
    }

    @Override
    protected void initView() {



    }

    @Override
    protected void initData() {
        persenter = new TopicCommentPresenter(this);
        persenter.getTopicComment(valueId, typeId, size);
    }


    @Override
    public void getTopicCommentReturn(TopicCommentBean result) {
        List<TopicCommentBean.DataBeanX.DataBean> data = result.getData().getData();



    }   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
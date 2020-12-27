package com.example.myhttp.ui.home.fragment.topic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.topic.TopicCommentAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.topic.TopicCommentBean;
import com.example.myhttp.model.bean.topic.TopicRelatedBean;
import com.example.myhttp.model.bean.topic.TopicdeBean;
import com.example.myhttp.presenter.topic.TopicCommentPresenter;
import com.example.myhttp.view.topic.ITopicCommennt;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Topic_Comment_InfoActivity extends BaseActivity<TopicCommentPresenter> implements ITopicCommennt.View {


    @BindView(R.id.mRlv_topic_comment)
    RecyclerView mRlvTopicComment;
    private int id;
    private int typeId = 1;
    private int size = 5;

    @Override
    protected int getLayout() {
        return R.layout.activity_topic__comment__info;
    }

    @Override
    protected TopicCommentPresenter createPersenter() {
        return new TopicCommentPresenter(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    @Override
    protected void initData() {
        persenter = new TopicCommentPresenter(this);
        persenter.getTopicComment(id, typeId, size);
    }

    @Override
    public void getTopicCommentReturn(TopicCommentBean result) {
        List<TopicCommentBean.DataBeanX.DataBean> data = result.getData().getData();
        mRlvTopicComment.setLayoutManager(new LinearLayoutManager(this));
        mRlvTopicComment.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        TopicCommentAdapter topicCommentAdapter = new TopicCommentAdapter(this, data);
        mRlvTopicComment.setAdapter(topicCommentAdapter);
        topicCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void getTopicdeReturn(TopicdeBean result) {

    }

    @Override
    public void getTopicRelaReturn(TopicRelatedBean result) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
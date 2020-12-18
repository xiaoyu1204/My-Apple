package com.example.myhttp.ui.home.fragment.topic;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.topic.TopicAdapter;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.model.bean.topic.TopicBean;
import com.example.myhttp.presenter.topic.TopicPresenter;
import com.example.myhttp.view.topic.ITopic;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TopicFragment extends BaseFragment<ITopic.Persenter> implements ITopic.View {

    ITopic.Persenter persenter;
    @BindView(R.id.topic_rlv)
    RecyclerView topicRlv;
    @BindView(R.id.topic_btn_shang)
    Button topicBtnShang;
    @BindView(R.id.topic_btn_xia)
    Button topicBtnXia;
    private int page = 1;
    private List<TopicBean.DataBeanX.DataBean> dataBeans;
    private TopicAdapter topicAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected ITopic.Persenter createPrenter() {
        return new TopicPresenter(this);
    }

    @Override
    protected void initView() {
        topicRlv.setLayoutManager(new LinearLayoutManager(mContext));

        dataBeans = new ArrayList<>();
        topicAdapter = new TopicAdapter(mContext,dataBeans);

        topicRlv.setAdapter(topicAdapter);

    }

    @Override
    protected void initData() {
        persenter = new TopicPresenter(this);
        persenter.getTopic(page);
    }

    @Override
    public void getTopicReturn(TopicBean result) {
        List<TopicBean.DataBeanX.DataBean> data = result.getData().getData();
        dataBeans.addAll(data);
        topicAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.topic_btn_shang, R.id.topic_btn_xia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topic_btn_shang:
                break;
            case R.id.topic_btn_xia:
                break;
        }
    }

}

package com.example.myhttp.ui.home.fragment.topic;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.topic.TopicAdapter;
import com.example.myhttp.base.BaseAdapter;
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
    @BindView(R.id.fr_topic_sc)
    NestedScrollView frTopicSc;
    @BindView(R.id.lfr_topic_loading)
    TextView lfrTopicloading;
    @BindView(R.id.fr_topic_all)
    ImageView frTopicAll;

    private int ONE = 1;
    private int TWO = 2;
    private int page = ONE;

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
        topicAdapter = new TopicAdapter(mContext, dataBeans);

        topicRlv.setAdapter(topicAdapter);

    }

    @Override
    protected void initData() {
        persenter = new TopicPresenter(this);
        persenter.getTopic(page);
    }

    @Override
    public void getTopicReturn(TopicBean result) {
        //清空集合
        dataBeans.clear();
        List<TopicBean.DataBeanX.DataBean> data = result.getData().getData();
        dataBeans.addAll(data);
        topicAdapter.notifyDataSetChanged();

        //隐藏加载中...
        lfrTopicloading.setVisibility(View.GONE);
        frTopicAll.setVisibility(View.GONE);

        topicAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(mContext, Topic_CommentActivity.class);
                intent.putExtra("id",dataBeans.get(pos).getId());
                startActivity(intent);
            }
        });

    }

    @OnClick({R.id.topic_btn_shang, R.id.topic_btn_xia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topic_btn_shang:
                //更换page页
                page = ONE;

                //显示加载中...      白版
                lfrTopicloading.setVisibility(View.VISIBLE);
                frTopicAll.setVisibility(View.VISIBLE);
                //请求数据
                persenter.getTopic(page);
                // 返回顶部
                frTopicSc.fullScroll(ScrollView.FOCUS_UP);

                break;
            case R.id.topic_btn_xia:
                //更换page页
                page = TWO;

                //显示加载中...  白板
                lfrTopicloading.setVisibility(View.VISIBLE);
                frTopicAll.setVisibility(View.VISIBLE);
                //请求数据
                persenter.getTopic(page);
                // 返回顶部
                frTopicSc.fullScroll(ScrollView.FOCUS_UP);

                break;
        }
    }

}

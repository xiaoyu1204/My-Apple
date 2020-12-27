package com.example.myhttp.ui.home.fragment.topic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.topic.RlvDeToAdapter;
import com.example.myhttp.adapter.topic.TopicCommentAdapter;
import com.example.myhttp.adapter.topic.TopicRelatedAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.topic.TopicCommentBean;
import com.example.myhttp.model.bean.topic.TopicRelatedBean;
import com.example.myhttp.model.bean.topic.TopicdeBean;
import com.example.myhttp.presenter.topic.TopicCommentPresenter;
import com.example.myhttp.view.topic.ITopicCommennt;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Topic_CommentActivity extends BaseActivity<TopicCommentPresenter> implements ITopicCommennt.View {

    ITopicCommennt.Persenter persenter;
    @BindView(R.id.topic_rlv_img)
    RecyclerView topic_rlv_img;
    @BindView(R.id.topic_comment_txt)
    TextView topicCommentTxt;
    @BindView(R.id.topic_comment_img)
    ImageView topicCommentImg;
    @BindView(R.id.topic_comment_view)
    View topicCommentView;
    @BindView(R.id.topic_comment_rlv)
    RecyclerView topicCommentRlv;
    @BindView(R.id.topic_jingxuan_rlv)
    RecyclerView topicJingxuanRlv;
    @BindView(R.id.topic_comment_btn_gengduo)
    Button Gengduo;

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
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        persenter.getTopicde(id);
        persenter.getTopicRela(id);
        persenter.getTopicComment(id, typeId, size);
    }

    @Override
    public void getTopicCommentReturn(TopicCommentBean result) {

        List<TopicCommentBean.DataBeanX.DataBean> data = result.getData().getData();

        if(data.size() >=5 ){
            Gengduo.setVisibility(View.VISIBLE);
        }else{
            Gengduo.setVisibility(View.GONE);
        }

        topicCommentRlv.setLayoutManager(new LinearLayoutManager(this));
        topicCommentRlv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        TopicCommentAdapter topicCommentAdapter = new TopicCommentAdapter(this, data);
        topicCommentRlv.setAdapter(topicCommentAdapter);
        topicCommentAdapter.notifyDataSetChanged();

    }

    @Override
    public void getTopicdeReturn(TopicdeBean result) {
        TopicdeBean.DataBean data = result.getData();
        initgetImage(data.getContent());
    }

    private void initgetImage(String content) {
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            //判断图片的格式
            if (end > 0) {
                String url = word.substring(start, end);
                if (url != null) {
                    url = "http:" + url + ".jpg";
                    list.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    list.add(url);
                } else {
                    return;
                }
            }
        }
        topic_rlv_img.setLayoutManager(new LinearLayoutManager(this));
        RlvDeToAdapter rlvDeToAdapter = new RlvDeToAdapter(this, list);
        topic_rlv_img.setAdapter(rlvDeToAdapter);
        rlvDeToAdapter.notifyDataSetChanged();
    }

    @Override
    public void getTopicRelaReturn(TopicRelatedBean topicRelatedBean) {

        List<TopicRelatedBean.DataBean> data = topicRelatedBean.getData();
        Log.e("TAG", "getTopicRelaReturn: "+data.size() );

        topicJingxuanRlv.setLayoutManager(new LinearLayoutManager(this));
        TopicRelatedAdapter relatedAdapter = new TopicRelatedAdapter(this, data);
        topicJingxuanRlv.setAdapter(relatedAdapter);
        relatedAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.topic_comment_img, R.id.topic_comment_btn_gengduo})
    public void onClick(View view) {
        switch (view.getId()) {
            //点击编辑的图片
            case R.id.topic_comment_img:
                break;
            //查看更多
            case R.id.topic_comment_btn_gengduo:
                break;
        }
    }

    @Override
    public void tips(String tip) {//错误时的view方法
        Log.e("TAG", "getTopicRelaReturntips: "+tip );
    }

}
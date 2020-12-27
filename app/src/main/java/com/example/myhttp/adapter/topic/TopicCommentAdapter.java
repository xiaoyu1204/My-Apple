package com.example.myhttp.adapter.topic;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.topic.TopicCommentBean;
import com.example.myhttp.model.bean.topic.TopicRelatedBean;
import com.example.myhttp.utils.TxtUtils;

import java.util.List;

public class TopicCommentAdapter extends BaseAdapter {

    public TopicCommentAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_comment_rlv_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        TopicCommentBean.DataBeanX.DataBean bean = (TopicCommentBean.DataBeanX.DataBean) data;

        TextView topic_comment_head_name = (TextView) vh.getViewById(R.id.topic_comment_head_name);
        TextView topic_comment_head_date = (TextView) vh.getViewById(R.id.topic_comment_head_date);
        TextView topiv_comment_head_desc = (TextView) vh.getViewById(R.id.topiv_comment_head_desc);

        TxtUtils.setTextView(topic_comment_head_name,bean.getUser_info().getUsername());
        TxtUtils.setTextView(topic_comment_head_date,bean.getAdd_time());
        TxtUtils.setTextView(topiv_comment_head_desc,bean.getContent());


    }
}

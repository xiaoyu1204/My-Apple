package com.example.myhttp.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class RecyTopicAdapter extends BaseAdapter {

    Context context;
    public RecyTopicAdapter(Context context, List Data) {
        super(context, Data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        HomeBean.DataBean.TopicListBean topicListBean = (HomeBean.DataBean.TopicListBean)data;

        ImageView topic_item_iv = (ImageView) vh.getViewById(R.id.topic_item_iv);
        TextView topic_item_tv_name = (TextView) vh.getViewById(R.id.topic_item_tv_name);
        TextView topic_item_price = (TextView) vh.getViewById(R.id.topic_item_price);
        TextView topic_item_brif = (TextView) vh.getViewById(R.id.topic_item_brif);

        TxUtils.setTextView(topic_item_tv_name,topicListBean.getTitle());
        TxUtils.setTextView(topic_item_price, topicListBean.getPrice_info()+"元起");
        TxUtils.setTextView(topic_item_brif, topicListBean.getSubtitle());
        Glide.with(context).load(topicListBean.getItem_pic_url()).into(topic_item_iv);

    }

}

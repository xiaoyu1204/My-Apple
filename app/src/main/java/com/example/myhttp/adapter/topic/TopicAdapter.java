package com.example.myhttp.adapter.topic;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.topic.TopicBean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class TopicAdapter extends BaseAdapter {

    Context context;
    public TopicAdapter(Context context, List Data) {
        super(context, Data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.fragment_topic_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        TopicBean.DataBeanX.DataBean dataBean = (TopicBean.DataBeanX.DataBean) data;

        ImageView topic_rlv_item_iv = (ImageView) vh.getViewById(R.id.topic_rlv_item_iv);
        TextView topic_rlv_item_tv_name = (TextView) vh.getViewById(R.id.topic_rlv_item_tv_name);
        TextView topic_rlv_item_brif = (TextView) vh.getViewById(R.id.topic_rlv_item_brif);
        TextView topic_rlv_item_price = (TextView) vh.getViewById(R.id.topic_rlv_item_price);

        TxUtils.setTextView(topic_rlv_item_tv_name, dataBean.getTitle());
        TxUtils.setTextView(topic_rlv_item_brif, dataBean.getSubtitle());
        TxUtils.setTextView(topic_rlv_item_price, dataBean.getPrice_info()+"元起");
        Glide.with(context).load(dataBean.getScene_pic_url()).into(topic_rlv_item_iv);

    }

}

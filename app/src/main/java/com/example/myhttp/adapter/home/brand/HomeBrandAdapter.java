package com.example.myhttp.adapter.home.brand;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_Brand_Bean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class HomeBrandAdapter extends BaseAdapter {

    public HomeBrandAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.fragment_topic_rlv_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        Home_Brand_Bean.DataBeanX.DataBean dataBean = (Home_Brand_Bean.DataBeanX.DataBean) data;

        ImageView fr_topic_rlv_img = (ImageView) vh.getViewById(R.id.fr_topic_rlv_img);
        TextView fr_topic_rlv_tv_name = (TextView) vh.getViewById(R.id.fr_topic_rlv_tv_name);
        TextView fr_topic_rlv_tv_price = (TextView) vh.getViewById(R.id.fr_topic_rlv_tv_price);

        TxUtils.setTextView(fr_topic_rlv_tv_name,dataBean.getName());
        TxUtils.setTextView(fr_topic_rlv_tv_price, dataBean.getFloor_price()+"元起");
        Glide.with(context).load(dataBean.getApp_list_pic_url()).into(fr_topic_rlv_img);

    }

}

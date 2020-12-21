package com.example.myhttp.adapter.home.newgoods;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class RecyNewgoodAdapter extends BaseAdapter {

    Context context;
    public RecyNewgoodAdapter(Context context, List Data) {
        super(context, Data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.goods_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        HomeBean.DataBean.NewGoodsListBean newGoodsListBean = (HomeBean.DataBean.NewGoodsListBean) data;

        ImageView goods_item_iv = (ImageView) vh.getViewById(R.id.goods_item_iv);
        TextView goods_item_tv_name = (TextView) vh.getViewById(R.id.goods_item_tv_name);
        TextView goods_item_tv_price = (TextView) vh.getViewById(R.id.goods_item_tv_price);

        TxUtils.setTextView(goods_item_tv_name, newGoodsListBean.getName());
        TxUtils.setTextView(goods_item_tv_price, newGoodsListBean.getRetail_price()+"元起");
        Glide.with(context).load(newGoodsListBean.getList_pic_url()).into(goods_item_iv);

    }

}

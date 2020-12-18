package com.example.myhttp.adapter.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class RecyHotgoodAdapter extends BaseAdapter {

    Context context;
    public RecyHotgoodAdapter(Context context, List Data) {
        super(context, Data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.hotgoods_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        HomeBean.DataBean.HotGoodsListBean hotGoodsListBean = (HomeBean.DataBean.HotGoodsListBean)data;

        ImageView hotgoods_item_iv = (ImageView) vh.getViewById(R.id.hotgoods_item_iv);
        TextView hotgoods_item_tv_name = (TextView) vh.getViewById(R.id.hotgoods_item_tv_name);
        TextView hotgoods_item_tv_brif = (TextView) vh.getViewById(R.id.hotgoods_item_tv_brif);
        TextView hotgoods_item_tv_price = (TextView) vh.getViewById(R.id.hotgoods_item_tv_price);

        TxUtils.setTextView(hotgoods_item_tv_name,hotGoodsListBean.getName());
        TxUtils.setTextView(hotgoods_item_tv_brif, hotGoodsListBean.getGoods_brief());
        TxUtils.setTextView(hotgoods_item_tv_price, "￥"+hotGoodsListBean.getRetail_price());
        Glide.with(context).load(hotGoodsListBean.getList_pic_url()).into(hotgoods_item_iv);

    }

}

package com.example.myhttp.adapter.home.brand;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.HomeBean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class RecyBrandAdapter extends BaseAdapter {

    Context context;
    public RecyBrandAdapter(Context context, List Data) {
        super(context, Data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.pinpai_item_layout;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        HomeBean.DataBean.BrandListBean brandListBean = (HomeBean.DataBean.BrandListBean) data;

        ImageView pinpai_item_iv = (ImageView) vh.getViewById(R.id.pinpai_item_iv);
        TextView pinpai_item_tv_name = (TextView) vh.getViewById(R.id.pinpai_item_tv_name);
        TextView pinpai_item_tv_price = (TextView) vh.getViewById(R.id.pinpai_item_tv_price);

        TxUtils.setTextView(pinpai_item_tv_name, brandListBean.getName());
        TxUtils.setTextView(pinpai_item_tv_price, brandListBean.getFloor_price()+"元起");
        Glide.with(context).load(brandListBean.getNew_pic_url()).into(pinpai_item_iv);

    }
}

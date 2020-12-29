package com.example.myhttp.adapter.shop;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;;

import java.util.List;

public class SendingAdapter extends BaseAdapter {
    public SendingAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.rlv_ping_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String path = (String) data;
        ImageView img_pic = (ImageView) vh.getViewById(R.id.img_pic);
        Glide.with(context).load(path).into(img_pic);
    }
}

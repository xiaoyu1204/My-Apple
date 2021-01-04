package com.example.myhttp.adapter.room;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.room.RoomBean;
import com.example.myhttp.utils.TxtUtils;

import java.util.List;

public class RoomAdapter extends BaseAdapter {

    public RoomAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.room_rlv_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        RoomBean.DataBean bean = (RoomBean.DataBean) data;

        TextView room_rlv_name = (TextView) vh.getViewById(R.id.room_rlv_name);
        ImageView room_rlv_img = (ImageView) vh.getViewById(R.id.room_rlv_img);

        TxtUtils.setTextView(room_rlv_name,bean.getName());

    }

}

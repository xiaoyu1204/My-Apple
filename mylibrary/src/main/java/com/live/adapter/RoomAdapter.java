package com.live.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.live.R;
import com.live.base.BaseAdapter;
import com.live.model.bean.RoomBean;

import java.util.List;

public class RoomAdapter extends BaseAdapter {

    public RoomAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_room_rlv_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        RoomBean.DataBean bean = (RoomBean.DataBean) data;

        TextView room_rlv_name = (TextView) vh.getViewById(R.id.room_rlv_name);

        room_rlv_name.setText(bean.getName());

    }

}

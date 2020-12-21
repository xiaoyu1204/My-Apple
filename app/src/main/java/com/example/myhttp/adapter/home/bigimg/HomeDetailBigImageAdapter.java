package com.example.myhttp.adapter.home.bigimg;

import android.content.Context;
import android.widget.ImageView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.utils.ImageLoaderUtils;

import java.util.List;

public class HomeDetailBigImageAdapter extends BaseAdapter {

    public HomeDetailBigImageAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_home_detail_bigimage;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        String bean = (String) data;

        ImageView img= (ImageView) vh.getViewById(R.id.home_detail_bigimage_img);
        ImageLoaderUtils.loadImage(bean,img);

    }
}

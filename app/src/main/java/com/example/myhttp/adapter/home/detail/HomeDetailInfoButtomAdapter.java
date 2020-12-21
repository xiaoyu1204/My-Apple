package com.example.myhttp.adapter.home.detail;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bottom_Bean;
import com.example.myhttp.utils.ImageLoaderUtils;
import com.example.myhttp.utils.TxtUtils;

import java.util.List;

public class HomeDetailInfoButtomAdapter extends BaseAdapter {

    public HomeDetailInfoButtomAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_home_detail_info_bottom_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        Home_DetailInfo_Bottom_Bean.DataBean.GoodsListBean bean= (Home_DetailInfo_Bottom_Bean.DataBean.GoodsListBean) data;

        ImageView image = (ImageView) vh.getViewById(R.id.iv_category_img);
        TextView name = (TextView) vh.getViewById(R.id.tv_category_name);
        TextView floor_price = (TextView) vh.getViewById(R.id.tv_category_price);

        ImageLoaderUtils.loadImage(bean.getList_pic_url(),image);
        TxtUtils.setTextView(name,bean.getName());
        floor_price.setText("￥"+bean.getRetail_price());
    }
}

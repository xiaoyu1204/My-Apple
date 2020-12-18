package com.example.myhttp.adapter.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_Brand_Info_Below_Bean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class HomeBrandInfoBelowAdapter extends BaseAdapter {

    public HomeBrandInfoBelowAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_home_brand_info_below_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        Home_Brand_Info_Below_Bean.DataBeanX.DataBean dataBean = (Home_Brand_Info_Below_Bean.DataBeanX.DataBean) data;

        ImageView iv_brand_name_detail_list_img = (ImageView) vh.getViewById(R.id.iv_brand_name_detail_list_img);
        TextView tv_brand_name_detail_list_name = (TextView) vh.getViewById(R.id.tv_brand_name_detail_list_name);
        TextView tv_brand_name_detail_list_price = (TextView) vh.getViewById(R.id.tv_brand_name_detail_list_price);

        TxUtils.setTextView(tv_brand_name_detail_list_name, dataBean.getName());
        TxUtils.setTextView(tv_brand_name_detail_list_price, dataBean.getRetail_price()+"元起");
        Glide.with(context).load(dataBean.getList_pic_url()).into(iv_brand_name_detail_list_img);

    }

}

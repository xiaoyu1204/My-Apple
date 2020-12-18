package com.example.myhttp.adapter.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_Channel_Tree_Bean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class HomeTreeAdapter extends BaseAdapter {

    Context context;
    public HomeTreeAdapter(Context context, List Data) {
        super(context, Data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_hometree_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        Home_Channel_Tree_Bean.DataBeanX.DataBean dataBean = (Home_Channel_Tree_Bean.DataBeanX.DataBean) data;

        ImageView iv_home_tree_img = (ImageView) vh.getViewById(R.id.iv_home_tree_img);
        TextView tv_home_tree_name = (TextView) vh.getViewById(R.id.tv_home_tree_name);
        TextView tv_home_tree_price = (TextView) vh.getViewById(R.id.tv_home_tree_price);

        TxUtils.setTextView(tv_home_tree_name, dataBean.getName());
        TxUtils.setTextView(tv_home_tree_price, dataBean.getRetail_price()+"元起");
        Glide.with(context).load(dataBean.getList_pic_url()).into(iv_home_tree_img);

    }

}

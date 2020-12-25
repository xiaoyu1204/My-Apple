package com.example.myhttp.adapter.sort;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.sort.SortDataBean;
import com.example.myhttp.utils.TxUtils;

import java.util.List;

public class SortDataAdapter extends BaseAdapter {

    Context context;
    public SortDataAdapter(Context context, List Data) {
        super(context, Data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_sort_data_rlv_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean subCategoryListBean = (SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;

        ImageView sort_data_rlv_img = (ImageView) vh.getViewById(R.id.sort_data_rlv_img);
        TextView sort_data_rlv_name = (TextView) vh.getViewById(R.id.sort_data_rlv_name);

        TxUtils.setTextView(sort_data_rlv_name, subCategoryListBean.getName());
        Glide.with(context).load(subCategoryListBean.getWap_banner_url()).into(sort_data_rlv_img);

    }

}

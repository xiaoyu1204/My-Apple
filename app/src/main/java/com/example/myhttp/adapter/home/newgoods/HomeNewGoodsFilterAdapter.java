package com.example.myhttp.adapter.home.newgoods;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_NewGoods_Below_Bean;

import java.util.List;

public class HomeNewGoodsFilterAdapter extends BaseAdapter {

    public HomeNewGoodsFilterAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.home_newgoods_filter_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        TextView home_newgoods_fiter_item_tv_type = (TextView) vh.getViewById(R.id.home_newgoods_fiter_item_tv_type);
        Home_NewGoods_Below_Bean.DataBeanX.FilterCategoryBean filterCategoryBean= (Home_NewGoods_Below_Bean.DataBeanX.FilterCategoryBean) data;
        home_newgoods_fiter_item_tv_type.setText(filterCategoryBean.getName());

    }
}

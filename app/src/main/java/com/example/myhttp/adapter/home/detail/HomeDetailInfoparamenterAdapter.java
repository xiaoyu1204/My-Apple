package com.example.myhttp.adapter.home.detail;

import android.content.Context;
import android.widget.TextView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.utils.TxtUtils;

import java.util.List;

public class HomeDetailInfoparamenterAdapter extends BaseAdapter {

    public HomeDetailInfoparamenterAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_home_info_parameter_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        Home_DetailInfo_Bean.DataBeanX.AttributeBean bean = (Home_DetailInfo_Bean.DataBeanX.AttributeBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_parameter_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_parameter_value);

        TxtUtils.setTextView(key,bean.getName());
        TxtUtils.setTextView(value,bean.getValue());
    }
}

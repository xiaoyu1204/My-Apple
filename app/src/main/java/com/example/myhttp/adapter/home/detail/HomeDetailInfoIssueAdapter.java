package com.example.myhttp.adapter.home.detail;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bean;
import com.example.myhttp.model.bean.home.Home_DetailInfo_Bottom_Bean;
import com.example.myhttp.utils.ImageLoaderUtils;
import com.example.myhttp.utils.TxtUtils;

import java.util.List;

public class HomeDetailInfoIssueAdapter extends BaseAdapter {

    public HomeDetailInfoIssueAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_home_detail_info_issue_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        Home_DetailInfo_Bean.DataBeanX.IssueBean bean = (Home_DetailInfo_Bean.DataBeanX.IssueBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_issue_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_issue_value);

        TxtUtils.setTextView(key,bean.getQuestion());
        TxtUtils.setTextView(value,bean.getAnswer());
    }
}

package com.example.myhttp.adapter.shop;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.model.bean.shop.ShopAddressBean_String;
import com.example.myhttp.utils.ImageLoaderUtils;

import java.util.List;
import java.util.jar.Attributes;

public class ShopAddressesAdapter extends BaseAdapter {

    public ShopAddressesAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.shop_address_rlv_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        ShopAddressBean_String bean = (ShopAddressBean_String) data;

        TextView name = (TextView) vh.getViewById(R.id.tv_address_form_name);
        TextView phone = (TextView) vh.getViewById(R.id.tv_address_form_phone);
        TextView address = (TextView) vh.getViewById(R.id.tv_address_form_address);

        name.setText(bean.getAddName());
        phone.setText(bean.getAddPhone());
        address.setText(bean.getAddShengQuXian()+bean.getAddXiangxi());

    }

}

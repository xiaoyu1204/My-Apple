package com.example.myhttp.ui.home.fragment.me.shoucang;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.utils.ImageLoaderUtils;

import java.util.List;

public class FavoritesAdapter extends BaseAdapter<Favorites> {

    public FavoritesAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_favorites_item;
    }

    @Override
    protected void bindData(Favorites data, VH vh) {
        TextView tvfavoritesname = (TextView) vh.getViewById(R.id.tv_favorites_name);
        TextView tvfavoritestitle = (TextView) vh.getViewById(R.id.tv_favorites_title);
        TextView tvfavoritesprice = (TextView) vh.getViewById(R.id.tv_favorites_price);
        ImageView ivfavoritespic = (ImageView) vh.getViewById(R.id.iv_favorites_pic);

        tvfavoritesname.setText(data.getName());
        tvfavoritesprice.setText("￥"+data.getPrice());
        tvfavoritestitle.setText(data.getTitle());
        ImageLoaderUtils.loadImage(data.getPic(),ivfavoritespic);
    }

}

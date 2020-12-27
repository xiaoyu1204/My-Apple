package com.example.myhttp.ui.home.fragment.me.shoucang;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.base.IBasePersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

//TODO 添加数据库
public class FavoritesActivity extends BaseActivity {

    @BindView(R.id.recycler_favorites)
    RecyclerView mRlv;

    private List<Favorites> list;
    private FavoritesAdapter favoritesAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_favorites;
    }

    @Override
    protected IBasePersenter createPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        favoritesAdapter = new FavoritesAdapter(this,list);
        mRlv.setAdapter(favoritesAdapter);

        //查询保存到数据库的值
        RealmResults<Favorites> all = Realms.getRealm(FavoritesActivity.this).where(Favorites.class).findAll();
        //list.clear();
        list.addAll(all);
        favoritesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

}
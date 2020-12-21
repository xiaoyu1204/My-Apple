package com.example.myhttp.ui.home.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.home.HomeTreeAdapter;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.model.bean.home.Home_Channel_Tree_Bean;
import com.example.myhttp.presenter.home.Home_Tree_Presenter;
import com.example.myhttp.ui.home.Home_DetailInfo_Activity;
import com.example.myhttp.utils.ItemDecoration;
import com.example.myhttp.view.home.IHomeTree;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeTreeFragment extends BaseFragment<Home_Tree_Presenter> implements IHomeTree.View {

    IHomeTree.Persenter persenter;
    @BindView(R.id.home_tree_name)
    TextView homeTreeName;
    @BindView(R.id.home_tree_front_name)
    TextView homeTreeFrontName;
    @BindView(R.id.home_tree_rlv)
    RecyclerView homeTreeRlv;
    private int categoryId;
    private List<Home_Channel_Tree_Bean.DataBeanX.DataBean> dataBeans;
    private HomeTreeAdapter homeTreeAdapter;
    private String name;
    private String front_name;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_tree;
    }

    @Override
    protected Home_Tree_Presenter createPrenter() {
        return new Home_Tree_Presenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryId = getArguments().getInt("id");
        name = getArguments().getString("name");
        front_name = getArguments().getString("front_name");
    }

    @Override
    protected void initView() {

        homeTreeRlv.setLayoutManager(new GridLayoutManager(mContext,2));
        homeTreeRlv.addItemDecoration(new ItemDecoration(getActivity()));

        dataBeans = new ArrayList<>();

        homeTreeName.setText(name);
        homeTreeFrontName.setText(front_name);

        homeTreeAdapter = new HomeTreeAdapter(mContext,dataBeans);
        homeTreeRlv.setAdapter(homeTreeAdapter);

    }

    @Override
    protected void initData() {
        persenter = new Home_Tree_Presenter(this);
        persenter.getHomeTree(categoryId);
    }

    @Override
    public void getHomeTreeReturn(Home_Channel_Tree_Bean result) {

        List<Home_Channel_Tree_Bean.DataBeanX.DataBean> data = result.getData().getData();

        dataBeans.addAll(data);
        //刷新适配器
        homeTreeAdapter.notifyDataSetChanged();

    }

}

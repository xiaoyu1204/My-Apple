package com.example.myhttp.ui.home.fragment.sort;


import com.example.myhttp.R;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.model.bean.topic.TopicBean;
import com.example.myhttp.view.sort.ISort;
import com.example.myhttp.view.topic.ITopic;

public class SortFragment extends BaseFragment<ISort.Persenter> implements ISort.View {

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected ISort.Persenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void getSortReturn(TopicBean result) {

    }
}

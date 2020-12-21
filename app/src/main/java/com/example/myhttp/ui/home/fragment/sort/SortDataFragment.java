package com.example.myhttp.ui.home.fragment.sort;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myhttp.R;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.model.bean.sort.SortBean;
import com.example.myhttp.model.bean.sort.SortDataBean;
import com.example.myhttp.presenter.sort.SortPresenter;
import com.example.myhttp.sort.SortDataAdapter;
import com.example.myhttp.utils.TxUtils;
import com.example.myhttp.view.sort.ISort;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SortDataFragment extends BaseFragment<SortPresenter> implements ISort.View {

    ISort.Persenter presenter;
    @BindView(R.id.sort_data_head_img)
    ImageView sortDataHeadImg;
    @BindView(R.id.sort_data_head_desc)
    TextView sortDataHeadDesc;
    @BindView(R.id.sort_data_title)
    TextView sortDataTitle;
    @BindView(R.id.sort_data_mRlv)
    RecyclerView sortDataMRlv;
    @BindView(R.id.sort_data_info_nsl)
    NestedScrollView sortDataInfoNsl;
    private int id;
    private List<SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryListBeans;
    private SortDataAdapter sortDataAdapter;
    private List<SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort_data;
    }

    @Override
    protected SortPresenter createPrenter() {
        return new SortPresenter(this);
    }

    @Override
    protected void initView() {
        id = getArguments().getInt("id");

        sortDataMRlv.setLayoutManager(new GridLayoutManager(mContext, 3));

        subCategoryListBeans = new ArrayList<>();

        sortDataAdapter = new SortDataAdapter(mContext, subCategoryListBeans);
        sortDataMRlv.setAdapter(sortDataAdapter);

        sortDataAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(mContext, Sort_Data_InfoActivity.class);
                intent.putExtra("id", subCategoryListBeans.get(pos).getId());
                startActivity(intent);
            }
        });

        // 返回顶部
        sortDataInfoNsl.fullScroll(ScrollView.FOCUS_UP);

    }

    @Override
    protected void initData() {
        presenter = new SortPresenter(this);
        presenter.getSortData(id);
    }

    @Override
    public void getSortReturn(SortBean result) {

    }

    @Override
    public void getSortDataReturn(SortDataBean result) {

        //本布局内容
        SortDataBean.DataBean.CurrentCategoryBean currentCategory = result.getData().getCurrentCategory();
        Glide.with(mContext).load(currentCategory.getWap_banner_url()).into(sortDataHeadImg);
        TxUtils.setTextView(sortDataTitle, "————" + currentCategory.getName() + "分类————");
        TxUtils.setTextView(sortDataHeadDesc, currentCategory.getFront_name());

        //rlv内容
        subCategoryList = result.getData().getCurrentCategory().getSubCategoryList();
        subCategoryListBeans.addAll(subCategoryList);
        sortDataAdapter.notifyDataSetChanged();

    }

}

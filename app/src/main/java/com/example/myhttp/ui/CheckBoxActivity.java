package com.example.myhttp.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.chexkbox.CheckBox_Adapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.checkbox.CheckBox_Bean;
import com.example.myhttp.model.bean.checkbox.CheckBox_Bean1;
import com.example.myhttp.presenter.chexkbox.CheckBoxPresenter;
import com.example.myhttp.view.IChexkBox;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CheckBoxActivity extends BaseActivity<CheckBoxPresenter> implements IChexkBox.View{

    //指明接口P层类型
    IChexkBox.Persenter persenter;
    @BindView(R.id.checkbox_rlv)
    RecyclerView checkboxRlv;
    /**
     * 提交
     */
    @BindView(R.id.btn_ok)
    Button btnOk;
    //黄油刀
    private Unbinder bind;
    //适配器
    private CheckBox_Adapter checkBox_adapter;
    //集合有数据的 return
    private List<CheckBox_Bean.DataBean> data;
    //集合    空集合     后添加数据   没修改的数据
    private List<CheckBox_Bean.DataBean> datalist;
    //空集合   第二个bean 修改后的数据
    private List<CheckBox_Bean1> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_check_box;
    }

    @Override
    protected void initView() {

        bind = ButterKnife.bind(this);

        //布局管理器你
        checkboxRlv.setLayoutManager(new LinearLayoutManager(this));
        checkboxRlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //创建空集合 没修改的数据
        datalist = new ArrayList<>();
        //绑定适配器
        checkBox_adapter = new CheckBox_Adapter(this, datalist);
        checkboxRlv.setAdapter(checkBox_adapter);

        //点击监听
        checkBox_adapter.setIonclickitem(new CheckBox_Adapter.Ionclickitem() {
            @Override
            public void onitem(int qq) {
                //通过下标获取值
                CheckBox_Bean.DataBean bean = datalist.get(qq);
                //如果是选中状态
                if (bean.isSelect()){
                    bean.setSelect(false);  //更改为不选中
                }else {     //不选中状态
                    bean.setSelect(true);   //更改为选中状态
                }
            }
        });

    }

    @Override
    protected CheckBoxPresenter createPersenter() {
        //创建对应的P层实现类
        return new CheckBoxPresenter(this);
    }

    @Override
    protected void initData() {
        persenter = new CheckBoxPresenter(this);
        persenter.getChexkBox();
    }

    @Override
    public void getChexkBoxReturn(CheckBox_Bean checkBoxBean) {

        Log.e("TAG", "getChexkBoxReturn: "+checkBoxBean.getData().get(0).getName());
        
        //获取数据
        data = checkBoxBean.getData();

        //把获取的数据添加到空集合  没修改的数据
        datalist.addAll(data);
        //刷新适配器
        checkBox_adapter.notifyDataSetChanged();

        //修改后的数据    空集合
        list = new ArrayList<>();
        //遍历数据
        for (int i = 0; i < data.size(); i++) {
            //通过下标获取数据  全部数据
            CheckBox_Bean.DataBean bean = data.get(i);
            //修改后的数据
            CheckBox_Bean1 checkBox_bean1 = new CheckBox_Bean1();
            //添加数据  到封装类
            checkBox_bean1.setName(bean.getName());
            checkBox_bean1.setSelect(bean.isSelect());
            //把封装类的数据添加到    没修改的数据--集合
            list.add(checkBox_bean1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_ok)
    public void onClick() {
        //方法--打印修改的数据
        isSelect();
    }

    private void isSelect() {
        //为了打印数据的集合
        ArrayList<CheckBox_Bean.DataBean> dataBeans = new ArrayList<>();
        //遍历集合, 没修改数据的集合
        for (int i = 0; i < datalist.size(); i++) {
            //通过下标获取数据  没修改的
            CheckBox_Bean.DataBean bean = datalist.get(i);
            //通过下标获取数据  修改后的
            CheckBox_Bean1 checkBox_bean1 = list.get(i);
            //没修改的选中    ！=      修改后的选中
            if (bean.isSelect()!=checkBox_bean1.isSelect()){
                //添加筛选出来的数据到集合
                dataBeans.add(bean);
            }
        }
        //打印
        Log.e("TAG", "isSelect: "+dataBeans.toString());
    }

}
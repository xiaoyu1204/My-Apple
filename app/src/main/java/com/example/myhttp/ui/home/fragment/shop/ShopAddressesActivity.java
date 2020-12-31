package com.example.myhttp.ui.home.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.shop.ShopAddressesAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.shop.AddressAddProvinceBean;
import com.example.myhttp.model.bean.shop.AddressBean;
import com.example.myhttp.model.bean.shop.ShopAddressBean_String;
import com.example.myhttp.presenter.shop.ShopAddressesPresenter;
import com.example.myhttp.view.shop.IShopAddress;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopAddressesActivity extends BaseActivity<IShopAddress.Presenter> implements IShopAddress.View {

    IShopAddress.Presenter presenter;
    @BindView(R.id.address_rlv)
    RecyclerView addressRlv;
    @BindView(R.id.address_btn_add)
    Button addressBtnAdd;
    private ArrayList<AddressBean.DataBean> list;
    private ShopAddressesAdapter addressesAdapter;
    private ArrayList<ShopAddressBean_String> strings;

    @Override
    protected int getLayout() {
        return R.layout.activity_shop_addresses;
    }

    @Override
    protected IShopAddress.Presenter createPersenter() {
        return new ShopAddressesPresenter(this);
    }

    @Override
    protected void initView() {

        addressRlv.setLayoutManager(new LinearLayoutManager(this));
        addressRlv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        list = new ArrayList<>();

        //自定义
        strings = new ArrayList<>();
        addressesAdapter = new ShopAddressesAdapter(this, strings);
        addressRlv.setAdapter(addressesAdapter);

    }

    @Override
    protected void initData() {
        presenter = new ShopAddressesPresenter(this);
    }

    @Override
    public void getAddressReturn(AddressBean result) {
        if(result != null){
            Log.e("TAG", "getAddressReturn: " +result.getData().get(1).getName());
        }
    }

    //省市地址
    @Override
    public void getAddressAddProvinceReturn(AddressAddProvinceBean result) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.address_btn_add)
    public void onClick() {
        Intent intent = new Intent(this,ShopAddressesAddActivity.class);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == 200){

            String addName = data.getStringExtra("addName");
            String addPhone = data.getStringExtra("addPhone");
            String addShengQuXian = data.getStringExtra("addShengQuXian");
            String addXiangxi = data.getStringExtra("addXiangxi");

            ShopAddressBean_String shopAddressBean_string = new ShopAddressBean_String();
            shopAddressBean_string.setAddName(addName);
            shopAddressBean_string.setAddPhone(addPhone);
            shopAddressBean_string.setAddShengQuXian(addShengQuXian);
            shopAddressBean_string.setAddXiangxi(addXiangxi);

            strings.add(shopAddressBean_string);
            addressesAdapter.notifyDataSetChanged();

        }

    }
}
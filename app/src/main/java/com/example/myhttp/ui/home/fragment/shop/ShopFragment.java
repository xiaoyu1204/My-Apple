package com.example.myhttp.ui.home.fragment.shop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.shop.ShopAdapter;
import com.example.myhttp.base.BaseAdapter;
import com.example.myhttp.base.BaseFragment;
import com.example.myhttp.model.bean.shop.ShopBean;
import com.example.myhttp.presenter.shop.ShopPresenter;
import com.example.myhttp.ui.home.fragment.me.MeLoginActivity;
import com.example.myhttp.utils.ActivityManagerUtils;
import com.example.myhttp.utils.SpUtils;
import com.example.myhttp.view.shop.IShop;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


//TODO 购物车的展示
public class ShopFragment extends BaseFragment<IShop.Persenter> implements IShop.View {

    IShop.Persenter persenter;
    @BindView(R.id.mRlv_Shopping_Car)
    RecyclerView mRlvShoppingCar;       //集合
    @BindView(R.id.cb_Shopping_car_all)
    CheckBox cbShoppingCarAll;          //全选
    @BindView(R.id.tv_Shopping_Car_totalPrice)
    TextView tvShoppingCarTotalPrice;       //价格
    @BindView(R.id.tv_Shopping_Car_edit)
    TextView tvShoppingCarEdit;     //编辑
    @BindView(R.id.tv_Shopping_Car_submit)
    TextView tvShoppingCarSubmit;       //下单

    private boolean isEdit; //是否是编辑状态
    private ShopBean shopBean;
    private ShopAdapter shopAdapter;
    private List<ShopBean.DataBean.CartListBean> cartListBeans;

    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected IShop.Persenter createPrenter() {
        return new ShopPresenter(this);
    }

    @Override
    protected void initView() {
        initShopping();
        //获取列表

        cbShoppingCarAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isEdit){
                    updateGoodSelectStateEdit(isChecked);
                }else{
                    updateGoodSelectStateOrder(isChecked);
                }
            }
        });

    }

    private void initShopping() {
        cartListBeans=new ArrayList<>();
        mRlvShoppingCar.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopAdapter = new ShopAdapter(getContext(),cartListBeans);
        mRlvShoppingCar.setAdapter(shopAdapter);
    }

    @Override
    protected void initData() {
        
        String token = SpUtils.getInstance().getString("token");

        persenter = new ShopPresenter(this);
        if(!TextUtils.isEmpty(token)){
            presenter.getShop();
            //取出数值
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("shu");
            mContext.registerReceiver(receiver,intentFilter);
        }else{
            ActivityManagerUtils.startFragmentForResult(this,100, MeLoginActivity.class);
        }

        initItemClick();

    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //goodsId
            int goodsId = intent.getIntExtra("goodsId",0);
            //number
            String number = intent.getStringExtra("number");
            //productId
            int productId = intent.getIntExtra("productId",0);
            Log.e("TAG", "onReceive:goodsId "+goodsId );
            Log.e("TAG", "onReceive:number "+number );
            Log.e("TAG", "onReceive:productId "+productId );
            //请求数据
            if(goodsId>0 && number!=null && productId>0){   //不为空
                persenter.ShopAdd(goodsId,number,productId);
            }else{  //为空
                Log.e("TAG", "onReceive: 无" );
            }

        }
    };

    private void initItemClick() {
        /**
         * 监听条目元素点击的时候的接口回调
         */
        shopAdapter.addItemViewClick(new BaseAdapter.IItemViewClick() {
            @Override
            public void itemViewClick(int id, Object data) {
                for(ShopBean.DataBean.CartListBean item : shopBean.getData().getCartList()){
                    if(item.getId() == id){
                        if(!isEdit){
                            item.selectOrder = (boolean) data;
                        }else{
                            item.selectEdit = (boolean) data;
                        }
                        break;
                    }
                }
                boolean isSelectAll;
                if(!isEdit){
                    isSelectAll = totalSelectOrder();
                }else{
                    isSelectAll = totalSelectEdit();
                }
                cbShoppingCarAll.setChecked(isSelectAll);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getShop();
    }

    @Override
    public void getShopReturn(ShopBean result) {
        this.shopBean=result;
        cartListBeans.clear();
        cartListBeans.addAll(shopBean.getData().getCartList());
        shopAdapter.notifyDataSetChanged();
    }

    @Override
    public void ShopAddCarReturn(ShopBean result) {
        Log.e("TAG", "ShopAddCarReturn: "+result.getData().getCartList().get(0).getGoods_name());
    }

    //TODO 下单状态的数据刷新
    private void updateGoodSelectStateOrder(boolean isChecked) {
        for(ShopBean.DataBean.CartListBean item : shopBean.getData().getCartList()){
            item.selectOrder = isChecked;
        }
        totalSelectOrder();
        // 更新列表条目的选中状态
        shopAdapter.notifyDataSetChanged();
    }

    //TODO 编辑状态下的数据刷新
    private void updateGoodSelectStateEdit(boolean isChecked) {
        for(ShopBean.DataBean.CartListBean item:shopBean.getData().getCartList()){
            item.selectEdit = isChecked;
        }
        totalSelectOrder();
    }

    //TODO 下单状态下的总数和价格的计算
    private boolean totalSelectOrder() {
        int num = 0;
        int totalPrice = 0;
        boolean isSelectAll = true;
        for(ShopBean.DataBean.CartListBean item:shopBean.getData().getCartList()){
            if(item.selectOrder){
                num += item.getNumber();
                totalPrice += item.getNumber()*item.getRetail_price();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        cbShoppingCarAll.setText(strAll);
        tvShoppingCarTotalPrice.setText("￥"+totalPrice);
        return isSelectAll;
    }

    //TODO 编辑状态下的总数和价格的计算
    private boolean totalSelectEdit(){
        int num = 0;
        int totalPrice = 0;
        boolean isSelectAll = true;
        for(ShopBean.DataBean.CartListBean item:shopBean.getData().getCartList()){
            if(item.selectEdit){
                num += item.getNumber();
                totalPrice += item.getNumber()*item.getRetail_price();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        cbShoppingCarAll.setText(strAll);
        tvShoppingCarTotalPrice.setText("￥"+totalPrice);
        return isSelectAll;
    }

    @OnClick({R.id.cb_Shopping_car_all, R.id.tv_Shopping_Car_edit, R.id.tv_Shopping_Car_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_Shopping_car_all:
                if (cbShoppingCarAll.isSelected()) {
                    shopAdapter.setChecked(true);
                    shopAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_Shopping_Car_edit:     //点击编辑
                changeEdit();
                break;
            case R.id.tv_Shopping_Car_submit:   //点击下单
                submit();
                break;
        }
    }

    //TODO 修改编辑和完成的状态
    private void changeEdit() {
        if("编辑".equals(tvShoppingCarEdit.getText().toString())){
            tvShoppingCarEdit.setText("完成");
            tvShoppingCarSubmit.setText("删除所选");

            shopAdapter.setEditState(true);//删除
            shopAdapter.notifyDataSetChanged();
        }else if("完成".equals(tvShoppingCarEdit.getText().toString())){
            tvShoppingCarEdit.setText("编辑");
            tvShoppingCarSubmit.setText("下单");

            shopAdapter.setEditState(false);//删除
            shopAdapter.notifyDataSetChanged();
        }
    }

    //TODO 下单 提交
    private void submit() {
        if("下单".equals(tvShoppingCarSubmit.getText().toString())){
            //下单
        }else if("删除所选".equals(tvShoppingCarSubmit.getText().toString())){
            //删除购物车所选数据
        }
    }

}

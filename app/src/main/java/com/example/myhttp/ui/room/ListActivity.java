package com.example.myhttp.ui.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.room.RoomAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.room.RoomBean;
import com.example.myhttp.presenter.room.RoomPresenter;
import com.example.myhttp.view.room.IRoom;
import com.live.PushActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends BaseActivity<RoomPresenter> implements IRoom.View{

    @BindView(R.id.list_rlv)
    RecyclerView listRlv;
    @BindView(R.id.img_startLive)
    ImageView imgStartLive;
    @BindView(R.id.list_con)
    ConstraintLayout listCon;
    private ArrayList<RoomBean.DataBean> roomBeans;
    private RoomAdapter roomAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_list;
    }

    @Override
    protected RoomPresenter createPersenter() {
        return  new RoomPresenter(this);
    }

    @Override
    protected void initView() {

        roomBeans = new ArrayList<>();
        roomAdapter = new RoomAdapter(this,roomBeans);
        listRlv.setAdapter(roomAdapter);

    }

    @Override
    protected void initData() {
        persenter.room();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_startLive)
    public void onClick() {
        Intent intent = new Intent(this, PushActivity.class);
        startActivity(intent);
    }

    @Override
    public void roomreturn(RoomBean result) {
            roomBeans.clear();
        if (result.getErrno()==0){
            List<RoomBean.DataBean> data = result.getData();
            roomBeans.addAll(data);
            roomAdapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }

    }

}
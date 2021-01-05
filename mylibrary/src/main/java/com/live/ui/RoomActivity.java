package com.live.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.live.MyApplication;
import com.live.R;
import com.live.adapter.RoomAdapter;
import com.live.base.BaseActivity;
import com.live.base.BaseAdapter;
import com.live.model.bean.RoomBean;
import com.live.presenter.RoomPresenter;
import com.live.utils.SpUtils;
import com.live.view.IRoom;

import java.util.ArrayList;
import java.util.List;

/*主界面*/
public class RoomActivity extends BaseActivity<IRoom.Presenter> implements IRoom.View, View.OnClickListener {

    ImageView imgBack;
    TextView title;
    RecyclerView recyList;
    ImageView imgHome;
    ImageView imgStartLive;

    List<RoomBean.DataBean> roomBeans;
    RoomAdapter roomAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_room;
    }

    @Override
    public void tips(String tip) {
        super.tips(tip);
        Log.i("TAG", "tips: "+tip);
    }

    @Override
    protected IRoom.Presenter createPersenter() {
        return new RoomPresenter(this);
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        title = findViewById(R.id.title_room);
        recyList = findViewById(R.id.recy_list);
        imgStartLive = findViewById(R.id.img_startLive);
        imgHome = findViewById(R.id.img_home);

        imgHome.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgStartLive.setOnClickListener(this);

        recyList.setLayoutManager(new GridLayoutManager(MyApplication.application,2));
        roomBeans = new ArrayList<>();
        roomAdapter = new RoomAdapter(this,roomBeans);
        recyList.setAdapter(roomAdapter);

        roomAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
//                Intent intent = new Intent(RoomActivity.this, CreateHomeActivity.class);
//                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        persenter.room();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.img_startLive) {
            //摄像头
            Intent intent = new Intent(RoomActivity.this, PushActivity.class);
            startActivity(intent);
        } else if (id == R.id.img_back) {
            finish();
        }else if(id == R.id.img_home){
            //创建房间
            Intent intent = new Intent(RoomActivity.this, CreateRoomActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void roomreturn(RoomBean result) {
        roomBeans.clear();
        if (result.getErrno() == 0) {
            List<RoomBean.DataBean> data = result.getData();
            roomBeans.addAll(data);
            roomAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }

}

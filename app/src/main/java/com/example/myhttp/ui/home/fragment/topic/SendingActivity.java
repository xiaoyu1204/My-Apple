package com.example.myhttp.ui.home.fragment.topic;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.adapter.topic.SendingAdapter;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.utils.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendingActivity extends BaseActivity {

    @BindView(R.id.tv_sending_cancel)
    TextView tvSendingCancel;
    @BindView(R.id.tv_sending)
    TextView tvSending;
    @BindView(R.id.tv_sending_desc)
    EditText tvSendingDesc;
    @BindView(R.id.mRlv_sending)
    RecyclerView rlv;
    @BindView(R.id.img_addimg)
    ImageView imgAddimg;

    private SendingAdapter pingAdapter;
    private ArrayList<String> strings;

    @Override
    protected int getLayout() {
        return R.layout.activity_sending;
    }

    @Override
    protected IBasePersenter createPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        strings = new ArrayList<>();
        rlv.setLayoutManager(new GridLayoutManager(SendingActivity.this,3));
        pingAdapter = new SendingAdapter(SendingActivity.this,strings);
        rlv.setAdapter(pingAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_sending_cancel, R.id.tv_sending, R.id.img_addimg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sending_cancel:
                finishAfterTransition();
                break;
            case R.id.tv_sending:
                initBtn();
                break;
            case R.id.img_addimg:
                openPhoto();
                break;
        }
    }

    private void initBtn() {
        String et = tvSendingDesc.getText().toString();
        if (TextUtils.isEmpty(et)){
            Toast.makeText(this, "评论不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
//        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString("text",et);
//        bundle.putStringArrayList("list",strings);
//        intent.putExtra("bundle",bundle);
//        setResult(200,intent);
        finish();
    }

    private void openPhoto() {
        int sum = 9-strings.size();
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(sum)
                .imageSpanCount(9)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表


                for (int i = 0; i < selectList.size(); i++) {
                    String img = selectList.get(i).getPath();
                    strings.add(img);
                }
                pingAdapter.notifyDataSetChanged();
                if (strings.size()>=9){
                    imgAddimg.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

}
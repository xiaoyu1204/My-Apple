package com.example.myhttp.ui.home.fragment.me;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myhttp.R;
import com.example.myhttp.app.Constants;
import com.example.myhttp.base.BaseActivity;
import com.example.myhttp.model.bean.me.UserInfoBean;
import com.example.myhttp.presenter.me.UserPresenter;
import com.example.myhttp.utils.BitmapUtils;
import com.example.myhttp.utils.GlideEngine;
import com.example.myhttp.utils.SpUtils;
import com.example.myhttp.utils.SystemUtils;
import com.example.myhttp.view.me.IUser;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeInfoActivity extends BaseActivity<IUser.Presenter> implements IUser.View {

    @BindView(R.id.txt_input)
    EditText txtInput;
    @BindView(R.id.layout_input)
    ConstraintLayout layoutInput;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;

    String bucketName = "2002a02"; //Bucket 名
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";    //Bucket 名

    //这个通常使用密钥传输，直接放着不安全
    String key = "LTAI4G3x7KtcnYUxxSmYK17e";  //appkey
    String secret = "D1PLamKr1tuzcMC0J4dGrXTfvAe9Jq";  //密码



    private OSS ossClient;

    @Override
    protected int getLayout() {
        return R.layout.activity_meinfo;
    }

    @Override
    protected IUser.Presenter createPersenter() {
        return new UserPresenter();
    }

    @Override
    protected void initView() {
        initOss();

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoto();
            }
        });

        String img = SpUtils.getInstance().getString("img");
        if (!TextUtils.isEmpty(img)) {
            Glide.with(this).load(img).apply(new RequestOptions().circleCrop()).into(imgAvatar);
        }

    }

    @Override
    protected void initData() {

    }

    //初始化OSS
    private void initOss() {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key, secret, "");
        // 配置类如果不设置，会有默认配置。
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。
        ossClient = new OSSClient(getApplicationContext(), ossPoint, credentialProvider);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_avatar, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_avatar:
                openPhoto();
                break;
            case R.id.btn_save:
                String nickname = txtInput.getText().toString();
                if (!TextUtils.isEmpty(nickname)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("nickname", nickname);
                    persenter.updateUserInfo(map);
                }
                break;
        }
    }

    //TODO 打开相册
    private void openPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器

                //把选中的图片插入到列表
                try {
                    for (int i = 0; i < selectList.size(); i++) {
                        //头像的压缩和二次采样
                        Bitmap scaleBitmp = BitmapUtils.getScaleBitmap(selectList.get(i).getPath(), Constants.HEAD_WIDTH, Constants.HEAD_HEIGHT);
                        //Bitmap转uri
                        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), scaleBitmp, null, null));
                        //uri转字符串
                        String path = getRealPathFromUri(this, uri);
                        uploadHead(path);//上传头像
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);

        }
    }

    //TODO uri转字符串的方法
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //上传到阿里云
    private void uploadHead(String path) {
        String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
        PutObjectRequest put = new PutObjectRequest(bucketName, fileName, path);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                //上次进度
                Log.i("oss_upload", currentSize + "/" + totalSize);
                // 进度百分比的计算
                // int p = (int) (currentSize/totalSize*100);
                if (currentSize == totalSize) {
                    //完成
                    String headUrl = request.getUploadFilePath();
                    //
                    Log.i("HeadUrl", headUrl);
                    //request.getUploadFilePath()
                }

            }
        });
        OSSAsyncTask task = ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                //成功的回调中读取相关的上传文件的信息  生成一个url地址
                String url = ossClient.presignPublicObjectURL(request.getBucketName(), request.getObjectKey());
                //TODO 刷新显示到界面上
                updateHead(url);
                //调用服务器接口，把url上传到服务器的接口
                SpUtils.getInstance().setValue("img", url);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    //TODO 上传头像
    private void updateHead(String url) {
        imgAvatar.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(imgAvatar).load(url).apply(new RequestOptions().circleCrop()).into(imgAvatar);
            }
        });
    }

    private void showInput() {
        layoutInput.setVisibility(View.VISIBLE);
        txtInput.setFocusable(true);
        SystemUtils.openSoftKeyBoard(this);
    }

    @Override
    public void updateUserInfoReturn(UserInfoBean result) {
        if (result.getErrno() == 0) {
            SystemUtils.closeSoftKeyBoard(this);
            layoutInput.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.img_avatar)
    public void onClick() {
    }
}
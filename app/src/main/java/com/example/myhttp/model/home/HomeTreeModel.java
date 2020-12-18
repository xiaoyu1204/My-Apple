package com.example.myhttp.model.home;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.Home_Channel_Tree_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.home.IHomeTree;
import com.example.myhttp.view.home.IHomeType;

public class HomeTreeModel extends BaseModel implements IHomeTree.Model {
    @Override
    public void getHomeTree(int categoryId, Callback callback) {
        addDisposable(
                HttpManager.getInstance().getApiShop().getChannelTree(categoryId)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<Home_Channel_Tree_Bean>(callback) {
                    @Override
                    public void onNext(Home_Channel_Tree_Bean home_channel_tree_bean) {
                        callback.success(home_channel_tree_bean);
                    }
                })
        );
    }
}

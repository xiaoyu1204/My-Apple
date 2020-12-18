package com.example.myhttp.model.home;

import com.example.myhttp.base.BaseModel;
import com.example.myhttp.model.bean.home.Home_NewGoods_Below_Bean;
import com.example.myhttp.model.bean.home.Home_NewGoods_Top_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.net.CommonSubscriber;
import com.example.myhttp.net.HttpManager;
import com.example.myhttp.utils.RxUtils;
import com.example.myhttp.view.home.IHomeNewGoods;

public class Home_NewGoods_Model extends BaseModel implements IHomeNewGoods.Model {
    @Override
    public void getHomeNewGoodsTop(Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getHomeNewBrandsTop()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<Home_NewGoods_Top_Bean>(callback) {
                    @Override
                    public void onNext(Home_NewGoods_Top_Bean home_newGoods_top_bean) {
                        callback.success(home_newGoods_top_bean);
                    }
                })
        );
    }

    @Override
    public void getHomeNewGoodsBelow(int isNew, int page, int size, String order, String sort, int categoryId, Callback callback) {
        addDisposable(HttpManager.getInstance().getApiShop().getHomeNewGoodsBelow(isNew,page,size,order,sort,categoryId)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<Home_NewGoods_Below_Bean>(callback) {
            @Override
            public void onNext(Home_NewGoods_Below_Bean home_newGoods_below_bean) {
                callback.success(home_newGoods_below_bean);
            }
        }));
    }

}

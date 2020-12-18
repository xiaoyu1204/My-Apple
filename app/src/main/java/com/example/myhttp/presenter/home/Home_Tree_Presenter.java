package com.example.myhttp.presenter.home;

import com.example.myhttp.base.BasePresenter;
import com.example.myhttp.model.bean.home.Home_Channel_Tree_Bean;
import com.example.myhttp.model.callback.Callback;
import com.example.myhttp.model.home.HomeTreeModel;
import com.example.myhttp.model.home.HomeTypeModel;
import com.example.myhttp.view.home.IHomeTree;
import com.example.myhttp.view.home.IHomeType;

public class Home_Tree_Presenter extends BasePresenter<IHomeTree.View> implements IHomeTree.Persenter {

    IHomeTree.Model model;
    IHomeTree.View mView;

    public Home_Tree_Presenter(IHomeTree.View mView) {
        this.mView = mView;
        model = new HomeTreeModel();
    }

    @Override
    public void getHomeTree(int categoryId) {
        model.getHomeTree(categoryId, new Callback() {
            @Override
            public void success(Object o) {
                if(mView != null){
                    mView.getHomeTreeReturn((Home_Channel_Tree_Bean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if(mView != null){
                    mView.tips(msg);
                }
            }
        });
    }
}

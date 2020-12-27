package com.example.myhttp.view.topic;

import com.example.myhttp.base.IBaseModel;
import com.example.myhttp.base.IBasePersenter;
import com.example.myhttp.base.IBaseView;
import com.example.myhttp.model.bean.topic.TopicCommentBean;
import com.example.myhttp.model.bean.topic.TopicRelatedBean;
import com.example.myhttp.model.bean.topic.TopicdeBean;
import com.example.myhttp.model.callback.Callback;

//接口契约类
public interface ITopicCommennt {

    interface View extends IBaseView {
        //定义一个被实现的View层接口方法
        //专题评论
        void getTopicCommentReturn(TopicCommentBean result);
        //专题详情数据
        void getTopicdeReturn(TopicdeBean result);
        //专题底部列表
        void getTopicRelaReturn(TopicRelatedBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        //定义一个V层调用的接口
        void getTopicComment(int valueId,int typeId,int size);
        void getTopicde(int id);
        void getTopicRela(int id);
    }

    interface Model extends IBaseModel {
        //定义一个加载数据的接口方法   被P层
        void getTopicComment(int valueId,int typeId,int size,Callback callback);
        void getTopicde(int id,Callback callback);
        void getTopicRela(int id,Callback callback);
    }

}

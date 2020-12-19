package com.example.myhttp.utils.flow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义流式布局(实现热门标签页)
 * Create by: chenwei.li
 * Date: 2017/7/27
 * Time: 下午8:39
 * Email: lichenwei.me@foxmail.com
 */

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        this(context, null);
    }

    //这个方法必须实现
    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //布局：给每一个子view布局，childView.layout(l,t,r,b)
    private List<Integer> allHeights = new ArrayList<>();//集合中的元素：记录每一行的高度
    private List<List<View>> allViews = new ArrayList<>();//外层集合中的元素：由每行元素构成的集合

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = this.getWidth();//得到父视图的宽度

        int lineWidth = 0;
        int lineHeight = 0;

        // 一、给集合元素赋值
        int childCount = getChildCount();
        List<View> lineList = new ArrayList<>();//一行元素构成的集合
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //子视图的宽高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            //获取视图的边距
            MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();
            if (lineWidth + childWidth + mp.leftMargin + mp.rightMargin < width) {//不换行
                lineList.add(childView);//添加子视图到集合中
                lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = Math.max(lineHeight, childHeight + mp.topMargin + mp.bottomMargin);
            } else {//换行
                allViews.add(lineList);
                allHeights.add(lineHeight);

                //换行以后需要执行的情况
                lineList = new ArrayList<>();
                lineList.add(childView);
                lineWidth = childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;
            }

            if (i == childCount - 1) {//如果最后一个元素
                allViews.add(lineList);
                allHeights.add(lineHeight);
            }
        }


//        Log.e("TAG", "allViews.size()==" + allViews.size() + "allHeights.size()==" + allHeights.size());

        //二、遍历集合元素，调用元素的layout()

        int x = 0;
        int y = 0;

        for (int i = 0; i < allViews.size(); i++) {
            List<View> lineViews = allViews.get(i);//获取每一行的集合
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);//获取一行的指定的j位置

                MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();
                //计算的到left,top,right,bottom
                int left = x + mp.leftMargin;
                int top = y + mp.topMargin;
                int right = left + childView.getMeasuredWidth();
                int bottom = top + childView.getMeasuredHeight();

                childView.layout(left, top, right, bottom);

                //重新赋值x,y
                x += childView.getMeasuredWidth() + mp.leftMargin + mp.rightMargin;
            }

            //换行
            x = 0;
            y += allHeights.get(i);
        }
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取宽度和高度的布局的数值，以及各自的设计模式，精确模式，至多模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //声明当前视图的宽和高，如果是至多模式，需要计算出此两个变量的值
        int width = 0;
        int height = 0;

        //声明每行的宽度和高度
        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();//获取子视图的个数
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            //为了保证能够获取子视图的测量的宽高，需要调下面的方法
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //获取子视图测量的宽高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //获取视图的边距
            MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();

            if (lineWidth + childWidth + mp.leftMargin + mp.rightMargin <= widthSize) {//不换行
                lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = Math.max(lineHeight, childHeight + mp.topMargin + mp.bottomMargin);
            } else {//换行
                width = Math.max(width, lineWidth);
                height += lineHeight;

                //重新赋值
                lineWidth = childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;

            }
            //单独的考虑一下最后一个！因为最后一个元素并没有计算进去
            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

//        Log.e("TAG", "width ==" + width + ",height==" + height);
//        Log.e("TAG", "widthSize ==" + widthSize + ",heightSize==" + heightSize);

        //调用此方法，设置当前布局的宽高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);

    }

    //FlowLayout中有了如下的方法，在onMeasure()中可通过child就可以getLayoutParams()
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;

    }

}

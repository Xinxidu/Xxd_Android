package com.xinxidu.xxd.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class MyRecyclerView extends RecyclerView {

/**
 * 记录当前第一个View
 */
private View mCurrentView;

private OnItemScrollChangeListener mItemScrollChangeListener;

public void setOnItemScrollChangeListener(
        OnItemScrollChangeListener mItemScrollChangeListener)
{
    this.mItemScrollChangeListener = mItemScrollChangeListener;
}

public interface OnItemScrollChangeListener
{
    void onChange(View view, int position);
}

public MyRecyclerView(Context context, AttributeSet attrs)
{
    super(context, attrs);
}

@Override
protected void onLayout(boolean changed, int l, int t, int r, int b)
{
    super.onLayout(changed, l, t, r, b);

    mCurrentView = getChildAt(0);

    if (mItemScrollChangeListener != null)
    {
        mItemScrollChangeListener.onChange(mCurrentView,
                getChildPosition(mCurrentView));
    }
}

}

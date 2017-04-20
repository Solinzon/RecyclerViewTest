package com.example.xushuzhan.recyclerviewtest.common_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xushuzhan.recyclerviewtest.ViewHolder;

/**
 * Created by xushuzhan on 2017/4/20.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews; //
    private View mConvertView;
    private Context mContext;
    public CommonViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mContext = context; //得到上下文
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static CommonViewHolder get(Context context, ViewGroup parent, int layoutId){  //创建一个ViewHolder
        View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false); //得到每个item的view
        CommonViewHolder holder = new CommonViewHolder(context,itemView,parent);//创建ViewHolder
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */

    public <T extends View>T getView(int viewId){
        View view = mViews.get(viewId);
        if(view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }

        return (T) view;
    }

    //常用的绑定数据的方法
    public CommonViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}

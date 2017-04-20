package com.example.xushuzhan.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xushuzhan on 2017/1/30.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas; //bean文件的集合
    protected LayoutInflater mInflater;
    public abstract void convert(ViewHolder holder, T t);//用于绑定数据的方法，交给用户去控制
    public CommonAdapter(Context context, int layoutId, List<T> datas){
        mContext = context;
        mLayoutId = layoutId;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.get(parent.getContext(),parent,mLayoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.updatePosition(position);
        convert(holder,mDatas.get(position));
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //用于支持不同的itemViewType的情况
    public interface MultiItemTypeSupport<T>
    {
        int getLayoutId(int itemType);

        int getItemViewType(int position, T t);
    }
}

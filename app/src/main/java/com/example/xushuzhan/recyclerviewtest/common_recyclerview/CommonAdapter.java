package com.example.xushuzhan.recyclerviewtest.common_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.xushuzhan.recyclerviewtest.ViewHolder;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by xushuzhan on 2017/4/20.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder>{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas; //bean文件的集合
    protected LayoutInflater mInflater;
    public abstract void convert(CommonViewHolder holder, T t);//用于绑定数据的方法，交给用户去控制
    public CommonAdapter(Context context, int layoutId, List<T> datas){
        mContext = context;
        mLayoutId = layoutId;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder holder = CommonViewHolder.get(parent.getContext(),parent,mLayoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        //holder.updatePosition(position);
        convert(holder,mDatas.get(position));
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

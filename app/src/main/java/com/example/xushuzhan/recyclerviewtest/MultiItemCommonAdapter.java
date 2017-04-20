package com.example.xushuzhan.recyclerviewtest;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xushuzhan on 2017/2/2.
 */

public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {
    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position,mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.get(mContext,parent,layoutId);
        return holder;
    }
}

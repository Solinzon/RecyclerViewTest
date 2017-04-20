package com.example.xushuzhan.recyclerviewtest.common_recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 用于支持上拉加载的装饰Adapter
 * Created by xushuzhan on 2017/4/20.
 */

public abstract class RefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter adapter;//普通的Adapter
    private View footerView;
    public static final int NORMAL_VIEW_TYPE = 1;
    public static final int FOOTER_VIEW_TYPE = 2;

    public RefreshAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public void addFooterView(View footerView) {
        this.footerView = footerView;
    }

    @Override
    public int getItemViewType(int position) {
        return position == adapter.getItemCount() - 1 ? FOOTER_VIEW_TYPE : NORMAL_VIEW_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType == NORMAL_VIEW_TYPE ? adapter.onCreateViewHolder(parent, viewType) : new RecyclerView.ViewHolder(footerView) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == adapter.getItemCount() - 1) {
            return;
        } else {
            adapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount() + 1;
    }
}

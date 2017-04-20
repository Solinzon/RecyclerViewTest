package com.example.xushuzhan.recyclerviewtest.common_recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xushuzhan.recyclerviewtest.R;
import com.example.xushuzhan.recyclerviewtest.load_more.MyRefreshAdapter;
import com.example.xushuzhan.recyclerviewtest.load_more.MyRefreshRecyclerView;

/**
 * Created by xushuzhan on 2017/4/20.
 */

public class CommonRecyclerView extends RecyclerView {
    private static final String TAG = "MyRefreshRecyclerView";
    private MyRefreshAdapter myRefreshAdapter;
    private MyRefreshRecyclerView.MyLoadListener myLoadListener;
    private boolean isLoadMore;
    LinearLayout footerLayout;
    LinearLayoutManager manager;


    private View loadingMoreView = LayoutInflater.from(getContext()).inflate(R.layout.load_more,null);//正在加载更多时显示的View...
    private View noMoreView = LayoutInflater.from(getContext()).inflate(R.layout.load_finish,null);//没有更多了的View
    private View erroView;//出现错误时候的View
    public CommonRecyclerView(Context context) {
        super(context);
        init();
    }

    public CommonRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        loadingMoreView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        noMoreView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        erroView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        this.addOnScrollListener(new OnScrollListener() {
            /**
             *
             * @param recyclerView 当前在滚动的RecyclerView
             * @param newState 当前滚动状态
            其中newState有三种值:

            //停止滚动
            public static final int SCROLL_STATE_IDLE = 0;

            //正在被外部拖拽,一般为用户正在用手指滚动
            public static final int SCROLL_STATE_DRAGGING = 1;

            //自动滚动开始
            public static final int SCROLL_STATE_SETTLING = 2;
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                changeFooterView(loadingMoreView);
                manager= (LinearLayoutManager) recyclerView.getLayoutManager();
                //停止滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示Item的Position
                    int lastVisibleItem = manager.findLastVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    Log.d(TAG, "onScrollStateChanged->lastVisibleItem: "+lastVisibleItem);
                    Log.d(TAG, "onScrollStateChanged->totalItemCount: "+totalItemCount);
                    // 判断是否滚动到底部，并且不在加载状态
                    if (lastVisibleItem == (totalItemCount - 1) && !isLoadMore ) {
                        isLoadMore = true;
                        footerLayout.setVisibility(VISIBLE);
                        myLoadListener.onLoadMore();
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void setAdapter(Adapter adapter) {
        footerLayout = new LinearLayout(getContext());
        footerLayout.setGravity(Gravity.CENTER);
        footerLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        footerLayout.addView(loadingMoreView);
        myRefreshAdapter = new MyRefreshAdapter(adapter);
        myRefreshAdapter.addFooterView(footerLayout);
        super.setAdapter(myRefreshAdapter);
    }

    public void setLoadMore(boolean complete) {
        if (complete) {
           changeFooterView(noMoreView);
            Toast.makeText(getContext(), "加载完了", Toast.LENGTH_SHORT).show();
        } else {
            footerLayout.setVisibility(GONE);
            Toast.makeText(getContext(), "没加载完", Toast.LENGTH_SHORT).show();
        }
        isLoadMore = false;
    }

    public void setMyLoadListener(MyRefreshRecyclerView.MyLoadListener myLoadListener) {
        this.myLoadListener = myLoadListener;
    }

    public CommonRecyclerView setLoadingMoreView(@NonNull View LoadingMoreView){
        this.loadingMoreView = LoadingMoreView;
        this.loadingMoreView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return this;
    }
    public CommonRecyclerView setLoadingMoreView(@NonNull  int LayoutId){
        setLoadingMoreView(LayoutInflater.from(getContext()).inflate(LayoutId,null));
        return this;
    }

    public CommonRecyclerView setNoMoreView(@NonNull View noMoreView){
        this.noMoreView = noMoreView;
        this.noMoreView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return this;
    }
    public CommonRecyclerView setNoMoreView(@NonNull int LayoutId){
        setNoMoreView(LayoutInflater.from(getContext()).inflate(LayoutId,null));
        return this;
    }


    public CommonRecyclerView setErroView(@NonNull View erroView){
        this.erroView = erroView;
        return this;
    }

    public void changeFooterView(View newView){
        footerLayout.removeAllViews();
        footerLayout.addView(newView);
    }

}

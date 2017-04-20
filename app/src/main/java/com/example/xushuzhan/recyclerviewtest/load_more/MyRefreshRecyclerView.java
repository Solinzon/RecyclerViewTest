package com.example.xushuzhan.recyclerviewtest.load_more;
import android.content.Context;
import android.graphics.Color;
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

public class MyRefreshRecyclerView extends RecyclerView {
    private static final String TAG = "MyRefreshRecyclerView";
    private MyRefreshAdapter myRefreshAdapter;
    private View footerView;
    private MyLoadListener myLoadListener;
    private boolean isLoadMore;
    private TextView loadTxt;
    private View circleProgressView;


    public void setMyLoadListener(MyLoadListener myLoadListener) {
        this.myLoadListener = myLoadListener;
    }

    public MyRefreshRecyclerView(Context context) {
        super(context);
        init();
    }

    public MyRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {
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
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
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
                        loadTxt.setText("正在加载...");
                        circleProgressView.setVisibility(VISIBLE);
                        footerView.setVisibility(VISIBLE);
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

        LinearLayout footerLayout = new LinearLayout(getContext());
        footerLayout.setGravity(Gravity.CENTER);
        footerLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 160));

        circleProgressView = LayoutInflater.from(getContext()).inflate(R.layout.load_more,null);
        circleProgressView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
        footerLayout.addView(circleProgressView);
        loadTxt = new TextView(getContext());
        footerLayout.addView(loadTxt);
        footerView = footerLayout;
        myRefreshAdapter = new MyRefreshAdapter(adapter);
        myRefreshAdapter.addFooterView(footerView);
        super.setAdapter(myRefreshAdapter);
    }

    public interface MyLoadListener {
        void onLoadMore();
    }

    public void setLoadMore(boolean complete) {
        if (complete) {
            loadTxt.setText("已经全部加载完啦!");
            circleProgressView.setVisibility(GONE);
        } else {
            footerView.setVisibility(GONE);
            Toast.makeText(getContext(), "没加载完", Toast.LENGTH_SHORT).show();
        }
        isLoadMore = false;
    }

    public void setLoadMoreFinish(boolean isFinish){
        if(isFinish){
            loadTxt.setText("已经全部加载完啦!");
        }
    }

}

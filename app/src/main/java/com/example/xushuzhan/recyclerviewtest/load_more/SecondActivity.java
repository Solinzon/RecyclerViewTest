package com.example.xushuzhan.recyclerviewtest.load_more;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.xushuzhan.recyclerviewtest.R;
import com.example.xushuzhan.recyclerviewtest.common_recyclerview.CommonAdapter;
import com.example.xushuzhan.recyclerviewtest.common_recyclerview.CommonRecyclerView;
import com.example.xushuzhan.recyclerviewtest.common_recyclerview.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    NormalAdapter adapter;
    MyRefreshRecyclerView myRefreshRecyclerView;
    CommonRecyclerView commonRecyclerView;
    CommonAdapter<String> commonAdapter;
    private Handler mHandler = new Handler();

    private List<String> integerList = new ArrayList<>();


    int mostData = 20;//最多的数据量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        for (int i = 0; i < 10; i++) {
            integerList.add(i + "");
        }
//        myRefreshRecyclerView = (MyRefreshRecyclerView) findViewById(R.id.rv_second_activity);
//        myRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new NormalAdapter(integerList);
//        myRefreshRecyclerView.setAdapter(adapter);
//        myRefreshRecyclerView.setMyLoadListener(new MyRefreshRecyclerView.MyLoadListener() {
//            @Override
//            public void onLoadMore() {
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (integerList.size() > 20) {
//                            myRefreshRecyclerView.setLoadMore(true);
//                        } else {
//                            int randomInt = new Random().nextInt(100);
//                            for (int i = 0; i < 4; i++) {
//                                integerList.add("上拉加载添加数字:" + randomInt);
//                            }
//
//                            adapter.notifyDataSetChanged();
//                            myRefreshRecyclerView.setLoadMore(false);
//                        }
//
//                    }
//                }, 1000);
//            }
//        });

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostData += 10;
            }
        });
        commonRecyclerView = (CommonRecyclerView) findViewById(R.id.rv_second_activity);
        commonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commonAdapter = new CommonAdapter<String>(SecondActivity.this,R.layout.item__normal,integerList) {
            @Override
            public void convert(CommonViewHolder holder, String s) {
                holder.setText(R.id.tv_item_normal,s);
            }
        };
        commonRecyclerView.setLoadingMoreView(R.layout.loading_more).setNoMoreView(R.layout.no_more);
        commonRecyclerView.setAdapter(commonAdapter);
        commonRecyclerView.setMyLoadListener(new MyRefreshRecyclerView.MyLoadListener() {
            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (integerList.size() > mostData) {
                            commonRecyclerView.setLoadMore(true);
                        } else {
                            int randomInt = new Random().nextInt(100);
                            for (int i = 0; i < 4; i++) {
                                integerList.add("上拉加载添加数字:" + randomInt);
                            }
                            commonAdapter.notifyDataSetChanged();
                            commonRecyclerView.setLoadMore(false);
                        }

                    }
                }, 1000);
            }
        });

    }
}
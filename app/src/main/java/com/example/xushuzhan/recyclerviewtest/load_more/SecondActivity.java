package com.example.xushuzhan.recyclerviewtest.load_more;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.xushuzhan.recyclerviewtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    NormalAdapter adapter;
    MyRefreshRecyclerView myRefreshRecyclerView;
    private Handler mHandler = new Handler();

    private List<String> integerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        for(int i = 0;i<100;i++){
            integerList.add(i+"");
        }
        myRefreshRecyclerView = (MyRefreshRecyclerView) findViewById(R.id.rv_second_activity);
        myRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NormalAdapter(integerList);
        myRefreshRecyclerView.setAdapter(adapter);
        myRefreshRecyclerView.setMyLoadListener(new MyRefreshRecyclerView.MyLoadListener() {
            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (integerList.size() > 500) {
                            myRefreshRecyclerView.setLoadMore(true);
                        } else {
                            int randomInt = new Random().nextInt(100);
                            for (int i= 0;i<100;i++) {
                                integerList.add("上拉加载添加数字:" + randomInt);

                            }
                          adapter.notifyDataSetChanged();

//                            adapter.notifyItemRangeChanged(myRefreshRecyclerView.getAdapter().getItemCount()-1-3,3);
                            myRefreshRecyclerView.setLoadMore(false);
                        }

                    }
                }, 1000);
            }
        });
    }
}
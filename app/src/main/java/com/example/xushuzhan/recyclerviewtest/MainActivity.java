package com.example.xushuzhan.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xushuzhan.recyclerviewtest.load_more.SecondActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<TestDataBeen> mDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i= 100;i<109;i++){
            mDates.add(new TestDataBeen(0,i));
        }
        for(int i= 100;i<120;i++){
            mDates.add(new TestDataBeen(1,i+9));
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

//        mRecyclerView.setAdapter(new CommonAdapter<TDB>(MainActivity.this,R.layout.item,mDates){
//            @Override
//            public void convert(ViewHolder holder, final String s) {
//                holder.setText(R.id.tv_item,s);
//                holder.setOnClickListener(R.id.tv_item, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(mContext,s, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        CommonAdapter.MultiItemTypeSupport<TestDataBeen> multiItemTypeSupport = new CommonAdapter.MultiItemTypeSupport<TestDataBeen>() {
            @Override
            public int getLayoutId(int itemType) {
                if(itemType == 0){
                    return R.layout.item_1;
                }else if(itemType == 1){
                    return R.layout.item_2;

                }else {
                    return R.layout.item;

                }
            }

            @Override
            public int getItemViewType(int position, TestDataBeen testDataBeen) {
                    return testDataBeen.mViewType;
            }
        };
        final MultiItemCommonAdapter multiItemCommonAdapter =new MultiItemCommonAdapter<TestDataBeen>(MainActivity.this,mDates,multiItemTypeSupport) {
            @Override
            public void convert(ViewHolder holder, TestDataBeen testDataBeen) {
                holder.setText(R.id.tv_item,testDataBeen.mData+"");
            }
        };

        mRecyclerView.setAdapter(multiItemCommonAdapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mDates.add(mDates.size(),new TestDataBeen(1,99999999));
//                multiItemCommonAdapter.notifyDataSetChanged();
//                //linearLayoutManager.scrollToPosition(mDates.size());
////                linearLayoutManager.scrollToPositionWithOffset(3,0);
//                mRecyclerView.smoothScrollToPosition(mDates.size());//跳转到最底部

            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }
}

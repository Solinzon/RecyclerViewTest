package com.example.xushuzhan.recyclerviewtest.load_more;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xushuzhan.recyclerviewtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2017/4/20.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.ViewHolder>{
    public List<String> integerList;

    public NormalAdapter(List<String> integerList){
        this.integerList = integerList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item__normal,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(integerList.get(position));
    }

    @Override
    public int getItemCount() {
        return integerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item_normal);
        }
    }
}

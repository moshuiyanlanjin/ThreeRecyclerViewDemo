package com.zhongyagroup.threerecyclerviewdemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongyagroup.threerecyclerviewdemo.MainActivity;
import com.zhongyagroup.threerecyclerviewdemo.R;
import com.zhongyagroup.threerecyclerviewdemo.Tree.Node;
import com.zhongyagroup.threerecyclerviewdemo.model.CountryModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter<T> extends TreeRecycleViewAdapter<T>{
    public MyAdapter(RecyclerView recy, Context context, List<T> mdata, int size) throws IllegalArgumentException,IllegalAccessException {
        super(recy,context,mdata,size);
    }

    @Override
    public RecyclerView.ViewHolder getCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tree_view, parent, false);
        return new mViewHolder(inflate);
    }


    @Override
    public void getBindViewHolder(Node node, int position, RecyclerView.ViewHolder holder) {
        mViewHolder holder1 = (mViewHolder) holder;
        // 设置内边距
        holder1.linear.setPadding(node.getLevel() * 40, 0, 0, 0);
        if(node.isIsxia()){
            holder1.viewone.setVisibility(View.GONE);
        }else{
            holder1.viewone.setVisibility(View.VISIBLE);
        }
        if(node.isTop()){
            holder1.rela.setVisibility(View.GONE);
        }else{
            holder1.rela.setVisibility(View.VISIBLE);
        }
        if (node.getIcon() == -1) {
            holder1.mItemRightImage.setVisibility(View.GONE);
        } else {
            holder1.mItemRightImage.setVisibility(View.VISIBLE);
            holder1.mItemRightImage.setImageResource(node.getIcon());
        }
        holder1.itemGroupNameTv.setText(node.getName());
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        TextView itemGroupNameTv;
        ImageView mItemRightImage;
        LinearLayout linear;
        View viewone;
        RelativeLayout rela;

        public mViewHolder(View itemView) {
            super(itemView);
            itemGroupNameTv = (TextView) itemView.findViewById(R.id.itemGroupNameTv);
            mItemRightImage = (ImageView) itemView.findViewById(R.id.itemRightImage);
            linear = itemView.findViewById(R.id.linear);
            viewone = itemView.findViewById(R.id.viewone);
            rela = itemView.findViewById(R.id.rela);
        }
    }
}

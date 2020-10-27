package com.zhongyagroup.threerecyclerviewdemo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongyagroup.threerecyclerviewdemo.Tree.Node;
import com.zhongyagroup.threerecyclerviewdemo.Tree.TreeHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class TreeRecycleViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;

    /**
     * 存储所有可见的Node
     */
    protected List<Node> mNoder;

    /**
     * 筛选之前mNoder的集合
     */
    private  List<Node> mNoderLoseList = new ArrayList<>();
    /**
     * 筛选之前点击的层级
     */
    private int mNoderLoseTire;

    protected LayoutInflater mInflater;
    /**
     * 储存所有的Node
     */
    protected List<Node> mAllNode;

    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener {
        void onClike(Node node, int postion);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public TreeRecycleViewAdapter(RecyclerView mTree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        mContext = context;
        /**
         * 对所有的Node进行排序
         */
        mAllNode = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        /**
         * 过滤出可见的Node
         */
        mNoder = TreeHelper.filterVisibleNode(mAllNode);
        mInflater = LayoutInflater.from(context);

    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position
     */
    public void expandOrCollapse(int position) {

        Log.d("当前的数据长度是：", mNoder.size() + "");
        for (int i = 0; i < mNoder.size(); i++) {
            if (!mNoder.get(i).isLeaf()) {
                if (i == position) {
                    mNoder.get(i).setExpand(!mNoder.get(i).isExpand());
                } else {
                    Log.d("当前的数据级别是：", i + "...." + position);
                    if (mNoder.get(i).getTier() == mNoder.get(position).getTier()) {
                        mNoder.get(i).setExpand(false);
                    }
                }

            }
        }
        if (!mNoder.get(position).isLeaf()) {
            mNoderLoseList = mNoder;
            mNoderLoseTire = mNoder.get(position).getTier();
            mNoder = TreeHelper.filterVisibleNode(mAllNode);
            Log.d("当前的数据长度2是：", mNoder.size() + "");
            notifyDataSetChanged();// 刷新视图
        }
      /*  Node n = mNoder.get(position);
        if (n != null) {// 排除传入参数错误异常
            if (!n.isLeaf()) {
                n.setExpand(!n.isExpand());
                mNoder = TreeHelper.filterVisibleNode(mAllNode);
                notifyDataSetChanged();// 刷新视图
            }
        }*/
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Node node = mNoder.get(position);
        getBindViewHolder(node, position, holder);

        /**
         * 设置节点点击时，可以展开以及关闭；并且将ItemClick事件继续往外公布
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandOrCollapse(position);
                if (onTreeNodeClickListener != null) {

                    Log.d("当前选择的position是：",mNoderLoseList.get(position).getName() + "..." +position + "..." + (position - (mNoderLoseList.size() - mNoder.size())) + "..." + mNoderLoseList.size() + "..." + mNoder.size());
                  /*  if(mNoderLoseList.size() - mNoder.size()>0){
                        onTreeNodeClickListener.onClike(mNoder.get(position - (mNoderLoseList.size() - mNoder.size())), position- (mNoderLoseList.size() - mNoder.size()));
                    }else{*/
                        onTreeNodeClickListener.onClike(mNoderLoseList.get(position), position);
                  //  }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoder.size();
    }


    public abstract RecyclerView.ViewHolder getCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void getBindViewHolder(Node node, int position, RecyclerView.ViewHolder holder);
}

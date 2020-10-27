package com.zhongyagroup.threerecyclerviewdemo.model;

import com.zhongyagroup.threerecyclerviewdemo.Tree.ThrrNodePid;
import com.zhongyagroup.threerecyclerviewdemo.Tree.TreeNodeId;
import com.zhongyagroup.threerecyclerviewdemo.Tree.TreeNodeLabel;
import com.zhongyagroup.threerecyclerviewdemo.Tree.TreeNodeTier;

import java.io.Serializable;

public class CountryModel implements Serializable {

    @TreeNodeId
    private int mId;

    @ThrrNodePid
    private int mParentId;

    @TreeNodeLabel
    private String mName;

    @TreeNodeTier
    private int tier;

    public CountryModel(int mId, int mParentId, String mName,int tier) {
        this.mId = mId;
        this.mParentId = mParentId;
        this.mName = mName;
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmParentId() {
        return mParentId;
    }

    public void setmParentId(int mParentId) {
        this.mParentId = mParentId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}

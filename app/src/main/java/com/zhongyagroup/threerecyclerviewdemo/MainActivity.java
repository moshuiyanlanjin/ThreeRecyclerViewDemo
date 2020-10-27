package com.zhongyagroup.threerecyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhongyagroup.threerecyclerviewdemo.Adapter.MyAdapter;
import com.zhongyagroup.threerecyclerviewdemo.Adapter.TreeRecycleViewAdapter;
import com.zhongyagroup.threerecyclerviewdemo.Tree.Node;
import com.zhongyagroup.threerecyclerviewdemo.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recy;
    private List<CountryModel> mdata = new ArrayList<>();

    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recy = findViewById(R.id.recy);
        initView();

    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        manager.setAutoMeasureEnabled(true);
        recy.setLayoutManager(manager);
        //确定item的改变不会影响recycleview的宽高时在
        recy.setHasFixedSize(true);
        recy.getItemAnimator().setChangeDuration(300);
        recy.getItemAnimator().setMoveDuration(300);
        addDatas();
        try {
            adapter = new MyAdapter(recy,this,mdata,mdata.size());
            recy.setAdapter(adapter);
            adapter.setOnTreeNodeClickListener(mOnTreeNodeClickListener);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private TreeRecycleViewAdapter.OnTreeNodeClickListener mOnTreeNodeClickListener=new TreeRecycleViewAdapter.OnTreeNodeClickListener() {

        @Override
        public void onClike(Node node, int postion) {
            if (node.isLeaf()) {//最后一层
                Toast.makeText(MainActivity.this, node.getName() + node.isIsxia() + node.getTier(), Toast.LENGTH_SHORT).show();

            }
        }
    };

    private void addDatas() {
        mdata.add(new CountryModel(1,0,"河南",1));
        mdata.add(new CountryModel(2,0,"陕西",1));
        mdata.add(new CountryModel(3,0,"北京",1));
        mdata.add(new CountryModel(4,0,"浙江",1));

        mdata.add(new CountryModel(11,1,"郑州",2));
        mdata.add(new CountryModel(12,1,"南阳",2));
        mdata.add(new CountryModel(13,1,"平顶山",2));
        mdata.add(new CountryModel(14,1,"新乡",2));

        mdata.add(new CountryModel(21,2,"西安",2));
        mdata.add(new CountryModel(22,2,"咸阳",2));
        mdata.add(new CountryModel(23,2,"延安",2));
        mdata.add(new CountryModel(24,2,"汉中",2));

        mdata.add(new CountryModel(31,3,"朝阳",2));
        mdata.add(new CountryModel(32,3,"西城",2));
        mdata.add(new CountryModel(33,3,"东城",2));
        mdata.add(new CountryModel(34,3,"西城",2));

        mdata.add(new CountryModel(41,4,"杭州",2));
        mdata.add(new CountryModel(42,4,"宁波",2));
        mdata.add(new CountryModel(43,4,"嘉兴",2));
        mdata.add(new CountryModel(44,4,"衢州",2));

        mdata.add(new CountryModel(121,12,"淅川",3));
        mdata.add(new CountryModel(122,12,"西峡",3));
        mdata.add(new CountryModel(123,12,"邓州",3));
        mdata.add(new CountryModel(124,12,"方城",3));


        mdata.add(new CountryModel(1211,121,"荆紫关",4));
        mdata.add(new CountryModel(1212,121,"西黄",4));
        mdata.add(new CountryModel(1213,121,"寺湾",4));
        mdata.add(new CountryModel(1214,121,"毛堂",4));

        mdata.add(new CountryModel(12111,1211,"中街村",5));
        mdata.add(new CountryModel(12112,1211,"南街村",5));
        mdata.add(new CountryModel(12113,1211,"北街村",5));
        mdata.add(new CountryModel(12114,1211,"梁湾",5));

    }
}

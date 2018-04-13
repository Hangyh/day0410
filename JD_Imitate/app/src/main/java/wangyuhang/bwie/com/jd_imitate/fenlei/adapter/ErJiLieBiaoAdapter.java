package wangyuhang.bwie.com.jd_imitate.fenlei.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.fenlei.activity.ZiFenLeiXiangQingActivity;
import wangyuhang.bwie.com.jd_imitate.fenlei.bean.ErJiLieBiao;


/**
 * Created by lenovo on 2018/3/9.
 */

public class ErJiLieBiaoAdapter extends BaseExpandableListAdapter {
    Context context;


    List<ErJiLieBiao.DataBean> data;



    public ErJiLieBiaoAdapter(Context context,List<ErJiLieBiao.DataBean> data) {
        this.context = context;

        this.data = data;

    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        gViewHolder gHolder ;
        if (convertView== null) {
            convertView = View.inflate(context, R.layout.erjiliebaiitem1, null);
            gHolder = new gViewHolder();
            gHolder.group_text = (TextView) convertView.findViewById(R.id.text);
            //绑定
            convertView.setTag(gHolder);
        }else {
            gHolder = (gViewHolder) convertView.getTag();
        }
        gHolder.group_text.setText(data.get(groupPosition).getName());
        return convertView;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {
        cViewHolder cViewHolder;
       if(convertView==null){
        convertView = View.inflate(context, R.layout.erjiliebaiitem2, null);
           cViewHolder = new cViewHolder();
           cViewHolder.rlv = (RecyclerView) convertView.findViewById(R.id.rlv);
           convertView.setTag(cViewHolder);
       }else{

           cViewHolder= (cViewHolder) convertView.getTag();
       }
            //绑定
        final List<ErJiLieBiao.DataBean.ListBean> list = data.get(groupPosition).getList();

        cViewHolder.rlv.setLayoutManager(new GridLayoutManager(context,3));

        MyAdaptera myAdaptera=new MyAdaptera(context,list);
        cViewHolder.rlv.setAdapter(myAdaptera);
        myAdaptera.getCidListener(new MyAdaptera.setCid() {
            @Override
            public void OnSuccess(int pscid) {
//                list.get(pscid).getPscid()
                Toast.makeText(context,""+pscid,Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("id",pscid+"");
                intent.putExtras(bundle);
                intent.setClass(context, ZiFenLeiXiangQingActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    //组的优化
    public class gViewHolder{

        private TextView group_text;
    }
    //子条目的优化
    public class cViewHolder{
     private RecyclerView rlv;
    }
}

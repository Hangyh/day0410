package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.bean.ZiLeiBean;
import wangyuhang.bwie.com.jd_imitate.fenlei.activity.ZiFenLeiXiangQingActivity;


/**
 * Created by dell on 2018/3/14.
 */

public class FenLeiYou extends BaseExpandableListAdapter {
    Context context;
    List<ZiLeiBean.DataBean> list;

    public FenLeiYou(Context context, List<ZiLeiBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return  list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        groupViewHolder gViewHolder;

        if (convertView==null){
            gViewHolder = new groupViewHolder();
            convertView = View.inflate(context,R.layout.groupitem,null);
            gViewHolder.group_text = convertView.findViewById(R.id.group_text);
            convertView.setTag(gViewHolder);
        }else{
            gViewHolder = (groupViewHolder) convertView.getTag();
        }
            gViewHolder.group_text.setText(list.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        childViewHolder childViewHolder;

        if (convertView==null){
            childViewHolder = new childViewHolder();
            convertView = View.inflate(context,R.layout.childenitem,null);
            childViewHolder.rlv = convertView.findViewById(R.id.childen_rlv);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (childViewHolder) convertView.getTag();
        }
        List<ZiLeiBean.DataBean.ListBean> list = this.list.get(groupPosition).getList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        MyAdaptera_rl myAdap = new MyAdaptera_rl(list,context);
        childViewHolder.rlv.setLayoutManager(gridLayoutManager);
        myAdap.getCidListener(new MyAdaptera_rl.setCid() {
            @Override
            public void OnSuccess(int pscid) {
                Toast.makeText(context,""+pscid,Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("id",pscid+"");
                intent.putExtras(bundle);
                intent.setClass(context, ZiFenLeiXiangQingActivity.class);
                context.startActivity(intent);
            }
        });



        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    //组的优化
    public class groupViewHolder{

        private TextView group_text;
    }
    //子条目的优化
    public class childViewHolder{
        private RecyclerView rlv;
    }
}

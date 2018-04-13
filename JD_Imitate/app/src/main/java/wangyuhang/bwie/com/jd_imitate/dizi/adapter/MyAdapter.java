package wangyuhang.bwie.com.jd_imitate.dizi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.dizi.bean.DiziBean;


/**
 * Created by dell on 2018/4/12.
 */

public class MyAdapter extends BaseAdapter{
    List<DiziBean.DataBean> list;
    Context context;

    public MyAdapter(List<DiziBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView==null){
            holder = new MyViewHolder();
            convertView = View.inflate(context, R.layout.shezi_item,null);
            holder.radioButton = convertView.findViewById(R.id.rb_shezi);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        long mobile = list.get(position).getMobile();
        String s = String.valueOf(mobile);
        holder.radioButton.setText(list.get(position).getName()+","+list.get(position).getAddr()+","+s);

        return convertView;
    }


    public class MyViewHolder{
        CheckBox radioButton;
    }
}

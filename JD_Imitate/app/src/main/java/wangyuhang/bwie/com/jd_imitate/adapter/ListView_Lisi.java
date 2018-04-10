package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.sql.SqlBean;

/**
 * Created by dell on 2018/4/9.
 */

public class ListView_Lisi extends BaseAdapter{
    List<SqlBean> sele;
    Context context;

    public ListView_Lisi(List<SqlBean> sele, Context context) {
        this.sele = sele;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sele.size();
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

//        ViewHolder holder;

//        if (convertView==null){
//            holder= new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_listview,null);
            TextView textView = convertView.findViewById(R.id.tv_jilu);
        textView.setText(sele.get(position).getName());
//            holder.textView = convertView.findViewById(R.id.tv_lisi);
//            convertView.setTag(holder);
//        }else {
//            holder = (ViewHolder) convertView.getTag();
//        }

//        SqlBean sqlBean = sele.get(position);
//        String name = sqlBean.getName();
//        holder.textView.setText(name);

        return convertView;
    }


//    public class ViewHolder{
//        TextView textView;
//    }
}

package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.bean.GridBean;

/**
 * Created by dell on 2018/3/14.
 */

public class GridAdapter extends BaseAdapter{

    List<GridBean.DataBean> list;
    Context context;

    public GridAdapter(List<GridBean.DataBean> list, Context context) {
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
        ViewHolde holde ;
        if (convertView==null){
            holde = new ViewHolde();
            convertView = View.inflate(context, R.layout.griditem,null);
            holde.textView = convertView.findViewById(R.id.grid_tv);
            holde.imageView = convertView.findViewById(R.id.grid_iv);
            convertView.setTag(holde);
        }else {
            holde = (ViewHolde) convertView.getTag();
        }
        holde.textView.setText(list.get(position).getName());
        Picasso.with(context).load(list.get(position).getIcon()).into(holde.imageView);
        holde.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    public class ViewHolde{
        ImageView imageView;
        TextView textView;
    }
}

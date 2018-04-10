package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.bean.ListBeanList;
import wangyuhang.bwie.com.jd_imitate.bean.XiangQingBean;

/**
 * Created by dell on 2018/3/14.
 */

public class XiangQingAdapter extends RecyclerView.Adapter<XiangQingAdapter.XQViewHolde>{

    List<ListBeanList> listBeen;
    Context context;

    public XiangQingAdapter(List<ListBeanList> listBeen, Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }

    @Override
    public XQViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.xiangqing,parent,false);
        XQViewHolde xqViewHolde = new XQViewHolde(view);
        return xqViewHolde;
    }

    @Override
    public void onBindViewHolder(XQViewHolde holder, int position) {

        holder.textView.setText(listBeen.get(position).getSubhead());
        Picasso.with(context).load(listBeen.get(position).getImages()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    public class XQViewHolde extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public XQViewHolde(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_xiangqing);
            textView = itemView.findViewById(R.id.tv_xiangqing);
        }
    }
}

package wangyuhang.bwie.com.jd_imitate.gwc.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.gwc.bean.GwcBean;

/**
 * Created by dell on 2018/3/30.
 */

public class MyAdapter_Gwc extends RecyclerView.Adapter<MyAdapter_Gwc.MyViewHolder>{

    List<GwcBean.DataBean> list;
    Context context;

    public MyAdapter_Gwc(List<GwcBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.gwc_item,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        List<GwcBean.DataBean.ListBean> list = this.list.get(position).getList();

        if (this.list.size()!=0){
            String images = list.get(0).getImages();
            holder.textView.setText(list.get(0).getTitle());
            holder.textView2.setText(list.get(0).getSubhead());
            if (images.contains("|")){

                String[] split = images.split("\\|");

                Uri uri = Uri.parse(split[0]);

                holder.simpleDraweeView.setImageURI(uri);
            }else{
                Uri uri = Uri.parse(images);
                holder.simpleDraweeView.setImageURI(uri);
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView textView,textView2;
        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.swdv);
            textView = itemView.findViewById(R.id.tv_gwc);
            textView2 = itemView.findViewById(R.id.tv2_gwc);
        }
    }



}

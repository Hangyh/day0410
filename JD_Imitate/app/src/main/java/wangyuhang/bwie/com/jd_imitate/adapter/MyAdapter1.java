package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.bean.SuperBean;


/**
 * Created by dell on 2018/3/12.
 */

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder1>{

    Context context;
    List<SuperBean.MiaoshaBean.ListBeanX> list;

    public MyAdapter1(Context context, List<SuperBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item1,parent,false);

        final MyViewHolder1 myViewHolder1 = new MyViewHolder1(view);

        return myViewHolder1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder1 holder, int position) {

        String images = list.get(position).getImages();

        String[] split = images.split("\\|");

        for (int i  = 0 ;i<split.length;i++){
            Log.i("TAG",split[i]);
//            Picasso.with(context).load(split[i]).into(holder.image);
            holder.textView.setText("￥:"+list.get(position).getPrice());
            Uri uri = Uri.parse(split[i]);
            holder.image.setImageURI(uri);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder{

        SimpleDraweeView image;
        TextView textView;
        public MyViewHolder1(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_price);
            image = itemView.findViewById(R.id.iv2);
        }
    }

}

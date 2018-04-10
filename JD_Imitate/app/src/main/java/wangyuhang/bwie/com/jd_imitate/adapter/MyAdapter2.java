package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.bean.SuperBean;

/**
 * Created by dell on 2018/3/12.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2>{

    Context context;
    List<SuperBean.TuijianBean.ListBean> list1;

    public MyAdapter2(Context context, List<SuperBean.TuijianBean.ListBean> list1) {
        this.context = context;
        this.list1 = list1;
    }



    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);

        final MyViewHolder2 myViewHolder2 = new MyViewHolder2(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = myViewHolder2.getLayoutPosition();
                onItemListenner2.setonItemListenner(layoutPosition);
            }
        });
        return myViewHolder2;
    }

    @Override
    public void onBindViewHolder(MyViewHolder2 holder, int position) {

        String images = list1.get(position).getImages();

        if (images.contains("|")){
            String[] split = images.split("\\|");
                Log.i("TAG",split[0]);
//                Picasso.with(context).load(split[0]).into(holder.imageView);
            Uri uu = Uri.parse(split[0]);
            holder.imageView.setImageURI(uu);
        }else {
//            Picasso.with(context).load(images).into(holder.imageView);
            Uri uri = Uri.parse(images);
            holder.imageView.setImageURI(uri);
        }

        holder.textView.setText(list1.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder{

        SimpleDraweeView imageView;
        TextView textView;

        public MyViewHolder2(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv3);
            textView = itemView.findViewById(R.id.tv2);
        }
    }
    public interface onItemListenner{
        void setonItemListenner(int position);
    }
    onItemListenner onItemListenner2;
    public void setOnItemListenner(onItemListenner onItemListenner){
        onItemListenner2 = onItemListenner;
    }
}

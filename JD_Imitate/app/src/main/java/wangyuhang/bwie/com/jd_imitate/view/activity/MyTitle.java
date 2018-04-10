package wangyuhang.bwie.com.jd_imitate.view.activity;

/**
 * Created by dell on 2018/3/20.
 */

import android.view.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import wangyuhang.bwie.com.jd_imitate.R;

/**
 * Created by dell on 2018/3/14.
 */

public class MyTitle extends LinearLayout {

    public MyTitle(Context context) {
        this(context,null);
    }

    public MyTitle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTitle(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化布局
        LayoutInflater from = LayoutInflater.from(context);
        View inflate = from.inflate(R.layout.title, this, true);

        ImageView sao = inflate.findViewById(R.id.head_sys);
        final EditText viewById = inflate.findViewById(R.id.et);
        viewById.setCursorVisible(false);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewById.setCursorVisible(true);

            }
        });

        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

}

package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseActivity;
import wangyuhang.bwie.com.jd_imitate.bean.UserBean;
import wangyuhang.bwie.com.jd_imitate.bean.XiuGaiBean;
import wangyuhang.bwie.com.jd_imitate.modle.UploadUtil;
import wangyuhang.bwie.com.jd_imitate.presenter.UpdatePresenter;
import wangyuhang.bwie.com.jd_imitate.presenter.UsetPrensenter;
import wangyuhang.bwie.com.jd_imitate.view.UpdateView;
import wangyuhang.bwie.com.jd_imitate.view.UserView;




public class UserActivity extends BaseActivity<UsetPrensenter> implements UserView,UploadUtil.OnUploadProcessListener {
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    private static final int UPLOAD_INIT_PROCESS = 4;//上传初始化
    protected static final int UPLOAD_FILE_DONE = 2;//上传中
    private static final int UPLOAD_IN_PROCESS = 5;//上传文件响应
    private File filepath;
    private ProgressDialog pd;
    private ImageView mIvTou;
    /**
     * 未登录
     */
    private TextView mTvName;
    /**
     * 未登录
     */
    private TextView mTvUname;
    /**
     * 退出登录
     */
    private Button mBtTuichu;
    /**
     * 跳转到商品搜索页
     */
    private Button mBtShopp,bt_xiugai;
    private SharedPreferences sp;
    private UserBean.DataBean data;
    private int uid;
    private String token;


    @Override
    public Context context() {
        return UserActivity.this;
    }

    @Override
    public void userFailData() {

    }

    @Override
    public void userSuccessData(final UserBean userBean) {

        data = userBean.getData();
        final Object nickname = data.getNickname();
        final String username = data.getUsername();
        final int icon = (int) data.getIcon();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvName.setText(username);
                mTvUname.setText(nickname+"");
                mIvTou.setImageResource(icon);
            }
        });
        mIvTou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog builder = new AlertDialog.Builder(UserActivity.this)
                        .setTitle("选择头像")
                        .setPositiveButton("相机", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getCamera();

                            }
                        })
                        .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getPhoto();

                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    public UsetPrensenter getPresenter() {
        return new UsetPrensenter(this);
    }

    @Override
    public void initView() {

        mIvTou = (ImageView) findViewById(R.id.iv_tou);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvUname = (TextView) findViewById(R.id.tv_uname);
        mBtTuichu = (Button) findViewById(R.id.bt_tuichu);
        mBtShopp = (Button) findViewById(R.id.bt_shopp);
        sp = getSharedPreferences("sp",MODE_PRIVATE);

        uid = sp.getInt("uid",0);
        token = sp.getString("token", null);
        presenter.userP(uid);

        mBtTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sp.edit();
                edit.remove("uid");
                finish();
            }
        });

        mBtShopp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,AllActivity.class));
            }
        });

        mTvUname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view2 = View.inflate(UserActivity.this,R.layout.alert,null);
//
               new AlertDialog.Builder(UserActivity.this)
                .setTitle("修改昵称")
                .setView(view2)
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//
                        EditText viewById = view2.findViewById(R.id.et_name);
//
                     final  String s2 = viewById.getText().toString();
                      Log.d("++++++++++++",s2+"");

                        UpdatePresenter updatePresenter = new UpdatePresenter(new UpdateView() {
                            @Override
                            public void xiugaiFail() {

                            }

                            @Override
                            public void xiugaiSuccess(final XiuGaiBean xiuGaiBean) {

                                presenter.userP(uid);
                                Log.i("++++++++++++++++++",xiuGaiBean.getMsg());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UserActivity.this, ""+xiuGaiBean.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public Context context() {
                                return UserActivity.this;
                            }
                        });
                        updatePresenter.updateP("12574",s2,token);

                            }
                })
                       .create()
                .show();


//                        .setTitle("输入修改的内容")
////                        .setView()
//                        .show();

            }
        });
    }


    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);
    }
    private void getCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "img.jpg")));
        startActivityForResult(intent, CAMERA_REQUEST);
    }
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }
    private void toUploadFile() {
        pd = ProgressDialog.show(this, "", "正在上传文件...");
        pd.show();
        String fileKey = "avatarFile";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        uploadUtil.setOnUploadProcessListener(UserActivity.this); //设置监听器监听上传状态

        Map<String, String> params = new HashMap<String, String>();//上传map对象
        params.put("uid", data.getUid()+"");
        uploadUtil.uploadFile(filepath, data.getAppkey(), "https://www.zhaoapi.cn/file/upload", params);
        Toast.makeText(this, "上传成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUploadDone(int responseCode, String message) {
        pd.dismiss();
        Message msg = Message.obtain();
        msg.what = UPLOAD_FILE_DONE;
        msg.arg1 = responseCode;
        msg.obj = message;
    }

    @Override
    public void onUploadProcess(int uploadSize) {
        Message msg = Message.obtain();
        msg.what = UPLOAD_IN_PROCESS;
        msg.arg1 = uploadSize;
    }

    @Override
    public void initUpload(int fileSize) {
        //准备上传
        Message msg = Message.obtain();
        msg.what = UPLOAD_INIT_PROCESS;
        msg.arg1 = fileSize;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1://-1表示拍照成功
                        File file = new File(Environment.getExternalStorageDirectory()
                                + "/img.jpg");//保存图片
                        if (file.exists()) {
                            //对相机拍照照片进行裁剪
                            photoClip(Uri.fromFile(file));
                        }
                }
                break;

            case PHOTO_REQUEST://从相册取
                if (data != null) {
                    Uri uri = data.getData();
                    //对相册取出照片进行裁剪
                    photoClip(uri);

                }
                break;
            case PHOTO_CLIP:
                //完成
                if (data != null) {

                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        try {
                            //获得图片路径
                            filepath = UploadUtil.saveFile(photo, Environment.getExternalStorageDirectory().toString(), "img.jpg");
                            //上传照片
                            toUploadFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //上传完成将照片写入imageview与用户进行交互
                        mIvTou.setImageBitmap(photo);
                    }
                }
                break;
        }
    }


}

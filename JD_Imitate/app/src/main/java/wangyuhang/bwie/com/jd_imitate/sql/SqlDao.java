package wangyuhang.bwie.com.jd_imitate.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2018/4/9.
 */

public class SqlDao {


    private final SqlOpenHelp sqlOpenHelp;
    private final SQLiteDatabase db;

    public SqlDao(Context context) {
        sqlOpenHelp = new SqlOpenHelp(context);
        db = sqlOpenHelp.getWritableDatabase();
    }


    public void add(String name){

        ContentValues values = new ContentValues();
        values.put("name",name);
        db.insert("aaa",null,values);
//        db.close();
    }

    public List<SqlBean> sele(){
//
        List<SqlBean> list = new ArrayList<>();
//
        Cursor cursor = db.rawQuery("select * from aaa", null);

        while (cursor.moveToNext()){

            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add( new SqlBean(name));
        }
        cursor.close();
//        db.close();
        return list;
    }

    public void delete(){
        db.execSQL("delete from aaa");
//        db.close();
    }
}

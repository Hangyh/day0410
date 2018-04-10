package wangyuhang.bwie.com.jd_imitate.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by dell on 2018/4/9.
 */

public class SqlOpenHelp extends SQLiteOpenHelper{
    public SqlOpenHelp(Context context) {
        super(context, "sele.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table aaa(id integer primary key autoincrement," +
                "name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

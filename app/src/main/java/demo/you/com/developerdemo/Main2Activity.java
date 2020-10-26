package demo.you.com.developerdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity  {
    private EditText name;
    private EditText pass;
    private String TAG = getClass().getName();

    MyOpenHelper helper;

    public Main2Activity() {
        helper = new MyOpenHelper(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);


    }

    public void sure_clicked(View view) {
        query();
    }



    protected void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,
                data);
        switch (resultCode) {
            case RESULT_OK:
                String str = data.getExtras().getString("data");
                String str2 = data.getExtras().getString("data2");
                Toast.makeText(this, "用户名：" + str + "密码：" + str2, Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    public void false_clicked(View view) {//注册
        Intent it = new Intent();
        it.setClass(this, Main3Activity.class);
        startActivityForResult(it, 1);

    }

    public void add(String name, String pass) {
        int register = query2();
        if (register == 1) {
            Toast.makeText(this, "该账号已被注册", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.execSQL("insert into info(name,pass) values(?,?); ", new Object[]{name, pass});
            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(this, "注册失败", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void query() {

        SQLiteDatabase db = helper.getWritableDatabase();
//因为execSQ没有返回值，不适合做查询操作
        String sql = "select * from info;";
        Cursor cursor = db.rawQuery(sql, null);//执行查询操作

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {//获取每一行每一个字段的值
                int pid = cursor.getInt(0);
                String pname = cursor.getString(1);
                String pphone = cursor.getString(2);
                if (pname.equals(name.getText().toString())) {
                    if (pname.equals(name.getText().toString()) && pphone.equals(pass.getText().toString())) {
                        String aaa = "";
                        String bbb = "";
                        aaa = name.getText().toString();
                        bbb = pass.getText().toString();

                        Intent it = new Intent();
                        it.setClass(this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("data", aaa);
                        bundle.putString("data2", bbb);
                        it.putExtras(bundle);
                        startActivityForResult(it, 0);
                        Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
                        return;

                    }
                    Toast.makeText(this, "账号或密码不正确", Toast.LENGTH_LONG).show();
                    return;
                }
                Log.i(TAG, "**************************************");
                Log.i(TAG, Integer.toString(pid));
                Log.i(TAG, pname);
                Log.i(TAG, pphone);
            }


        }
        Toast.makeText(this, "您还没有注册，请注册", Toast.LENGTH_LONG).show();
        return;
    }

    public int query2() {

        SQLiteDatabase db = helper.getWritableDatabase();
//因为execSQ没有返回值，不适合做查询操作
        String sql = "select * from info;";
        Cursor cursor = db.rawQuery(sql, null);//执行查询操作

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {//获取每一行每一个字段的值
                int pid = cursor.getInt(0);
                String pname = cursor.getString(1);
                String pphone = cursor.getString(2);
                if (pname.equals(name.getText().toString())) {

                    return 1;

                }

            }
        }
        return 2;
    }
    public void update(String name,String pass){
        if(query2()==2)
        {
            Toast.makeText(this, "该用户不存在", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("update info set pass=? where name=?;",new Object[]{pass,name});
        db.close();

    }

  /*  public void change_pass(View view) {
        String name2=name.getText().toString();
        String pass2=pass.getText().toString();
        update(name2,pass2);



    }
    public void delete(String name){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from info where name=?;",new Object[]{name});
        db.close();
    }

    public void delete_admin(View view) {
        String name2=name.getText().toString();
        if(query2()==2)
        {
            Toast.makeText(this, "该用户不存在", Toast.LENGTH_LONG).show();
            return;
        }
        delete(name2);
    }*/
}
class MyOpenHelper extends SQLiteOpenHelper {


    public MyOpenHelper(Context context) {
        super(context, "userinfo2.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),pass varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
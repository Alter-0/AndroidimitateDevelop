package demo.you.com.developerdemo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private EditText name;
    private EditText pass;
    private EditText pass2;
    private String TAG = getClass().getName();

    MyOpenHelper helper;
    public Main3Activity() {
        helper = new MyOpenHelper(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

            name = (EditText) findViewById(R.id.name2);
            pass = (EditText) findViewById(R.id.pass2);
        pass2=(EditText) findViewById(R.id.pass3);


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

    public void makesure(View view) {//注册
        if(pass.getText().toString().equals(pass2.getText().toString()))
        {    add(name.getText().toString(), pass.getText().toString());}
        else{Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_LONG).show();}

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

    public void makesure2(View view) {
        finish();
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



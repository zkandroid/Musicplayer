package com.example.administrator.mymediaplayer.activity;

import android.content.ContentValues;
import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    import com.example.administrator.mymediaplayer.R;
    import com.example.administrator.mymediaplayer.db.Mysql;
    import com.example.administrator.mymediaplayer.ui.MyButton;

    public class MainActivity extends AppCompatActivity {
        private Mysql mysql;
    private MyButton creat_jbxx;
    private Button creat_gzjl;
    private EditText name;
    private TextView na1;
    private TextView sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        na1= (TextView) findViewById(R.id.xiangmu);

        final String nam=name.getText().toString();


        mysql=new Mysql(this,"Resume.db",null,1);
        creat_jbxx= (MyButton) findViewById(R.id.creat_jbxx);
        creat_jbxx.setTextViewText("基本信息");
        creat_jbxx.setImageResource(R.drawable.me);
        creat_gzjl= (Button) findViewById(R.id.job);
        creat_jbxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= mysql.getWritableDatabase();
                    ContentValues values = new ContentValues();
                values.put("name",nam);
                db.insert("Resume",null,values);
                }
        });
        creat_gzjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mysql.getWritableDatabase(); // 查询Book表中所有的数据
                     Cursor cursor=db.query("Resume",null,null,null,null,null,null);
                if(cursor.moveToFirst()) {
                    do {
                        String na = cursor.getString(0);
                        sex.setText(na);
                    }while (cursor.moveToNext());

                }
                cursor.close();

            }
        });

    }

}

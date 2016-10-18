package com.example.administrator.firstapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button letsgo,setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        letsgo=(Button)findViewById(R.id.letsgo);
        setting=(Button)findViewById(R.id.setting);
        letsgo.setOnClickListener(this);
        setting.setOnClickListener(this);
    }
    //打开对话框
    public void openDialog(String tile,String message){
        new AlertDialog.Builder(this).setTitle(tile).setMessage(message).setPositiveButton("关闭",null).show();
    }

    @Override
    public void onClick(View v) {
        Intent  intent=new Intent();
        switch (v.getId()){
            case R.id.letsgo:
                intent.setClass(this,MyTab.class);
                startActivity(intent);
                finish();
                break;
            case R.id.setting:
                 intent.setClass(this,Setting.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

    }

    //添加菜单

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,1,"帮助").setIcon(R.drawable.menu01);
        menu.add(0,2,2,"Q&A").setIcon(R.drawable.menu01);
        menu.add(0,3,3,"退出").setIcon(R.drawable.menu01);
        menu.add(0,4,4,"关于").setIcon(R.drawable.menu01);
        /* add()方法的四个参数，依次是：         *
        * 1、组别，如果不分组的话就写Menu.NONE         *
        * 2、Id，这个很重要，Android根据这个Id来确定不同的菜单         *
        * 3、顺序，那个菜单现在在前面由这个参数的大小决定         *
        * 4、文本，菜单的显示文本*/
        return super.onCreateOptionsMenu(menu);
    }
    //设置菜单事件

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                openDialog("帮助","主要作用是帮助你快速设置好行程");
                break;
            case 2:
                openDialog("Q&A","要是不小心把提醒的对话框不正确关掉，铃声或振动");
                break;
            case 3:
                finish();
                break;
            case 4:
                openDialog("关于","此应用主要针对老师同学");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

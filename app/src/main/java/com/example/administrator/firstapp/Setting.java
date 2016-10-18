package com.example.administrator.firstapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.firstapp.db.MyDB;

public class Setting extends Activity {

    Intent intent;
    private Button addStudyPlan;
    private Button deleteAllCoursesAndAlarms;
    private Button deleteAllWeekendAndAlarms;
    private Button deleteAllPlanAndAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        setTitle("设置");

        addStudyPlan = (Button) findViewById(R.id.addstudyplan);
        deleteAllCoursesAndAlarms = (Button) findViewById(R.id.deleteAllCoursesAndAlarms);
        deleteAllWeekendAndAlarms = (Button) findViewById(R.id.deleteAllWeekendAndAlarms);
        deleteAllPlanAndAlarm = (Button) findViewById(R.id.deleteAllPlanAndAlarms);

        addStudyPlan.setOnClickListener(new MyButtonOnClickListener());
        deleteAllCoursesAndAlarms.setOnClickListener(new MyButtonOnClickListener());
        deleteAllWeekendAndAlarms.setOnClickListener(new MyButtonOnClickListener());
        deleteAllPlanAndAlarm.setOnClickListener(new MyButtonOnClickListener());


    }

    // 添加菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(0,1,1, "退出").setIcon(R.drawable.menu01);
        menu.add(0,2,2, "返回首页").setIcon(R.drawable.menu01);
        menu.add(0,3,3, "行程").setIcon(R.drawable.menu01);

        return super.onCreateOptionsMenu(menu);
    }

    // 设置菜单事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case 1:
                finish();
                break;
            case 2:
                finish();
                intent = getIntent();
                intent.setClass(Setting.this, MainActivity.class);
                Setting.this.startActivity(intent);
                break;
            case 3:
                finish();
                intent = getIntent();
                intent.setClass(Setting.this, MyTab.class);
                Setting.this.startActivity(intent);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // 打开确定删除对话框
    public void openIsConfirmDialog(final int btId, String title, String content) {

        intent = getIntent();

        new AlertDialog.Builder(this).setTitle(title).setMessage(content)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    MyDB db = new MyDB(Setting.this);
                    Cursor cursor;
                    int id;
                    int pno;
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (btId) {

                            case R.id.deleteAllCoursesAndAlarms:
                                cursor = db.queryWeekCourses();
                                if(cursor.getCount() > 0){
                                    while (cursor.moveToNext()) {
                                        id = cursor.getInt(cursor.getColumnIndex("id"));
                                        db.deleteCourseById(id);
                                    }
                                    Toast.makeText(Setting.this, "删除星期一~星期五所有行程和闹钟成功！", Toast.LENGTH_LONG).show();
                                }
                                break;
                            case R.id.deleteAllWeekendAndAlarms:
                                cursor = db.queryWeekendCourses();
                                if(cursor.getCount() > 0){
                                    while (cursor.moveToNext()) {
                                        id = cursor.getInt(cursor.getColumnIndex("id"));
                                        db.deleteCourseById(id);
                                    }
                                    Toast.makeText(Setting.this, "删除周末行程和闹钟成功！", Toast.LENGTH_LONG).show();
                                }
                                break;
                            case R.id.deleteAllPlanAndAlarms:
                                cursor = db.queryAllPlans();
                                if(cursor.getCount() > 0){
                                    while (cursor.moveToNext()) {
                                        pno = cursor.getInt(cursor.getColumnIndex("pno"));
                                        db.deletePlan(pno);
                                    }
                                    Toast.makeText(Setting.this, "删除所有计划同时删除闹钟成功！", Toast.LENGTH_LONG).show();
                                }
                                break;

                            default:
                                break;
                        }
                        cursor.close();
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    class MyButtonOnClickListener implements View.OnClickListener {

        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.addstudyplan:
                    intent = getIntent();
                    intent.setClass(Setting.this, MyTab.class);
                    Setting.this.startActivity(intent);
                    finish();
                    break;
                case R.id.deleteAllCoursesAndAlarms:
                    openIsConfirmDialog(R.id.deleteAllCoursesAndAlarms, "确定\"删除星期一~星期五所有行程同时删除闹钟\" ?" , "按确定之后，所有星期一~星期五的行程和闹钟都不复存在！");
                    break;
                case R.id.deleteAllWeekendAndAlarms:
                    openIsConfirmDialog(R.id.deleteAllWeekendAndAlarms, "确定\"删除周末所有行程同时删除闹钟\" ?", "按确定之后，所有周末的行程和闹钟都不复存在！");
                    break;
                case R.id.deleteAllPlanAndAlarms:
                    openIsConfirmDialog(R.id.deleteAllPlanAndAlarms, "确定\"删除所有计划同时删除闹钟\" ?", "按确定之后，所有计划和闹钟都不复存在！");
                    break;

                default:
                    break;
            }
        }

    }

}


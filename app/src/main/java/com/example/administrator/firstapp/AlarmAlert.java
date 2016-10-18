package com.example.administrator.firstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AlarmAlert extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        intent = getIntent();
        String[] cInfo = intent.getStringArrayExtra("cInfo");

//		System.out.println(cInfo.length);
        if(cInfo.length > 4){
            String week = cInfo[0];
            String whichLesson = cInfo[1];
            String cName = cInfo[2];
            if(cName.equals("有课")){
                cName = "";
            }
            String address = cInfo[3];
            String startTime = cInfo[4];
            String endTime = cInfo[5];
            String aheadTime = cInfo[6];
//		    String isRemind = cInfo[7];
//		    final String isRemindByVibrato = cInfo[8];
//		    final String isRemindByRing = cInfo[9];
            final String teacher = cInfo[10];

            new AlertDialog.Builder(AlarmAlert.this)
                    .setTitle("还有 "+aheadTime+" 分钟哦，准备好了吗？")
                    .setMessage("星期"+week+"  "+whichLesson+"\n"+
                            "课程名称："+cName+"\n"+
                            "上课地点："+address+"\n"+
                            "课堂时间："+startTime+"-"+endTime+"\n"+
                            "任课老师："+teacher)
                    .setPositiveButton("关掉提醒",
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int whichButton)
                                {
                                    AlarmAlert.this.finish();
                                }
                            })
                    .setNegativeButton("请假",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    AlarmAlert.this.finish();
                                    intent.setClass(AlarmAlert.this, SendMessage.class);
                                    intent.putExtra("SMScontent", teacher);
                                    AlarmAlert.this.startActivity(intent);
                                }
                            })
                    .show();
        }
        else{
            String content = cInfo[0];
            String remindTime = cInfo[1];
//			final String isRemindByVibrato = cInfo[2];
//			final String isRemindByRing = cInfo[3];

            new AlertDialog.Builder(AlarmAlert.this)
                    .setTitle("计划提醒")
                    .setMessage(content+"\n"+remindTime)
                    .setPositiveButton("关掉提醒",new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            AlarmAlert.this.finish();
                        }
                    }).show();

        }

    }

}

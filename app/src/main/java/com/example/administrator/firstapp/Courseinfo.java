package com.example.administrator.firstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;

import com.example.administrator.firstapp.db.MyDB;
import com.example.administrator.firstapp.model.Course;
import com.example.administrator.firstapp.util.Base;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Courseinfo extends AppCompatActivity {

    Intent intent;
    private ListView courseinfo;
    private Button submit;
    private Button cancel;

    private TimePicker startTimePicker;
    private TimePicker endTimePicker;
    private RadioButton tp_single;
    private RadioButton tp_couple;
    private RadioButton tp_singleAndCouple;

    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
    private String foreSetStartHour;
    private String foreSetStartMinute;
    private String foreSetEndHour;
    private String foreSetEndMinute;

    private String strPeriod;
    private String strNowIsSingerOrCouple;
    private int singerOrCouple = 0;
    private String strRemindTime = "10";
    private String id;
    private String strWeekAndClassNumber;
    private String strWeek;
    private String strWhichLesson;
    private String strCourseName;
    private String strAddress;
    private String strIsRemind;
    private String strIsRemindByVibrato;
    private String strIsRemindByRing;
    private String strTeacher;
    private String strSubmitDate;

    private int intLesson;

    private View localView;

    final int ITEMID_COURSE_NAME = 0;
    final int ITEMID_ADDRESS = 1;
    final int ITEMID_TIME = 2;
    final int ITEMID_REMIND_TIME = 3;
    final int ITEMID_IS_REMIND = 4;
    final int ITEMID_IS_REMIND_VIBRATO = 5;
    final int ITEMID_IS_REMIND_RING = 6;
    final int ITEMID_TEACHER = 7;

    boolean flag = false;

    private String weekNumber;
    private String whichLesson;

    private Base base;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courseinfo);

        base = new Base();

        submit = (Button) findViewById(R.id.submit);
        cancel = (Button) findViewById(R.id.cancel);
        courseinfo = (ListView) findViewById(R.id.courseinfo);

        intent = getIntent();

        id = intent.getStringExtra("id");
        if(id.equals("")){// 新建对话框进入
            strWeekAndClassNumber = intent.getStringExtra("week_classnumber");
            setTitle("星期"+strWeekAndClassNumber+"节");
            strCourseName = intent.getStringExtra("courseName");
            strAddress = intent.getStringExtra("address");
            strPeriod = intent.getStringExtra("period");
            strIsRemind = "是";
            strIsRemindByVibrato = "是";
            strIsRemindByRing = "是";
            strTeacher = "";

            String strsWCNumber[] = strWeekAndClassNumber.split("  ");
            weekNumber = strsWCNumber[0];
            System.out.println(weekNumber);
            whichLesson = strsWCNumber[1];
            String strsLesson[] = whichLesson.split("-");
            intLesson = Integer.parseInt(strsLesson[0]);

            ArrayList<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();

            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("标题", "课程名称");
            item.put("说明", strCourseName);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "上课地点");
            item.put("说明", strAddress);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "时间（"+strPeriod+"）");
            item.put("说明", foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "提醒时间");
            item.put("说明", "提前"+strRemindTime+"分钟");
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "启动提醒");
            item.put("说明", strIsRemind);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "提醒震动");
            item.put("说明", strIsRemindByVibrato);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "提醒铃声");
            item.put("说明", strIsRemindByRing);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "任课老师");
            item.put("说明", strTeacher);
            listItem.add(item);

            SimpleAdapter adapter = new SimpleAdapter(this, listItem,
                    android.R.layout.simple_list_item_2, new String[] {"标题","说明"},
                    new int[] {android.R.id.text1, android.R.id.text2 });

            courseinfo.setAdapter(adapter);

            courseinfo.setOnItemClickListener(new MyItemClickListener());

            useExternalLayoutId();

            setInitTime(intLesson);
            saveForeSetTime();// 初始化前一次操作的时间
            updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," + foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);

        }else{// 编辑对话框进入

            MyDB db = new MyDB(this);
            Course c = db.queryCourseByID(Integer.parseInt(id));

            int intStartMinute = Integer.parseInt(c.getStartTime().split(":")[1]);
            int intRemindMinute = Integer.parseInt(c.getRemindTime().split(":")[1]);
            int intAheadMinute;
            if(intStartMinute < intRemindMinute){
                intAheadMinute = 60 + intStartMinute - intRemindMinute;
            }else{
                intAheadMinute = intStartMinute - intRemindMinute;
            }

            strWeek = c.getWeek();
            strWhichLesson = c.getWhichLesson();
            strWeekAndClassNumber = strWeek + "  " + strWhichLesson;
            setTitle("星期"+strWeekAndClassNumber+"节");
            strCourseName = c.getcName();
            if (strCourseName.equals("有课")) {
                strCourseName = "";
            }
            strAddress = c.getAddress();
            strTeacher = c.getTeacher();
            String period  = c.getPeriod();
            if(period.equals("0")){
                strPeriod = "单双周";
            }else if(period.equals("11") || period.equals("12")){
                strPeriod = "单周";
            }else if(period.equals("22") || period.equals("21")){
                strPeriod = "双周";
            }
            System.out.println("period："+period);
            System.out.println("period："+strPeriod);
            startHour = c.getStartTime().split(":")[0];
            startMinute= c.getStartTime().split(":")[1];
            endHour = c.getEndTime().split(":")[0];
            endMinute = c.getEndTime().split(":")[1];
            strRemindTime = intAheadMinute+"";
            if(c.isRemind()){
                strIsRemind = "是";
            }else{
                strIsRemind = "否";
            }
            if(c.isRemindByVibrato()){
                strIsRemindByVibrato = "是";
            }else{
                strIsRemindByVibrato = "否";
            }
            if(c.isRemindByRing()){
                strIsRemindByRing = "是";
            }else{
                strIsRemindByRing = "否";
            }

            ArrayList<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();

            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("标题", "课程名称");
            item.put("说明", strCourseName);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "上课地点");
            item.put("说明", strAddress);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "时间（"+strPeriod+"）");
            item.put("说明", startHour+":"+startMinute+"-"+endHour+":"+endMinute);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "提醒时间");
            item.put("说明", "提前"+strRemindTime+"分钟");
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "启动提醒");
            item.put("说明", strIsRemind);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "提醒震动");
            item.put("说明", strIsRemindByVibrato);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "提醒铃声");
            item.put("说明", strIsRemindByRing);
            listItem.add(item);
            item = new HashMap<String, Object>();
            item.put("标题", "任课老师");
            item.put("说明", strTeacher);
            listItem.add(item);

            SimpleAdapter adapter = new SimpleAdapter(this, listItem,
                    android.R.layout.simple_list_item_2, new String[] {"标题","说明"},
                    new int[] {android.R.id.text1, android.R.id.text2 });

            courseinfo.setAdapter(adapter);
            courseinfo.setOnItemClickListener(new MyItemClickListener());

            useExternalLayoutId();
            saveForeSetTime();

        }
        submit.setOnClickListener(new MyButtonListener());
        cancel.setOnClickListener(new MyButtonListener());

    }

    // 保存前一次操作的时间，初始值为默认的时间值
    public void saveForeSetTime() {
        foreSetStartHour = startHour;
        foreSetStartMinute = startMinute;
        foreSetEndHour = endHour;
        foreSetEndMinute = endMinute;
    }

    // 初始化每个上课时间段的时间
    public void initFirstClassTime() {
        startHour = base.formateTime(9);
        startMinute = base.formateTime(0);
        endHour = base.formateTime(10);
        endMinute = base.formateTime(20);
    }
    public void initSecondClassTime() {
        startHour = base.formateTime(10);
        startMinute = base.formateTime(30);
        endHour = base.formateTime(11);
        endMinute = base.formateTime(50);
    }
    public void initThirdClassTime() {
        startHour = base.formateTime(12);
        startMinute = base.formateTime(30);
        endHour = base.formateTime(13);
        endMinute = base.formateTime(50);
    }
    public void initFourthClassTime() {
        startHour = base.formateTime(14);
        startMinute = base.formateTime(0);
        endHour = base.formateTime(15);
        endMinute = base.formateTime(20);
    }
    public void initFifthClassTime() {
        startHour = base.formateTime(15);
        startMinute = base.formateTime(30);
        endHour = base.formateTime(16);
        endMinute = base.formateTime(50);
    }
    public void initSixthClassTime() {
        startHour = base.formateTime(17);
        startMinute = base.formateTime(0);
        endHour = base.formateTime(18);
        endMinute = base.formateTime(20);
    }
    public void initSeventhClassTime() {
        startHour = base.formateTime(19);
        startMinute = base.formateTime(0);
        endHour = base.formateTime(20);
        endMinute = base.formateTime(20);
    }
    public void initEighthClassTime() {
        startHour = base.formateTime(20);
        startMinute = base.formateTime(30);
        endHour = base.formateTime(21);
        endMinute = base.formateTime(50);
    }

    // 执行初始时间值的设置
    public void setInitTime(int i) {

        switch (i) {
            case 1:
                initFirstClassTime();
                updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," +foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
                saveForeSetTime();// 改变前一次操作的时间记录并保存
                break;
            case 3:
                initSecondClassTime();
                updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," + foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
                saveForeSetTime();// 改变前一次操作的时间记录并保存
                break;
            case 5:
                initThirdClassTime();
                updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," + foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
                saveForeSetTime();// 改变前一次操作的时间记录并保存
                break;
            case 7:
                initFourthClassTime();
                updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," + foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
                saveForeSetTime();// 改变前一次操作的时间记录并保存
                break;
            case 9:
                initFifthClassTime();
                updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," + foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
                saveForeSetTime();// 改变前一次操作的时间记录并保存
                break;
            case 11:
                initSixthClassTime();
                updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," + foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
                saveForeSetTime();// 改变前一次操作的时间记录并保存
                break;
            case 13:
                initSeventhClassTime();
                updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," + foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
                saveForeSetTime();// 改变前一次操作的时间记录并保存
                break;
            case 15:
                initEighthClassTime();
                updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," + foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);
                saveForeSetTime();// 改变前一次操作的时间记录并保存
                break;

            default:
                break;
        }
    }

    // 引用该Activity的Layout布局之外的自定义的时间选择器布局
    public void useExternalLayoutId() {
        localView = LayoutInflater.from(this).inflate(R.layout.timepicker, null);

        startTimePicker = (TimePicker)localView.findViewById(R.id.starttimepicker);
        startTimePicker.setIs24HourView(true);

        endTimePicker = (TimePicker)localView.findViewById(R.id.endtimepicker);
        endTimePicker.setIs24HourView(true);

        tp_single = (RadioButton) localView.findViewById(R.id.tp_single);
        tp_couple = (RadioButton) localView.findViewById(R.id.tp_couple);
        tp_singleAndCouple = (RadioButton) localView.findViewById(R.id.tp_singleandcouple);
    }

    // 启动编辑的Dialog
    public EditText openEidtDialog(int id, String title, String details) {
        EditText et = null;
        switch (id) {
            case ITEMID_COURSE_NAME:
                et = new EditText(this);
                et.setHint(details);
                new AlertDialog.Builder(this).setTitle("编辑"+title).setView(et).setPositiveButton("确定", otherDialogButtonListener(ITEMID_COURSE_NAME, et))
                        .setNegativeButton("取消", otherDialogButtonListener(ITEMID_COURSE_NAME, et)).show();
                break;
            case ITEMID_ADDRESS:
                et = new EditText(this);
                et.setHint(details);
                new AlertDialog.Builder(this).setTitle("编辑"+title).setView(et).setPositiveButton("确定", otherDialogButtonListener(ITEMID_ADDRESS, et))
                        .setNegativeButton("取消", otherDialogButtonListener(ITEMID_ADDRESS, et)).show();
                break;
            case ITEMID_TEACHER:
                et = new EditText(this);
                et.setHint(details);
                new AlertDialog.Builder(this).setTitle("编辑"+title).setView(et).setPositiveButton("确定", otherDialogButtonListener(ITEMID_TEACHER, et))
                        .setNegativeButton("取消", otherDialogButtonListener(ITEMID_TEACHER, et)).show();
                break;

            default:
                break;

        }
        return et;

    }

    // 启动提醒时间的单选Dialog
    public void openRemindTimeChoiceDialog() {
        final String [] timeDate = new String[] { "10", "20", "30"};
        new AlertDialog.Builder(this).setTitle("设置提醒时间（分钟）").setSingleChoiceItems(timeDate , 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        strRemindTime = timeDate[which];
                        updateList(ITEMID_REMIND_TIME, "提前"+strRemindTime+"分钟");
                        dialog.cancel();
                    }
                }).setNegativeButton("取消", otherDialogButtonListener(ITEMID_REMIND_TIME, null)).show();
    }

    // 判断编辑Dialog中按下确定还是取消按钮
    public void isEnsure(int id, DialogInterface dialog, int which, EditText et) {
        String toSet;
        String hint;
        switch (which) {
            case -1:
                toSet = et.getText().toString();
                hint = et.getHint().toString();
                if(toSet.equals("") && !hint.equals("")){
                    updateList(id, hint);
                }else{
                    updateList(id, toSet);
                }
            case -2:
                dialog.cancel();
                break;

            default:
                break;
        }
    }

    // 编辑Dialog和单选Dialog里的按钮事件
    public android.content.DialogInterface.OnClickListener otherDialogButtonListener(final int id, final EditText et){
        return new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                switch (id) {
                    case ITEMID_COURSE_NAME:
                        isEnsure(ITEMID_COURSE_NAME, dialog, which, et);
                        break;
                    case ITEMID_ADDRESS:
                        isEnsure(ITEMID_ADDRESS, dialog, which, et);
                        break;
                    case ITEMID_REMIND_TIME:
                        isEnsure(ITEMID_ADDRESS, dialog, which, et);
                        break;
                    case ITEMID_TEACHER:
                        isEnsure(ITEMID_TEACHER, dialog, which, et);
                        break;

                    default:
                        break;
                }
            }

        };

    }

    // 启动带有时间选择器的Dialog
    public void openSetTimeDialog(String foreSetStartHour, String foreSetStartMinute,String foreSetEndHour,String foreSetEndMinute) {

        useExternalLayoutId();

        new AlertDialog.Builder(this).setTitle("课堂时间").setView(localView)
                .setPositiveButton("确定", timePickerDialogButtonListener())
                .setNegativeButton("取消", timePickerDialogButtonListener()).show();

        saveForeSetTime();// 保存前一次操作的时间记录

        // 设置每次启动时间选择器的初始值
        startTimePicker.setCurrentHour(Integer.parseInt(foreSetStartHour));
        startTimePicker.setCurrentMinute(Integer.parseInt(foreSetStartMinute));
        endTimePicker.setCurrentHour(Integer.parseInt(foreSetEndHour));
        endTimePicker.setCurrentMinute(Integer.parseInt(foreSetEndMinute));

        if(strPeriod.equals("单周")){
            tp_single.setChecked(true);
        }else if(strPeriod.equals("双周")){
            tp_couple.setChecked(true);
        }else if(strPeriod.equals("单双周")){
            tp_singleAndCouple.setChecked(true);
        }
    }


    // 时间选择器Dialog中的按钮事件
    public android.content.DialogInterface.OnClickListener timePickerDialogButtonListener() {

        return new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case -1:

                        startHour = base.formateTime(startTimePicker.getCurrentHour());
                        startMinute = base.formateTime(startTimePicker.getCurrentMinute());
                        endHour = base.formateTime(endTimePicker.getCurrentHour());
                        endMinute = base.formateTime(endTimePicker.getCurrentMinute());
//					int intStartHour = Integer.parseInt(foreSetStartHour);
//					int intStartMinute = Integer.parseInt(foreSetStartMinute);
//					int intEndHour = Integer.parseInt(foreSetEndHour);
//					int intEndMinute = Integer.parseInt(foreSetEndMinute);

                        saveForeSetTime();// 把上一次的时间记录下来并保存

                        if(tp_single.isChecked()){
                            strPeriod = tp_single.getText().toString();
                        }
                        else if(tp_couple.isChecked()){
                            strPeriod = tp_couple.getText().toString();
                        }
                        else if(tp_singleAndCouple.isChecked()){
                            strPeriod = tp_singleAndCouple.getText().toString();
                        }

                        updateList(ITEMID_TIME, "时间（"+strPeriod+"）" + "," +foreSetStartHour+":"+foreSetStartMinute+"-"+foreSetEndHour+":"+foreSetEndMinute);

                        startTimePicker.setCurrentHour(Integer.parseInt(foreSetStartHour));
                        startTimePicker.setCurrentMinute(Integer.parseInt(foreSetStartMinute));
                        endTimePicker.setCurrentHour(Integer.parseInt(foreSetEndHour));
                        endTimePicker.setCurrentMinute(Integer.parseInt(foreSetEndMinute));

                        break;
                    case -2:
                        dialog.cancel();
                        break;

                    default:
                        break;
                }
            }
        };

    }
    // 更新ListView
    @SuppressWarnings("unchecked")
    public void updateList(int itemId, String toSet)
    {
        ListAdapter la = courseinfo.getAdapter();
        HashMap<String, Object> map = (HashMap<String, Object>)la.getItem(itemId);

        switch (itemId) {
            case ITEMID_COURSE_NAME:
                map.put("说明", toSet);
                break;
            case ITEMID_ADDRESS:
                map.put("说明", toSet);
                break;
            case ITEMID_TIME:
                map.put("标题", toSet.split(",")[0]);
                map.put("说明", toSet.split(",")[1]);
                break;
            case ITEMID_REMIND_TIME:
                map.put("说明", toSet);
                break;
            case ITEMID_IS_REMIND:
                map.put("说明", toSet);
                break;
            case ITEMID_IS_REMIND_VIBRATO:
                map.put("说明", toSet);
                break;
            case ITEMID_IS_REMIND_RING:
                map.put("说明", toSet);
                break;
            case ITEMID_TEACHER:
                map.put("说明", toSet);
                break;

            default:
                break;
        }

        ((SimpleAdapter)la).notifyDataSetChanged();
    }

    // 列表事件内部类
    class MyItemClickListener implements AdapterView.OnItemClickListener {

        @SuppressWarnings("unchecked")
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO Auto-generated method stub
            ListAdapter la = (ListAdapter) parent.getAdapter();;
            HashMap<String, Object> map;
            String infoTitle;
            String infoDetails;
            switch (position) {
                case ITEMID_COURSE_NAME:
                    map = (HashMap<String, Object>)la.getItem(ITEMID_COURSE_NAME);
                    infoTitle = map.get("标题").toString();
                    infoDetails = map.get("说明").toString();
                    openEidtDialog(ITEMID_COURSE_NAME, infoTitle, infoDetails);
                    break;
                case ITEMID_ADDRESS:
                    map = (HashMap<String, Object>)la.getItem(ITEMID_ADDRESS);
                    infoTitle = map.get("标题").toString();
                    infoDetails = map.get("说明").toString();
                    openEidtDialog(ITEMID_ADDRESS, infoTitle, infoDetails);
                    break;
                case ITEMID_TIME:
                    saveForeSetTime();// 初始化前一次操作的时间值
                    openSetTimeDialog(foreSetStartHour, foreSetStartMinute, foreSetEndHour, foreSetEndMinute);
                    break;
                case ITEMID_REMIND_TIME:
                    openRemindTimeChoiceDialog();
                    break;
                case ITEMID_IS_REMIND:
                    map = (HashMap<String, Object>)la.getItem(ITEMID_IS_REMIND);
                    infoTitle = map.get("标题").toString();
                    infoDetails = map.get("说明").toString();
                    if(infoDetails.equals("否")){
                        infoDetails = "是";
                    }
                    else if(infoDetails.equals("是")){
                        infoDetails = "否";
                    }
                    updateList(ITEMID_IS_REMIND, infoDetails);
                    break;
                case ITEMID_IS_REMIND_VIBRATO:
                    map = (HashMap<String, Object>)la.getItem(ITEMID_IS_REMIND_VIBRATO);
                    infoTitle = map.get("标题").toString();
                    infoDetails = map.get("说明").toString();
                    if(infoDetails.equals("否")){
                        infoDetails = "是";
                    }
                    else if(infoDetails.equals("是")){
                        infoDetails = "否";
                    }
                    updateList(ITEMID_IS_REMIND_VIBRATO, infoDetails);
                    break;
                case ITEMID_IS_REMIND_RING:
                    map = (HashMap<String, Object>)la.getItem(ITEMID_IS_REMIND_RING);
                    infoTitle = map.get("标题").toString();
                    infoDetails = map.get("说明").toString();
                    if(infoDetails.equals("否")){
                        infoDetails = "是";
                    }
                    else if(infoDetails.equals("是")){
                        infoDetails = "否";
                    }
                    updateList(ITEMID_IS_REMIND_RING, infoDetails);
                    break;
                case ITEMID_TEACHER:
                    map = (HashMap<String, Object>)la.getItem(ITEMID_TEACHER);
                    infoTitle = map.get("标题").toString();
                    infoDetails = map.get("说明").toString();
                    openEidtDialog(ITEMID_TEACHER, infoTitle, infoDetails);
                    break;

                default:
                    break;
            }
        }

    }

    // 按钮事件内部类
    class MyButtonListener implements View.OnClickListener {

        private HashMap<String, Object> map;
        private String cName;
        private String startTime;
        private String endTime;
        private int startHour;
        private int startMinute;
        private int remindHour;
        private int remindMinute;
        private int aheadMinute;
        private String remindTime;
        private String address;
        private boolean isRemind = false;;
        private boolean isRemindByVibrato  = false;
        private boolean isRemindByRing  = false;
        private String teacher;
        private String week;
        private String whichLesson;
        private MyDB mydb;
        private Course c;


        @SuppressWarnings("unchecked")
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.submit:

                    final Base base = new Base();
                    ListAdapter la = courseinfo.getAdapter();

                    map= (HashMap<String, Object>)la.getItem(ITEMID_COURSE_NAME);
                    cName = map.get("说明").toString();
                    if(cName.trim().equals("")){
                        cName = "有课";
                    }
                    map= (HashMap<String, Object>)la.getItem(ITEMID_TIME);
                    String startToEnd = map.get("说明").toString();
                    startTime = startToEnd.split("-")[0];
                    endTime = startToEnd.split("-")[1];
                    startHour = Integer.parseInt(startTime.split(":")[0]);
                    startMinute = Integer.parseInt(startTime.split(":")[1]);
                    aheadMinute= Integer.parseInt(strRemindTime);
                    if(aheadMinute > startMinute){
                        if(startHour == 0){
                            remindHour = 23;
                        }else{
                            remindHour = startHour - 1;
                        }
                        remindMinute = startMinute + 60 - aheadMinute;
                    }else{
                        remindHour = startHour;
                        remindMinute = startMinute - aheadMinute;
                    }
                    remindTime = base.formateTime(remindHour) + ":" + base.formateTime(remindMinute);
                    map= (HashMap<String, Object>)la.getItem(ITEMID_ADDRESS);
                    address = map.get("说明").toString();

                    map= (HashMap<String, Object>)la.getItem(ITEMID_IS_REMIND);

                    if(map.get("说明").toString().equals("是")){
                        isRemind = true;
                    }else if(map.get("说明").toString().equals("否")){
                        isRemind = false;
                    }
                    map= (HashMap<String, Object>)la.getItem(ITEMID_IS_REMIND_VIBRATO);

                    if(map.get("说明").toString().equals("是")){
                        isRemindByVibrato = true;
                    }else if(map.get("说明").toString().equals("否")){
                        isRemindByVibrato = false;
                    }
                    map= (HashMap<String, Object>)la.getItem(ITEMID_IS_REMIND_RING);

                    if(map.get("说明").toString().equals("是")){
                        isRemindByRing = true;
                    }else if(map.get("说明").toString().equals("否")){
                        isRemindByRing = false;
                    }
                    map= (HashMap<String, Object>)la.getItem(ITEMID_TEACHER);
                    teacher = map.get("说明").toString();

                    week = strWeekAndClassNumber.split("  ")[0];
                    whichLesson = strWeekAndClassNumber.split("  ")[1];


                    mydb = new MyDB(Courseinfo.this);

                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MARCH)+1;
                    int date = cal.get(Calendar.DAY_OF_MONTH);
                    strSubmitDate = year+"-"+month+"-"+date;

                    if(strPeriod.equals("单周") || strPeriod.equals("双周")){// 设置了单周、或者双周的情况
                        final String strs[] = {"单周", "双周"};

                        new AlertDialog.Builder(Courseinfo.this).setTitle("您设置了 "+strPeriod+" 课程，目前是学期中的单周还是双周？")
                                .setSingleChoiceItems(strs, 0, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                        strNowIsSingerOrCouple = strs[which];
                                        if(strPeriod.equals("单周") && strNowIsSingerOrCouple.equals("单周")){
                                            singerOrCouple = 11;
//						    	System.out.println("1111111111111111111111");
                                        }else if(strPeriod.equals("双周") && strNowIsSingerOrCouple.equals("双周")){
                                            singerOrCouple = 22;
//								System.out.println("2222222222222222222222");
                                        }else if(strPeriod.equals("单周") && strNowIsSingerOrCouple.equals("双周")){
                                            singerOrCouple = 12;
//								System.out.println("12");
                                        }else if(strPeriod.equals("双周") && strNowIsSingerOrCouple.equals("单周")){
                                            singerOrCouple = 21;
//								System.out.println("21");
                                        }
                                        System.out.println("strPeriod:"+strPeriod);
                                        System.out.println("strNowIsSingerOrCouple:"+strNowIsSingerOrCouple);

                                        System.out.println(strSubmitDate);
                                        c = new Course(week, whichLesson, cName, singerOrCouple+"", startTime, endTime, address, remindTime, isRemind, isRemindByVibrato, isRemindByRing, teacher, strSubmitDate);
                                        if(id.equals("")){// 添加课程
                                            mydb.insertCourse(c);
                                        }else{// 更新课程
                                            mydb.updateCourseById(Integer.parseInt(id), c);
                                        }

                                        intent = getIntent();
                                        intent.setClass(Courseinfo.this, MyTab.class);
                                        Courseinfo.this.startActivity(intent);
                                        finish();


                                    }
                                }).setPositiveButton("取消", null)
                                .show();
                    }else{// 设置了单双周的情况

                        singerOrCouple = 0;
                        c = new Course(week, whichLesson, cName, singerOrCouple+"", startTime, endTime, address, remindTime, isRemind, isRemindByVibrato, isRemindByRing, teacher, strSubmitDate);
                        if(id.equals("")){// 添加课程
                            mydb.insertCourse(c);
                        }else{// 更新课程
                            mydb.updateCourseById(Integer.parseInt(id), c);
                        }

                        intent = getIntent();
                        intent.setClass(Courseinfo.this, MyTab.class);
                        Courseinfo.this.startActivity(intent);
                        finish();
                    }

                    break;
                case R.id.cancel:
                    finish();
                    intent.setClass(Courseinfo.this, MyTab.class);
                    Courseinfo.this.startActivity(intent);
                    break;
                default:
                    break;
            }
        }

    }


}


package com.example.administrator.firstapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.administrator.firstapp.db.MyDB;
import com.example.administrator.firstapp.model.Course;
import com.example.administrator.firstapp.model.Plan;
import com.example.administrator.firstapp.util.Base;

public class MyTab extends TabActivity{// 主界面

    private Intent intent;

    private Button monday;
    private Button tuesday;
    private Button wednesday;
    private Button thursday;
    private Button friday;

    private Button first;
    private Button second;
    private Button third;
    private Button fourth;
    private Button fifth;
    private Button sixth;
    private Button seventh;
    private Button eighth;

    private Button mon_first;
    private Button mon_second;
    private Button mon_third;
    private Button mon_fourth;
    private Button mon_fifth;
    private Button mon_sixth;
    private Button mon_seventh;
    private Button mon_eighth;

    private Button tues_first;
    private Button tues_second;
    private Button tues_third;
    private Button tues_fourth;
    private Button tues_fifth;
    private Button tues_sixth;
    private Button tues_seventh;
    private Button tues_eighth;

    private Button wed_first;
    private Button wed_second;
    private Button wed_third;
    private Button wed_fourth;
    private Button wed_fifth;
    private Button wed_sixth;
    private Button wed_seventh;
    private Button wed_eighth;

    private Button thurs_first;
    private Button thurs_second;
    private Button thurs_third;
    private Button thurs_fourth;
    private Button thurs_fifth;
    private Button thurs_sixth;
    private Button thurs_seventh;
    private Button thurs_eighth;

    private Button fri_first;
    private Button fri_second;
    private Button fri_third;
    private Button fri_fourth;
    private Button fri_fifth;
    private Button fri_sixth;
    private Button fri_seventh;
    private Button fri_eighth;

    private EditText dialog_courseName;
    private EditText dialog_address;
    private RadioButton dialog_single;
    private RadioButton dialog_couple;
    private RadioButton dialog_singleAndCouple;

    private View layout;

    private int mon_first_id;
    private int mon_second_id;
    private int mon_third_id;
    private int mon_fourth_id;
    private int mon_fifth_id;
    private int mon_sixth_id;
    private int mon_seventh_id;
    private int mon_eighth_id;

    private int tues_first_id;
    private int tues_second_id;
    private int tues_third_id;
    private int tues_fourth_id;
    private int tues_fifth_id;
    private int tues_sixth_id;
    private int tues_seventh_id;
    private int tues_eighth_id;

    private int wed_first_id;
    private int wed_second_id;
    private int wed_third_id;
    private int wed_fourth_id;
    private int wed_fifth_id;
    private int wed_sixth_id;
    private int wed_seventh_id;
    private int wed_eighth_id;

    private int thurs_first_id;
    private int thurs_second_id;
    private int thurs_third_id;
    private int thurs_fourth_id;
    private int thurs_fifth_id;
    private int thurs_sixth_id;
    private int thurs_seventh_id;
    private int thurs_eighth_id;

    private int fri_first_id;
    private int fri_second_id;
    private int fri_third_id;
    private int fri_fourth_id;
    private int fri_fifth_id;
    private int fri_sixth_id;
    private int fri_seventh_id;
    private int fri_eighth_id;

    private String mon_first_cName = "";
    private String mon_second_cName = "";
    private String mon_third_cName = "";
    private String mon_fourth_cName = "";
    private String mon_fifth_cName = "";
    private String mon_sixth_cName = "";
    private String mon_seventh_cName = "";
    private String mon_eighth_cName = "";

    private String tues_first_cName = "";
    private String tues_second_cName = "";
    private String tues_third_cName = "";
    private String tues_fourth_cName = "";
    private String tues_fifth_cName = "";
    private String tues_sixth_cName = "";
    private String tues_seventh_cName = "";
    private String tues_eighth_cName = "";

    private String wed_first_cName = "";
    private String wed_second_cName = "";
    private String wed_third_cName = "";
    private String wed_fourth_cName = "";
    private String wed_fifth_cName = "";
    private String wed_sixth_cName = "";
    private String wed_seventh_cName = "";
    private String wed_eighth_cName = "";

    private String thurs_first_cName = "";
    private String thurs_second_cName = "";
    private String thurs_third_cName = "";
    private String thurs_fourth_cName = "";
    private String thurs_fifth_cName = "";
    private String thurs_sixth_cName = "";
    private String thurs_seventh_cName = "";
    private String thurs_eighth_cName = "";

    private String fri_first_cName = "";
    private String fri_second_cName = "";
    private String fri_third_cName = "";
    private String fri_fourth_cName = "";
    private String fri_fifth_cName = "";
    private String fri_sixth_cName = "";
    private String fri_seventh_cName = "";
    private String fri_eighth_cName = "";

    private ListView weekendList;
    private Button sta_add;
    private Button sun_add;

    private ListView planList;
    private TextView remindContent;
    private Button addPlan;
    private RadioButton rb_remind_open;
    private RadioButton rb_remind_close;
    private CheckBox cb_remind_vibrato;
    private CheckBox cb_remind_ring;
    private DatePicker planDatePicker;
    private TimePicker planTimePicker;

    private boolean is_plan_remind;
    private String weekend_title;

    private int count;

    private int mYear = 2011;
    private int mMonth = 0;
    private int mDay = 1;
    private int mHour = 0;
    private int mMinute = 0;

    Date date = new Date();
    Calendar calendar = Calendar.getInstance();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabHost th = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.mytab, th.getTabContentView(), true);


        th.addTab(th.newTabSpec("tab1").setIndicator("行程", getResources().getDrawable(R.drawable.menu01)).setContent(R.id.schooltimetable));
        th.addTab(th.newTabSpec("tab2").setIndicator("周末", getResources().getDrawable(R.drawable.menu01)).setContent(R.id.weekend));
        th.addTab(th.newTabSpec("tab3").setIndicator("计划", getResources().getDrawable(R.drawable.menu01)).setContent(R.id.plan));

        /*---------------------------------------课表---------------------------------------*/
        monday = (Button) findViewById(R.id.monday);
        tuesday = (Button) findViewById(R.id.tuesday);
        wednesday = (Button) findViewById(R.id.wednesday);
        thursday = (Button) findViewById(R.id.thursday);
        friday = (Button) findViewById(R.id.friday);

        mon_first = (Button) findViewById(R.id.mon_first);
        mon_second = (Button) findViewById(R.id.mon_second);
        mon_third = (Button) findViewById(R.id.mon_third);
        mon_fourth = (Button) findViewById(R.id.mon_fourth);
        mon_fifth = (Button) findViewById(R.id.mon_fifth);
        mon_sixth = (Button) findViewById(R.id.mon_sixth);
        mon_seventh = (Button) findViewById(R.id.mon_seventh);
        mon_eighth = (Button) findViewById(R.id.mon_eighth);

        first = (Button) findViewById(R.id.first);
        second = (Button) findViewById(R.id.second);
        third = (Button) findViewById(R.id.third);
        fourth = (Button) findViewById(R.id.fourth);
        fifth = (Button) findViewById(R.id.fifth);
        sixth = (Button) findViewById(R.id.sixth);
        seventh = (Button) findViewById(R.id.seventh);
        eighth = (Button) findViewById(R.id.eighth);

        tues_first = (Button) findViewById(R.id.tues_first);
        tues_second = (Button) findViewById(R.id.tues_second);
        tues_third = (Button) findViewById(R.id.tues_third);
        tues_fourth = (Button) findViewById(R.id.tues_fourth);
        tues_fifth = (Button) findViewById(R.id.tues_fifth);
        tues_sixth = (Button) findViewById(R.id.tues_sixth);
        tues_seventh = (Button) findViewById(R.id.tues_seventh);
        tues_eighth = (Button) findViewById(R.id.tues_eighth);

        wed_first = (Button) findViewById(R.id.wed_first);
        wed_second = (Button) findViewById(R.id.wed_second);
        wed_third = (Button) findViewById(R.id.wed_third);
        wed_fourth = (Button) findViewById(R.id.wed_fourth);
        wed_fifth = (Button) findViewById(R.id.wed_fifth);
        wed_sixth = (Button) findViewById(R.id.wed_sixth);
        wed_seventh = (Button) findViewById(R.id.wed_seventh);
        wed_eighth = (Button) findViewById(R.id.wed_eighth);

        thurs_first = (Button) findViewById(R.id.thurs_first);
        thurs_second = (Button) findViewById(R.id.thurs_second);
        thurs_third = (Button) findViewById(R.id.thurs_third);
        thurs_fourth = (Button) findViewById(R.id.thurs_fourth);
        thurs_fifth = (Button) findViewById(R.id.thurs_fifth);
        thurs_sixth = (Button) findViewById(R.id.thurs_sixth);
        thurs_seventh = (Button) findViewById(R.id.thurs_seventh);
        thurs_eighth = (Button) findViewById(R.id.thurs_eighth);

        fri_first = (Button) findViewById(R.id.fri_first);
        fri_second = (Button) findViewById(R.id.fri_second);
        fri_third = (Button) findViewById(R.id.fri_third);
        fri_fourth = (Button) findViewById(R.id.fri_fourth);
        fri_fifth = (Button) findViewById(R.id.fri_fifth);
        fri_sixth = (Button) findViewById(R.id.fri_sixth);
        fri_seventh = (Button) findViewById(R.id.fri_seventh);
        fri_eighth = (Button) findViewById(R.id.fri_eighth);


        mon_first.setOnClickListener(new MyButtonListener());
        mon_second.setOnClickListener(new MyButtonListener());
        mon_third.setOnClickListener(new MyButtonListener());
        mon_fourth.setOnClickListener(new MyButtonListener());
        mon_fifth.setOnClickListener(new MyButtonListener());
        mon_sixth.setOnClickListener(new MyButtonListener());
        mon_seventh.setOnClickListener(new MyButtonListener());
        mon_eighth.setOnClickListener(new MyButtonListener());

        tues_first.setOnClickListener(new MyButtonListener());
        tues_second.setOnClickListener(new MyButtonListener());
        tues_third.setOnClickListener(new MyButtonListener());
        tues_fourth.setOnClickListener(new MyButtonListener());
        tues_fifth.setOnClickListener(new MyButtonListener());
        tues_sixth.setOnClickListener(new MyButtonListener());
        tues_seventh.setOnClickListener(new MyButtonListener());
        tues_eighth.setOnClickListener(new MyButtonListener());

        wed_first.setOnClickListener(new MyButtonListener());
        wed_second.setOnClickListener(new MyButtonListener());
        wed_third.setOnClickListener(new MyButtonListener());
        wed_fourth.setOnClickListener(new MyButtonListener());
        wed_fifth.setOnClickListener(new MyButtonListener());
        wed_sixth.setOnClickListener(new MyButtonListener());
        wed_seventh.setOnClickListener(new MyButtonListener());
        wed_eighth.setOnClickListener(new MyButtonListener());

        thurs_first.setOnClickListener(new MyButtonListener());
        thurs_second.setOnClickListener(new MyButtonListener());
        thurs_third.setOnClickListener(new MyButtonListener());
        thurs_fourth.setOnClickListener(new MyButtonListener());
        thurs_fifth.setOnClickListener(new MyButtonListener());
        thurs_sixth.setOnClickListener(new MyButtonListener());
        thurs_seventh.setOnClickListener(new MyButtonListener());
        thurs_eighth.setOnClickListener(new MyButtonListener());

        fri_first.setOnClickListener(new MyButtonListener());
        fri_second.setOnClickListener(new MyButtonListener());
        fri_third.setOnClickListener(new MyButtonListener());
        fri_fourth.setOnClickListener(new MyButtonListener());
        fri_fifth.setOnClickListener(new MyButtonListener());
        fri_sixth.setOnClickListener(new MyButtonListener());
        fri_seventh.setOnClickListener(new MyButtonListener());
        fri_eighth.setOnClickListener(new MyButtonListener());

        /*---------------------------------------周末列表---------------------------------------*/
        weekendList = (ListView) findViewById(R.id.weekendlist);
        weekendList.setVisibility(ListView.VISIBLE);

        sta_add =  (Button) findViewById(R.id.sta_add);
        sun_add =  (Button) findViewById(R.id.sun_add);
        sta_add.setOnClickListener(new MyButtonListener());
        sun_add.setOnClickListener(new MyButtonListener());

        /*---------------------------------------计划列表---------------------------------------*/
        planList = (ListView) findViewById(R.id.planlist);
        remindContent = (TextView) findViewById(R.id.remindcontent);
        addPlan = (Button) findViewById(R.id.addplan);
        addPlan.setOnClickListener(new MyButtonListener());

        // 初始化课表
        initTable();
        // 初始化周末列表
        initWeekend();
        // 初始化课外列表
        initPlan();

    }

    /*---------------------------------------整个TabActivity---------------------------------------*/

    // 添加菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(0,1,1, "退出").setIcon(R.drawable.menu01);
        menu.add(0,2,2, "返回首页").setIcon(R.drawable.menu01);

        return super.onCreateOptionsMenu(menu);
    }

    // 设置菜单事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if(item.getItemId()==1){
            finish();
        }
        else if(item.getItemId()==2){
            finish();
            intent = getIntent();
            intent.setClass(MyTab.this, MainActivity.class);
            MyTab.this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // 按钮事件内部类
    class MyButtonListener implements OnClickListener{

        public void onClick(View v) {
            // TODO Auto-generated method stub

            switch (v.getId()) {
                case R.id.mon_first:
                    if(mon_first_cName.equals("")){
                        openAddCourseEditDialog(monday.getText()+"  "+ first.getText());
                    }else{
                        openShowCourseDetailDialog(mon_first_id);
                    }
                    break;
                case R.id.tues_first:
                    if(tues_first_cName.equals("")){
                        openAddCourseEditDialog(tuesday.getText()+"  "+first.getText());
                    }else{
                        openShowCourseDetailDialog(tues_first_id);
                    }
                    break;
                case R.id.wed_first:
                    if(wed_first_cName.equals("")){
                        openAddCourseEditDialog(wednesday.getText()+"  "+first.getText());
                    }else{
                        openShowCourseDetailDialog(wed_first_id);
                    }
                    break;
                case R.id.thurs_first:
                    if(thurs_first_cName.equals("")){
                        openAddCourseEditDialog(thursday.getText()+"  "+first.getText());
                    }else{
                        openShowCourseDetailDialog(thurs_first_id);
                    }
                    break;
                case R.id.fri_first:
                    if(fri_first_cName.equals("")){
                        openAddCourseEditDialog(friday.getText()+"  "+first.getText());
                    }else{
                        openShowCourseDetailDialog(fri_first_id);
                    }
                    break;

                case R.id.mon_second:
                    if(mon_second_cName.equals("")){
                        openAddCourseEditDialog(monday.getText()+"  "+second.getText());
                    }else{
                        openShowCourseDetailDialog(mon_second_id);
                    }
                    break;
                case R.id.tues_second:
                    if(tues_second_cName.equals("")){
                        openAddCourseEditDialog(tuesday.getText()+"  "+second.getText());
                    }else{
                        openShowCourseDetailDialog(tues_second_id);
                    }
                    break;
                case R.id.wed_second:
                    if(wed_second_cName.equals("")){
                        openAddCourseEditDialog(wednesday.getText()+"  "+second.getText());
                    }else{
                        openShowCourseDetailDialog(wed_second_id);
                    }
                    break;
                case R.id.thurs_second:
                    if(thurs_second_cName.equals("")){
                        openAddCourseEditDialog(thursday.getText()+"  "+second.getText());
                    }else{
                        openShowCourseDetailDialog(thurs_second_id);
                    }
                    break;
                case R.id.fri_second:
                    if(fri_second_cName.equals("")){
                        openAddCourseEditDialog(friday.getText()+"  "+second.getText());
                    }else{
                        openShowCourseDetailDialog(fri_second_id);
                    }
                    break;

                case R.id.mon_third:
                    if(mon_third_cName.equals("")){
                        openAddCourseEditDialog(monday.getText()+"  "+third.getText());
                    }else{
                        openShowCourseDetailDialog(mon_third_id);
                    }
                    break;
                case R.id.tues_third:
                    if(tues_third_cName.equals("")){
                        openAddCourseEditDialog(tuesday.getText()+"  "+third.getText());
                    }else{
                        openShowCourseDetailDialog(tues_third_id);
                    }
                    break;
                case R.id.wed_third:
                    if(wed_third_cName.equals("")){
                        openAddCourseEditDialog(wednesday.getText()+"  "+third.getText());
                    }else{
                        openShowCourseDetailDialog(wed_third_id);
                    }
                    break;
                case R.id.thurs_third:
                    if(thurs_third_cName.equals("")){
                        openAddCourseEditDialog(thursday.getText()+"  "+third.getText());
                    }else{
                        openShowCourseDetailDialog(thurs_third_id);
                    }
                    break;
                case R.id.fri_third:
                    if(fri_third_cName.equals("")){
                        openAddCourseEditDialog(friday.getText()+"  "+third.getText());
                    }else{
                        openShowCourseDetailDialog(fri_third_id);
                    }
                    break;

                case R.id.mon_fourth:
                    if(mon_fourth_cName.equals("")){
                        openAddCourseEditDialog(monday.getText()+"  "+fourth.getText());
                    }else{
                        openShowCourseDetailDialog(mon_fourth_id);
                    }
                    break;
                case R.id.tues_fourth:
                    if(tues_fourth_cName.equals("")){
                        openAddCourseEditDialog(tuesday.getText()+"  "+fourth.getText());
                    }else{
                        openShowCourseDetailDialog(tues_fourth_id);
                    }
                    break;
                case R.id.wed_fourth:
                    if(wed_fourth_cName.equals("")){
                        openAddCourseEditDialog(wednesday.getText()+"  "+fourth.getText());
                    }else{
                        openShowCourseDetailDialog(wed_fourth_id);
                    }
                    break;
                case R.id.thurs_fourth:
                    if(thurs_fourth_cName.equals("")){
                        openAddCourseEditDialog(thursday.getText()+"  "+fourth.getText());
                    }else{
                        openShowCourseDetailDialog(thurs_fourth_id);
                    }
                    break;
                case R.id.fri_fourth:
                    if(fri_fourth_cName.equals("")){
                        openAddCourseEditDialog(friday.getText()+"  "+fourth.getText());
                    }else{
                        openShowCourseDetailDialog(fri_fourth_id);
                    }
                    break;

                case R.id.mon_fifth:
                    if(mon_fifth_cName.equals("")){
                        openAddCourseEditDialog(monday.getText()+"  "+fifth.getText());
                    }else{
                        openShowCourseDetailDialog(mon_fifth_id);
                    }
                    break;
                case R.id.tues_fifth:
                    if(tues_fifth_cName.equals("")){
                        openAddCourseEditDialog(tuesday.getText()+"  "+fifth.getText());
                    }else{
                        openShowCourseDetailDialog(tues_fifth_id);
                    }
                    break;
                case R.id.wed_fifth:
                    if(wed_fifth_cName.equals("")){
                        openAddCourseEditDialog(wednesday.getText()+"  "+fifth.getText());
                    }else{
                        openShowCourseDetailDialog(wed_fifth_id);
                    }
                    break;
                case R.id.thurs_fifth:
                    if(thurs_fifth_cName.equals("")){
                        openAddCourseEditDialog(thursday.getText()+"  "+fifth.getText());
                    }else{
                        openShowCourseDetailDialog(thurs_fifth_id);
                    }
                    break;
                case R.id.fri_fifth:
                    if(fri_fifth_cName.equals("")){
                        openAddCourseEditDialog(friday.getText()+"  "+fifth.getText());
                    }else{
                        openShowCourseDetailDialog(fri_fifth_id);
                    }
                    break;

                case R.id.mon_sixth:
                    if(mon_sixth_cName.equals("")){
                        openAddCourseEditDialog(monday.getText()+"  "+sixth.getText());
                    }else{
                        openShowCourseDetailDialog(mon_sixth_id);
                    }
                    break;
                case R.id.tues_sixth:
                    if(tues_sixth_cName.equals("")){
                        openAddCourseEditDialog(tuesday.getText()+"  "+sixth.getText());
                    }else{
                        openShowCourseDetailDialog(tues_sixth_id);
                    }
                    break;
                case R.id.wed_sixth:
                    if(wed_sixth_cName.equals("")){
                        openAddCourseEditDialog(wednesday.getText()+"  "+sixth.getText());
                    }else{
                        openShowCourseDetailDialog(wed_sixth_id);
                    }
                    break;
                case R.id.thurs_sixth:
                    if(thurs_sixth_cName.equals("")){
                        openAddCourseEditDialog(thursday.getText()+"  "+sixth.getText());
                    }else{
                        openShowCourseDetailDialog(thurs_sixth_id);
                    }
                    break;
                case R.id.fri_sixth:
                    if(fri_sixth_cName.equals("")){
                        openAddCourseEditDialog(friday.getText()+"  "+sixth.getText());
                    }else{
                        openShowCourseDetailDialog(fri_sixth_id);
                    }
                    break;

                case R.id.mon_seventh:
                    if(mon_seventh_cName.equals("")){
                        openAddCourseEditDialog(monday.getText()+"  "+seventh.getText());
                    }else{
                        openShowCourseDetailDialog(mon_seventh_id);
                    }
                    break;
                case R.id.tues_seventh:
                    if(tues_seventh_cName.equals("")){
                        openAddCourseEditDialog(tuesday.getText()+"  "+seventh.getText());
                    }else{
                        openShowCourseDetailDialog(tues_seventh_id);
                    }
                    break;
                case R.id.wed_seventh:
                    if(wed_seventh_cName.equals("")){
                        openAddCourseEditDialog(wednesday.getText()+"  "+seventh.getText());
                    }else{
                        openShowCourseDetailDialog(wed_seventh_id);
                    }
                    break;
                case R.id.thurs_seventh:
                    if(thurs_seventh_cName.equals("")){
                        openAddCourseEditDialog(thursday.getText()+"  "+seventh.getText());
                    }else{
                        openShowCourseDetailDialog(thurs_seventh_id);
                    }
                    break;
                case R.id.fri_seventh:
                    if(fri_seventh_cName.equals("")){
                        openAddCourseEditDialog(friday.getText()+"  "+seventh.getText());
                    }else{
                        openShowCourseDetailDialog(fri_seventh_id);
                    }
                    break;

                case R.id.mon_eighth:
                    if(mon_eighth_cName.equals("")){
                        openAddCourseEditDialog(monday.getText()+"  "+eighth.getText());
                    }else{
                        openShowCourseDetailDialog(mon_eighth_id);
                    }
                    break;
                case R.id.tues_eighth:
                    if(tues_eighth_cName.equals("")){
                        openAddCourseEditDialog(tuesday.getText()+"  "+eighth.getText());
                    }else{
                        openShowCourseDetailDialog(tues_eighth_id);
                    }
                    break;
                case R.id.wed_eighth:
                    if(wed_eighth_cName.equals("")){
                        openAddCourseEditDialog(wednesday.getText()+"  "+eighth.getText());
                    }else{
                        openShowCourseDetailDialog(wed_eighth_id);
                    }
                    break;
                case R.id.thurs_eighth:
                    if(thurs_eighth_cName.equals("")){
                        openAddCourseEditDialog(thursday.getText()+"  "+eighth.getText());
                    }else{
                        openShowCourseDetailDialog(thurs_eighth_id);
                    }
                    break;
                case R.id.fri_eighth:
                    if(fri_eighth_cName.equals("")){
                        openAddCourseEditDialog(friday.getText()+"  "+eighth.getText());
                    }else{
                        openShowCourseDetailDialog(fri_eighth_id);
                    }
                    break;
                case R.id.sta_add:
                    openWeekendDialog(sta_add.getText().toString());
                    break;
                case R.id.sun_add:
                    openWeekendDialog(sun_add.getText().toString());
                    break;
                case R.id.addplan:
                    if(remindContent.getText().toString().trim().equals("")){
                        Toast.makeText(MyTab.this, "请添加提醒内容", Toast.LENGTH_SHORT).show();
                    }else{
                       Intent intent1=new Intent(MyTab.this,plan.class);
                        startActivity(intent1);
                    }
                    break;

                default:
                    break;

            }
        }
    }

    /*---------------------------------------课表---------------------------------------*/

    // 初始化课表
    public void initTable() {
        MyDB db = new MyDB(MyTab.this);
        Cursor cursor = db.queryAllCourses();
        Base base = new Base();
        while(cursor.moveToNext()){
            String strWeek = cursor.getString(cursor.getColumnIndex("week"));
            String strWhichLesson = cursor.getString(cursor.getColumnIndex("whichLesson"));
            // 星期一
            if(strWeek.equals("一") && strWhichLesson.equals("1-2")){
                mon_first_cName = cursor.getString(cursor.getColumnIndex("cName"));
                mon_first_id = cursor.getInt(cursor.getColumnIndex("id"));
                mon_first.setText(base.cutCourseName(mon_first_cName));
                mon_first.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("一") && strWhichLesson.equals("3-4")){
                mon_second_cName = cursor.getString(cursor.getColumnIndex("cName"));
                mon_second_id = cursor.getInt(cursor.getColumnIndex("id"));
                mon_second.setText(base.cutCourseName(mon_second_cName));
                mon_second.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("一") && strWhichLesson.equals("5-6")){
                mon_third_cName = cursor.getString(cursor.getColumnIndex("cName"));
                mon_third_id = cursor.getInt(cursor.getColumnIndex("id"));
                mon_third.setText(base.cutCourseName(mon_third_cName));
                mon_third.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("一") && strWhichLesson.equals("7-8")){
                mon_fourth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                mon_fourth_id = cursor.getInt(cursor.getColumnIndex("id"));
                mon_fourth.setText(base.cutCourseName(mon_fourth_cName));
                mon_fourth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("一") && strWhichLesson.equals("9-10")){
                mon_fifth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                mon_fifth_id = cursor.getInt(cursor.getColumnIndex("id"));
                mon_fifth.setText(base.cutCourseName(mon_fifth_cName));
                mon_fifth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("一") && strWhichLesson.equals("11-12")){
                mon_sixth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                mon_sixth_id = cursor.getInt(cursor.getColumnIndex("id"));
                mon_sixth.setText(base.cutCourseName(mon_sixth_cName));
                mon_sixth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("一") && strWhichLesson.equals("13-14")){
                mon_seventh_cName = cursor.getString(cursor.getColumnIndex("cName"));
                mon_seventh_id = cursor.getInt(cursor.getColumnIndex("id"));
                mon_seventh.setText(base.cutCourseName(mon_seventh_cName));
                mon_seventh.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("一") && strWhichLesson.equals("15-16")){
                mon_eighth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                mon_eighth_id = cursor.getInt(cursor.getColumnIndex("id"));
                mon_eighth.setText(base.cutCourseName(mon_eighth_cName));
                mon_eighth.setBackgroundResource(R.drawable.menu01);
            }
            // 星期二
            if(strWeek.equals("二") && strWhichLesson.equals("1-2")){
                tues_first_cName = cursor.getString(cursor.getColumnIndex("cName"));
                tues_first_id = cursor.getInt(cursor.getColumnIndex("id"));
                tues_first.setText(base.cutCourseName(tues_first_cName));
                tues_first.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("二") && strWhichLesson.equals("3-4")){
                tues_second_cName = cursor.getString(cursor.getColumnIndex("cName"));
                tues_second_id = cursor.getInt(cursor.getColumnIndex("id"));
                tues_second.setText(base.cutCourseName(tues_second_cName));
                tues_second.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("二") && strWhichLesson.equals("5-6")){
                tues_third_cName = cursor.getString(cursor.getColumnIndex("cName"));
                tues_third_id = cursor.getInt(cursor.getColumnIndex("id"));
                tues_third.setText(base.cutCourseName(tues_third_cName));
                tues_third.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("二") && strWhichLesson.equals("7-8")){
                tues_fourth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                tues_fourth_id = cursor.getInt(cursor.getColumnIndex("id"));
                tues_fourth.setText(base.cutCourseName(tues_fourth_cName));
                tues_fourth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("二") && strWhichLesson.equals("9-10")){
                tues_fifth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                tues_fifth_id = cursor.getInt(cursor.getColumnIndex("id"));
                tues_fifth.setText(base.cutCourseName(tues_fifth_cName));
                tues_fifth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("二") && strWhichLesson.equals("11-12")){
                tues_sixth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                tues_sixth_id = cursor.getInt(cursor.getColumnIndex("id"));
                tues_sixth.setText(base.cutCourseName(tues_sixth_cName));
                tues_sixth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("二") && strWhichLesson.equals("13-14")){
                tues_seventh_cName = cursor.getString(cursor.getColumnIndex("cName"));
                tues_seventh_id = cursor.getInt(cursor.getColumnIndex("id"));
                tues_seventh.setText(base.cutCourseName(tues_seventh_cName));
                tues_seventh.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("二") && strWhichLesson.equals("15-16")){
                tues_eighth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                tues_eighth_id = cursor.getInt(cursor.getColumnIndex("id"));
                tues_eighth.setText(base.cutCourseName(tues_eighth_cName));
                tues_eighth.setBackgroundResource(R.drawable.menu01);
            }
            // 星期三
            if(strWeek.equals("三") && strWhichLesson.equals("1-2")){
                wed_first_cName = cursor.getString(cursor.getColumnIndex("cName"));
                wed_first_id = cursor.getInt(cursor.getColumnIndex("id"));
                wed_first.setText(base.cutCourseName(wed_first_cName));
                wed_first.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("三") && strWhichLesson.equals("3-4")){
                wed_second_cName = cursor.getString(cursor.getColumnIndex("cName"));
                wed_second_id = cursor.getInt(cursor.getColumnIndex("id"));
                wed_second.setText(base.cutCourseName(wed_second_cName));
                wed_second.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("三") && strWhichLesson.equals("5-6")){
                wed_third_cName = cursor.getString(cursor.getColumnIndex("cName"));
                wed_third_id = cursor.getInt(cursor.getColumnIndex("id"));
                wed_third.setText(base.cutCourseName(wed_third_cName));
                wed_third.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("三") && strWhichLesson.equals("7-8")){
                wed_fourth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                wed_fourth_id = cursor.getInt(cursor.getColumnIndex("id"));
                wed_fourth.setText(base.cutCourseName(wed_fourth_cName));
                wed_fourth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("三") && strWhichLesson.equals("9-10")){
                wed_fifth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                wed_fifth_id = cursor.getInt(cursor.getColumnIndex("id"));
                wed_fifth.setText(base.cutCourseName(wed_fifth_cName));
                wed_fifth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("三") && strWhichLesson.equals("11-12")){
                wed_sixth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                wed_sixth_id = cursor.getInt(cursor.getColumnIndex("id"));
                wed_sixth.setText(base.cutCourseName(wed_sixth_cName));
                wed_sixth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("三") && strWhichLesson.equals("13-14")){
                wed_seventh_cName = cursor.getString(cursor.getColumnIndex("cName"));
                wed_seventh_id = cursor.getInt(cursor.getColumnIndex("id"));
                wed_seventh.setText(base.cutCourseName(wed_seventh_cName));
                wed_seventh.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("三") && strWhichLesson.equals("15-16")){
                wed_eighth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                wed_eighth_id = cursor.getInt(cursor.getColumnIndex("id"));
                wed_eighth.setText(base.cutCourseName(wed_eighth_cName));
                wed_eighth.setBackgroundResource(R.drawable.menu01);
            }
            // 星期四
            if(strWeek.equals("四") && strWhichLesson.equals("1-2")){
                thurs_first_cName = cursor.getString(cursor.getColumnIndex("cName"));
                thurs_first_id = cursor.getInt(cursor.getColumnIndex("id"));
                thurs_first.setText(base.cutCourseName(thurs_first_cName));
                thurs_first.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("四") && strWhichLesson.equals("3-4")){
                thurs_second_cName = cursor.getString(cursor.getColumnIndex("cName"));
                thurs_second_id = cursor.getInt(cursor.getColumnIndex("id"));
                thurs_second.setText(base.cutCourseName(thurs_second_cName));
                thurs_second.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("四") && strWhichLesson.equals("5-6")){
                thurs_third_cName = cursor.getString(cursor.getColumnIndex("cName"));
                thurs_third_id = cursor.getInt(cursor.getColumnIndex("id"));
                thurs_third.setText(base.cutCourseName(thurs_third_cName));
                thurs_third.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("四") && strWhichLesson.equals("7-8")){
                thurs_fourth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                thurs_fourth_id = cursor.getInt(cursor.getColumnIndex("id"));
                thurs_fourth.setText(base.cutCourseName(thurs_fourth_cName));
                thurs_fourth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("四") && strWhichLesson.equals("9-10")){
                thurs_fifth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                thurs_fifth_id = cursor.getInt(cursor.getColumnIndex("id"));
                thurs_fifth.setText(base.cutCourseName(thurs_fifth_cName));
                thurs_fifth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("四") && strWhichLesson.equals("11-12")){
                thurs_sixth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                thurs_sixth_id = cursor.getInt(cursor.getColumnIndex("id"));
                thurs_sixth.setText(base.cutCourseName(thurs_sixth_cName));
                thurs_sixth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("四") && strWhichLesson.equals("13-14")){
                thurs_seventh_cName = cursor.getString(cursor.getColumnIndex("cName"));
                thurs_seventh_id = cursor.getInt(cursor.getColumnIndex("id"));
                thurs_seventh.setText(base.cutCourseName(thurs_seventh_cName));
                thurs_seventh.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("四") && strWhichLesson.equals("15-16")){
                thurs_eighth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                thurs_eighth_id = cursor.getInt(cursor.getColumnIndex("id"));
                thurs_eighth.setText(base.cutCourseName(thurs_eighth_cName));
                thurs_eighth.setBackgroundResource(R.drawable.menu01);
            }
            // 星期五
            if(strWeek.equals("五") && strWhichLesson.equals("1-2")){
                fri_first_cName = cursor.getString(cursor.getColumnIndex("cName"));
                fri_first_id = cursor.getInt(cursor.getColumnIndex("id"));
                fri_first.setText(base.cutCourseName(fri_first_cName));
                fri_first.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("五") && strWhichLesson.equals("3-4")){
                fri_second_cName = cursor.getString(cursor.getColumnIndex("cName"));
                fri_second_id = cursor.getInt(cursor.getColumnIndex("id"));
                fri_second.setText(base.cutCourseName(fri_second_cName));
                fri_second.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("五") && strWhichLesson.equals("5-6")){
                fri_third_cName = cursor.getString(cursor.getColumnIndex("cName"));
                fri_third_id = cursor.getInt(cursor.getColumnIndex("id"));
                fri_third.setText(base.cutCourseName(fri_third_cName));
                fri_third.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("五") && strWhichLesson.equals("7-8")){
                fri_fourth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                fri_fourth_id = cursor.getInt(cursor.getColumnIndex("id"));
                fri_fourth.setText(base.cutCourseName(fri_fourth_cName));
                fri_fourth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("五") && strWhichLesson.equals("9-10")){
                fri_fifth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                fri_fifth_id = cursor.getInt(cursor.getColumnIndex("id"));
                fri_fifth.setText(base.cutCourseName(fri_fifth_cName));
                fri_fifth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("五") && strWhichLesson.equals("11-12")){
                fri_sixth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                fri_sixth_id = cursor.getInt(cursor.getColumnIndex("id"));
                fri_sixth.setText(base.cutCourseName(fri_sixth_cName));
                fri_sixth.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("五") && strWhichLesson.equals("13-14")){
                fri_seventh_cName = cursor.getString(cursor.getColumnIndex("cName"));
                fri_seventh_id = cursor.getInt(cursor.getColumnIndex("id"));
                fri_seventh.setText(base.cutCourseName(fri_seventh_cName));
                fri_seventh.setBackgroundResource(R.drawable.menu01);
            }
            else if(strWeek.equals("五") && strWhichLesson.equals("15-16")){
                fri_eighth_cName = cursor.getString(cursor.getColumnIndex("cName"));
                fri_eighth_id = cursor.getInt(cursor.getColumnIndex("id"));
                fri_eighth.setText(base.cutCourseName(fri_eighth_cName));
                fri_eighth.setBackgroundResource(R.drawable.menu01);
            }


        }
        cursor.close();
    }

    // 打开添加课程的对话框
    public void openAddCourseEditDialog(String title) {

        // 获取到另一个布局的引用，从此能再次activity中使用该布局引用的组件id，即findViewById可以顺利使用
        Intent intent=new Intent(MyTab.this,dialog.class);
        startActivity(intent);

      /*  dialog_address = (EditText) layout.findViewById(R.id.dialog_address);
        dialog_courseName = (EditText) layout.findViewById(R.id.dialog_coursename);
        dialog_single = (RadioButton) layout.findViewById(R.id.single);
        dialog_couple = (RadioButton) layout.findViewById(R.id.couple);
        dialog_singleAndCouple = (RadioButton) layout.findViewById(R.id.singleandcouple);

        new AlertDialog.Builder(this).setTitle("星期"+title+"节").setView(layout)
                .setPositiveButton("确定", dialogButtonListener(title))
                .setNegativeButton("取消", dialogButtonListener(title)).show();*/
    }

    // 对话框里的按钮事件
    public android.content.DialogInterface.OnClickListener dialogButtonListener(final String title) {

        return new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                switch (which) {
                    case -1:
                        String strRadioButtonText = null;
                        if(dialog_single.isChecked()){
                            strRadioButtonText = dialog_single.getText().toString();
                        }else if(dialog_couple.isChecked()){
                            strRadioButtonText = dialog_couple.getText().toString();
                        }else if(dialog_singleAndCouple.isChecked()){
                            strRadioButtonText = dialog_singleAndCouple.getText().toString();
                        }
                        intent = getIntent();
                        intent.setClass(MyTab.this, Courseinfo.class);
                        intent.putExtra("id", "");
                        intent.putExtra("week_classnumber", title);
                        intent.putExtra("courseName", dialog_courseName.getEditableText().toString());
                        intent.putExtra("address", dialog_address.getEditableText().toString());
                        intent.putExtra("period", strRadioButtonText);
                        MyTab.this.startActivity(intent);
                        finish();
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

    // 打开查看课程信息列表对话框
    public void openShowCourseDetailDialog(final int id) {
        intent = getIntent();
        final MyDB db = new MyDB(MyTab.this);
        final Course course = db.queryCourseByID(id);
        String strcName = course.getcName();
        if(strcName.equals("有课")){
            strcName = "";
        }
        String strIsRemind;
        String strIsRemindByVibrato;
        String strIsRemindByRing;
        if(course.isRemind()){
            strIsRemind = "是";
        }else{
            strIsRemind = "否";
        }
        if(course.isRemindByVibrato()){
            strIsRemindByVibrato = "是";
        }else{
            strIsRemindByVibrato = "否";
        }
        if(course.isRemindByRing()){
            strIsRemindByRing = "是";
        }else{
            strIsRemindByRing = "否";
        }
        String period = course.getPeriod();
        String strPeriod = null;
        if(period.equals("0")){
            strPeriod = "单双周";
        }else if(period.equals("11") || period.equals("12")){
            strPeriod = "单周";
        }else if(period.equals("22") || period.equals("21")){
            strPeriod = "双周";
        }
        String infoItemp[] = {"课程名称："+strcName, "上课地点："+course.getAddress(),"时间("+strPeriod+")："+course.getStartTime()+"-"+course.getEndTime(),"提醒时间："+course.getRemindTime(),"启动提醒："+strIsRemind,"提醒震动："+strIsRemindByVibrato,"提醒铃声："+strIsRemindByRing,"任课老师："+course.getTeacher()};
        new AlertDialog.Builder(this).setTitle("课程信息（"+"星期"+course.getWeek()+"  "+course.getWhichLesson()+"节）").setItems(infoItemp, null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                }).setNeutralButton("编辑", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                intent.setClass(MyTab.this, Courseinfo.class);
                intent.putExtra("id", id+"");
                MyTab.this.startActivity(intent);
                finish();
            }
        }).setNegativeButton("删除", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                openIsConfirmDeleteDialog(id, db, course);

            }
        }).show();
    }

    // 打开确认删除星期1-5某个课程的对话框
    public void openIsConfirmDeleteDialog(final int id, final MyDB db, final Course course) {
        String cName = course.getcName();
        if(cName.equals("有课")){
            cName = "";
        }
        new AlertDialog.Builder(this).setTitle("删除确认对话框")
                .setMessage("星期"+course.getWeek()+"  "+course.getWhichLesson()+"节\n"+cName+"\n确定删除吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        db.deleteCourseById(id);// 删除数据库里的对应记录

                        finish();
                        intent = getIntent();
                        intent.setClass(MyTab.this, MyTab.class);
                        MyTab.this.startActivity(intent);
                    }
                }).setNegativeButton("取消", null).show();
    }

    /*---------------------------------------周末列表---------------------------------------*/

    // 初始化周末列表
    public void initWeekend() {
        ArrayList<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> item = new HashMap<String, Object>();
        Cursor c = new MyDB(this).queryWeekendCourses();
        String week = null;
        String whichLesson = null;
        String cName = null;
        String period = null;
        String startTime = null;
        String endTime = null;
        String address = null;
        String remindTime = null;
        String isRemind = null;
        String remind = null;
        String isRemindByVibrato = null;
        String isRemindByRing = null;
        String teacher = null;
        int i=0;
        int count = c.getCount();
        int weedId[]= new int[count];

        if(c.getCount()>0){
            while (c.moveToNext()) {
                weedId[i] = c.getInt(c.getColumnIndex("id"));
                week = c.getString(c.getColumnIndex("week"));
                whichLesson = c.getString(c.getColumnIndex("whichLesson"));
                cName = c.getString(c.getColumnIndex("cName"));
                if(cName.equals("有课")){
                    cName = "";
                }
                period = c.getString(c.getColumnIndex("period"));
                startTime = c.getString(c.getColumnIndex("startTime"));
                endTime = c.getString(c.getColumnIndex("endTime"));
                address = c.getString(c.getColumnIndex("address"));
                remindTime = c.getString(c.getColumnIndex("remindTime"));
                isRemind = c.getString(c.getColumnIndex("isRemind"));
                isRemindByVibrato = c.getString(c.getColumnIndex("isRemindByVibrato"));
                isRemindByRing = c.getString(c.getColumnIndex("isRemindByRing"));

                if(period.equals("0")){
                    period = "单双周";
                }else if(period.equals("11") || period.equals("12")){
                    period = "单周";
                }else if(period.equals("21") || period.equals("22")){
                    period = "双周";
                }
                if(isRemind.equals("1") && isRemindByVibrato.equals("1") && isRemindByRing.equals("1")){
                    remind = "提醒开启（"+remindTime+"）：震动+铃声";
                }
                if(isRemind.equals("1") && isRemindByVibrato.equals("1") && isRemindByRing.equals("0")){
                    remind = "提醒开启（"+remindTime+"）：震动";
                }
                if(isRemind.equals("1") && isRemindByVibrato.equals("0") && isRemindByRing.equals("1")){
                    remind = "提醒开启（"+remindTime+"）：铃声";
                }
                if(isRemind.equals("1") && isRemindByVibrato.equals("0") && isRemindByRing.equals("0")){
                    remind = "提醒关闭（"+remindTime+"）";
                }
                if(isRemind.equals("0")){
                    remind = "提醒关闭（"+remindTime+"）";
                }
                teacher = c.getString(c.getColumnIndex("teacher"));

                item = new HashMap<String, Object>();
                item.put("星期", "星期"+week+"  "+whichLesson+"节");
                item.put("细节", period+" "+cName+" "+address+" "+startTime+"-"+endTime+" \n"+remind+" "+teacher);
                listItem.add(item);
                i++;

            }

        }

        SimpleAdapter adapter = new SimpleAdapter(this, listItem,
                android.R.layout.simple_list_item_2, new String[] {"星期","细节"},
                new int[] {android.R.id.text1, android.R.id.text2 });

        weekendList.setAdapter(adapter);
        weekendList.setVisibility(ListView.VISIBLE);
        weekendList.setOnItemClickListener(new WeekendOnItemClickListener(weedId, week, whichLesson));
        c.close();
    }

    // 周末列表事件内部类
    class WeekendOnItemClickListener implements OnItemClickListener{

        int[] wid;
        String week;
        String whichLesson;

        public WeekendOnItemClickListener(int[] wid, String week, String whichLesson) {
            // TODO Auto-generated constructor stub
            this.wid  = wid;
            this.week  = week;
            this.whichLesson  = whichLesson;

        }
        public void onItemClick(AdapterView<?> parent, View view, final int position,
                                long id) {
            // TODO Auto-generated method stub
            String infoItemp[] = {"编辑","删除"};
            new AlertDialog.Builder(MyTab.this).setTitle("操作菜单").setItems(infoItemp, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    switch (which) {
                        case 0:
                            finish();
                            intent = getIntent();
                            intent.setClass(MyTab.this, Courseinfo.class);
                            intent.putExtra("id", wid[position]+"");
                            MyTab.this.startActivity(intent);
                            break;
                        case 1:
                            new MyDB(MyTab.this).deleteCourseById(wid[position]);// 删除数据库里的对应记录
//						System.out.println("delete:"+wid[position]);

                            // 更新显示
//						initTable();
                            initWeekend();
//						initPlan();
                            break;

                        default:
                            break;
                    }
                }
            })
                    .setPositiveButton("取消", null).show();

        }

    }

    // 打开周末课次的列表对话框
    public void openWeekendDialog(final String weekend) {

        if(weekend.equals("星期六")){
            weekend_title = "六" + "  ";
        }else if(weekend.equals("星期日")){
            weekend_title = "日" + "  ";
        }
        final String infoItemp[] = {"1-2", "3-4","5-6","7-8","9-10","11-12","13-14","15-16"};
        new AlertDialog.Builder(MyTab.this).setTitle("选择"+weekend+"的课次").setItems(infoItemp, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                String id = "";
                MyDB db = new MyDB(MyTab.this);
                Cursor c = null;
                int weekId;
                switch (which) {
                    case 0:
                        c = db.queryWeekendCourseByWeekAndWhichLesson(weekend.substring(2), infoItemp[which]);
                        if(c.getCount() > 0){
                            while (c.moveToNext()) {
                                weekId = c.getInt(c.getColumnIndex("id"));
                                id = weekId + "";
                            }
                        }
                        weekend_title = weekend_title + infoItemp[which];
                        break;
                    case 1:
                        c = db.queryWeekendCourseByWeekAndWhichLesson(weekend.substring(2), infoItemp[which]);
                        if(c.getCount() > 0){
                            while (c.moveToNext()) {
                                weekId = c.getInt(c.getColumnIndex("id"));
                                id = weekId + "";
                            }
                        }
                        weekend_title = weekend_title + infoItemp[which];
                        break;
                    case 2:
                        c = db.queryWeekendCourseByWeekAndWhichLesson(weekend.substring(2), infoItemp[which]);
                        if(c.getCount() > 0){
                            while (c.moveToNext()) {
                                weekId = c.getInt(c.getColumnIndex("id"));
                                id = weekId + "";
                            }
                        }
                        weekend_title = weekend_title + infoItemp[which];
                        break;
                    case 3:
                        c = db.queryWeekendCourseByWeekAndWhichLesson(weekend.substring(2), infoItemp[which]);
                        if(c.getCount() > 0){
                            while (c.moveToNext()) {
                                weekId = c.getInt(c.getColumnIndex("id"));
                                id = weekId + "";
                            }
                        }
                        weekend_title = weekend_title + infoItemp[which];
                        break;
                    case 4:
                        c = db.queryWeekendCourseByWeekAndWhichLesson(weekend.substring(2), infoItemp[which]);
                        if(c.getCount() > 0){
                            while (c.moveToNext()) {
                                weekId = c.getInt(c.getColumnIndex("id"));
                                id = weekId + "";
                            }
                        }
                        weekend_title = weekend_title + infoItemp[which];
                        break;
                    case 5:
                        c = db.queryWeekendCourseByWeekAndWhichLesson(weekend.substring(2), infoItemp[which]);
                        if(c.getCount() > 0){
                            while (c.moveToNext()) {
                                weekId = c.getInt(c.getColumnIndex("id"));
                                id = weekId + "";
                            }
                        }
                        weekend_title = weekend_title + infoItemp[which];
                        break;
                    case 6:
                        c = db.queryWeekendCourseByWeekAndWhichLesson(weekend.substring(2), infoItemp[which]);
                        if(c.getCount() > 0){
                            while (c.moveToNext()) {
                                weekId = c.getInt(c.getColumnIndex("id"));
                                id = weekId + "";
                            }
                        }
                        weekend_title = weekend_title + infoItemp[which];
                        break;
                    case 7:
                        c = db.queryWeekendCourseByWeekAndWhichLesson(weekend.substring(2), infoItemp[which]);
                        if(c.getCount() > 0){
                            while (c.moveToNext()) {
                                weekId = c.getInt(c.getColumnIndex("id"));
                                id = weekId + "";
                            }
                        }
                        weekend_title = weekend_title + infoItemp[which];
                        break;

                    default:
                        break;
                }

                c.close();

                intent = getIntent();
                intent.setClass(MyTab.this, Courseinfo.class);
                intent.putExtra("id", id);
                intent.putExtra("week_classnumber", weekend_title);
                intent.putExtra("courseName", "");
                intent.putExtra("address", "");
                intent.putExtra("period", "单双周");
                MyTab.this.startActivity(intent);
                finish();
            }
        }).setPositiveButton("取消", null).show();
    }

    /*---------------------------------------计划列表---------------------------------------*/

    // 初始化计划列表
    public void initPlan() {
        ArrayList<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> item = new HashMap<String, Object>();
        Cursor c = new MyDB(this).queryAllPlans();
        count = c.getCount();
        int i = 0;
        int pnos[]= new int[count];
        String content = null;
        String remindTime = null;
        String isRemind = null;
        String isRemindByVibrato = null;
        String isRemindByRing = null;
        if(c.getCount() > 0){
            while (c.moveToNext()) {
                pnos[i] = c.getInt(c.getColumnIndex("pno"));
                content = c.getString(c.getColumnIndex("content"));
                remindTime = c.getString(c.getColumnIndex("remindTime"));
                isRemind = c.getString(c.getColumnIndex("isRemind"));
                isRemindByVibrato = c.getString(c.getColumnIndex("isRemindByVibrato"));
                isRemindByRing = c.getString(c.getColumnIndex("isRemindByRing"));
                String remind = null;
                if(isRemind.equals("1") && isRemindByVibrato.equals("1") && isRemindByRing.equals("1")){
                    remind = "提醒开启（"+remindTime+"）：震动+铃声";
                }
                if(isRemind.equals("1") && isRemindByVibrato.equals("1") && isRemindByRing.equals("0")){
                    remind = "提醒开启（"+remindTime+"）：震动";
                }
                if(isRemind.equals("1") && isRemindByVibrato.equals("0") && isRemindByRing.equals("1")){
                    remind = "提醒开启（"+remindTime+"）：铃声";
                }
                if(isRemind.equals("1") && isRemindByVibrato.equals("0") && isRemindByRing.equals("0")){
                    remind = "提醒关闭（"+remindTime+"）";
                }
                if(isRemind.equals("0")){
                    remind = "提醒关闭（"+remindTime+"）";
                }
                item = new HashMap<String, Object>();
                item.put("内容", content);
                item.put("细节", remind);
                listItem.add(item);
                i++;
            }

        }

        SimpleAdapter adapter = new SimpleAdapter(this, listItem,
                android.R.layout.simple_list_item_2, new String[] {"内容","细节"},
                new int[] {android.R.id.text1, android.R.id.text2 });

        planList.setAdapter(adapter);
        planList.setVisibility(ListView.VISIBLE);
        planList.setOnItemClickListener(new PlanOnItemClickListener(pnos));

        c.close();

    }

    // 计划列表事件内部类
    class PlanOnItemClickListener implements OnItemClickListener{

        int[] pno;
        public PlanOnItemClickListener(int[] pno) {
            // TODO Auto-generated constructor stub
            this.pno = pno;

        }

        String infoItemp[] = {"编辑","删除"};
        public void onItemClick(AdapterView<?> parent, View view, final int position,
                                long id) {
            // TODO Auto-generated method stub
            System.out.println("position:"+position);
            new AlertDialog.Builder(MyTab.this).setTitle("操作菜单").setItems(infoItemp, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    switch (which) {
                        case 0:
                            openPlanDialog(pno[position]);
                            System.out.println("pno:"+pno[position]+"position:"+position);
                            break;
                        case 1:
                            new MyDB(MyTab.this).deletePlan(pno[position]);
//							System.out.println("delete:"+ pno[position]);

                            initPlan();
                            break;

                        default:
                            break;
                    }
                }

            }).setPositiveButton("取消", null).show();

        }

    }

    // 打开计划日期时间选择器的对话框
    public void openPlanDialog(final int pno) {

        // 获取外部布局文件的资源
        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.plan, (ViewGroup) findViewById(R.id.plantime));

        rb_remind_open = (RadioButton) layout.findViewById(R.id.rb_remind_open);
        rb_remind_close = (RadioButton) layout.findViewById(R.id.rb_remind_close);
        cb_remind_vibrato = (CheckBox) layout.findViewById(R.id.cb_remindbyvibrato);
        cb_remind_ring = (CheckBox) layout.findViewById(R.id.cb_remindbyring);

        is_plan_remind = true;
        rb_remind_open.setChecked(true);
        cb_remind_vibrato.setChecked(true);
        cb_remind_ring.setChecked(true);

        rb_remind_open.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                cb_remind_vibrato.setEnabled(true);
                cb_remind_vibrato.setChecked(true);
                cb_remind_ring.setEnabled(true);
                cb_remind_ring.setChecked(true);
                is_plan_remind = true;
            }
        });
        rb_remind_close.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                cb_remind_vibrato.setEnabled(false);
                cb_remind_vibrato.setChecked(false);
                cb_remind_ring.setEnabled(false);
                cb_remind_ring.setChecked(false);
                is_plan_remind = false;
            }
        });
        cb_remind_vibrato.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    cb_remind_vibrato.setChecked(true);
                    System.out.println("is_plan_remind_vibrato = true");
                }else{
                    cb_remind_vibrato.setChecked(false);
                    System.out.println("is_plan_remind_vibrato = false");
                }
            }
        });
        cb_remind_ring.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    cb_remind_ring.setChecked(true);
                    System.out.println("is_plan_remind_ring = true");
                }else{
                    cb_remind_ring.setChecked(false);
                    System.out.println("is_plan_remind_ring = false");
                }
            }
        });

        calendar.setTimeInMillis(System.currentTimeMillis());
        planDatePicker = (DatePicker)layout.findViewById(R.id.plandatepicker);
        planDatePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {

            // 当时间被修改时，执行的方法
            public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                calendar.set(Calendar.YEAR, year+1900);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                mYear = calendar.getTime().getYear();
                mMonth = calendar.getTime().getMonth();
                mDay = calendar.getTime().getDate();
                System.out.println(mYear+"."+mMonth+"."+mDay);
            }
        });

        planTimePicker = (TimePicker)layout.findViewById(R.id.plantimepicker);
        planTimePicker.setIs24HourView(true);
        planTimePicker.setCurrentHour(mHour);
        planTimePicker.setCurrentMinute(mMinute);
        planTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                mHour = calendar.getTime().getHours();
                mMinute = calendar.getTime().getMinutes();
            }
        });


        final MyDB db = new MyDB(MyTab.this);
        Cursor c = db.queryAllPlans();
        final int count = c .getCount();

        if(count == 5 && pno == 0){
            Toast.makeText(MyTab.this, "只能添加5个", Toast.LENGTH_SHORT).show();
        }else{
            new AlertDialog.Builder(MyTab.this).setTitle(remindContent.getText().toString().trim()).setView(layout)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            if(rb_remind_open.isChecked() && !cb_remind_vibrato.isChecked() && !cb_remind_ring.isChecked()){
                                Toast.makeText(MyTab.this, "请选择提醒方式", Toast.LENGTH_LONG).show();
                            }else{

                                Calendar nowCalendar = Calendar.getInstance();
                                nowCalendar.setTimeInMillis(System.currentTimeMillis());
                                System.out.println(date.toString());
                                calendar.set(Calendar.YEAR, mYear);
                                calendar.set(Calendar.MONTH, mMonth);
                                calendar.set(Calendar.DAY_OF_MONTH, mDay);
                                System.out.println(planDatePicker.getYear()+"/"+(planDatePicker.getMonth()+1)+"/"+(planDatePicker.getDayOfMonth()));
                                calendar.set(Calendar.HOUR_OF_DAY, mHour);
                                calendar.set(Calendar.MINUTE, mMinute);
                                calendar.set(Calendar.SECOND,0);
                                calendar.set(Calendar.MILLISECOND,0);
                                Base base = new Base();
                                String[] strsWeek= {"星期日","星期一","星期二","星期三","星期四","星期五","星期六" };
                                String remindTime = (calendar.getTime().getYear()+1900) +"/"+ (calendar.getTime().getMonth()+1)+"/"+(calendar.getTime().getDate())+" "+ base.formateTime(calendar.getTime().getHours())+":"+base.formateTime(calendar.getTime().getMinutes())+" "+strsWeek[calendar.getTime().getDay()];


                                Plan plan = new Plan(pno, remindContent.getText().toString().trim(), remindTime, is_plan_remind, cb_remind_vibrato.isChecked(), cb_remind_ring.isChecked());
                                System.out.println("pno:"+plan.getPno());
                                if(pno == 0){
                                    // 添加pno不重复，而且在1-5之内的计划
                                    int planNo = 1;
                                    Cursor c1 = db.queryPlanByPno(1);
                                    Cursor c2 = db.queryPlanByPno(2);
                                    Cursor c3 = db.queryPlanByPno(3);
                                    Cursor c4 = db.queryPlanByPno(4);
                                    Cursor c5 = db.queryPlanByPno(5);

                                    if(c1.getCount()<=0){
                                        planNo = 1;
                                    }else if(c2.getCount()<=0){
                                        planNo = 2;
                                    }else if(c3.getCount()<=0){
                                        planNo = 3;
                                    }else if(c4.getCount()<=0){
                                        planNo = 4;
                                    }else if(c5.getCount()<=0){
                                        planNo = 5;
                                    }
                                    c1.close();
                                    c2.close();
                                    c3.close();
                                    c4.close();
                                    c5.close();

                                    plan.setPno(planNo);
                                    System.out.println("planNo:"+planNo);
                                    db.insertPlan(plan);// 添加计划提醒
                                }else{
                                    System.out.println("udpdate pno:"+pno);
                                    db.updatePlanByPno(pno, plan);// 更新计划提醒
                                }

                                initPlan();// 更新列表
                            }

                        }
                    })
                    .setNegativeButton("取消", null).show();
        }

        c.close();

    }

}
package com.example.administrator.firstapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SendMessage extends AppCompatActivity {

    Intent intent;
    private EditText phoneNo;
    private EditText massageContent;
    private Button cancle;
    private Button sent;
    private Button call;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmessage);

        intent = getIntent();
        String teacher = intent.getStringExtra("SMScontent");
        String content = teacher + "您好！";

        phoneNo = (EditText) findViewById(R.id.edtPhoneNo);
        massageContent = (EditText) findViewById(R.id.edtContent);
        cancle = (Button) findViewById(R.id.btnCancle);
        sent = (Button) findViewById(R.id.btnSend);
        call = (Button) findViewById(R.id.btnCall);

        massageContent.setText(content);

        // 注册监听
        cancle.setOnClickListener(new MyButtonListener());
        sent.setOnClickListener(new MyButtonListener());
        call.setOnClickListener(new MyButtonListener());
    }

    // 按钮事件内部类
    class MyButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            // TODO Auto-generated method stub
            intent = getIntent();
            String strPhoneNo = phoneNo.getText().toString().trim();
            String smsContents = massageContent.getText().toString();

            switch (v.getId()) {
                case R.id.btnCancle:
                    finish();
                    break;
                case R.id.btnSend:
                    if (strPhoneNo.equals("")) {
                        Toast.makeText(SendMessage.this, "电话号码不能为空！",
                                Toast.LENGTH_SHORT).show();
                    } else if (smsContents.equals("")) {
                        Toast.makeText(SendMessage.this, "不能发送空信息！",
                                Toast.LENGTH_SHORT).show();
                    } else{
                        if (PhoneNumberUtils.isGlobalPhoneNumber(strPhoneNo)) {
                            sentSMS(strPhoneNo, smsContents);
                        }else{
                            Toast.makeText(SendMessage.this, "电话格式不正确",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                case R.id.btnCall:
                    if (strPhoneNo.equals("")) {
                        Toast.makeText(SendMessage.this, "电话号码不能为空！",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (PhoneNumberUtils.isGlobalPhoneNumber(strPhoneNo)) {
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel://"+strPhoneNo));
                            SendMessage.this.startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(SendMessage.this, "电话格式不正确",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;

                default:
                    break;
            }

        }

    }

    // 发送短息
    public void sentSMS(String phoneNumber, String smsContents) {
        finish();

        SmsManager sms = SmsManager.getDefault();
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,
                MyTab.class), 0);
        if (smsContents.length() > 70) {// 若短信的长度大于70则切割短信内容
            List<String> msgs = sms.divideMessage(smsContents);
            for (String msg : msgs) {
                sms.sendTextMessage(phoneNumber, null, msg, pi, null);
            }
        } else {
            sms.sendTextMessage(phoneNumber, null, smsContents, pi, null);
        }
        Toast.makeText(SendMessage.this, "发送完成", Toast.LENGTH_LONG).show();

    }

}


package com.example.administrator.yingdaoye;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.IOException;

public class MusicMain extends Activity {
    private static final String TAG ="d";
    // 显示组件
    private ImageButton imgBtn_Previous;
    private ImageButton imgBtn_PlayOrPause;
    private ImageButton imgBtn_Stop;
    private ImageButton imgBtn_Next;
    private ListView list;
    // 当前歌曲的序号，下标从1开始
    private int number;
    //播放状态
    private int status;
    // 广播接收器
    private StatusChangedReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mucis_main);
        findViews();
        registerListeners();
        number = 1;
        status = MusicService.STATUS_STOPPED;
        startService(new Intent(this, MusicService.class));
        // 绑定广播接收器，可以接收广播
        bindStatusChangedReceiver();
        // 检查播放器是否正在播放。如果正在播放，以上绑定的接收器会改变UI
        sendBroadcastOnCommand(MusicService.COMMAND_CHECK_IS_PLAYING);

    }
    /** 绑定广播接收器 */
    private void bindStatusChangedReceiver() {
        receiver = new StatusChangedReceiver();
        IntentFilter filter = new IntentFilter(
                MusicService.BROADCAST_MUSICSERVICE_UPDATE_STATUS);
        registerReceiver(receiver, filter);
    }

    /** 获取显示组件 */
    private void findViews() {
        imgBtn_Previous = (ImageButton) findViewById(R.id.imageButton1);
        imgBtn_PlayOrPause = (ImageButton) findViewById(R.id.imageButton2);
        imgBtn_Stop = (ImageButton) findViewById(R.id.imageButton3);
        imgBtn_Next = (ImageButton) findViewById(R.id.imageButton4);
        list = (ListView) findViewById(R.id.listView1);
    }
    /** 为显示组件注册监听器 */
      private void registerListeners(){
          imgBtn_Previous.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 sendBroadcastOnCommand(MusicService.COMMAND_PREVIOUS);

              }
          });
          imgBtn_PlayOrPause.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(isPlaying()){
                      sendBroadcastOnCommand(MusicService.COMMAND_PAUSE);
                  }else if(isPaused()){
                      sendBroadcastOnCommand(MusicService.COMMAND_RESUME);
                  }else if(isStopped()){
                      sendBroadcastOnCommand(MusicService.COMMAND_PLAY);
                  }

              }
          });
          imgBtn_Stop.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 sendBroadcastOnCommand(MusicService.COMMAND_STOP);

              }
          });
          imgBtn_Next.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                sendBroadcastOnCommand(MusicService.COMMAND_NEXT);

              }
          });
          list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  // position下标从0开始，number下标从1开始
                  number = position + 1;
                sendBroadcastOnCommand(MusicService.COMMAND_PLAY);

          }
          });
      }
    private void moveNumberToNext(){
        //判断是否到达了列表底端
        if((number+1)>list.getCount()){
            number=1;
            Toast.makeText(MusicMain.this,
                    MusicMain.this.getString(R.string.tip_reach_bottom),
                    Toast.LENGTH_SHORT).show();
        } else {
            ++number;
        }

    }
    private void moveNumberToPrevious() {
        // 判断是否到达了列表顶端
        if (number == 1) {
            number = list.getCount();
            Toast.makeText(MusicMain.this,
                    MusicMain.this.getString(R.string.tip_reach_top),
                    Toast.LENGTH_SHORT).show();
        } else {
            --number;
        }
    }


    //是否正在播放音乐
    private boolean isPlaying(){
        return status==MusicService.STATUS_PLAYING;
    }
    //是否暂停了播放音乐
    private boolean isPaused(){
        return status==MusicService.STATUS_PAUSED;
    }
    //是否停止状态
    private boolean isStopped(){
        return status==MusicService.STATUS_STOPPED;
    }

    //媒体播放类




    @Override
    protected void onResume() {
        super.onResume();
        //初始化音乐列表
        initMusicList();
        //如果列表中没有歌曲，则播放按钮不可用，并提醒用户
        if (list.getCount() == 0) {
            imgBtn_Previous.setEnabled(false);
            imgBtn_PlayOrPause.setEnabled(false);
            imgBtn_Stop.setEnabled(false);
            imgBtn_Next.setEnabled(false);
            Toast.makeText(this, this.getString(R.string.tip_no_music_file),
                    Toast.LENGTH_SHORT).show();
        } else {
            imgBtn_Previous.setEnabled(true);
            imgBtn_PlayOrPause.setEnabled(true);
            imgBtn_Stop.setEnabled(true);
            imgBtn_Next.setEnabled(true);
        }

    }
    //初始化音乐列表包括获取音乐集和更新显示列表
    public void initMusicList(){
        Cursor cursor = getMusicCursor();//Cursor可随机访问的结果集，用于保存数据库的查询结果
        setListContent(cursor);
    }
    //更新列表内容
    private void setListContent(Cursor musicCursor){
        CursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, musicCursor, new String[] {
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.ARTIST
        }, new int[] {
                android.R.id.text1, android.R.id.text2 });//歌曲标题在Cursor对象中的列名，
        // MediaStore基于SQLITE的多媒体数据库，包含了音频，视频，图片等所有多媒体文件的信息
        //歌曲艺术家在Cursor对象中的列名
        list.setAdapter(adapter);

    }
    //获取系统扫描得到的音乐媒体集，用ContentResplver.getContentResolver()获取一个 ContentResolver实例 resolver
    //接着用 resolver.query选取音乐文件的数据，并存储在Cursor对象中，setListContent方法将cursor内容转入适配器CursorAdapter对象中
    //有了适配器就可以投入列表ListView的内容了
    private Cursor getMusicCursor() {
        // 获取数据选择器
        ContentResolver resolver = getContentResolver();//数据选择器用于筛选需要的数据
        // 选择音乐媒体集
        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                null);
        return cursor;
    }
    /** 内部类，用于播放器状态更新的接收广播 */
    class StatusChangedReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            // 获取播放器状态
            status = intent.getIntExtra("status", -1);
            switch (status) {
                case MusicService.STATUS_PLAYING:
                    imgBtn_PlayOrPause.setBackgroundResource(R.drawable.pause);
                    break;
                case MusicService.STATUS_PAUSED:
                    imgBtn_PlayOrPause.setBackgroundResource(R.drawable.play);
                    break;
                case MusicService.STATUS_STOPPED:
                    MusicMain.this.setTitle("GracePlayer");
                    imgBtn_PlayOrPause.setBackgroundResource(R.drawable.play);
                    break;
                case MusicService.STATUS_COMPLETED:
                    sendBroadcastOnCommand(MusicService.COMMAND_NEXT);
                    MusicMain.this.setTitle("GracePlayer");
                    imgBtn_PlayOrPause.setBackgroundResource(R.drawable.play);
                    break;
                default:
                    break;
            }
        }
    }

    /** 发送命令，控制音乐播放。参数定义在MusicService类中 */
    private void sendBroadcastOnCommand(int command) {
        Intent intent = new Intent(MusicService.BROADCAST_MUSICSERVICE_CONTROL);//让过滤器识别广播
        intent.putExtra("command", command);
        // 根据不同命令，封装不同的数据（歌曲序号）
        switch (command) {
            case MusicService.COMMAND_PLAY:
                intent.putExtra("number", number);
                break;
            case MusicService.COMMAND_PREVIOUS:
                moveNumberToPrevious();
                intent.putExtra("number", number);
                break;
            case MusicService.COMMAND_NEXT:
                moveNumberToNext();
                intent.putExtra("number", number);
                break;
            case MusicService.COMMAND_PAUSE:
            case MusicService.COMMAND_STOP:
            case MusicService.COMMAND_RESUME:
            default:
                break;
        }
        sendBroadcast(intent);
    }


}

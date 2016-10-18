package com.example.administrator.mymediaplayer.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.mymediaplayer.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class musicPlay extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private String play_mode;
    //播放列表
    private List<String> myMusicList=new ArrayList<String>();
    //当前播放歌曲的索引
    private int currentListItem=0;
    //音乐的路径
    private static final String MUSIC_PATH=new String("/sdcard/");
    private Button start,stop,next,pause,last;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_play);
        mMediaPlayer=new MediaPlayer();
        findView();
        musicList();
        listener();

    }
    //绑定音乐
    void musicList(){
        File home=new File(MUSIC_PATH);
        if(home.listFiles(new MusicFilter()).length>0){
            for(File file:home.listFiles(new MusicFilter())){
                myMusicList.add(file.getName());
            }
            listView= (ListView) findViewById(R.id.musicitme);
            ArrayAdapter<String> musicList=new ArrayAdapter<String>
                    (musicPlay.this,R.layout.text, myMusicList);
           listView.setAdapter(musicList);
        }
    }
    //获取按钮
    void findView(){
        start=(Button)findViewById(R.id.start);
        stop=(Button)findViewById(R.id.stop);
       next=(Button)findViewById(R.id.next);
        pause=(Button)findViewById(R.id.pause);
       last=(Button)findViewById(R.id.last);
    }
    //监听事件
    void listener(){
        //停止
       stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mMediaPlayer.isPlaying()){
                    mMediaPlayer.reset();
                }
            }
        });
        //开始
      start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                playMusic(MUSIC_PATH+myMusicList.get(currentListItem));
            }
        });
        //下一首
      next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                nextMusic();
            }
        });
        //暂停
       pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mMediaPlayer.isPlaying()){
                    mMediaPlayer.pause();
                }else{
                    mMediaPlayer.start();
                }
            }
        });
        //上一首
     last.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                lastMusic();
            }
        });

    }

    //播放音乐
    void playMusic(String path){
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    nextMusic();
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    //下一首
    void nextMusic(){
        if(++currentListItem>=myMusicList.size()){
            currentListItem=0;
        }
        else{
            playMusic(MUSIC_PATH+myMusicList.get(currentListItem));
        }
    }

    //上一首
    void lastMusic(){
        if(currentListItem!=0)
        {
            if(--currentListItem>=0){
                currentListItem=myMusicList.size();
            } else{
                playMusic(MUSIC_PATH+myMusicList.get(currentListItem));
            }
        }  else{
            playMusic(MUSIC_PATH+myMusicList.get(currentListItem));
        }
    }

    //当用户返回时结束音乐并释放音乐对象
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode== KeyEvent.KEYCODE_BACK){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //当选择列表项时播放音乐

    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        currentListItem=position;
        playMusic(MUSIC_PATH+myMusicList.get(currentListItem));
    }


}

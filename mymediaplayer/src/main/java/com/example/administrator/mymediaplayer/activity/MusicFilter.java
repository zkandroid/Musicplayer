package com.example.administrator.mymediaplayer.activity;

/**
 * Created by Administrator on 2016/10/5.
 */
import java.io.File;
import java.io.FilenameFilter;

public class MusicFilter implements FilenameFilter{

    @Override
    public boolean accept(File dir, String filename) {
        // TODO Auto-generated method stub
        return (filename.endsWith(".mp3"));
    }


}
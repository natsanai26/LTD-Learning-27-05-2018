package com.example.windows10.ltd_learning.mActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.windows10.ltd_learning.mInterface.MyAPI;
import com.example.windows10.ltd_learning.R;


public class FullScreenVideoActivity extends AppCompatActivity implements OnPreparedListener {

    private final String VIDEO_URL = "http://158.108.207.7:8090/api/ts/key999/512/558/index.m3u8";
    private VideoView videoView;
    //rivate final String VIDEO_URL = "https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_video);

        init();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //getSupportActionBar().hide();

        Intent intent = getIntent();

        String videoPath = intent.getStringExtra("video_path");

        long videoSeek = intent.getLongExtra("video_seek",0);

        //Toast.makeText(this,videoPath,Toast.LENGTH_LONG).show();

        videoView.setVideoPath(MyAPI.BASE_URL_COURSE_API+videoPath);
        videoView.seekTo(videoSeek);
        videoView.start();







    }

    @Override
    public void onPrepared() {

    }

    private void init()
    {
        videoView = (VideoView) findViewById(R.id.video_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("video_seek",videoView.getCurrentPosition());
        setResult(RESULT_OK,intent);
        super.finish();
    }
}

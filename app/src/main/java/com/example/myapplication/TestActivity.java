package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.media.AudioManager;
        import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Bundle;
        import android.view.View;
        import android.widget.SeekBar;

        import java.util.Timer;
        import java.util.TimerTask;

public class TestActivity extends AppCompatActivity {


    MediaPlayer mplayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mplayer = MediaPlayer.create(this,R.raw.bgm2);
        float speed = 1.0f;
        mplayer.setPlaybackParams(new PlaybackParams().setSpeed(speed));

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        SeekBar volumeController = (SeekBar) findViewById(R.id.seekBar1);
        volumeController.setMax(maxVolume);
        volumeController.setProgress(curVolume);

        volumeController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar scrubber  = (SeekBar)findViewById(R.id.seekBar2);
        scrubber.setMax(mplayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mplayer.getCurrentPosition());
            }
        },0,100);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                mplayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void onclickPlay(View view){


        mplayer.start();

    }

    public void onclickPause(View view){

        mplayer.pause();

    }

    public void onclickStop(View view){

        mplayer.pause();

    }




}

package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RawRes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class TestActivity extends Activity {
    UseAudio useAudio;

    // hello_world.wav のサンプリングレート
//    private static final int SamplingRate = 88000;
//
//
//    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

          useAudio = new UseAudio();

//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(v-> wavPlay(R.raw.pinpon));
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(v-> wavPlay(R.raw.boo));
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                useAudio.wavPlay(TestActivity.this,R.raw.boo);
            }
        });
//
//        Button buttonStart = findViewById(R.id.button1);
//        buttonStart.setOnClickListener( v ->  {
//            // 音楽再生
//            audioPlay();
//        });
//        // 音楽停止ボタン
//        Button buttonStop = findViewById(R.id.button2);
//        buttonStop.setOnClickListener( v -> {
//            if (mediaPlayer != null) {
//                // 音楽停止
//                audioStop();
//            }
//        });
    }
//    private void wavPlay(@RawRes int id) {
//        InputStream input = null;
//        byte[] wavData = null;
//
//        try {
//            // wavを読み込む
//            input = getResources().openRawResource(id);
//            wavData = new byte[input.available()];
//
//            // input.read(wavData)
//            String readBytes = String.format(Locale.US, "read bytes = %d",input.read(wavData));
//            // input.read(wavData)のwarning回避のためだけ
//            Log.d("debug",readBytes);
//            input.close();
//        } catch (FileNotFoundException fne) {
//            fne.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//            Log.d("debug", "error");
//        } finally{
//            try{
//                if(input != null) input.close();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        // バッファサイズの計算
//        int bufSize = android.media.AudioTrack.getMinBufferSize(
//                SamplingRate,
//                AudioFormat.CHANNEL_OUT_MONO,
//                AudioFormat.ENCODING_PCM_16BIT);
//        // AudioTrack.Builder API level 26より
//        AudioTrack audioTrack = new AudioTrack.Builder()
//                .setAudioAttributes(new AudioAttributes.Builder()
//                        .setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION)
//                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
//                        .build())
//                .setAudioFormat(new AudioFormat.Builder()
//                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
//                        .setSampleRate(SamplingRate)
//                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
//                        .build())
//                .setBufferSizeInBytes(bufSize)
//                .build();
//        // 再生
//        audioTrack.play();
//        // ヘッダ44byteをオミット（プツッがなくなる）44byteはヘッダ情報
//        assert wavData != null;
//        audioTrack.write(wavData, 44, wavData.length-44);
//
//    }

    //MediaPlayerを使用（BGMなどゆっくりでいいやつ）
//    private boolean audioSetup(){
//        // インタンスを生成
//        mediaPlayer = new MediaPlayer();
//
//        //音楽ファイル名, あるいはパス
//        String filePath = "music.mp3";
//
//        boolean fileCheck = false;
//
//        // assetsから mp3 ファイルを読み込み
//        try(AssetFileDescriptor afdescripter = getAssets().openFd(filePath))
//        {
//            // MediaPlayerに読み込んだ音楽ファイルを指定
//            mediaPlayer.setDataSource(afdescripter.getFileDescriptor(),
//                    afdescripter.getStartOffset(),
//                    afdescripter.getLength());
//            // 音量調整を端末のボタンに任せる
//            setVolumeControlStream(AudioManager.STREAM_MUSIC);
//            mediaPlayer.prepare();
//            fileCheck = true;
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//
//        return fileCheck;
//    }
//
//    private void audioPlay() {
//
//        if (mediaPlayer == null) {
//            // audio ファイルを読出し
//            if (audioSetup()){
//                Toast.makeText(getApplication(), "Rread audio file", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(getApplication(), "Error: read audio file", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//        else{
//            // 繰り返し再生する場合
//            mediaPlayer.stop();
//            mediaPlayer.reset();
//            // リソースの解放
//            mediaPlayer.release();
//        }
//
//        // 再生する
//        mediaPlayer.start();
//
//        // 終了を検知するリスナー
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                Log.d("debug","end of audio");
//                audioStop();
//            }
//        });
//        // lambda
////        mediaPlayer.setOnCompletionListener( mp -> {
////            Log.d("debug","end of audio");
////            audioStop();
////        });
//
//    }
//
//    private void audioStop() {
//        // 再生終了
//        mediaPlayer.stop();
//        // リセット
//        mediaPlayer.reset();
//        // リソースの解放
//        mediaPlayer.release();
//
//        mediaPlayer = null;
//    }
}

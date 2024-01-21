package com.example.myapplication;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class TestActivity extends Activity {

    // hello_world.wav のサンプリングレート
    private static final int SamplingRate = 32000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // ボタンを設定
        Button button = findViewById(R.id.button);

        // リスナーをボタンに登録
        // expression lambda
        button.setOnClickListener(v-> wavPlay());

    }

    private void wavPlay() {
        InputStream input = null;
        byte[] wavData = null;

        try {
            // wavを読み込む
            input = getResources().openRawResource(R.raw.pinpon);
            wavData = new byte[input.available()];

            // input.read(wavData)
            String readBytes = String.format(
                    Locale.US, "read bytes = %d",input.read(wavData));
            // input.read(wavData)のwarning回避のためだけ
            Log.d("debug",readBytes);
            input.close();
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            Log.d("debug", "error");
        } finally{
            try{
                if(input != null) input.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        // バッファサイズの計算
        int bufSize = android.media.AudioTrack.getMinBufferSize(
                SamplingRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        // AudioTrack.Builder API level 26より
        AudioTrack audioTrack = new AudioTrack.Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build())
                .setAudioFormat(new AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(SamplingRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build())
                .setBufferSizeInBytes(bufSize)
                .build();

        // 再生
        audioTrack.play();

        //audioTrack.write(wavData, 0, wavData.length);
        // ヘッダ44byteをオミット
        assert wavData != null;
        audioTrack.write(wavData, 44, wavData.length-44);

    }
}
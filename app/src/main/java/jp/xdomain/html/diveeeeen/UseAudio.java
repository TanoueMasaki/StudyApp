package jp.xdomain.html.diveeeeen;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.util.Log;

import androidx.annotation.RawRes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class UseAudio extends Activity{

      //wavPlayで使うサンプリングレート
    private static final int SamplingRate = 88000;

    public void wavPlay(Activity activity,@RawRes int id) {

        InputStream input = null;
        byte[] wavData = null;

        try {
            // wavを読み込む（引数で受け取ったidを読み込む）
            input = activity.getResources().openRawResource(id);
            wavData = new byte[input.available()];
            // input.read(wavData)
            String readBytes = String.format(Locale.US, "read bytes = %d",input.read(wavData));
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
        int bufSize = AudioTrack.getMinBufferSize(
                SamplingRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        // AudioTrack.Builder API level 26より
        AudioTrack audioTrack = new AudioTrack.Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAudioFormat(new AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(SamplingRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build())
                .setBufferSizeInBytes(bufSize)
                //AudioTrack.MODE_STREAMかAudioTrack.MODE_STATICを引数modeで受け取って設定する
//                .setTransferMode(mode)
                .build();
        // 再生
        audioTrack.play();
//        audioTrack.setLoopPoints(0,bufSize-1,-1);
        // ヘッダ44byteをオミット（プツッがなくなる）44byteはヘッダ情報
        assert wavData != null;
        audioTrack.write(wavData, 44, wavData.length-44);
    }
}

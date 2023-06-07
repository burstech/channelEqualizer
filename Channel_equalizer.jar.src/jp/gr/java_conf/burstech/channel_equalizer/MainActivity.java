package jp.gr.java_conf.burstech.channel_equalizer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioRecord;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MainActivity extends Activity {
  private static final int CUSTOM_DIALOG = 0;
  
  public static int ET = 0;
  
  public static int ET2 = 0;
  
  public static int ET3 = 0;
  
  public static int ET4 = 0;
  
  static final int FFT_SIZE = 4096;
  
  static final int SAMPLING_RATE = 44100;
  
  float FEQ;
  
  AudioRecord audioRec = null;
  
  boolean bIsRecording = false;
  
  int bufSize;
  
  byte[] byte1;
  
  double dB_baseline = Math.pow(2.0D, 15.0D) * 4096.0D * Math.sqrt(2.0D);
  
  Thread fft;
  
  View layout;
  
  public VisualizerView mVisualizerView;
  
  double resol = 10.7666015625D;
  
  TextView textView;
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2130903063);
    RelativeLayout relativeLayout = (RelativeLayout)findViewById(2131296319);
    this.mVisualizerView = new VisualizerView((Context)this, null);
    relativeLayout.addView(this.mVisualizerView);
    ((Button)findViewById(2131296320)).setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            MainActivity.this.showDialog(0);
          }
        });
    this.bufSize = AudioRecord.getMinBufferSize(44100, 16, 2);
    if (4096 > this.bufSize)
      this.bufSize = 4096; 
    Toast.makeText((Context)this, "PrivacyPolicy⇒http://burstech.net/privacy-policy/privacy%20-policy.html", 0).show();
  }
  
  @SuppressLint({"InflateParams"})
  protected Dialog onCreateDialog(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 0:
        break;
    } 
    final View inputView = LayoutInflater.from((Context)this).inflate(2130903064, null);
    return (Dialog)(new AlertDialog.Builder((Context)this)).setIcon(17301543).setTitle("Edit Hz").setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            int i;
            boolean bool2;
            boolean bool1 = true;
            EditText editText1 = (EditText)inputView.findViewById(2131296323);
            EditText editText2 = (EditText)inputView.findViewById(2131296325);
            EditText editText3 = (EditText)inputView.findViewById(2131296327);
            EditText editText4 = (EditText)inputView.findViewById(2131296329);
            String str1 = editText1.getText().toString();
            String str2 = editText2.getText().toString();
            String str3 = editText3.getText().toString();
            String str4 = editText4.getText().toString();
            if (str1.length() > 0) {
              param1Int = 1;
            } else {
              param1Int = 0;
            } 
            if (str2.length() > 0) {
              i = 1;
            } else {
              i = 0;
            } 
            if (str3.length() > 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            if (str4.length() <= 0)
              bool1 = false; 
            if ((bool2 & i & param1Int & bool1) != 0) {
              MainActivity.ET = Integer.valueOf(str1).intValue();
              MainActivity.ET2 = Integer.valueOf(str2).intValue();
              MainActivity.ET3 = Integer.valueOf(str3).intValue();
              MainActivity.ET4 = Integer.valueOf(str4).intValue();
              MainActivity.this.mVisualizerView.Act();
            } 
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {}
        }).create();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4) {
      finish();
      return false;
    } 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause() {
    super.onPause();
    if (this.bIsRecording)
      this.bIsRecording = false; 
  }
  
  protected void onResume() {
    super.onResume();
    this.audioRec = new AudioRecord(1, 44100, 16, 2, this.bufSize * 2);
    this.audioRec.startRecording();
    this.bIsRecording = true;
    this.fft = new Thread(new Runnable() {
          public void run() {
            byte[] arrayOfByte = new byte[MainActivity.this.bufSize * 2];
            label26: while (true) {
              if (!MainActivity.this.bIsRecording) {
                MainActivity.this.audioRec.stop();
                MainActivity.this.audioRec.release();
                return;
              } 
              MainActivity.this.audioRec.read(arrayOfByte, 0, arrayOfByte.length);
              ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte);
              byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
              short[] arrayOfShort = new short[MainActivity.this.bufSize];
              for (int i = byteBuffer.position();; i++) {
                double[] arrayOfDouble1;
                final double[] dbfs;
                if (i >= byteBuffer.capacity() / 2) {
                  FFT4g fFT4g = new FFT4g(4096);
                  arrayOfDouble1 = new double[4096];
                  for (i = 0;; i++) {
                    if (i >= 4096) {
                      fFT4g.rdft(1, arrayOfDouble1);
                      arrayOfDouble2 = new double[2048];
                      double d = -120.0D;
                      i = 0;
                      while (true) {
                        if (i >= 4096) {
                          mhandler.post(new Runnable() {
                                public void run() {
                                  (MainActivity.null.access$0(MainActivity.null.this)).mVisualizerView.update(dbfs);
                                }
                              });
                          continue label26;
                        } 
                        arrayOfDouble2[i / 2] = (int)(20.0D * Math.log10(Math.sqrt(Math.pow(arrayOfDouble1[i], 2.0D) + Math.pow(arrayOfDouble1[i + 1], 2.0D)) / MainActivity.this.dB_baseline));
                        double d1 = d;
                        if (d < arrayOfDouble2[i / 2])
                          d1 = arrayOfDouble2[i / 2]; 
                        i += 2;
                        d = d1;
                      } 
                      break;
                    } 
                    arrayOfDouble1[i] = arrayOfDouble2[i];
                  } 
                  break;
                } 
                arrayOfDouble2[i] = arrayOfDouble1.getShort();
              } 
              break;
            } 
          }
        });
    this.fft.start();
  }
}


/* Location:              C:\Users\Daisuke\Desktop\Channel_equalizer.jar!\jp\gr\java_conf\burstech\channel_equalizer\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
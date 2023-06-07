package jp.gr.java_conf.burstech.channel_equalizer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class VisualizerView extends View {
  private static float DISPLAY_MINIMUM_DB = -1000.0F;
  
  static int feq;
  
  public static double[] fftData_;
  
  private static int samplingRate_ = 44100;
  
  private int BAND_DEFAULT_NUMBER = 32;
  
  private float BAND_INNER_OFFSET = 1.0F;
  
  private float BAND_MAXIMUM_HZ = 28000.0F;
  
  private float BAND_MINIMUM_HZ = 40.0F;
  
  private float DISPLAY_MAXIMUM_HZ = 20000.0F;
  
  private float DISPLAY_MINIMUM_HZ = 35.0F;
  
  private int FFT_DATA_SHADER_END_COLOR_ID = 17170443;
  
  @SuppressLint({"InlinedApi"})
  private int FFT_DATA_SHADER_START_COLOR_ID = 17170451;
  
  private float FFT_PEAK_VALUE = (float)(100.0D * Math.sqrt(0.3D));
  
  public int Hi_mid_hi_HZ = 8000;
  
  public int Hi_mid_min_HZ = 400;
  
  private int LOG_GRID_COLOR_ID = 17170439;
  
  public int Low_mid_hi_HZ = 1600;
  
  public int Low_mid_min_HZ = 80;
  
  private int bandNumber_;
  
  private RectF[] bandRects_;
  
  private int bandRegionMaxX_;
  
  private int bandRegionMinX_;
  
  private int bandWidth_;
  
  private int currentHeight_;
  
  private int currentWidth_;
  
  private Paint fftDataPaint_;
  
  private LinearGradient fftDataShader_;
  
  int left = getLeft();
  
  private float logBlockWidth_;
  
  private float[] logGridDataX_;
  
  private float[] logGridDataY_;
  
  private Paint logGridPaint_;
  
  private float logOffsetX_;
  
  float maxLog = (float)Math.log10(this.DISPLAY_MAXIMUM_HZ);
  
  private int maxLogarithm_;
  
  float minLog = (float)Math.log10(this.DISPLAY_MINIMUM_HZ);
  
  private int minLogarithm_;
  
  int right = getRight();
  
  float xtouch;
  
  float ytouch;
  
  public VisualizerView(Context paramContext, float paramFloat) {
    super(paramContext);
    initialize();
  }
  
  public VisualizerView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initialize();
  }
  
  public VisualizerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    initialize();
  }
  
  private void calculateViewSizeDependedData() {
    this.minLogarithm_ = (int)Math.floor(Math.log10(this.DISPLAY_MINIMUM_HZ));
    this.maxLogarithm_ = (int)Math.ceil(Math.log10(this.DISPLAY_MAXIMUM_HZ));
    this.logBlockWidth_ = (float)(getWidth() / (Math.log10(this.DISPLAY_MAXIMUM_HZ) - Math.log10(this.DISPLAY_MINIMUM_HZ)));
    this.logOffsetX_ = (float)(Math.log10(this.DISPLAY_MINIMUM_HZ) * this.logBlockWidth_);
    this.logGridDataX_ = new float[10 - (int)(this.DISPLAY_MINIMUM_HZ / Math.pow(10.0D, this.minLogarithm_)) + (this.maxLogarithm_ - this.minLogarithm_ - 2) * 9 + (int)(this.DISPLAY_MAXIMUM_HZ / Math.pow(10.0D, (this.maxLogarithm_ - 1)))];
    int i = 0;
    int j = getLeft();
    int k = getRight();
    int m = this.minLogarithm_;
    label28: while (true) {
      if (m >= this.maxLogarithm_) {
        int n = (int)Math.ceil(30.0D);
        float f = (getHeight() / 300 * 10);
        this.logGridDataY_ = new float[n];
        i = getTop();
        for (m = 0;; m++) {
          if (m >= n) {
            this.bandRegionMinX_ = (int)(Math.log10(this.BAND_MINIMUM_HZ) * this.logBlockWidth_ - this.logOffsetX_);
            this.bandRegionMaxX_ = (int)(Math.log10(this.BAND_MAXIMUM_HZ) * this.logBlockWidth_ - this.logOffsetX_);
            this.bandWidth_ = (this.bandRegionMaxX_ - this.bandRegionMinX_) / this.bandNumber_;
            n = getBottom();
            for (m = 0;; m++) {
              if (m >= this.bandNumber_) {
                int i1 = getResources().getColor(this.FFT_DATA_SHADER_START_COLOR_ID);
                m = getResources().getColor(this.FFT_DATA_SHADER_END_COLOR_ID);
                this.fftDataShader_ = new LinearGradient(0.0F, n, 0.0F, i, i1, m, Shader.TileMode.CLAMP);
                this.fftDataPaint_.setShader((Shader)this.fftDataShader_);
                return;
              } 
              (this.bandRects_[m]).bottom = n;
              (this.bandRects_[m]).top = n;
              (this.bandRects_[m]).left = (this.bandRegionMinX_ + this.bandWidth_ * m) + this.BAND_INNER_OFFSET;
              (this.bandRects_[m]).right = (this.bandRects_[m]).left + this.bandWidth_ - this.BAND_INNER_OFFSET;
            } 
            break;
          } 
          this.logGridDataY_[m] = i + m * f;
        } 
        break;
      } 
      byte b = 1;
      while (true) {
        if (b >= 10) {
          m++;
          continue label28;
        } 
        float f = (float)Math.log10(Math.pow(10.0D, m) * b) * this.logBlockWidth_ - this.logOffsetX_;
        int n = i;
        if (f >= j) {
          n = i;
          if (f <= k) {
            this.logGridDataX_[i] = f;
            n = i + 1;
          } 
        } 
        b++;
        i = n;
      } 
      break;
    } 
  }
  
  private void drawFft(Canvas paramCanvas) {
    double d = (fftData_.length / 2);
    int i = getBottom();
    int j = getRight();
    int k = getLeft();
    for (byte b = 1;; b++) {
      if (b >= d)
        return; 
      double d1 = (samplingRate_ * b / 2) / d;
      float f1 = (float)(20.0D * Math.log10(((float)Math.sqrt(Math.pow((float)fftData_[b * 2], 2.0D) + Math.pow((float)fftData_[b * 2 + 1], 2.0D)) / this.FFT_PEAK_VALUE)));
      float f2 = (float)(Math.log10(d1) * this.logBlockWidth_) - this.logOffsetX_;
      if (f2 >= k && f2 <= j) {
        float f = i;
        f1 = DISPLAY_MINIMUM_DB / f1;
        paramCanvas.drawLine(f2, i, f2, f + f1 * 1.0F, this.fftDataPaint_);
      } 
    } 
  }
  
  private void drawLogGrid(Canvas paramCanvas) {
    int i = getBottom();
    int j = getTop();
    float[] arrayOfFloat = this.logGridDataX_;
    int k = arrayOfFloat.length;
    for (byte b = 0;; b++) {
      if (b >= k) {
        i = getWidth();
        arrayOfFloat = this.logGridDataY_;
        j = arrayOfFloat.length;
        for (b = 0;; b++) {
          if (b >= j)
            return; 
          float f1 = arrayOfFloat[b];
          paramCanvas.drawLine(0.0F, f1, i, f1, this.logGridPaint_);
        } 
        break;
      } 
      float f = arrayOfFloat[b];
      paramCanvas.drawLine(f, i, f, j, this.logGridPaint_);
    } 
  }
  
  private void initialize() {
    this.bandNumber_ = this.BAND_DEFAULT_NUMBER;
    this.bandRects_ = new RectF[this.bandNumber_];
    for (byte b = 0;; b++) {
      if (b >= this.bandNumber_) {
        this.fftDataPaint_ = new Paint();
        this.fftDataPaint_.setStrokeWidth(1.0F);
        this.fftDataPaint_.setAntiAlias(true);
        this.logGridPaint_ = new Paint();
        this.logGridPaint_.setStrokeWidth(1.0F);
        this.logGridPaint_.setAntiAlias(true);
        this.logGridPaint_.setColor(getResources().getColor(this.LOG_GRID_COLOR_ID));
        return;
      } 
      this.bandRects_[b] = new RectF();
    } 
  }
  
  public static void setSamplingRate(int paramInt) {
    samplingRate_ = paramInt / 1000;
  }
  
  public void Act() {
    int i = MainActivity.ET;
    int j = MainActivity.ET2;
    int k = MainActivity.ET3;
    int m = MainActivity.ET4;
    this.Low_mid_min_HZ = i;
    this.Low_mid_hi_HZ = j;
    this.Hi_mid_min_HZ = k;
    this.Hi_mid_hi_HZ = m;
    Log.d("Low_mid_min_HZ", (new StringBuilder(String.valueOf(i))).toString());
  }
  
  @SuppressLint({"WrongCall"})
  public void drawtext(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    Paint paint = new Paint();
    if (paramCanvas != null) {
      int i = getWidth();
      int j = getHeight();
      paint.setTextSize(100.0F);
      paint.setColor(-65536);
      paramCanvas.drawText(String.valueOf(feq) + "Hz", 20.0F, 100.0F, paint);
      paint.setTextSize(70.0F);
      paint.setColor(-7829368);
      paramCanvas.drawText("Low-MID", (float)((i / 8) * 1.4D), (j / 6) * 1.5F + (i / 8) * 1.3F, paint);
      paramCanvas.drawText("Hi-MID", (float)((i / 8) * 5.5D), (j / 6) * 1.5F + (i / 8) * 1.3F, paint);
      paint.setTextSize(50.0F);
      paint.setColor(-7829368);
      paramCanvas.drawText((new StringBuilder(String.valueOf(this.Low_mid_min_HZ))).toString(), (i / 8), (j / 6) * 1.5F + (i / 8) * 2.0F, paint);
      paramCanvas.drawText((new StringBuilder(String.valueOf(this.Low_mid_hi_HZ))).toString(), (int)((i / 8) * 2.6F), (j / 6) * 1.5F + (i / 8) * 2.0F, paint);
      paramCanvas.drawText((new StringBuilder(String.valueOf(this.Hi_mid_min_HZ))).toString(), (i / 8 * 5), (j / 6) * 1.5F + (i / 8) * 2.0F, paint);
      paramCanvas.drawText((new StringBuilder(String.valueOf(this.Hi_mid_hi_HZ))).toString(), (int)((i / 8) * 6.6F), (j / 6) * 1.5F + (i / 8) * 2.0F, paint);
      if (this.ytouch > (j / 4)) {
        int k = getBottom();
        int m = getTop();
        paint.setColor(-65536);
        paramCanvas.drawLine(this.xtouch, k, 1.0F + this.xtouch, m, paint);
      } 
      paint.setStyle(Paint.Style.STROKE);
      paint.setStrokeWidth(5.0F);
      paint.setColor(-12303292);
      int[] arrayOfInt = new int[11];
      arrayOfInt[0] = 60;
      arrayOfInt[1] = 30;
      arrayOfInt[2] = 0;
      arrayOfInt[3] = -30;
      arrayOfInt[4] = -60;
      arrayOfInt[5] = -90;
      arrayOfInt[6] = -120;
      arrayOfInt[7] = -150;
      arrayOfInt[8] = -180;
      arrayOfInt[9] = -210;
      arrayOfInt[10] = -240;
      paramCanvas.drawArc(new RectF((i / 8), (j / 6) * 1.5F, (i / 8 * 3), (j / 6) * 1.5F + (i / 8 * 2)), 120.0F, 300.0F, false, paint);
      for (byte b = 0;; b++) {
        if (b >= 11) {
          paramCanvas.drawArc(new RectF((i / 8 * 5), (j / 6) * 1.5F, (i / 8 * 7), (j / 6) * 1.5F + (i / 8 * 2)), 120.0F, 300.0F, false, paint);
          for (b = 0;; b++) {
            if (b >= 11) {
              byte b1;
              paint.setStyle(Paint.Style.STROKE);
              paint.setStrokeWidth(10.0F);
              paint.setColor(-65536);
              float f5 = (float)Math.log10(this.Low_mid_min_HZ);
              float f6 = (float)Math.log10(this.Low_mid_hi_HZ);
              if (this.Low_mid_min_HZ <= feq) {
                b = 1;
              } else {
                b = 0;
              } 
              if (feq <= this.Low_mid_hi_HZ) {
                b1 = 1;
              } else {
                b1 = 0;
              } 
              if ((b & b1) != 0) {
                f6 = -240.0F + (float)(Math.log10(feq) - f5) * 300.0F / (f6 - f5);
                f5 = (float)((i / 8 * 2) + Math.cos(f6 * 0.017453292519943295D) * i / 8.0D);
                float f = (float)(((j / 6) * 1.5F + (i / 8)) + Math.sin(f6 * 0.017453292519943295D) * i / 8.0D);
                paramCanvas.drawLine(f5, f, f5 + (float)Math.cos(f6 * 0.017453292519943295D) * 50.0F, f + (float)Math.sin(f6 * 0.017453292519943295D) * 50.0F, paint);
              } 
              f6 = (float)Math.log10(this.Hi_mid_min_HZ);
              f5 = (float)Math.log10(this.Hi_mid_hi_HZ);
              if (this.Hi_mid_min_HZ <= feq) {
                b = 1;
              } else {
                b = 0;
              } 
              if (feq <= this.Hi_mid_hi_HZ) {
                b1 = 1;
              } else {
                b1 = 0;
              } 
              if ((b & b1) != 0) {
                float f = -240.0F + (float)(Math.log10(feq) - f6) * 300.0F / (f5 - f6);
                f5 = (float)((i / 8 * 6) + Math.cos(f * 0.017453292519943295D) * i / 8.0D);
                f6 = (float)(((j / 6) * 1.5F + (i / 8)) + Math.sin(f * 0.017453292519943295D) * i / 8.0D);
                paramCanvas.drawLine(f5, f6, f5 + (float)Math.cos(f * 0.017453292519943295D) * 50.0F, f6 + (float)Math.sin(f * 0.017453292519943295D) * 50.0F, paint);
              } 
              return;
            } 
            float f4 = (float)((i / 8 * 6) + Math.cos(arrayOfInt[b] * 0.017453292519943295D) * i / 8.0D);
            float f3 = (float)(((j / 6) * 1.5F + (i / 8)) + Math.sin(arrayOfInt[b] * 0.017453292519943295D) * i / 8.0D);
            paramCanvas.drawLine(f4, f3, f4 + (float)Math.cos(arrayOfInt[b] * 0.017453292519943295D) * 50.0F, f3 + (float)Math.sin(arrayOfInt[b] * 0.017453292519943295D) * 50.0F, paint);
          } 
          break;
        } 
        float f1 = (float)((i / 8 * 2) + Math.cos(arrayOfInt[b] * 0.017453292519943295D) * i / 8.0D);
        float f2 = (float)(((j / 6) * 1.5F + (i / 8)) + Math.sin(arrayOfInt[b] * 0.017453292519943295D) * i / 8.0D);
        paramCanvas.drawLine(f1, f2, f1 + (float)Math.cos(arrayOfInt[b] * 0.017453292519943295D) * 50.0F, f2 + (float)Math.sin(arrayOfInt[b] * 0.017453292519943295D) * 50.0F, paint);
      } 
    } 
  }
  
  public int getSamplingRate() {
    return samplingRate_;
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.currentWidth_ != getWidth() || this.currentHeight_ != getHeight())
      calculateViewSizeDependedData(); 
    drawLogGrid(paramCanvas);
    if (fftData_ != null) {
      drawFft(paramCanvas);
      drawtext(paramCanvas);
    } 
  }
  
  @SuppressLint({"ClickableViewAccessibility"})
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    this.xtouch = paramMotionEvent.getX();
    this.ytouch = paramMotionEvent.getY();
    int i = getHeight();
    if (this.ytouch > (i / 4)) {
      float f = (this.maxLog - this.minLog) / getWidth();
      feq = (int)Math.pow(10.0D, (this.xtouch * f + this.minLog));
    } 
    return true;
  }
  
  public void onWindowFocusChanged(boolean paramBoolean) {
    super.onWindowFocusChanged(paramBoolean);
    this.currentHeight_ = getHeight();
    this.currentWidth_ = getWidth();
    calculateViewSizeDependedData();
  }
  
  public void update(double[] paramArrayOfdouble) {
    fftData_ = paramArrayOfdouble;
    invalidate();
  }
}


/* Location:              C:\Users\Daisuke\Desktop\Channel_equalizer.jar!\jp\gr\java_conf\burstech\channel_equalizer\VisualizerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
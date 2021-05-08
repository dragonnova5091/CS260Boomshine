package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class TimedBoomshineView extends BoomshineView
{

  private CountDownTimer mTimer;
  private String mTimeleft;


  public TimedBoomshineView( Context context, Display display )
  {
    super( context, display );

    mTimer = new CountDownTimer(10000, 1000) {

      public void onTick(long millisUntilFinished) {
        mTimeleft = "" + millisUntilFinished / 1000;
      }

      public void onFinish() {
        mTimeleft = "0";
        stopTimer();
      }
    }.start();
  }


  @Override
  public void onDraw( Canvas canvas )
  {
    super.onDraw( canvas );

    final int TEXT_SIZE = 24;
    float textSize = TEXT_SIZE * getResources().getDisplayMetrics().density;
    mPaint.setColor( Color.BLACK);
    mPaint.setTextSize(textSize);

    canvas.drawText( "Time: " + mTimeleft, 10,
            canvas.getHeight() - textSize - 10, mPaint);
  }

  @Override
  public boolean onTouchEvent( MotionEvent event )
  {

    if (!mbPlaying)
    {
      mTimer = new CountDownTimer(10000, 1000) {

        public void onTick(long millisUntilFinished) {
          mTimeleft = "" + millisUntilFinished / 1000;
        }

        public void onFinish() {
          mTimeleft = "0";
          stopTimer();
        }
      }.start();
    }

    return super.onTouchEvent( event );

  }

  public void stopTimer()
  {
    mbPlaying = false;
  }
}

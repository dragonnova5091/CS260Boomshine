package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;

/**
 * Defines the TimedBoomshineView class that contains the Boomshine
 * game logic and view layout.
 *
 * @author Hung Huynh And Ethan Hunter
 * @version 1.0
 * @since 5.8.2021
 */
public class TimedBoomshineView extends BoomshineView
{
  private CountDownTimer mTimer;
  private String mTimeleft;

  /**
   * Constructor that initializes the TimedBoomshineView
   *
   * @param context reference to application-specific resources
   * @param display the display
   */
  public TimedBoomshineView ( Context context, Display display )
  {
    super ( context, display );

    mTimer = new CountDownTimer ( 60000, 1000 )
    {
      /**
       * On every tick of the timer display time left.
       *
       * @param millisUntilFinished milliseconds left
       */
      public void onTick ( long millisUntilFinished )
      {
        mTimeleft = "" + millisUntilFinished / 1000;
      }

      /**
       * On timer end display 0 and stop Game.
       */
      public void onFinish ()
      {
        mTimeleft = "0";
        stopTimer ();
      }
    }.start ();
  }

  /**
   * Draw method that is repeatedly called for animation
   *
   * @param canvas used to host the draw calls
   */
  @Override
  public void onDraw ( Canvas canvas )
  {
    super.onDraw ( canvas );

    final int TEXT_SIZE = 24;
    float textSize = TEXT_SIZE * getResources ().getDisplayMetrics ().density;

    mPaint.setColor ( Color.BLACK );
    mPaint.setTextSize ( textSize );

    canvas.drawText ( "Time: " + mTimeleft, 10,
            canvas.getHeight () - textSize - 10, mPaint );
  }

  /**
   * Method that handles when a user touches the screen
   *
   * @param event used to get the event
   * @return true True if there was a click event
   */
  @Override
  public boolean onTouchEvent ( MotionEvent event )
  {

    if ( !mbPlaying )
    {
      mTimer = new CountDownTimer ( 10000, 1000 )
      {
        /**
         * On every tick of the timer display time left.
         *
         * @param millisUntilFinished milliseconds left
         */
        public void onTick ( long millisUntilFinished )
        {
          mTimeleft = "" + millisUntilFinished / 1000;
        }

        /**
         * On timer end display 0 and stop Game.
         */
        public void onFinish ()
        {
          mTimeleft = "0";
          stopTimer ();
        }
      }.start ();
    }

    return super.onTouchEvent ( event );
  }

  /**
   * Stops the game.
   */
  public void stopTimer ()
  {
    mbPlaying = false;
  }
}

package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Defines the BoomshineView class display and interaction for Boomshine's
 * view.
 *
 * @author Hung Huynh And Ethan Hunter
 * @version 1.0
 * @since 5.8.2021
 */
public class BoomshineView extends View
{
  private Boomshine mBoomshine;
  final ArrayList<ExpandingBall> mExpanding;
  final ArrayList<BoundedBouncingBall> mMoving;
  protected final Paint mPaint;
  private final Context mContext;
  private final Display mDisplay;
  protected int mHighscore;
  private boolean mbPlaced;
  protected boolean mbPlaying;
  private MediaPlayer mMediaPlayer;
  private MediaPlayer mBackgroundSound;

  /**
   * Constructor that initializes the BoomshineView
   *
   * @param context reference to application-specific resources
   * @param display the display
   */
  public BoomshineView ( Context context, Display display )
  {
    super ( context );
    setFocusable ( true );
    mBoomshine = new Boomshine ();
    mPaint = new Paint ();
    mContext = context;
    mDisplay = display;
    mExpanding = new ArrayList<> ();
    mMoving = new ArrayList<> ();
    mbPlaced = false;
    mbPlaying = true;
    mHighscore = 0;

    invalidate ();
  }

  /**
   * Stops the background music.
   */
  public void stopMusic ()
  {
    mBackgroundSound.pause ();
    mMediaPlayer.pause ();
  }

  /**
   * Draw method that is repeatedly called for animation
   *
   * @param canvas used to host the draw calls
   */
  @Override
  public void onDraw ( Canvas canvas )
  {
    final int TEXT_SIZE = 24;
    float textSize;

    if ( mBackgroundSound == null )
    {
      mBackgroundSound = MediaPlayer.create ( this.mContext, R.raw.main_music );
      mBackgroundSound.start ();
      mBackgroundSound.setLooping ( true );
    }

    if ( mMoving.size () == 0 && mExpanding.size () == 0 )
    {
      for ( int i = 0; i < mBoomshine.getNumBallsForWin () * 2; i++ )
      {
        addMovingBall ( canvas );
      }
    }

    update ();

    for ( int i = 0; i < mExpanding.size (); i++ )
    {
      mExpanding.get ( i ).doDraw ( canvas );
    }

    for ( int i = 0; i < mMoving.size (); i++ )
    {
      mMoving.get ( i ).doDraw ( canvas );
    }

    if ( mbPlaying )
    {
      textSize = TEXT_SIZE * getResources ().getDisplayMetrics ().density;
      mPaint.setColor ( Color.BLACK );
      mPaint.setTextSize ( textSize );
      canvas.drawText ( "Total score: " + ( mBoomshine.getTotalScore () +
              mBoomshine.getBallsPopped () ), 10, textSize + 10, mPaint );
      canvas.drawText ( "Level: " + mBoomshine.getNumBallsForWin (), 10,
              ( textSize + 10 ) * 2, mPaint );
      canvas.drawText ( "Balls Needed: " + mBoomshine.getNumBallsForWin (), 10,
              ( textSize + 10 ) * 3, mPaint );
      canvas.drawText ( "Current Round Score: " + mBoomshine.getBallsPopped (),
              10, ( textSize + 10 ) * 4, mPaint );
      canvas.drawText ( "Lives: " + mBoomshine.getLives (), 10,
              ( textSize + 10 ) * 5, mPaint );
    }
    else
    {
      float tempint;

      saveHighScore ();

      textSize = TEXT_SIZE * 2 * getResources ().getDisplayMetrics ().density;
      tempint = textSize;
      mPaint.setColor ( Color.BLACK );
      mPaint.setTextSize ( textSize );
      canvas.drawText ( "You Lose.", getWidth () / 4.0f, ( getHeight () / 2.0f ) -
              ( textSize / 2 ), mPaint );
      textSize = TEXT_SIZE * getResources ().getDisplayMetrics ().density;
      mPaint.setTextSize ( textSize );
      canvas.drawText ( "Total score: " + ( mBoomshine.getTotalScore () +
                      mBoomshine.getBallsPopped () ), getWidth () / 4.0f,
              ( getHeight () / 2.0f ) - ( int ) ( tempint * 1.5 ), mPaint );
      canvas.drawText ( "High score: " + ( getHighScore () ),
              getWidth () / 4.0f, ( getHeight () / 2.0f )
                      - ( int ) ( tempint * 2 ), mPaint );
    }
    checkWin ( canvas );

    invalidate ();
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
    if ( event.getAction () != MotionEvent.ACTION_DOWN )
    {
      return super.onTouchEvent ( event );
    }
    if ( !mbPlaced && mbPlaying )
    {
      if ( mMediaPlayer != null )
      {
        mMediaPlayer.release ();
      }
      mMediaPlayer = MediaPlayer.create ( getContext (), R.raw.click );
      mMediaPlayer.start ();

      addExpandingBall ( event );
      mbPlaced = true;
    }
    if ( !mbPlaying )
    {
      mBoomshine = new Boomshine ();
      startNewGame ();
      mbPlaying = true;
    }

    return true;
  }

  /**
   * Checks win after every round.
   *
   * @param canvas Canvas used between rounds
   */
  public void checkWin ( Canvas canvas )
  {
    if ( mbPlaced )
    {
      if ( mExpanding.size () == 0 &&
              mBoomshine.getBallsPopped () >= mBoomshine.getNumBallsForWin () )
      {
        nextRound ( canvas );
        mbPlaced = false;
      }
      else if ( mExpanding.size () == 0 &&
              mBoomshine.getBallsPopped () < mBoomshine.getNumBallsForWin () )
      {
        if ( reset () <= 0 )
        {
          mbPlaying = false;
        }
        mbPlaced = false;
      }
    }
  }

  /**
   * Create the next round upon winning the previous.
   *
   * @param canvas Canvas used between rounds
   */
  private void nextRound ( Canvas canvas )
  {
    mExpanding.clear ();
    mMoving.clear ();

    mBoomshine.nextRound ();

    for ( int i = 0; i < mBoomshine.getNumBallsForWin () * 2; i++ )
    {
      addMovingBall ( canvas );
      invalidate ();
    }
  }

  /**
   * Updates all ball movements and calls collision handling
   */
  private void update ()
  {
    boolean tempBool;
    for ( int i = 0; i < mExpanding.size (); i++ )
    {
      for ( int j = 0; j < mMoving.size (); j++ )
      {
        if ( mExpanding.get ( i ).collide ( mMoving.get ( j ) ) )
        {
          if ( mMediaPlayer != null )
          {
            mMediaPlayer.release ();
          }
          mMediaPlayer = MediaPlayer.create ( getContext (), R.raw.collision );
          mMediaPlayer.start ();
          addExpandingBall ( mMoving.get ( j ) );

          mMoving.remove ( j );
          mBoomshine.popOne ();

        }
      }

      tempBool = mExpanding.get ( i ).expandBall ();
      if ( tempBool )
      {
        mExpanding.remove ( i );
      }
    }

    for ( int i = 0; i < mMoving.size (); i++ )
    {
      mMoving.get ( i ).move ();
      mMoving.get ( i ).Bounce ();
    }
  }

  /**
   * Resets the game for same round.
   *
   * @return tempint Number of lives left.
   */
  public int reset ()
  {
    mExpanding.clear ();
    mMoving.clear ();

    return mBoomshine.reset ();
  }

  /**
   * Resets the game for new boomshine game.
   */
  public void startNewGame ()
  {
    mBoomshine = new Boomshine ();
    mExpanding.clear ();
    mMoving.clear ();
    mbPlaying = true;
    mbPlaced = false;

    invalidate ();
  }

  /**
   * Adds moving balls randomly to screen.
   *
   * @param canvas The canvas the balls are drawn on.
   */
  private void addMovingBall ( Canvas canvas )
  {
    int mScreenHeight = canvas.getHeight();
    int mScreenWidth = canvas.getWidth();
    Random rand = new Random ();
    int image = getRandImage ();
    double mVelocityMultiplier = 10;
    mMoving.add ( new BoundedBouncingBall ( mContext, mDisplay, image,
            rand.nextInt (mScreenWidth),
            rand.nextInt (mScreenHeight),
            ( rand.nextDouble () * 2 * mVelocityMultiplier)
                    - mVelocityMultiplier,
            ( rand.nextDouble () * 2 * mVelocityMultiplier)
                    - mVelocityMultiplier,
            0, mScreenHeight, 0, mScreenWidth) );
  }

  /**
   * Adds expanding balls to screen where the user clicked.
   *
   * @param event The event the ball are drawn at.
   */
  private void addExpandingBall ( MotionEvent event )
  {
    mExpanding.add ( new ExpandingBall ( mContext, mDisplay, R.drawable.ball_yellow,
            ( int ) event.getX (), ( int ) event.getY (),
            0, 0, mBoomshine.getNumBallsForWin (),
            10, 200 ) );
  }

  /**
   * Adds expanding balls to screen where the collision happened.
   *
   * @param movesprite Moving balls that became an expanding ball.
   */
  private void addExpandingBall ( MovingSprite movesprite )
  {
    mExpanding.add ( new ExpandingBall ( mContext, mDisplay, movesprite.getResID (),
            ( int ) ( movesprite.getXUpperLeft () ),
            ( int ) ( movesprite.getYUpperLeft () ),
            0, 0, mBoomshine.getNumBallsForWin (),
            movesprite.getRadius (),
            ( movesprite.getRadius () * 2 )
                    - mBoomshine.getNumBallsForWin () ) );

  }

  /**
   * Picks a random sprite image
   */
  private int getRandImage ()
  {
    int[] images = {
            R.drawable.ball_blue,
            R.drawable.ball_green,
            R.drawable.ball_yellow
    };

    Random r = new Random ();

    int num = r.nextInt ( images.length );

    return images[ num ];
  }

  /**
   * Saves and updates the Highest score.
   */
  public void saveHighScore ()
  {
    if ( mBoomshine.mTotalScore > mHighscore )
    {
      mHighscore = mBoomshine.mTotalScore;
    }
  }

  /**
   * Gets the Highscore.
   */
  public int getHighScore ()
  {
    return mHighscore;
  }
}

package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.util.Log;

/**
 * Defines the Boomshine class that contains the Boomshine game logic.
 *
 * @author Hung Huynh And Ethan Hunter
 * @version 1.0
 * @since 5.8.2021
 */
public class Boomshine
{
  private int mRound;
  private int mNumBallsForWin;
  private int mBallsPopped;
  protected int mTotalScore;
  private int mTotalLives;
  private int mLives;

  /**
   * Initializes member variables to default values.
   */
  public Boomshine ()
  {
    Log.d ( "here", "constructor" );
    mRound = 1;
    mNumBallsForWin = mRound;
    mBallsPopped = 0;

    mTotalScore = 0;
    mTotalLives = 3;
    mLives = mTotalLives;
  }

  /**
   * Resets to the current level.
   */
  public int reset ()
  {
    mNumBallsForWin = mRound;
    mLives--;
    mBallsPopped = 0;
    return mLives;
  }

  public void popOne ()
  {
    mBallsPopped++;
  }

  /**
   * Moves the user on to the next round, updates the total score and lives.
   */
  public int nextRound ()
  {
    int tempScore = mBallsPopped;
    mRound++;
    mTotalScore += mBallsPopped;
    mNumBallsForWin = mRound;
    mLives = mTotalLives;
    mBallsPopped = 0;

    return tempScore;
  }

  /**
   * Returns the number of balls popped.
   *
   * @return mBallsPopped The number of balls popped.
   */
  public int getBallsPopped ()
  {
    return mBallsPopped;
  }

  /**
   * Returns the number of balls to win round.
   *
   * @return mNumBallsForWin Number of balls to win.
   */
  public int getNumBallsForWin ()
  {
    return mNumBallsForWin;
  }

  /**
   * Returns the Total Score.
   *
   * @return mTotalScore The total score.
   */
  public int getTotalScore ()
  {
    return mTotalScore;
  }

  /**
   * Returns the number of lives.
   *
   * @return mLives Number of lives.
   */
  public int getLives ()
  {
    return mLives;
  }
}

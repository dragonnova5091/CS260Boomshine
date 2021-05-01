package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Boomshine
{

    private int mRound;
    private int mNumBallsForWin;
    private int mBallsPopped;
    private int mRoundScore;
    private int mTotalLives;
    private int mLives;
    private boolean mPlacedBall;

    public void onCreate()
    {
        Log.d("here", "constructor");
        mRound = 1;
        mNumBallsForWin = mRound;
        mBallsPopped = 0;
        mRoundScore = 0;
        mTotalLives = 3;
        mLives = mTotalLives;
        mPlacedBall = false;
    }

    public void onDrawUI(Canvas canvas)
    {

    }

    public void reset()
    {

    }

    public void quit()
    {

    }

    public void saveHighScore()
    {

    }

    public void popOne()
    {
        mBallsPopped++;
    }

    public void placeBall() { mPlacedBall = true; }

    public int nextRound()
    {
        int tempScore = mRoundScore;
        mRound++;
        mNumBallsForWin = mRound;
        mBallsPopped = 0;
        mRoundScore = 0;
        mPlacedBall = false;

        return tempScore;
    }

    public int getBallsPopped() { return mBallsPopped; }
    public int getNumBallsForWin() { return  mNumBallsForWin; }
}

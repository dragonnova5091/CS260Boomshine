package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.content.Intent;
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
    protected int mTotalScore;
    private int mTotalLives;
    private int mLives;

    //private GameOverLayoutActivity mGameOver;

    public  Boomshine()
    {
        Log.d("here", "constructor");
        mRound = 1;
        mNumBallsForWin = mRound;
        mBallsPopped = 0;

        mTotalScore = 0;
        mTotalLives = 3;
        mLives = mTotalLives;
    }

    public void onDrawUI(Canvas canvas)
    {

    }

    public int reset()
    {
        mNumBallsForWin = mRound;
        mLives--;
        mBallsPopped = 0;
        return mLives;
    }



    public void quit()
    {

    }


    public void popOne()
    {

        mBallsPopped++;
    }


    public int nextRound()
    {
        int tempScore = mBallsPopped;
        mRound++;
        mTotalScore += mBallsPopped;
        mNumBallsForWin = mRound;
        mLives = mTotalLives;
        mBallsPopped = 0;

        return tempScore;
    }

    public int getBallsPopped() { return mBallsPopped; }
    public int getNumBallsForWin() { return  mNumBallsForWin; }
    public int getTotalScore() { return mTotalScore; }
    public int getLives() { return mLives; }
    public void setLives(int lives) {mLives = lives; mTotalLives = lives;}
}

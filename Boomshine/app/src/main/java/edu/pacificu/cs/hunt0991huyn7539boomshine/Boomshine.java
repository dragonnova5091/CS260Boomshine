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

public class Boomshine extends AppCompatActivity
{

    private int mRound;
    private int mNumBallsForWin;
    private int mBallsPopped;
    private int mTotalScore;
    private int mTotalLives;
    private int mLives;
    private int mLastTotal;
    //private GameOverLayoutActivity mGameOver;

    public  Boomshine()
    {
        Log.d("here", "constructor");
        mRound = 1;
        mNumBallsForWin = mRound;
        mBallsPopped = 0;

        mTotalScore = 0;
        mTotalLives = 3;
        mLastTotal = 0;
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

    public void saveHighScore()
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

    public void onGameOver(int score)
    {
        final String sPlayerScore = "player_score";
        Intent gameOverIntent = new Intent (context,
                GameOverOverlayActivity.class);
        gameOverIntent.putExtra (sPlayerScore, score);

        startActivity (gameOverIntent);
    }

    public int getBallsPopped() { return mBallsPopped; }
    public int getNumBallsForWin() { return  mNumBallsForWin; }
    public int getTotalScore() { return mTotalScore; }
    public int getLives() { return mLives; }
}

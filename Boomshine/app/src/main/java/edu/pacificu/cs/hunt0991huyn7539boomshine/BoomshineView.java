package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class BoomshineView extends View {
    private final int LIVES = 3;
    private final int LEVEL_MULTIPLIER = 5;
    private double mVelocityMultiplier = 10;
    private int mScreenWidth, mScreenHeight;
    private Boomshine mBoomshine;
    ArrayList<ExpandingBall> mExpanding;
    ArrayList<BoundedBouncingBall> mMoving;
    protected Paint mPaint;
    private Context mContext;
    private Display mDisplay;
    protected int mHighscore;
    private boolean mbPlaced;
    protected boolean mbPlaying;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer mBackgroundSound;
    private boolean mDonePlayingSound;

    public BoomshineView(Context context, Display display) {
        super(context);
        setFocusable(true);
        mBoomshine = new Boomshine();
        mPaint = new Paint();
        mContext = context;
        mDisplay = display;
        mExpanding = new ArrayList<>();
        mMoving = new ArrayList<>();
        mbPlaced = false;
        mbPlaying = true;
        mDonePlayingSound = false;
        mHighscore = 0;



        invalidate();
    }

    public void stopMusic()
    {
        mBackgroundSound.pause();
        mMediaPlayer.pause();
    }



    @Override
    public void onDraw(Canvas canvas) {

        final int TEXT_SIZE = 24;
        float textSize;

        if (mBackgroundSound == null)
        {
            mBackgroundSound = MediaPlayer.create( this.mContext, R.raw.main_music );
            mBackgroundSound.start();
            mBackgroundSound.setLooping( true );
        }


        if (mMoving.size() == 0 && mExpanding.size() == 0)
        {
            for (int i = 0; i < mBoomshine.getNumBallsForWin() * 2; i++) {
                addMovingBall(canvas);
            }
        }


        update();

        for (int i = 0; i < mExpanding.size(); i++) {
            mExpanding.get(i).doDraw(canvas);
            //mExpanding.get(i).expandBall();
        }

        for (int i = 0; i < mMoving.size(); i++) {
            mMoving.get(i).doDraw(canvas);
        }

        if (mbPlaying) {
            textSize = TEXT_SIZE * getResources().getDisplayMetrics().density;
            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(textSize);
            canvas.drawText("Total score: " + (mBoomshine.getTotalScore() +
                    mBoomshine.getBallsPopped()), 10, textSize + 10, mPaint);
            canvas.drawText("Balls Needed: " + mBoomshine.getNumBallsForWin(), 10,
                    (textSize + 10) * 2, mPaint);
            canvas.drawText("Current Round Score: " + mBoomshine.getBallsPopped(),
                    10, (textSize + 10) * 3, mPaint);
            canvas.drawText("Lives: " + mBoomshine.getLives(), 10,
                    (textSize + 10) * 4, mPaint);

        } else {
            float tempint;

            saveHighScore();

            textSize = TEXT_SIZE * 2 * getResources().getDisplayMetrics().density;
            tempint = textSize;
            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(textSize);
            canvas.drawText("You Lose.", getWidth() / 4, (getHeight() / 2) -
                    (textSize / 2), mPaint);
            textSize = TEXT_SIZE * getResources().getDisplayMetrics().density;
            mPaint.setTextSize(textSize);
            canvas.drawText("Total score: " + (mBoomshine.getTotalScore() +
                    mBoomshine.getBallsPopped()), getWidth() / 4, (getHeight() / 2) -
                    (int) (tempint * 1.5), mPaint);
            canvas.drawText("High score: " + (getHighScore()),
                    getWidth() / 4, (getHeight() / 2) - (int) (tempint * 2), mPaint);

        }

        checkWin(canvas);


        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(event);
        }
        if (!mbPlaced && mbPlaying) {


            if (mMediaPlayer != null)
            {
                mMediaPlayer.release ();
            }
            mMediaPlayer = MediaPlayer.create (getContext(), R.raw.click);
            mMediaPlayer.start();

            addExpandingBall(event);
            mbPlaced = true;
        }
        if (!mbPlaying) {
            mBoomshine = new Boomshine();
            reset();
            mbPlaying = true;
        }

        return true;
    }


    public void checkWin(Canvas canvas) {
        if (mbPlaced) {
            if (mExpanding.size() == 0 &&
                    mBoomshine.getBallsPopped() >= mBoomshine.getNumBallsForWin()) {
                nextRound(canvas);
                mbPlaced = false;
            } else if (mExpanding.size() == 0 &&
                    mBoomshine.getBallsPopped() < mBoomshine.getNumBallsForWin()) {
                if (reset() <= 0) {
                    mbPlaying = false;
                }
                mbPlaced = false;
            }
        }

    }

    private void nextRound(Canvas canvas) {
        mExpanding.clear();
        mMoving.clear();

        mBoomshine.nextRound();

        for (int i = 0; i < mBoomshine.getNumBallsForWin() * 2; i++) {
            addMovingBall(canvas);
            invalidate();
        }
    }

    private void update() {
        boolean tempBool;
        for (int i = 0; i < mExpanding.size(); i++) {
            for (int j = 0; j < mMoving.size(); j++) {
                if (mExpanding.get(i).collide(mMoving.get(j))) {
                    if (mMediaPlayer != null)
                    {
                        mMediaPlayer.release ();
                    }
                    mMediaPlayer = MediaPlayer.create (getContext(), R.raw.collision);
                    mMediaPlayer.start();
                    addExpandingBall(mMoving.get(j));

                    mMoving.remove(j);
                    mBoomshine.popOne();

                }
            }

            tempBool = mExpanding.get(i).expandBall();
            if (tempBool) {
                mExpanding.remove(i);
            }
        }

        for (int i = 0; i < mMoving.size(); i++) {
            mMoving.get(i).move();
            mMoving.get(i).Bounce();
        }
    }


    public int reset() {
        mExpanding.clear();
        mMoving.clear();

        int tempint = mBoomshine.reset();

        return tempint;
    }

    public void startNewGame() {
        mBoomshine = new Boomshine();
        mExpanding.clear();
        mMoving.clear();
        mbPlaying = true;
        mbPlaced = false;

        invalidate();
    }

    public void quit() {

    }

    private void addMovingBall(Canvas canvas) {
        mScreenHeight = canvas.getHeight();
        mScreenWidth = canvas.getWidth();
        Random rand = new Random();
        int image = getRandImage();
        mMoving.add(new BoundedBouncingBall(mContext, mDisplay, image,
                rand.nextInt(mScreenWidth), rand.nextInt(mScreenHeight),
                (rand.nextDouble() * 2 * mVelocityMultiplier) - mVelocityMultiplier,
                (rand.nextDouble() * 2 * mVelocityMultiplier) - mVelocityMultiplier,
                0, mScreenHeight, 0, mScreenWidth));
    }

    private void addExpandingBall(MotionEvent event) {
        mExpanding.add(new ExpandingBall(mContext, mDisplay, R.drawable.ball_yellow,
                (int) event.getX(), (int) event.getY(), 0, 0, mBoomshine.getNumBallsForWin(), 10,
                200));
    }

    private void addExpandingBall(MovingSprite movesprite) {
        mExpanding.add(new ExpandingBall(mContext, mDisplay, movesprite.getResID(),
                (int) (movesprite.getXUpperLeft()),
                (int) (movesprite.getYUpperLeft()),
                0, 0, mBoomshine.getNumBallsForWin(), movesprite.getRadius(), (movesprite.getRadius() * 2) - mBoomshine.getNumBallsForWin()));

    }

    private int getRandImage() {
        int images[] = {R.drawable.ball_blue, R.drawable.ball_green, R.drawable.ball_yellow};

        Random r = new Random();

        int num = r.nextInt(images.length);


        return images[num];
    }


    public void saveHighScore() {
        if (mBoomshine.mTotalScore > mHighscore) {
            mHighscore = mBoomshine.mTotalScore;
        }
    }

    public int getHighScore() {
        return mHighscore;
    }

}

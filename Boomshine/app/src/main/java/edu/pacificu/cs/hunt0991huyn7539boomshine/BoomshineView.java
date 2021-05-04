package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class BoomshineView extends View
{
    private final int LIVES = 3;
    private final int LEVEL_MULTIPLIER = 5;
    private double mVelocityMultiplier = 10;
    private Boomshine mBoomshine;
    ArrayList<ExpandingBall> mExpanding;
    ArrayList<BoundedBouncingBall> mMoving;
    private Paint mPaint;
    private Context mContext;
    private Display mDisplay;

    private boolean mbPlaced;


    public BoomshineView (Context context, Display display)
    {
        super (context);
        setFocusable (true);
        mBoomshine = new Boomshine ();
        mPaint = new Paint ();
        mContext = context;
        mDisplay = display;
        mExpanding = new ArrayList<> ();
        mMoving = new ArrayList<> ();
        mbPlaced = false;


        Log.d("debug", "" + mBoomshine.getNumBallsForWin());
        for (int i = 0; i < mBoomshine.getNumBallsForWin() * 2; i++)
        {
            addMovingBall();
        }

        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas)
    {

        final int TEXT_SIZE = 24;
        float textSize;
        update();


        Log.d("draw", "in doDraw" + mExpanding.size());
        for (int i  = 0; i < mExpanding.size(); i ++)
        {
            mExpanding.get(i).doDraw(canvas);
            //mExpanding.get(i).expandBall();
        }

        for (int i = 0; i < mMoving.size(); i++)
        {
            mMoving.get(i).doDraw( canvas );
        }

        textSize = TEXT_SIZE * getResources().getDisplayMetrics().density;
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(textSize);
        canvas.drawText("Total score: " + mBoomshine.getBallsPopped(), 10, textSize + 10, mPaint);


        checkWin();

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction () != MotionEvent.ACTION_DOWN)
        {
            return super.onTouchEvent (event);
        }
        if (!mbPlaced)
        {
            addExpandingBall( event );
            mbPlaced = true;
        }

        return true;
    }


    public void checkWin()
    {
        if (mbPlaced)
        {
            if (mExpanding.size() == 0 &&
                    mBoomshine.getBallsPopped() >= mBoomshine.getNumBallsForWin())
            {
                nextRound();
                mbPlaced = false;
            }
            else if (mExpanding.size() == 0 &&
                    mBoomshine.getBallsPopped() < mBoomshine.getNumBallsForWin())
            {
                reset();
                mbPlaced = false;
            }
        }

    }

    private void nextRound()
    {
        mExpanding.clear();
        mMoving.clear();

        mBoomshine.nextRound();

        for (int i = 0; i < mBoomshine.getNumBallsForWin() * 2; i++)
        {
            addMovingBall();
            invalidate();
        }
    }

    public void onDrawUI(Canvas canvas)
    {

    }

    private void update()
    {
        boolean tempBool;
        for (int i  = 0; i < mExpanding.size(); i ++)
        {
            for (int j = 0; j < mMoving.size(); j++)
            {
                if (mExpanding.get (i).collide( mMoving.get(j) ))
                {

                    addExpandingBall( mMoving.get(j));

                    mMoving.remove( j );
                    mBoomshine.popOne();

                }
            }

            tempBool = mExpanding.get(i).expandBall();
            if (tempBool)
            {
                mExpanding.remove( i );
            }
        }

        for (int i = 0; i < mMoving.size(); i++)
        {
            mMoving.get(i).move();
            mMoving.get(i).Bounce();
        }
    }



    public void reset()
    {
        mExpanding.clear();
        mMoving.clear();

        mBoomshine.reset();

        for (int i = 0; i < mBoomshine.getNumBallsForWin() * 2; i++)
        {
            addMovingBall();
            invalidate();
        }
    }

    public void quit()
    {

    }

    public void saveHighScore()
    {

    }

    private void addMovingBall()
    {
        int width = mDisplay.getWidth();
        int height = mDisplay.getHeight();
        Random rand = new Random();
        int image = R.drawable.ball_blue;
        mMoving.add(new BoundedBouncingBall( mContext, mDisplay, image,
                rand.nextInt(width), rand.nextInt(height),
                (rand.nextDouble() * 2 * mVelocityMultiplier) - mVelocityMultiplier,
                (rand.nextDouble() * 2 * mVelocityMultiplier) - mVelocityMultiplier,
                0, height, 0, width ));
    }

    private void addExpandingBall(MotionEvent event)
    {
        mExpanding.add(new ExpandingBall( mContext, mDisplay, R.drawable.ball_yellow,
                (int) event.getX(), (int) event.getY(), 0, 0, mBoomshine.getNumBallsForWin(), 10,
                200));
    }

    private void addExpandingBall(MovingSprite movesprite)
    {
        mExpanding.add(new ExpandingBall( mContext, mDisplay, movesprite.getResID(),
                (int) (movesprite.getXUpperLeft()),
                (int) (movesprite.getYUpperLeft()),
                0,0, 2, movesprite.getRadius(), movesprite.getRadius()*2));

    }
}

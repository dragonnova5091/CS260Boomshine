package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
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
                if (mExpanding.get(i).collide( mMoving.get(j) ))
                {

                    addExpandingBall( mMoving.get(j));

                    mMoving.remove( j );

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
                (int) (movesprite.getXUpperLeft() + movesprite.getRadius()),
                (int) (movesprite.getYUpperLeft() + movesprite.getRadius()),
                0,0, 2,10, movesprite.getRadius()*2));

    }
}

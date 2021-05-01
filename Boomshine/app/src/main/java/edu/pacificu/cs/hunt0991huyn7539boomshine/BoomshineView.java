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
    private Boomshine mBoomshine;
    ArrayList<ExpandingBall> mExpanding;
    ArrayList<BoundedBouncingBall> mMoving;
    private Paint mPaint;
    private Context mContext;
    private Display mDisplay;

    private boolean mbPlaced = false;


    public BoomshineView (Context context, Display display)
    {
        super (context);
        setFocusable (true);
        mBoomshine = new Boomshine ();
        mBoomshine.onCreate();
        mPaint = new Paint ();
        mContext = context;
        mDisplay = display;
        mExpanding = new ArrayList<> ();
        mMoving = new ArrayList<> ();


        int width = mDisplay.getWidth();
        int height = mDisplay.getHeight();
        Random rand = new Random();

        Log.d("debug", "" + mBoomshine.getNumBallsForWin());
        for (int i = 0; i < mBoomshine.getNumBallsForWin() * 2; i++)
        {
            mMoving.add(new BoundedBouncingBall( mContext, mDisplay, R.drawable.ball_green, rand.nextInt(width),
                    rand.nextInt(height), 0, height, 0, width ));
        }

        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        Log.d("draw", "in doDraw");
        for (int i  = 0; i < mExpanding.size(); i ++)
        {
            mExpanding.get(i).doDraw(canvas);
        }

        for (int i = 0; i < mMoving.size(); i++)
        {
            mMoving.get(i).doDraw( canvas );
        }




        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
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
        int width = mDisplay.getWidth();
        int height = mDisplay.getHeight();
        Random rand = new Random();
        int image = R.drawable.ball_blue;
        mExpanding.clear();
        mMoving.clear();

        for (int i = 0; i < mBoomshine.getNumBallsForWin() * 2; i++)
        {
            mMoving.add(new BoundedBouncingBall( mContext, mDisplay, image, rand.nextInt(width),
                    rand.nextInt(height), 0, height, 0, width ));
        }
    }

    public void onDrawUI(Canvas canvas)
    {

    }

    private void update()
    {
        for (int i  = 0; i < mExpanding.size(); i ++)
        {
            mExpanding.get(i).expandBall();
        }

        for (int i = 0; i < mMoving.size(); i++)
        {
            mMoving.get(i).move();
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
}

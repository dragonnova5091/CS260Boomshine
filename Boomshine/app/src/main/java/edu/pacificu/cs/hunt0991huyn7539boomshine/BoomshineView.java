package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;

public class BoomshineView extends ImageView
{
    private final int LIVES = 3;
    private final int LEVEL_MULTIPLIER = 5;
    private Boomshine mBoomshine;
    ArrayList<ExpandingBall> mExpanding;
    ArrayList<BoundedBouncingBall> mMoving;
    private Paint mPaint;
    private Context mContext;
    private Display mDisplay;


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
    }

    @Override
    public void onDraw(Canvas canvas)
    {

    }

    public void checkWin()
    {

    }

    public void onDrawUI(Canvas canvas)
    {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return true;
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

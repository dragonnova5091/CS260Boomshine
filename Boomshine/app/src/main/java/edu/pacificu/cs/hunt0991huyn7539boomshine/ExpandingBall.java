package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;

public class ExpandingBall extends MovingSprite
{
    private float mRate, mRadius, mMaxRadius;
    private float mScale;
    private boolean bExpand = true;
    private MovingSprite mSprite;

    public ExpandingBall(Context context, Display display, int drawable,
                         int topCoord, int leftCoord, double xVel, double yVel,
                         float rate, float radius, float max)
    {
        super(context, display, drawable, topCoord, leftCoord, xVel, yVel);
        mRate = rate;
        mRadius = radius;
        mMaxRadius = max;
    }

    public boolean expandBall()
    {
       if (bExpand)
        {
            mRadius += mRate;
            if (mRadius >= mMaxRadius)
            {
                bExpand = false;
            }
        }
        {
            mRadius -= mRate;
        }
        setXUpperLeft(getXUpperLeft());
        setYUpperLeft(getYUpperLeft());

        return mRadius <= 0;
    }

    public float getRadius()
    {
        return mRadius;
    }

    public boolean collide(BoundedBouncingBall mSprite)
    {
        return true;
    }

    @Override
    public void doDraw( Canvas canvas )
    {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.save();
        canvas.scale( mRadius/ getOGRadius(), mRadius/ getOGRadius(), 0, 0 );
        canvas.drawBitmap(mBitmapImage, (int) mXUpperLeft, (int) mYUpperLeft, paint);
        canvas.restore();
    }
}
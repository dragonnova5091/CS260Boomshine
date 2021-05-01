package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.view.Display;

public class ExpandingBall extends MovingSprite
{
    private int mRate, mRadius, mMaxRadius;
    private float mScale;
    private boolean bExpand = true;
    private MovingSprite mSprite;

    public ExpandingBall(Context context, Display display, int drawable,
                         int topCoord, int leftCoord, int rate, float scale,
                         int radius, int max)
    {
        super(context, display, drawable, topCoord, leftCoord);
        mRate = rate;
        mScale = scale;
        mRadius = radius;
        mMaxRadius = max;
    }

    public boolean expandBall()
    {
        final int RADIUS_MULTIPLIER = 2;
        final int REVERSE_RADIUS_MULTIPLIER = -1;
        final int _MULTIPLIER = 6;

        mRadius += bExpand ? RADIUS_MULTIPLIER : RADIUS_MULTIPLIER * REVERSE_RADIUS_MULTIPLIER;
        setXUpperLeft(getXUpperLeft());
        setYUpperLeft(getYUpperLeft());

        return mRadius <= 0;
    }

    public int getRadius()
    {
        return mRadius;
    }

    public boolean collide(BoundedBouncingBall mSprite)
    {
        return true;
    }
}
package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.view.Display;

public class BoundedBouncingBall extends MovingSprite
{
    private int mTopBound, mBottomBound, mLeftBound, mRightBound;
    private MovingSprite mSprite;

    public BoundedBouncingBall(Context context, Display display, int drawable,
                               int topCoord, int leftCoord, int topBound, int botBound,
                               int leftBound, int rightBound)
    {
        super(context, display, drawable, topCoord, leftCoord);
        mTopBound = topBound;
        mBottomBound = botBound;
        mLeftBound = leftBound;
        mRightBound = rightBound;
    }

    public void Bounce()
    {
        final int CHANGE_DIRECTION = -1;
        final int MULTIPLY_BY_TWO = 2;
        if (getXUpperLeft () <= mTopBound)
        {
            mYVel = mYVel * CHANGE_DIRECTION;
        }
        if (getYUpperLeft () <= mLeftBound)
        {
            mXVel = mXVel * CHANGE_DIRECTION;
        }
        if (mRadius * MULTIPLY_BY_TWO + getXUpperLeft () >= mBottomBound)
        {
            mYVel = mYVel * CHANGE_DIRECTION;
        }
        if (mRadius * MULTIPLY_BY_TWO + getYUpperLeft () >= mRightBound)
        {
            mXVel = mXVel * CHANGE_DIRECTION;
        }

    }
}
package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.view.Display;

public class BoundedBouncingBall extends MovingSprite
{
    private int mTopBound, mBottomBound, mLeftBound, mRightBound;
    private MovingSprite mSprite;

    public BoundedBouncingBall(Context context, Display display, int drawable,
                               int topCoord, int leftCoord, double xVel, double yVel, int topBound,
                               int botBound, int leftBound, int rightBound)
    {
        super(context, display, drawable, topCoord, leftCoord, xVel, yVel);
        mTopBound = topBound;
        mBottomBound = botBound;
        mLeftBound = leftBound;
        mRightBound = rightBound;

        //checks to see if the ball is overlapping the edge

        if (mXUpperLeft > mRightBound - getSpriteWidth())
        {
            mXUpperLeft = mRightBound - getSpriteWidth();
        }
        if (mYUpperLeft > mBottomBound - getSpriteHeight())
        {
            mYUpperLeft = mBottomBound - getSpriteHeight();
        }
    }

    public void Bounce()
    {
        final int CHANGE_DIRECTION = -1;
        final int MULTIPLY_BY_TWO = 2;
        if (getYUpperLeft () <= mTopBound)
        {
            mYVel = mYVel * CHANGE_DIRECTION;
        }
        if (getXUpperLeft () <= mLeftBound)
        {
            mXVel = mXVel * CHANGE_DIRECTION;
        }
        if ((mRadius * MULTIPLY_BY_TWO) + getYUpperLeft () >= mBottomBound)
        {
            mYVel = mYVel * CHANGE_DIRECTION;
        }
        if ((mRadius * MULTIPLY_BY_TWO) + getXUpperLeft () >= mRightBound)
        {
            mXVel = mXVel * CHANGE_DIRECTION;
        }

    }
}
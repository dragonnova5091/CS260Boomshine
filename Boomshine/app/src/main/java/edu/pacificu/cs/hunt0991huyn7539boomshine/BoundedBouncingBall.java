package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.view.Display;

/**
 * Creates a BoundedBouncingBall class that extends a MovingSprite.
 * Defines a moving sprite that is contained within set bounds by resetting
 * movement direction when contact is made with the defined bounds.
 *
 * @author Hung Huynh and Ethan Hunter
 * @version 1.0
 * @since 5.8.2021
 */
public class BoundedBouncingBall extends MovingSprite
{
  private final int mTopBound;
  private final int mBottomBound;
  private final int mLeftBound;
  private final int mRightBound;

  /**
   * Initializes bounding member variables with passed-in values
   * after calling superclass constructor.
   *
   * @param context    The calling context object
   * @param display    The calling display object
   * @param drawable   The drawable id value
   * @param topCoord   The top coordinate of created circle
   * @param leftCoord  The left coordinate of created circle
   * @param xVel       The initial X speed of the created circle
   * @param yVel       The initial Y speed of the created circle
   * @param topBound   The top bound for the created circle
   * @param botBound   The bottom bound for the created circle
   * @param leftBound  The left bound for the created circle
   * @param rightBound The right bound for the created circle
   */
  public BoundedBouncingBall ( Context context, Display display, int drawable,
                               int topCoord, int leftCoord, double xVel, double yVel,
                               int topBound, int botBound, int leftBound,
                               int rightBound )
  {
    super ( context, display, drawable, topCoord, leftCoord, xVel, yVel );
    mTopBound = topBound;
    mBottomBound = botBound;
    mLeftBound = leftBound;
    mRightBound = rightBound;

    if ( mXUpperLeft > mRightBound - getSpriteWidth () )
    {
      mXUpperLeft = mRightBound - getSpriteWidth ();
    }
    if ( mYUpperLeft > mBottomBound - getSpriteHeight () )
    {
      mYUpperLeft = mBottomBound - getSpriteHeight ();
    }
  }

  /**
   * Changes moving sprite direction based on current direction and
   * which bound was hit.
   */
  public void Bounce ()
  {
    final int CHANGE_DIRECTION = -1;
    final int MULTIPLY_BY_TWO = 2;
    if ( getYUpperLeft () <= mTopBound )
    {
      mYVel = mYVel * CHANGE_DIRECTION;
    }
    if ( getXUpperLeft () <= mLeftBound )
    {
      mXVel = mXVel * CHANGE_DIRECTION;
    }
    if ( ( mRadius * MULTIPLY_BY_TWO ) + getYUpperLeft () >= mBottomBound )
    {
      mYVel = mYVel * CHANGE_DIRECTION;
    }
    if ( ( mRadius * MULTIPLY_BY_TWO ) + getXUpperLeft () >= mRightBound )
    {
      mXVel = mXVel * CHANGE_DIRECTION;
    }
  }
}
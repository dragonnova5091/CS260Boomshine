package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Display;

import static java.lang.Math.sqrt;

/**
 * Creates a ExpandingBall class that extends a MovingSprite.
 * Defines a moving sprite that is contained within set bounds by resetting
 * movement direction when contact is made with the defined bounds.
 *
 * @author Hung Huynh and Ethan Hunter
 * @version 1.0
 * @since 5.8.2021
 */
public class ExpandingBall extends MovingSprite
{
  private float mRate, mMaxRadius;
  private boolean bExpand = true;

  /**
   * Initializes bounding member variables with passed-in values
   * after calling superclass constructor.
   *
   * @param context     The calling context object
   * @param display     The calling display object
   * @param drawable    The drawable id value
   * @param topCoord    The top coordinate of created circle
   * @param leftCoord   The left coordinate of created circle
   * @param xVel        The initial X speed of the created circle
   * @param yVel        The initial Y speed of the created circle
   * @param rate        The rate at the sprite expands
   * @param radius      The initial radius of sprite
   * @param max         The max radius can be.
   */
  public ExpandingBall (Context context, Display display, int drawable,
                        int topCoord, int leftCoord, double xVel, double yVel,
                        float rate, float radius, float max)
  {
    super (context, display, drawable, topCoord, leftCoord, xVel, yVel);
    mRate = rate;
    mMaxRadius = max;
    mRadius = radius;
    setBitmap (Bitmap.createScaledBitmap (mBitmapImage, (int) mRadius,
               (int) mRadius,true));
  }

  /**
   * Method to expand the sprites.
   *
   * @return Whether the ball is finished expanding or not
   */
  public boolean expandBall ()
  {
    Bitmap scaledBMap;
    if (bExpand)
    {
      mRadius = mRadius + mRate;

      setXUpperLeft( getXUpperLeft () - mRate );
      setYUpperLeft( getYUpperLeft () - mRate );

      if (mRadius >= mMaxRadius)
      {
        bExpand = false;
        mRadius = mMaxRadius;
      }
    }
    else
    {
      mRadius -= mRate;
      setXUpperLeft (getXUpperLeft () + mRate);
      setYUpperLeft (getYUpperLeft () + mRate);
    }

    if (mRadius > 0)
    {
      scaledBMap = Bitmap.createScaledBitmap (getOGBitmap (), (int) mRadius * 2,
              (int) mRadius * 2, true);
    }
    else
    {
      scaledBMap = Bitmap.createScaledBitmap (getOGBitmap (), 1,
              1, true);
    }
    setBitmap (scaledBMap);

    return mRadius <= 0;
  }

  /**
   * Retrieves the ExplodingBoundedMovingCircle radius value
   *
   * @return The radius value
   */
  public float getRadius ()
  {
    return mRadius;
  }

  /**
   * Checks of a expanding ball collides with a moving ball.
   *
   * @return The sprites collided
   */
  public boolean collide (BoundedBouncingBall mSprite)
  {
    int xCenter = (int) mXUpperLeft + (int) mRadius;
    int yCenter = (int) mYUpperLeft + (int) mRadius;

    int spriteXCenter = (int) (mSprite.mXUpperLeft + mSprite.mRadius);
    int spriteYCenter = (int) (mSprite.mYUpperLeft + mSprite.mRadius);
    int spriteRadius = (int) mSprite.mRadius;

    int centerDistance = (int) sqrt (((spriteXCenter - xCenter) * (spriteXCenter - xCenter))
                                     + ((spriteYCenter - yCenter) * (spriteYCenter - yCenter)));

    return centerDistance < mRadius + spriteRadius;
  }
}
package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;

import static android.content.ContentValues.TAG;
import static java.lang.Math.sqrt;

public class ExpandingBall extends MovingSprite
{
  private float mRate, mMaxRadius;
  private float mScale;
  private boolean bExpand = true;
  private MovingSprite mSprite;

  public ExpandingBall( Context context, Display display, int drawable,
                        int topCoord, int leftCoord, double xVel, double yVel,
                        float rate, float radius, float max )
  {
    super( context, display, drawable, topCoord, leftCoord, xVel, yVel );
    mRate = rate;
    mMaxRadius = max;
    mRadius = radius;
    setBitmap( Bitmap.createScaledBitmap( mBitmapImage, (int) mRadius,
            (int) mRadius,true ) );

  }

  public boolean expandBall()
  {
    float tempRadius;
    if ( bExpand )
    {
      mRadius = mRadius + mRate;

      setXUpperLeft( getXUpperLeft() - mRate );
      setYUpperLeft( getYUpperLeft() - mRate );

      if ( mRadius >= mMaxRadius )
      {
        bExpand = false;
      }
    }
    else
    {
      mRadius -= mRate;
      setXUpperLeft( getXUpperLeft() + mRate );
      setYUpperLeft( getYUpperLeft() + mRate );
    }

    if(mRadius > 0)
    {
      Bitmap scaledBMap = Bitmap.createScaledBitmap( getOGBitmap(), ( int ) mRadius * 2,
              ( int ) mRadius * 2,true );
      tempRadius = mRadius;
      setBitmap( scaledBMap );
      //mRadius = tempRadius;
    }

    return mRadius <= 0;
  }

  public float getRadius()
  {
    return mRadius;
  }

  public boolean collide( BoundedBouncingBall mSprite )
  {
    //boolean bcol = false;
    int xCenter = (int) mXUpperLeft + (int) mRadius;
    int yCenter = (int) mYUpperLeft + (int) mRadius;

    int spriteXCenter = (int) (mSprite.mXUpperLeft + mSprite.mRadius);
    int spriteYCenter = (int) (mSprite.mYUpperLeft + mSprite.mRadius);
    int spriteRadius = (int) mSprite.mRadius;

    int centerDistance = ( int ) sqrt(((spriteXCenter - xCenter) * (spriteXCenter - xCenter))
           + ((spriteYCenter - yCenter) * (spriteYCenter - yCenter)));

    return centerDistance < mRadius + spriteRadius;
  }

}
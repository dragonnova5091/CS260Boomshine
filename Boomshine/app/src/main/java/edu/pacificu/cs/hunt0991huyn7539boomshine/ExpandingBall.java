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
    //Log.d("constructor radius", "" + mRadius);
    setImageBitmap( Bitmap.createScaledBitmap( mBitmapImage, (int) mRadius,
            (int) mRadius,true ) );
    //Log.d("constructor radius 2", "" + mRadius);

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

    //Log.d( TAG, "expandBall: " + mRadius );

    if(mRadius > 0)
    {
      Bitmap scaledBMap = Bitmap.createScaledBitmap( getOGBitmap(), ( int ) mRadius * 2,
              ( int ) mRadius * 2,true );
      tempRadius = mRadius;
      setBitmap( scaledBMap );
      mRadius = tempRadius;
    }

    //Log.d("radius", "" + mRadius + " " + mMaxRadius );




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

   /* @Override
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
    }*/
}
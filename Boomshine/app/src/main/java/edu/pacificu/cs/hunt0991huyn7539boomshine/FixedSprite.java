package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

/**
 * Defines the FixedSprite class which maintains the specifics of a sprite
 * including its location, size, and bitmap.
 *
 * @author Computer Science, Pacific University.
 * @version 1.0
 */
public class FixedSprite extends ImageView
{
  protected Bitmap mBitmapImage;
  protected Bitmap mOGBitmap;
  protected Display mDisplay;
  private int mResID;
  // integer computations are less expensive than double, so if your game doesn't
  // need any kind of precision, use ints. floats use less space than doubles
  protected double mXUpperLeft;
  protected double mYUpperLeft;
  private int mWidth;
  private int mHeight;
  private Context mContext;
  private static int mCount = 0;
  protected float mRadius;
  private Paint mPaint;
  private int mColor;
  private float mOGRadius;
  private boolean mFirst;


  /**
   * Constructor that initializes the values associated with the sprite.
   *
   * @param context    reference to application-specific resources
   * @param display    the display
   * @param drawable   reference to a bitmap
   * @param xUpperLeft the x coordinate of the sprite
   * @param yUpperLeft the y coordinate of the sprite
   * @since 1.0
   */
//  public FixedSprite (Context context, Display display, int drawable,
//                      int topCoord, int leftCoord)
  public FixedSprite( Context context, Display display, int drawable,
                      double xUpperLeft, double yUpperLeft )

  {
    super( context );

    BitmapFactory.Options opts = new BitmapFactory.Options();
//    opts.inScaled = false;
    opts.inJustDecodeBounds = true;
    mBitmapImage = BitmapFactory.decodeResource( context.getResources(),
            drawable );
    mOGBitmap = mBitmapImage;
    mRadius = mBitmapImage.getWidth();
    mContext = context;
    mDisplay = display;
    mXUpperLeft = xUpperLeft;
    mYUpperLeft = yUpperLeft;
    mFirst = true;
    Log.d( "Constr Sprite height: ", Integer.toString( ( int ) this.getSpriteHeight() ) );
    Log.d( "Constr Sprite width: ", Integer.toString( ( int ) this.getSpriteWidth() ) );
    mResID = drawable;
    ++mCount;
  }

  /**
   * Draws the bitmap to the canvas.
   *
   * @param canvas the canvas to draw to
   * @since 1.0
   */
  public void doDraw( Canvas canvas )
  {
    canvas.drawBitmap( mBitmapImage, ( ( int ) this.mXUpperLeft ),
            ( int ) this.mYUpperLeft, null );

    //mFirst = false;




  }

  /**
   * Retrieves the bitmap.
   *
   * @return the bitmap
   * @since 1.0
   */
  public Bitmap getBitmap()
  {
    return mBitmapImage;
  }

  public void setBitmap(Bitmap bm) { mBitmapImage = bm; }

  public Bitmap getOGBitmap() { return mOGBitmap; }

  /**
   * Retrieves the sprite's reference id.
   *
   * @return the reference id
   * @since 1.0
   */
  public int getResID()
  {
    return this.mResID;
  }

  /**
   * Retrieves the value of the top y coordinate.
   *
   * @return the top y coordinate value
   * @since 1.0
   */
  public double getYUpperLeft()
  {
    return mYUpperLeft;
  }

  /**
   * Retrieves the value of the left x coordinate.
   *
   * @return the left x coordinate value
   * @since 1.0
   */
  public double getXUpperLeft()
  {
    return mXUpperLeft;
  }

  /**
   * Retrieves the number of sprites created.
   *
   * @return number of sprites
   * @since 1.0
   */
  public int getCount()
  {
    return mCount;
  }

  /**
   * Retrieves the width of the sprite.
   *
   * @return the width of the sprite
   * @since 1.0
   */
  public int getSpriteWidth()
  {
    // return mWidth;
    return mBitmapImage.getWidth();
  }

  /**
   * Retrieves the height of the sprite
   *
   * @return the height of the sprite
   * @since 1.0
   */
  public int getSpriteHeight()
  {
    // return mHeight;
    return mBitmapImage.getHeight();
  }

  /**
   * Retrieves the radius of the sprite using the bounding rectangle center
   * (x1, y1) is center and (x2, y2) is upper right of bounding rectangle
   * <p>
   * NOTE: Assumes round Sprite for radius
   *
   * @return the radius of the sprite
   * @since 1.0
   */
  public float getRadius()
  {
    return mRadius;
  }

  /**
   * Retrieves the height of the display.
   *
   * @return the height of the display
   * @since 1.0
   */
  public int getDisplayHeight()
  {
    return mContext.getResources().getDisplayMetrics().heightPixels;
  }

  /**
   * Retrieves the width of the display.
   *
   * @return the width of the display
   * @since 1.0
   */
  public int getDisplayWidth()
  {
    return mContext.getResources().getDisplayMetrics().widthPixels;
  }

  public float getOGRadius()
  {
    return mOGRadius;
  }
}

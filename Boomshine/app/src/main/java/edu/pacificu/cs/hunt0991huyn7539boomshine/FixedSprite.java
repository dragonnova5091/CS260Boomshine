package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
  protected final Bitmap mOGBitmap;
  protected final Display mDisplay;
  private final int mResID;
  // integer computations are less expensive than double, so if your game doesn't
  // need any kind of precision, use ints. floats use less space than doubles
  protected double mXUpperLeft;
  protected double mYUpperLeft;
  private static int mCount = 0;
  protected float mRadius;


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
  public FixedSprite ( Context context, Display display, int drawable,
                       double xUpperLeft, double yUpperLeft )

  {
    super ( context );

    BitmapFactory.Options opts = new BitmapFactory.Options ();
    opts.inJustDecodeBounds = true;
    mBitmapImage = BitmapFactory.decodeResource ( context.getResources (),
            drawable );
    mOGBitmap = mBitmapImage;
    mRadius = mBitmapImage.getWidth () / 2.0f;
    mDisplay = display;
    mXUpperLeft = xUpperLeft;
    mYUpperLeft = yUpperLeft;
    mResID = drawable;
    ++mCount;
  }

  /**
   * Draws the bitmap to the canvas.
   *
   * @param canvas the canvas to draw to
   * @since 1.0
   */
  public void doDraw ( Canvas canvas )
  {
    canvas.drawBitmap ( mBitmapImage, ( ( int ) this.mXUpperLeft ),
            ( int ) this.mYUpperLeft, null );

  }

  /**
   * Sets the bitmap.
   *
   * @param bm the bitmap to copy
   * @since 1.0
   */
  public void setBitmap ( Bitmap bm )
  {
    mBitmapImage = bm;
  }

  /**
   * Gets the original bitmap.
   *
   * @since 1.0
   */
  public Bitmap getOGBitmap ()
  {
    return mOGBitmap;
  }

  /**
   * Retrieves the sprite's reference id.
   *
   * @return the reference id
   * @since 1.0
   */
  public int getResID ()
  {
    return this.mResID;
  }

  /**
   * Retrieves the value of the top y coordinate.
   *
   * @return the top y coordinate value
   * @since 1.0
   */
  public double getYUpperLeft ()
  {
    return mYUpperLeft;
  }

  /**
   * Retrieves the value of the left x coordinate.
   *
   * @return the left x coordinate value
   * @since 1.0
   */
  public double getXUpperLeft ()
  {
    return mXUpperLeft;
  }

  /**
   * Retrieves the width of the sprite.
   *
   * @return the width of the sprite
   * @since 1.0
   */
  public int getSpriteWidth ()
  {
    return mBitmapImage.getWidth ();
  }

  /**
   * Retrieves the height of the sprite
   *
   * @return the height of the sprite
   * @since 1.0
   */
  public int getSpriteHeight ()
  {
    return mBitmapImage.getHeight ();
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
  public float getRadius ()
  {
    return mRadius;
  }
}

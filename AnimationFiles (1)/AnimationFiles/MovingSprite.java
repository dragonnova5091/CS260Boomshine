package edu.pacificu.cs.inclassanimation;

import android.content.Context;
import android.view.Display;

/**
 * Defines the MovingSprite class which maintains the specifics of a sprite
 * including its location, size, and bitmap.
 *
 * @author Computer Science, Pacific University.
 *
 * @version 1.0
 */
public class MovingSprite extends FixedSprite
{

  /**
   * Constructor that initializes the values associated with the sprite.
   *
   * @param context
   *          reference to application-specific resources
   *
   * @param display
   *          the display
   *
   * @param drawable
   *          reference to a bitmap
   *
   * @param topCoord
   *          the top coordinate of the sprite
   *
   * @param leftCoord
   *          the left coordinate of the sprite
   *
   *
   * @since 1.0
   */
  public MovingSprite (Context context, Display display, int drawable,
                       int topCoord, int leftCoord)
  {
    super (context, display, drawable, topCoord, leftCoord);
  }

  /**
   * Sets the value of the x coordinate.
   *
   * @since 1.0
   */
  public void setXUpperLeft(double xUpperLeft)
  {
    mXUpperLeft = xUpperLeft;
  }

  /**
   * Sets the value of the y coordinate.
   *
   * @since 1.0
   */
  public void setYUpperLeft(double yUpperLeft)
  {
    mYUpperLeft = yUpperLeft;
  }

  public void move ()
  {
    setXUpperLeft (this.getXUpperLeft() + 1);
  }

}

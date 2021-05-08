package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.view.Display;

/**
 * Defines the MovingSprite class which maintains the specifics of a sprite
 * including its location, size, and bitmap.
 *
 * @author Hung Huynh and Computer Science
 * @version 1.0
 */
public class MovingSprite extends FixedSprite
{

  protected double mXVel, mYVel;

  /**
   * Constructor that initializes the values associated with the sprite.
   *
   * @param context   reference to application-specific resources
   * @param display   the display
   * @param drawable  reference to a bitmap
   * @param topCoord  the top coordinate of the sprite
   * @param leftCoord the left coordinate of the sprite
   * @since 1.0
   */
  public MovingSprite ( Context context, Display display, int drawable,
                        int topCoord, int leftCoord, double xVel, double yVel )
  {
    super ( context, display, drawable, topCoord, leftCoord );
    mXVel = xVel;
    mYVel = yVel;
  }

  /**
   * Sets the value of the x coordinate.
   *
   * @since 1.0
   */
  public void setXUpperLeft ( double xUpperLeft )
  {
    mXUpperLeft = xUpperLeft;
  }

  /**
   * Sets the value of the y coordinate.
   *
   * @since 1.0
   */
  public void setYUpperLeft ( double yUpperLeft )
  {
    mYUpperLeft = yUpperLeft;
  }

  /**
   * Moves the sprite using x and y velocities.
   *
   * @since 1.0
   */
  public void move ()
  {
    mXUpperLeft += mXVel;
    mYUpperLeft += mYVel;
  }

}

package edu.pacificu.edu.boomshineNoPlay;

import android.content.Context;
import android.view.Display;

public class ExpandingBall extends MovingSprite
{
  private float mExpandSpeed, mMaxExpand, mScale;


  /**
   * Constructor that initializes the values associated with the sprite.
   *
   * @param context   reference to application-specific resources
   * @param display   the display
   * @param drawable  reference to a bitmap
   * @param topCoord  the top coordinate of the sprite
   * @param leftCoord the left coordinate of the sprite
   * @param xVel
   * @param yVel
   * @since 1.0
   */
  public ExpandingBall( Context context, Display display, int drawable, int topCoord, int leftCoord,
                        int xVel, int yVel, float expandSpeed, float maxExpand)
  {
    super( context, display, drawable, topCoord, leftCoord, xVel, yVel );
  }
}

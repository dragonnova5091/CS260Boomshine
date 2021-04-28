package edu.pacificu.cs.inclassanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.view.Display;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Defines the View for displaying the animation.
 *
 * @author Computer Science, Pacific University.
 *
 * @version 1.0
 */
public class AnimationView extends ImageView
{
  FixedSprite mFixedSprite;

  /**
   * Constructor that initializes the values associated with the sprite.
   *
   * @param context
   *          reference to application-specific resources
   *
   * @param display
   *          the display
   *
   * @since 1.0
   */
  public AnimationView (Context context, Display display)
  {
    super (context);
    setFocusable (true); // make sure we get key events
  }

  /**
   * Draw method that is repeatedly called for animation
   *
   * @param canvas
   *          used to host the draw calls
   *
   * @since 1.0
   */

  @Override
  public void onDraw (Canvas canvas)
  {
    mFixedSprite.doDraw (canvas);
    super.onDraw (canvas);
    invalidate ();
  }
}
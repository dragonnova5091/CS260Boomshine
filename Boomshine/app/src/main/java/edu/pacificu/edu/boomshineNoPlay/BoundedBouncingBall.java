package edu.pacificu.edu.boomshineNoPlay;

import android.content.Context;
import android.graphics.Canvas;
import android.view.Display;
import android.widget.ExpandableListAdapter;

import edu.pacificu.cs.hunt0991huyn7539boomshine.R;

public class BoundedBouncingBall extends MovingSprite
{
  private double mXVel, mYVel;

  public BoundedBouncingBall( Context context, Display display, double xPos, double yPos, int xVel,
                              int yVel)
  {
    super( context, display, R.drawable.ball_blue,
            (int) xPos, (int) yPos, xVel, yVel);
  }
  
  public void moveUpdate()
  {

    //update the coords with teh velocities

  }

  public double getXVelocity()
  {
    return mXVel;
  }

  public double getYVelocity()
  {
    return mYVel;
  }

  public void setXVelcoity(double xVel)
  {
    mXVel = xVel;
  }

  public void setYVelocity(double yVel)
  {
    mYVel = yVel;
  }

  public void bounceHor()
  {
    mYVel *= -1;
  }

  public void bounceVert()
  {
    mXVel *= -1;
  }

}

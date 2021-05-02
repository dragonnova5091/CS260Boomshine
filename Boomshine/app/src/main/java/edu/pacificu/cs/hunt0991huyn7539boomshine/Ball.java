package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.graphics.Canvas;
import android.view.Display;
import android.widget.ExpandableListAdapter;

import edu.pacificu.cs.hunt0991huyn7539boomshine.R;

public class Ball
{
    private double mXVel, mYVel;
    private MovingSprite mSprite;
    private ExpandingBall mExpandBall;

    public Ball( Context context, Display display, double xPos, double yPos)
    {
        //mSprite = new MovingSprite( context, display, R.drawable.ball_blue,
          //      (int) xPos, (int) yPos );
    }

    public void draw( Canvas canvas)
    {
        mSprite.draw( canvas );
    }

    public void moveUpdate()
    {

        //update the coords with teh velocities
        mSprite.setXUpperLeft( mSprite.getXUpperLeft() + mXVel );
        mSprite.setYUpperLeft( mSprite.getYUpperLeft() + mXVel );
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

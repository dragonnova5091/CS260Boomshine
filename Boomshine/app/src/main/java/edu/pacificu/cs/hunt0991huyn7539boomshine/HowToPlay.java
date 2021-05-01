package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class HowToPlay extends Activity
{
  private Button mBtnBack;

  /**
   * onCreate :: called when the Activity is created
   *
   * @param savedInstanceState - passed for the parent
   */
  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.howtoplay_layout );

    //setting the button function
    mBtnBack = ( Button ) findViewById( R.id.btnBack );
    mBtnBack.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onBtnBack( view );
      }
    } );
  }

  /**
   * the function called when the back button is pressed
   *
   * @param view - the view calling the function
   */
  protected void onBtnBack( View view )
  {
    //finishes the activity and returns to the paused activity prior
    this.finish();
  }
}

package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HowToPlay extends Activity
{

  /**
   * onCreate :: called when the Activity is created
   *
   * @param savedInstanceState - passed for the parent
   */
  @Override
  protected void onCreate ( Bundle savedInstanceState )
  {
    super.onCreate ( savedInstanceState );
    setContentView ( R.layout.howtoplay_layout );

    Button mBtnBack = (Button) findViewById(R.id.btnBack);
    mBtnBack.setOnClickListener (this::onBtnBack);
  }

  /**
   * The function called when the back button is pressed
   *
   * @param view - the view calling the function
   */
  protected void onBtnBack ( View view )
  {
    this.finish ();
  }
}

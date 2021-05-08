package edu.pacificu.cs.hunt0991huyn7539boomshine;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

/**
 * Defines the MainActivity class that contains display and
 * interaction logic for the Main Activity Screen.
 *
 * @author Hung Huynh
 * @version 1.0
 * @since 5.8.2021
 */
public class MainActivity extends AppCompatActivity
{
  private Display mDisplay;
  private BoomshineView mBoomshineView;

  @RequiresApi (api = Build.VERSION_CODES.R)

  /**
   * Overrides the MainActivity.onCreate method. Inflates the menu screen.
   *
   * @param savedInstanceState A Bundle containing previously saved
   *                           Activity state
   */
  @Override
  protected void onCreate ( Bundle savedInstanceState )
  {
    super.onCreate ( savedInstanceState );

    mBoomshineView = new BoomshineView ( this.getApplicationContext (),
                                        this.getDisplay () );

    mDisplay = this.getDisplay ();

    onCreateMenuButtons ();
  }

  /**
   * Overrides the MainActivity.onPause method. Pauses Music
   */
  @Override
  protected void onPause ()
  {
    super.onPause ();
    mBoomshineView.stopMusic ();
  }

  /**
   * Overrides the MainActivity.onCreateMenuButtons method.
   * inflates the menu buttons
   */
  private void onCreateMenuButtons ()
  {
    setContentView ( R.layout.activity_main );

    Button mbtnStart = (Button) findViewById(R.id.btnStart);
    Button mbtnHTP = (Button) findViewById(R.id.btnHowToPlay);
    Button mbtnQuit = (Button) findViewById(R.id.btnQuit);
    Button mbtnTimedStart = (Button) findViewById(R.id.btnTimedGame);

    mbtnStart.setOnClickListener (view -> onBtnStart ());
    mbtnQuit.setOnClickListener (view -> onBtnQuit ());
    mbtnHTP.setOnClickListener (view -> onBtnHTP ());
    mbtnTimedStart.setOnClickListener (view -> onBtnTimeStart ());
  }

  /**
   * Inflates the how-to-play screen.
   *
   */
  private void onBtnHTP()
  {
    startActivity ( new Intent ( this, HowToPlay.class ) );
  }

  /**
   * Quits the program.
   *
   */
  private void onBtnQuit()
  {
    this.finish ();
  }

  /**
   * Inflates the Boomshines screen and starts game play.
   *
   */
  private void onBtnStart()
  {
    mBoomshineView = new BoomshineView ( this, mDisplay );
    setContentView ( mBoomshineView );
    mBoomshineView.startNewGame ();
  }

  /**
   * Inflates the TimedBoomshines screen and starts game play.
   *
   */
  private void onBtnTimeStart()
  {
    mBoomshineView = new TimedBoomshineView ( this, mDisplay );
    setContentView ( mBoomshineView );
    mBoomshineView.startNewGame ();
  }

  /**
   * Overrides the MainActivity.onCreateOptionsMenu method.
   * Creates the drop down menu.
   *
   * @param menu the menu layout
   */
  @Override
  public boolean onCreateOptionsMenu ( Menu menu )
  {

    MenuInflater inflater = getMenuInflater ();
    inflater.inflate ( R.menu.hamburger_menu, menu );

    return true;
  }

  /**
   * Overrides the MainActivity.onOptionsItemSelected method.
   * Start the intents of the dropdown
   * menu
   *
   * @param item the item from menu to start
   */
  @Override
  public boolean onOptionsItemSelected ( final MenuItem item )
  {
    if ( item.getItemId () == R.id.mainmenu )
    {
      onCreateMenuButtons ();
    }
    else if ( item.getItemId () == R.id.resetGame )
    {
      mBoomshineView.startNewGame ();
    }
    else if ( item.getItemId () == R.id.normalgame )
    {
      mBoomshineView = new BoomshineView ( this, mDisplay );
      setContentView ( mBoomshineView );
      mBoomshineView.startNewGame ();
    }
    else if ( item.getItemId () == R.id.timedgame )
    {
      mBoomshineView = new TimedBoomshineView ( this, mDisplay );
      setContentView ( mBoomshineView );
      mBoomshineView.startNewGame ();
    }


    return true;
  }
}
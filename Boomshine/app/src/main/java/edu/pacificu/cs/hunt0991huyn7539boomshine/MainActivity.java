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
import android.view.View;
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
  private Button mbtnStart, mbtnHTP, mbtnQuit, mbtnTimedStart;

  @RequiresApi (api = Build.VERSION_CODES.R)

  /**
   * Overrides the MainActivity.onCreate method. Inflates the menu screen.
   *
   * @param savedInstanceState A Bundle containing previously saved
   *                           Activity state
   */
  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);

    mBoomshineView = new BoomshineView (this.getApplicationContext (), this.getDisplay ());

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
   * Overrides the MainActivity.onCreateMenuButtons method. inflates the menu buttons
   */
  private void onCreateMenuButtons ()
  {
    setContentView (R.layout.activity_main);

    mbtnStart = (Button) findViewById (R.id.btnStart);
    mbtnHTP = (Button) findViewById (R.id.btnHowToPlay);
    mbtnQuit = (Button) findViewById (R.id.btnQuit);
    mbtnTimedStart = (Button) findViewById (R.id.btnTimedGame);

    mbtnStart.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View view)
      {
        onBtnStart (view);
      }
    } );
    mbtnQuit.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View view)
      {
        onBtnQuit (view);
      }
    } );
    mbtnHTP.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View view)
      {
        onBtnHTP (view);
      }
    } );
    mbtnTimedStart.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View view)
      {
        onBtnTimeStart (view);
      }
    } );
  }

  /**
   * Inflates the how-to-play screen.
   *
   * @param view The view to display.
   */
  private void onBtnHTP (View view)
  {
    startActivity (new Intent (this, HowToPlay.class));
  }

  /**
   * Quits the program.
   *
   * @param view The view to display.
   */
  private void onBtnQuit (View view)
  {
    this.finish();
  }

  /**
   * Inflates the Boomshines screen and starts game play.
   *
   * @param view The view to display.
   */
  private void onBtnStart (View view)
  {
    setContentView( mBoomshineView );
    mBoomshineView.startNewGame();
  }

  /**
   * Inflates the TimedBoomshines screen and starts game play.
   *
   * @param view The view to display.
   */
  private void onBtnTimeStart (View view)
  {
    mBoomshineView = new TimedBoomshineView (this, mDisplay);
    setContentView (mBoomshineView);
    mBoomshineView.startNewGame ();
  }

  /**
   * Overrides the MainActivity.onCreateOptionsMenu method. Creates the drop down menu.
   *
   * @param menu the menu layout
   */
  @Override
  public boolean onCreateOptionsMenu (Menu menu)
  {

    MenuInflater inflater = getMenuInflater ();
    inflater.inflate (R.menu.hamburger_menu, menu);

    return true;
  }

  /**
   * Overrides the MainActivity.onOptionsItemSelected method. Start the intents of the dropdown
   * menu
   *
   * @param item the item from menu to start
   */
  @Override
  public boolean onOptionsItemSelected (final MenuItem item)
  {
    if (item.getItemId () == R.id.mainmenu)
    {
      onCreateMenuButtons ();
    }
    else if (item.getItemId () == R.id.resetGame)
    {
      mBoomshineView.startNewGame ();
    }

    return true;
  }
}
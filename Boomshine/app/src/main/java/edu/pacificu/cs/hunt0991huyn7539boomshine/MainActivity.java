package edu.pacificu.cs.hunt0991huyn7539boomshine;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
  private Display mDisplay;

  private BoomshineView mBoomshineView;

  private Button mbtnStart, mbtnHTP, mbtnQuit;

  @RequiresApi(api = Build.VERSION_CODES.R)
  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    mBoomshineView = new BoomshineView( this.getApplicationContext(), this.getDisplay() );



    //startActivity (new Intent(this,
      //      Boomshine.class));




    onCreateMenuButtons();

  }

  private void onCreateMenuButtons()
  {
    setContentView( R.layout.activity_main );


    mbtnStart = (Button) findViewById( R.id.btnStart );
    mbtnHTP = (Button) findViewById( R.id.btnHowToPlay );
    mbtnQuit = (Button) findViewById( R.id.btnQuit);


    mbtnStart.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onBtnStart( view );
      }
    } );
    mbtnQuit.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onBtnQuit( view );
      }
    } );
    mbtnHTP.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onBtnHTP( view );
      }
    } );
  }

  private void onBtnHTP( View view )
  {
    //Log.d("htp", "start HTP Activity");
    startActivity(new Intent(this, HowToPlay.class));
  }

  private void onBtnQuit( View view )
  {
    //Log.d("quit", "trying to quit");
    this.finish();
  }

  private void onBtnStart( View view )
  {
    //Log.d("start", "start Boomshine View");
    setContentView( mBoomshineView );
    mBoomshineView.startNewGame();
  }

  @Override
  public boolean onCreateOptionsMenu( Menu menu )
  {

    MenuInflater inflater = getMenuInflater();
    inflater.inflate( R.menu.hamburger_menu, menu );

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item)
  {
    if (item.getItemId() == R.id.mainmenu)
    {
      onCreateMenuButtons();
    }
    else if (item.getItemId() == R.id.resetGame)
    {
      mBoomshineView.startNewGame();
    }

    return true;
  }
}
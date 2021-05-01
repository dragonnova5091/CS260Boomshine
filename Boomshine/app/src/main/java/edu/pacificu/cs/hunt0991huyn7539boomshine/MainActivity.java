package edu.pacificu.cs.hunt0991huyn7539boomshine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
{
  private BoomshineView mView;
  private Display mDisplay;

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    mDisplay = this.getDisplay();
    mView = new BoomshineView (this, mDisplay);
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    startActivity (new Intent(this,
            Boomshine.class));
  }


}
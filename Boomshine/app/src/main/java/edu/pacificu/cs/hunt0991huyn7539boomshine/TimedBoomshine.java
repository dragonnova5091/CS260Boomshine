package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Timer extends Boomshine
{

    public  TimedBoomshine(Canvas canvas, Paint paint)
    {
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                canvas.drawText("seconds remaining: " + millisUntilFinished / 1000,
                        10, 10, paint);
            }

            public void onFinish() {
                setLives(0);
            }
        }.start();
    }

}

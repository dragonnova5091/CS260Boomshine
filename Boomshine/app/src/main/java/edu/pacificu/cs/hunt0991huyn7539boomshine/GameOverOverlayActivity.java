package edu.pacificu.cs.hunt0991huyn7539boomshine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Defines the GameOverActivity class that contains display and
 * interaction logic for the Game Over screen.
 *
 * @author Hung Huynh
 */

public class GameOverOverlayActivity extends AppCompatActivity
{
    TextView mcPlayerScoreTextView;
    Button mcBtnPlayAgain;
    private int mPlayerScore;

    /**
     * Initializes member variables to default values.
     * Extracts the player's overall score and name
     * from the triggering Intent object.
     *
     * @param savedInstanceState A Bundle containing previously saved
     *                           Activity state (un-used)
     */
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        final String sPlayerScore = "player_score";
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_game_over_overlay);

        Intent tempIntent = getIntent ();

        mPlayerScore = tempIntent.getIntExtra (sPlayerScore, 0);

        mcPlayerScoreTextView = findViewById (R.id.textViewPlayerscoreFinal);
        mcBtnPlayAgain = findViewById (R.id.btnPlayAgain);

        mcPlayerScoreTextView.setText (Integer.toString (mPlayerScore));
    }

    /**
     * Event handler for the Play Again button.
     *
     * Uses the player name (if available) to redirect the user
     * to the Game Menu screen
     *
     * @param view The view that triggered the event
     */

    public void onPlayAgainClicked (View view)
    {
        Intent cPlayAgain = new Intent (this, Boomshine.class);

        startActivity (cPlayAgain);
    }

    /**
     * Event handler for the Quit button.
     *
     * Creates and starts an Intent that returns the user to the
     * Main page. This is equivalent to logging out (if logged in)
     *
     * @param view The view that triggered the event
     */
    public void onQuitClicked (View view)
    {
        Intent cQuit = new Intent (this, MainActivity.class);
        startActivity (cQuit);
    }
}
package net.ingramintegrations.FitnessHelper;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private VideoView videoBG;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Button stat = findViewById(R.id.stat);

        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivate();
            }
        });

        Button bmiButton = findViewById(R.id.buttonBMI);

        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBMIActivity();
            }
        });

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        Uri uri = Uri.parse("android.resource://"
                + getPackageName()
                + "/"
                + R.raw.front);

        videoBG = findViewById(R.id.videoView);
        videoBG.setVideoURI(uri);
        videoBG.start();
        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                mMediaPlayer.setLooping(false);

                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        // Neck
                        setupButton(R.id.buttonNeck, NeckActivity.class);

                        // Shoulder
                        setupButton(R.id.buttonShoulder, ShoulderActivity.class);

                        //Elbow
                        setupButton(R.id.buttonElbow, ElbowActivity.class);


                        //Thight
                        setupButton(R.id.buttonThigh, ThighActivity.class);

                        //Knee
                        setupButton(R.id.buttonKnee, KneeActivity.class);

                        //Foot
                        setupButton(R.id.buttonFoot, FootActivity.class);

                        //Hand
                        setupButton(R.id.buttonHand, WristActivity.class);
                    }
                });
            }
        });
    }

    private <T extends AppCompatActivity> void setupButton(int buttonId, final Class<T> tClass) {
        Button buttonNeck = findViewById(buttonId);
        buttonNeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("elbow");
                Intent intent = new Intent(MainActivity.this, tClass);
                startActivity(intent);
            }
        });
        buttonNeck.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoBG.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoBG.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMediaPlayer.release();
        mMediaPlayer = null;
    }
//Rotates the figure
    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
//Switch to Statitstics
    public void openActivate() {
        Intent intent = new Intent(MainActivity.this, Statistics.class);
        startActivity(intent);
    }
//Switch to BMI Calcuator
    public void openBMIActivity() {
        Intent intent = new Intent(this, BMICalculatorActivity.class);
        startActivity(intent);
    }
}
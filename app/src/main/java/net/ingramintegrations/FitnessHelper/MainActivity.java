package net.ingramintegrations.FitnessHelper;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private VideoView videoBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
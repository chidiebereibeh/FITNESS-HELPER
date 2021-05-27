package net.ingramintegrations.FitnessHelper;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity2 extends AppCompatActivity {


    private VideoView videoBG;
    private VideoView videoBG1;
    private Button button;

    MediaPlayer mMediaPlayer;
    MediaPlayer mMediaPlayer1;
    int mCurrentVideoPosition;
    int mCurrentVideoPosition1;
    EditText name;
    Button click;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();

            }
        });

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


        Uri uri = Uri.parse("android.resource://"
                + getPackageName()
                + "/"
                + R.raw.back1);

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

                        // lowerBack
                        setupButton(R.id.lowerBack, UpperBackActivity.class, "upperBack");

                        // upperBack
                        setupButton(R.id.upperBack, LowerBackActivity.class, "lowerBack");
                    }
                });
            }
        });
    }

    private <T extends AppCompatActivity> void setupButton(int buttonId, final Class<T> tClass, String path) {
        Button button = findViewById(buttonId);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(path );
                Intent intent = new Intent(MainActivity2.this, tClass);
                startActivity(intent);
            }
        });
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

    public void openActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
    public void openActivate() {
        Intent intent = new Intent(MainActivity2.this, Statistics.class);
        startActivity(intent);
    }
    public void openBMIActivity() {
        Intent intent = new Intent(this, BMICalculatorActivity.class);
        startActivity(intent);
    }
}

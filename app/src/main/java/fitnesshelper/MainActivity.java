package fitnesshelper;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import fitnesshelper.R;


public class MainActivity extends AppCompatActivity {


    private VideoView videoV;
    private VideoView videoV2;
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
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();

            }
        });




        videoV = (VideoView) findViewById(R.id.videoView);


        Uri uri = Uri.parse("android.resource://"
                + getPackageName()
                + "/"
                + R.raw.front);

        videoV.setVideoURI(uri);


        videoV.start();


        videoV.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;

                mMediaPlayer.setLooping(false);

                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();

        mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoV.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        videoV.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);


    }

}
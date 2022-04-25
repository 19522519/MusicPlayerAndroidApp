package com.example.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    Button btnPlay, btnNext, btnPrevious, btnFastForward, btnFastRewind;
    TextView txtSongName, txtSongStart, txtSongStop;
    SeekBar seekMusic;

//    Animation rotateAnimation;
//    ImageView imgView;

    String sname;
    public static final String EXTRA_NAME = "song_name";
    static MediaPlayer mediaPlayer;
    int position;
    ArrayList<File> mySongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btnPlay = findViewById(R.id.btnplay);
        btnNext = findViewById(R.id.btnnext);
        btnPrevious = findViewById(R.id.btnprevious);
        btnFastForward = findViewById(R.id.btnfastforward);
        btnFastRewind = findViewById(R.id.btnfastrewind);
        txtSongName = findViewById(R.id.txtsongname);
        txtSongStart = findViewById(R.id.txtstart);
        txtSongStop = findViewById(R.id.txtstop);
        seekMusic = findViewById(R.id.seekbar);
//        imgView = findViewById(R.id.imageview);

        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");
        String songName = i.getStringExtra("songname");
        position = bundle.getInt("pos", 0);
        txtSongName.setSelected(true);
        Uri uri = Uri.parse(mySongs.get(position).toString());
        sname = mySongs.get(position).getName();
        txtSongName.setText(sname);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        mediaPlayer.start();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    btnPlay.setBackgroundResource(R.drawable.ic_play);
                    mediaPlayer.pause();
                } else {
                    btnPlay.setBackgroundResource(R.drawable.ic_pause);
                    mediaPlayer.start();

//                    rotateAnimation = AnimationUtils.loadAnimation(, R.anim.rotate);
//                    imgView.startAnimation(rotateAnimation);
                }
            }
        });
    }
}
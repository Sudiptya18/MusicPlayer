/*package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    TextView titleTv,currentTimeTv,totalTimeTv;
    SeekBar seekBar;
    ImageView pausePlay,nextBtn,previousBtn,musicIcon;
    ArrayList<AudioModel> songsList;
    AudioModel currentSong;
    MediaPlayer mediaPlayer = MyMediapPlayer.getInstance();
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        titleTv = findViewById(R.id.song_title);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.music_icon_big);

        titleTv.setSelected(true);

        songsList = (ArrayList<AudioModel>) getIntent().getSerializableExtra("LIST");
        setResourcesWithMusic();
        playMusic();
        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));

                    if(mediaPlayer.isPlaying()){
                        pausePlay.setImageResource(R.drawable.baseline_pause_24);
                        musicIcon.setRotation(x++);
                    }else{
                        pausePlay.setImageResource(R.drawable.baseline_play_arrow_24);
                        musicIcon.setRotation(0);
                    }

                }
                new Handler().postDelayed(this,100);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    void setResourcesWithMusic(){
        currentSong = songsList.get(MyMediapPlayer.currentIndex);

        titleTv.setText(currentSong.getTitle());

        totalTimeTv.setText(convertToMMSS(currentSong.getDuration()));

        pausePlay.setOnClickListener(v-> pausePlay());
        nextBtn.setOnClickListener(v-> playNextSong());
        previousBtn.setOnClickListener(v-> playPreviousSong());

        playMusic();


    }
    private void playMusic(){

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void playNextSong(){

        if(MyMediapPlayer.currentIndex== songsList.size()-1)
            return;
        MyMediapPlayer.currentIndex +=1;
        mediaPlayer.reset();
        setResourcesWithMusic();

    }

    private void playPreviousSong(){
        if(MyMediapPlayer.currentIndex== 0)
            return;
        MyMediapPlayer.currentIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void pausePlay(){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }
    @SuppressLint("DefaultLocale")
    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}*/
/* package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    TextView titleTv, currentTimeTv, totalTimeTv;
    SeekBar seekBar;
    ImageView pausePlay, nextBtn, previousBtn, musicIcon, repeat, shuffle;
    ArrayList<AudioModel> songsList;
    AudioModel currentSong;
    MediaPlayer mediaPlayer = MyMediapPlayer.getInstance();
    int x = 0;
    boolean isRepeatEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        titleTv = findViewById(R.id.song_title);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.music_icon_big);
        repeat = findViewById(R.id.repeat);
        shuffle = findViewById(R.id.shuffle);

        titleTv.setSelected(true);

        songsList = (ArrayList<AudioModel>) getIntent().getSerializableExtra("LIST");
        setResourcesWithMusic();
        playMusic();
        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMMSS(mediaPlayer.getCurrentPosition() + ""));

                    if (mediaPlayer.isPlaying()) {
                        pausePlay.setImageResource(R.drawable.baseline_pause_24);
                        musicIcon.setRotation(x++);
                    } else {
                        pausePlay.setImageResource(R.drawable.baseline_play_arrow_24);
                        musicIcon.setRotation(0);
                    }
                }
                new Handler().postDelayed(this, 100);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        repeat.setOnClickListener(v -> toggleRepeat());
    }

    void setResourcesWithMusic() {
        currentSong = songsList.get(MyMediapPlayer.currentIndex);

        titleTv.setText(currentSong.getTitle());

        totalTimeTv.setText(convertToMMSS(currentSong.getDuration()));

        pausePlay.setOnClickListener(v -> pausePlay());
        nextBtn.setOnClickListener(v -> playNextSong());
        previousBtn.setOnClickListener(v -> playPreviousSong());

        playMusic();
    }

    private void playMusic() {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playNextSong() {
        if (MyMediapPlayer.currentIndex == songsList.size() - 1)
            return;
        MyMediapPlayer.currentIndex += 1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void playPreviousSong() {
        if (MyMediapPlayer.currentIndex == 0)
            return;
        MyMediapPlayer.currentIndex -= 1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void pausePlay() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }

    private void toggleRepeat() {
        isRepeatEnabled = !isRepeatEnabled;
        if (isRepeatEnabled) {
            repeat.setImageResource(R.drawable.baseline_repeat_one_24); // Change the repeat button icon to indicate repeat one
            mediaPlayer.setLooping(true); // Enable looping for the MediaPlayer
        } else {
            repeat.setImageResource(R.drawable.baseline_repeat_24); // Change the repeat button icon to indicate repeat all
            mediaPlayer.setLooping(false); // Disable looping for the MediaPlayer
        }
    }

    @SuppressLint("DefaultLocale")
    public static String convertToMMSS(String duration) {
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}
*/
package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicplayer.AudioModel;
import com.example.musicplayer.MyMediapPlayer;
import com.example.musicplayer.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    TextView titleTv, currentTimeTv, totalTimeTv;
    SeekBar seekBar;
    ImageView pausePlay, nextBtn, previousBtn, musicIcon, repeat, shuffle;
    ArrayList<AudioModel> songsList;
    ArrayList<Integer> shuffledIndices;
    AudioModel currentSong;
    MediaPlayer mediaPlayer = MyMediapPlayer.getInstance();
    int x = 0;
    boolean isRepeatEnabled = false;
    boolean isShuffleEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        titleTv = findViewById(R.id.song_title);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.music_icon_big);
        repeat = findViewById(R.id.repeat);
        shuffle = findViewById(R.id.shuffle);

        titleTv.setSelected(true);

        songsList = (ArrayList<AudioModel>) getIntent().getSerializableExtra("LIST");
        shuffledIndices = new ArrayList<>();
        setResourcesWithMusic();
        playMusic();
        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMMSS(mediaPlayer.getCurrentPosition() + ""));

                    if (mediaPlayer.isPlaying()) {
                        pausePlay.setImageResource(R.drawable.baseline_pause_24);
                        musicIcon.setRotation(x++);
                    } else {
                        pausePlay.setImageResource(R.drawable.baseline_play_arrow_24);
                        musicIcon.setRotation(0);
                    }
                }
                new Handler().postDelayed(this, 100);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        repeat.setOnClickListener(v -> toggleRepeat());
        shuffle.setOnClickListener(v -> toggleShuffle());
    }

    void setResourcesWithMusic() {
        currentSong = songsList.get(MyMediapPlayer.currentIndex);

        titleTv.setText(currentSong.getTitle());

        totalTimeTv.setText(convertToMMSS(currentSong.getDuration()));

        pausePlay.setOnClickListener(v -> pausePlay());
        nextBtn.setOnClickListener(v -> playNextSong());
        previousBtn.setOnClickListener(v -> playPreviousSong());

        playMusic();
    }

    private void playMusic() {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playNextSong() {
        if (isShuffleEnabled) {
            // Play next shuffled song
            if (!shuffledIndices.isEmpty()) {
                int nextIndex = shuffledIndices.remove(0);
                if (nextIndex != MyMediapPlayer.currentIndex) {
                    MyMediapPlayer.currentIndex = nextIndex;
                    mediaPlayer.reset();
                    setResourcesWithMusic();
                } else {
                    // If the next shuffled index is the same as the current index, get the next one
                    playNextSong();
                }
            } else {
                // If all shuffled songs have been played, reset shuffle and play the next sequential song
                resetShuffle();
                if (MyMediapPlayer.currentIndex == songsList.size() - 1)
                    return;
                MyMediapPlayer.currentIndex += 1;
                mediaPlayer.reset();
                setResourcesWithMusic();
            }
        } else {
            // Play next sequential song
            if (MyMediapPlayer.currentIndex == songsList.size() - 1)
                return;
            MyMediapPlayer.currentIndex += 1;
            mediaPlayer.reset();
            setResourcesWithMusic();
        }
    }

    private void playPreviousSong() {
        if (isShuffleEnabled) {
            // Play previous shuffled song
            if (!shuffledIndices.isEmpty()) {
                int previousIndex = shuffledIndices.remove(shuffledIndices.size() - 1);
                if (previousIndex != MyMediapPlayer.currentIndex) {
                    MyMediapPlayer.currentIndex = previousIndex;
                    mediaPlayer.reset();
                    setResourcesWithMusic();
                } else {
                    // If the previous shuffled index is the same as the current index, get the previous one
                    playPreviousSong();
                }
            } else {
                // If all shuffled songs have been played, reset shuffle and play the previous sequential song
                resetShuffle();
                if (MyMediapPlayer.currentIndex == 0)
                    return;
                MyMediapPlayer.currentIndex -= 1;
                mediaPlayer.reset();
                setResourcesWithMusic();
            }
        } else {
            // Play previous sequential song
            if (MyMediapPlayer.currentIndex == 0)
                return;
            MyMediapPlayer.currentIndex -= 1;
            mediaPlayer.reset();
            setResourcesWithMusic();
        }
    }

    private void pausePlay() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }

    private void toggleRepeat() {
        isRepeatEnabled = !isRepeatEnabled;
        if (isRepeatEnabled) {
            repeat.setImageResource(R.drawable.baseline_repeat_one_24); // Change the repeat button icon to indicate repeat one
            mediaPlayer.setLooping(true); // Enable looping for the MediaPlayer
        } else {
            repeat.setImageResource(R.drawable.baseline_repeat_24); // Change the repeat button icon to indicate repeat all
            mediaPlayer.setLooping(false); // Disable looping for the MediaPlayer
        }
    }

    private void toggleShuffle() {
        isShuffleEnabled = !isShuffleEnabled;
        if (isShuffleEnabled) {
            shuffle.setImageResource(R.drawable.shuffle); // Change the shuffle button icon to indicate shuffle is enabled
            generateShuffledIndices(); // Generate shuffled indices for the songs
        } else {
            shuffle.setImageResource(R.drawable.shuffle); // Change the shuffle button icon to indicate shuffle is disabled
            resetShuffle(); // Reset the shuffled indices
        }
    }

    private void generateShuffledIndices() {
        shuffledIndices.clear();
        int totalSongs = songsList.size();
        for (int i = 0; i < totalSongs; i++) {
            if (i != MyMediapPlayer.currentIndex) {
                shuffledIndices.add(i);
            }
        }
        Collections.shuffle(shuffledIndices, new Random());
    }

    private void resetShuffle() {
        shuffledIndices.clear();
    }

    @SuppressLint("DefaultLocale")
    public static String convertToMMSS(String duration) {
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}

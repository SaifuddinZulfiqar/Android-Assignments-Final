package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {


    private MediaPlayer mMediaplayer;

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback

                        mMediaplayer.pause();
                        mMediaplayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback

                        mMediaplayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                        // Stop playback
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<word> words = new ArrayList<word>();

        words.add(new word("One", "Aik", R.drawable.number_one, R.raw.number_one));
        words.add(new word("Two", "Doo", R.drawable.number_two, R.raw.number_two));
        words.add(new word("Three", "Teen", R.drawable.number_three, R.raw.number_three));
        words.add(new word("Four", "Chaar", R.drawable.number_four, R.raw.number_four));
        words.add(new word("Five", "Panch", R.drawable.number_five, R.raw.number_five));
        words.add(new word("Six", "Chay", R.drawable.number_six, R.raw.number_six));
        words.add(new word("Seven", "Sath", R.drawable.number_seven, R.raw.number_seven));
        words.add(new word("Eight", "Aanth", R.drawable.number_eight, R.raw.number_eight));
        words.add(new word("Nine", "No’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new word("Ten", "Das", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter Adapter =
                new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word w = words.get(position);
                releaseMediaPlayer();


                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Start playback.

                mMediaplayer = MediaPlayer.create(NumbersActivity.this, w.getAudioResourceId());
                mMediaplayer.start();

                mMediaplayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

        private void releaseMediaPlayer() {
            // If the media player is not null, then it may be currently playing a sound.
            if (mMediaplayer != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mMediaplayer.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mMediaplayer = null;

                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }








    }


package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
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

        words.add(new word("Father", "Abbu", R.drawable.family_father, R.raw.family_father));
        words.add(new word("Mother", "Ammi", R.drawable.family_mother, R.raw.family_mother));
        words.add(new word("Son", "Beta", R.drawable.family_son, R.raw.family_son));
        words.add(new word("Daughter", "Beti", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new word("Older Brother", "Bara Bhai", R.drawable.family_older_brother,
                R.raw.family_older_brother));
        words.add(new word("Younger Brother", "Chota Bhai", R.drawable.family_younger_brother,
                R.raw.family_younger_brother));
        words.add(new word("Older Sister", "Bari Bhen", R.drawable.family_older_sister,
                R.raw.family_older_sister));
        words.add(new word("Younger Sister", "Choti Bhen", R.drawable.family_younger_sister,
                R.raw.family_younger_sister));
        words.add(new word("Grandmother ", "Dadi", R.drawable.family_grandmother,
                R.raw.family_grandmother));
        words.add(new word("Grandfather", "Dada", R.drawable.family_grandfather,
                R.raw.family_grandfather));


        WordAdapter Adapter =
                new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word w = words.get(position);

                releaseMediaPlayer();


                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Start playback.

                    mMediaplayer = MediaPlayer.create(FamilyActivity.this, w.getAudioResourceId());
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

        /**
         * Clean up the media player by releasing its resources.
         */
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


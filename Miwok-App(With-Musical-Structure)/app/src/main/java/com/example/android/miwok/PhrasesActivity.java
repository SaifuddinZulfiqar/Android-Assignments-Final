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

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new word("Where are you going?", "Kahan ja rahe ho?",
                R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name?", "Aap ka naam kya hai?",
                R.raw.phrase_what_is_your_name));
        words.add(new word("My name is...", "Mera naam hai...", R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?", "Aap ko kesa lag raha hai?", R.raw.phrase_how_are_you_feeling));
        words.add(new word("I’am feeling good.", "Mujhe acha lag raha hai.", R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?", "Aap aarahe ho?", R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I’m coming.", "Han main aaraha hun.", R.raw.phrase_yes_im_coming));
        words.add(new word("I’m coming.", "Main aaraha hun.", R.raw.phrase_im_coming));
        words.add(new word("Let’s go.", "Chalo chaltey hain.", R.raw.phrase_lets_go));
        words.add(new word("Come here.", "Idhar Aao.", R.raw.phrase_come_here));



        WordAdapter Adapter =
                new WordAdapter(this, words,R.color.category_phrases);

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

                    mMediaplayer = MediaPlayer.create(PhrasesActivity.this, w.getAudioResourceId());
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
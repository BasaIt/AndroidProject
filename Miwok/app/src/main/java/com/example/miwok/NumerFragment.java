package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumerFragment extends Fragment {

    private AudioManager mAudioManager;

    private MediaPlayer mMediaPlayer;

    private ListView mListView;



    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == mAudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == mAudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK) {
                mMediaPlayer.stop();
                mMediaPlayer.seekTo(0);
            } else if (i == mAudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (i == mAudioManager.AUDIOFOCUS_LOSS) {
                mMediaPlayer.release();
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.world_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<World> worlds = new ArrayList<World>();
        worlds.add(new World("one", "lutti", R.drawable.number_one, R.raw.number_one));
        worlds.add(new World("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        worlds.add(new World("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        worlds.add(new World("fore", "oyyisa", R.drawable.number_four, R.raw.number_four));
        worlds.add(new World("five", "massokka", R.drawable.number_five, R.raw.number_five));
        worlds.add(new World("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        worlds.add(new World("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        worlds.add(new World("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        worlds.add(new World("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        worlds.add(new World("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAddapter addapter = new WordAddapter(getActivity(), worlds, R.color.group_number);

        mListView = (ListView) rootView.findViewById(R.id.list_item);

        mListView.setAdapter(addapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                World world = worlds.get(i);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == mAudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), world.getmMediaPlayer());

                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return rootView;


    };

    @Override
    public void onStop() {
        super.onStop();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {

            mMediaPlayer.release();

            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
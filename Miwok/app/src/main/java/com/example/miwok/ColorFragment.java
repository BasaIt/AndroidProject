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

public class ColorFragment extends Fragment {

    private ListView mLinstView;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

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
        View rootView =  inflater.inflate(R.layout.world_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<World> worlds = new ArrayList<World>();
        worlds.add(new World("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        worlds.add(new World("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        worlds.add(new World("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        worlds.add(new World("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        worlds.add(new World("black", "kululli", R.drawable.color_black, R.raw.color_black));
        worlds.add(new World("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        worlds.add(new World("dusty yellow", "chiwiiṭә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));

        WordAddapter addapter = new WordAddapter(getActivity(), worlds, R.color.group_color);

        mLinstView = rootView.findViewById(R.id.list_item);
        mLinstView.setAdapter(addapter);

        mLinstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    }

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
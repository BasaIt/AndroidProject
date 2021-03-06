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

public class FamilyFragment extends Fragment {
    private ListView mListView;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == mAudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == mAudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.stop();
                mMediaPlayer.seekTo(0);
            } else if (i == mAudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (i == mAudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.world_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<World> worlds = new ArrayList<World>();
        worlds.add(new World("father", "әpә", R.drawable.family_father, R.raw.family_father));
        worlds.add(new World("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        worlds.add(new World("son", "angsi", R.drawable.family_son, R.raw.family_son));
        worlds.add(new World("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        worlds.add(new World("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        worlds.add(new World("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        worlds.add(new World("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        worlds.add(new World("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        worlds.add(new World("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        worlds.add(new World("grandfather ", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAddapter addapter = new WordAddapter(getActivity(), worlds, R.color.group_family);

        mListView = rootView.findViewById(R.id.list_item);

        mListView.setAdapter(addapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                World world = worlds.get(i);

                int result = mAudioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) mOnCompletionListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

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

            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
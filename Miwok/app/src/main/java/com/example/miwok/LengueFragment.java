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


public class LengueFragment extends Fragment {

    private ListView mListView;
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
        View rootView = inflater.inflate(R.layout.world_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<World> words = new ArrayList<World>();
        words.add(new World("Where are you", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new World("What is your", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new World("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new World("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new World("I’m feeling good.", " kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new World("Are you coming? ", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new World(" Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new World("I’m coming. ", "әәnәm", R.raw.phrase_im_coming));
        words.add(new World("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new World("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAddapter adapter = new WordAddapter(getActivity(), words, R.color.group_lengeu);

        mListView = rootView.findViewById(R.id.list_item);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                World world = words.get(i);
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
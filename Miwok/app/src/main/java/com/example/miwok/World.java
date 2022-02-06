package com.example.miwok;

public class World {
    private String mWorldEnglish;
    private String mWorldMiwok;
    private int mWorldImage;
    private int mMediaPlayer;

    public World(String mWorldEnglish, String mWorldMiwok, int mMediaPlayer) {
        this.mWorldEnglish = mWorldEnglish;
        this.mWorldMiwok = mWorldMiwok;
        this.mMediaPlayer = mMediaPlayer;
    }


    public World(String mWorldEnglish, String mWorldMiwok, int mWorldImage, int mMediaPlayer) {
        this.mWorldEnglish = mWorldEnglish;
        this.mWorldMiwok = mWorldMiwok;
        this.mWorldImage = mWorldImage;
        this.mMediaPlayer = mMediaPlayer;
    }

    public String getmWorldEnglish() {
        return mWorldEnglish;
    }

    public String getmWorldMiwok() {
        return mWorldMiwok;
    }

    public int getmWorldImage() {
        return mWorldImage;
    }

    public int getmMediaPlayer() {
        return mMediaPlayer;
    }
}

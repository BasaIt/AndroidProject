package com.example.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;



import androidx.core.content.ContextCompat;



import java.util.ArrayList;

public class WordAddapter extends ArrayAdapter<World> {

    private TextView mTextViewEnglish;
    private TextView mTextViewMiwok;
    private ImageView mImageViewSource;
    private int mImageResource;


    public WordAddapter(Context context, ArrayList<World> worlds, int mImageResource) {
        super(context, 0, worlds);
        this.mImageResource = mImageResource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        World currentWorld = getItem(position);

        mTextViewEnglish = listItemView.findViewById(R.id.english);
        mTextViewEnglish.setText(currentWorld.getmWorldEnglish());

        mTextViewMiwok = listItemView.findViewById(R.id.miwok);
        mTextViewMiwok.setText(currentWorld.getmWorldMiwok());

        mImageViewSource = listItemView.findViewById(R.id.image);
        mImageViewSource.setImageResource(currentWorld.getmWorldImage());

        View textContainer = listItemView.findViewById(R.id.text_conteiner);

        int color = ContextCompat.getColor(getContext(), mImageResource);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}

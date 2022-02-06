package com.example.music_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAddapter extends ArrayAdapter<CustomList> {

    public CustomAddapter(Context context, ArrayAdapter<CustomList> customAdapter, int mResourceId){
        super(context, 0, (List<CustomList>) customAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View listView = convertView;
        if (listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        CustomList customList = getItem(position);





        return listView;
    }
}

package com.example.miwok;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter  extends FragmentPagerAdapter {


    private final Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumerFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorFragment();
        } else {
            return new LengueFragment();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.button_numery);
        } else if (position == 1) {
            return mContext.getString(R.string.button_family);
        } else if (position == 2) {
            return mContext.getString(R.string.button_colors);
        } else {
            return mContext.getString(R.string.button_lengue);
        }
    }
}
package com.test.koibrowser.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.test.koibrowser.fragment.CompletedFragment;
import com.test.koibrowser.fragment.DownloadingFragment;


public class DownloadsPagerAdapter extends FragmentPagerAdapter {
    @Override 
    public int getCount() {
        return 2;
    }

    public DownloadsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override 
    public Fragment getItem(int i) {
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return new CompletedFragment();
        }
        return new DownloadingFragment();
    }
}

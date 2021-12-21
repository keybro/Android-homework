package com.example.tabandbnv.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabandbnv.fragment.entertainment;
import com.example.tabandbnv.fragment.head;
import com.example.tabandbnv.fragment.sports;

public class FragmentPagersAdapter extends FragmentPagerAdapter {

    private String [] listTitle = {"头条","娱乐","体育"};


    public FragmentPagersAdapter (FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle[position];
    }


    /**
     * 返回指定的fragment
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                //创建头条fragment
                fragment = new head();
                return fragment;
            case 1:
                fragment = new entertainment();
                return fragment;
            case 2:
                fragment = new sports();
                return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTitle.length;
    }
}

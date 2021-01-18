package com.gbh.eternalreader.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by gbh
 * Date 2021/1/13
 * Description
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;//ViewPager要填充的fragment列表

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;
    }
//    public FragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
//        super(fm);
//        this.fragments = fragments;
//    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

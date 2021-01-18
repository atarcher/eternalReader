package com.gbh.eternalreader.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.gbh.eternalreader.BR;
import com.gbh.eternalreader.R;
import com.gbh.eternalreader.adapter.FragmentAdapter;
import com.gbh.eternalreader.databinding.ActivityMainBinding;
import com.gbh.eternalreader.ui.fragment.BookRackFragment;
import com.gbh.eternalreader.ui.fragment.TabBar1Fragment;
import com.gbh.eternalreader.ui.fragment.TabBar3Fragment;
import com.gbh.eternalreader.vm.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * Created by gbh
 * Date 2020/12/30
 * Description
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private List<Fragment> fragments;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        List<Fragment> list = new ArrayList<>();
        list.add(new TabBar1Fragment());
        list.add(new BookRackFragment());
        list.add(new TabBar3Fragment());
        fragments = list;

        FragmentAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setCurrentItem(1);
    }

    @Override
    public void initViewObservable() {
    }

}
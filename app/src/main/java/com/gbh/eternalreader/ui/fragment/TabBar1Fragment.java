package com.gbh.eternalreader.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gbh.eternalreader.BR;
import com.gbh.eternalreader.R;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by gbh
 * Date 2021/1/13
 * Description
 */
public class TabBar1Fragment extends BaseFragment {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_one;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}

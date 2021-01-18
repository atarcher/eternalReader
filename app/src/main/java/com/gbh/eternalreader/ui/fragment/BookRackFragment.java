package com.gbh.eternalreader.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gbh.eternalreader.BR;
import com.gbh.eternalreader.R;
import com.gbh.eternalreader.database.DBHelper;
import com.gbh.eternalreader.database.entity.Book;
import com.gbh.eternalreader.databinding.FragmentBookrackBinding;
import com.gbh.eternalreader.vm.rack.BookGridItemViewModel;
import com.gbh.eternalreader.vm.rack.BookItemViewModel;
import com.gbh.eternalreader.vm.rack.BookRackViewModel;

import java.util.ArrayList;
import java.util.Objects;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * Created by gbh
 * Date 2021/1/13
 * Description
 */
public class BookRackFragment extends BaseFragment<FragmentBookrackBinding, BookRackViewModel> {

    private ArrayList<Book> mBooks = new ArrayList<>();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_bookrack;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        binding.recyclerView.setAdapter(new BindingRecyclerViewAdapter());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    @Override
    public void initViewObservable() {
        //监听下拉刷新完成
        viewModel.uc.finishRefreshing.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                //结束刷新
                binding.refreshLayout.finishRefresh();
            }
        });
        //监听上拉加载完成
        viewModel.uc.finishLoadMore.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                //结束刷新
                binding.refreshLayout.finishLoadMore();
            }
        });
    }

    private void initBook() {
        mBooks.clear();
        mBooks.addAll(DBHelper.getInstance().getAllBook());
        //调整书籍排序编码
        if (mBooks != null) {
            for (int i = 0; i < mBooks.size(); i++) {
                if (mBooks.get(i).getSortCode() != i + 1) {
                    mBooks.get(i).setSortCode(i + 1);
                    DBHelper.getInstance().updateBooksEntity(mBooks.get(i));
                }
            }
        }

        if (mBooks == null || mBooks.size() == 0) {
//            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            binding.getViewModel().setBook_Empty();
        } else {
            if (binding.recyclerView.getAdapter() == null) {
                binding.recyclerView.setAdapter(new BindingRecyclerViewAdapter());
            }
            if(binding.getViewModel().ViewSwitch) {
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.getViewModel().setBook_List(mBooks);
            } else {
                binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                binding.getViewModel().setBook_Grid(mBooks);
            }

        }

        initSurplusChapters();
    }

    /**
    * @Description: 检测尚未阅读章节数
    * @Param: []
    * @return: void
    * @Author: GBH
    * @Date: 2021/1/18
    */
    public void initSurplusChapters() {
        //todo:从网络获取剩余未读章节数
    }

    @Override
    public void onResume() {
        super.onResume();
        initBook();
    }
}

package com.gbh.eternalreader.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gbh.eternalreader.BR;
import com.gbh.eternalreader.R;
import com.gbh.eternalreader.database.DBHelper;
import com.gbh.eternalreader.database.entity.Book;
import com.gbh.eternalreader.databinding.FragmentBookrackBinding;
import com.gbh.eternalreader.vm.rack.BookRackViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * Created by gbh
 * Date 2021/1/13
 * Description
 */
public class BookRackFragment extends BaseFragment<FragmentBookrackBinding, BookRackViewModel> {

    private ArrayList<Book> mBooks = new ArrayList<>();
    public Boolean bookState = true;
    public Boolean bookChoseState = true;

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
        //默认网格布局
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //默认不显示
        for (int i = 0; i < viewModel.observableList.size(); i++) {
            viewModel.observableList.get(i).imgVisibility.set(View.GONE);
        }
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

        //监听书本项点击事件
        viewModel.itemClick.observe(this, bookItemViewModel -> {
            if (!bookState) {
                int index = viewModel.getPosition(bookItemViewModel);
                if (!booleanMap.containsKey(index)) {
                    booleanMap.put(index, true);
                }
                if (booleanMap.get(index)) {
                    viewModel.observableList.get(index).imgSrc =
                            ContextCompat.getDrawable(viewModel.getApplication(), R.drawable.image_select);
                } else {
                    viewModel.observableList.get(index).imgSrc =
                            ContextCompat.getDrawable(viewModel.getApplication(), R.drawable.image_unselect);
                }
                booleanMap.put(index, !booleanMap.get(index));
                Objects.requireNonNull(binding.recyclerView.getAdapter()).notifyDataSetChanged();
            } else {
                ToastUtils.showShort("点击成功");
                //进入阅读界面
            }
        });

        //监听书本项长按事件
        viewModel.LongClick.observe(this, bookItemViewModel -> {
            if (bookState) {
                for (int i = 0; i < viewModel.observableList.size(); i++) {
                    viewModel.observableList.get(i).imgVisibility.set(View.VISIBLE);
                }
            } else {
                for (int i = 0; i < viewModel.observableList.size(); i++) {
                    viewModel.observableList.get(i).imgSrc =
                            ContextCompat.getDrawable(viewModel.getApplication(), R.drawable.image_unselect);
                    viewModel.observableList.get(i).imgVisibility.set(View.GONE);
                }
                Objects.requireNonNull(binding.recyclerView.getAdapter()).notifyDataSetChanged();
            }
            ToastUtils.showShort("还是长按");
            bookState = !bookState;
        });
    }

    private Map<Integer, Boolean> booleanMap = new HashMap<>();

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
            if (binding.getViewModel().ViewSwitch) {
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
        booleanMap.clear();
        initBook();
    }
}

package com.gbh.eternalreader.vm.rack;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.bumptech.glide.Glide;
import com.gbh.eternalreader.BR;
import com.gbh.eternalreader.R;
import com.gbh.eternalreader.database.entity.Book;
import com.gbh.eternalreader.ui.fragment.BookRackFragment;
import com.gbh.eternalreader.vm.rack.BookItemViewModel;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by gbh
 * Date 2021/1/14
 * Description
 */
public class BookRackViewModel extends BaseViewModel {
    private static final String Book_Empty = "empty";
    private static final String Book_List = "list";
    private static final String Book_Grid = "grid";

    public Boolean ViewSwitch = false;//默认网格 false, 列表为 true

    public BookRackViewModel(@NonNull Application application) {
        super(application);
        //模拟10个条目，数据源可以来自网络
        for (int i = 0; i < 20; i++) {
            if (i == 0) {
                BookItemViewModel item = new BookEmptyItemViewModel(this);
                item.setItemType(Book_Empty);
//                observableList.add(item);
            } else {
                BookItemViewModel item = new BookGridItemViewModel(this);
                //条目类型为左布局
                item.setItemType(Book_Grid);
                observableList.add(item);
            }
        }
    }

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //下拉刷新完成
        public SingleLiveEvent<Void> finishRefreshing = new SingleLiveEvent<>();
        //上拉加载完成
        public SingleLiveEvent<Void> finishLoadMore = new SingleLiveEvent<>();

    }

    //给RecyclerView添加ObservableList
    public final ObservableList<BookItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<BookItemViewModel> itemBinding = ItemBinding.of((itemBinding, position, item) -> {
        String itemType = (String) item.getItemType();
        switch (itemType) {
            case Book_Empty:
                itemBinding.set(BR.viewModel, R.layout.item_book_empty);
                break;
            case Book_Grid:
                itemBinding.set(BR.viewModel, R.layout.item_book_grid);
                break;
            case Book_List:
                itemBinding.set(BR.viewModel, R.layout.item_book_list);
                break;
        }
    });

    @BindingAdapter({"onRefreshCommand"})
    public static void onRefreshCommand(SmartRefreshLayout smartRefreshLayout, final BindingCommand onRefresh) {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (onRefresh != null) {
                onRefresh.execute();
            }
        });

    }

    @BindingAdapter({"onLoadMoreCommand"})
    public static void onLoadMoreCommand(SmartRefreshLayout smartRefreshLayout, final BindingCommand onLoadMore) {
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (onLoadMore != null) {
                onLoadMore.execute();
            }
        });

    }

    //下拉刷新
    public BindingCommand onRefresh = new BindingCommand(() -> {
        ToastUtils.showShort("下拉刷新");
        uc.finishRefreshing.call();
    });
    //上拉加载
    public BindingCommand onLoadMore = new BindingCommand(() -> {
        ToastUtils.showShort("上拉加载");
        uc.finishLoadMore.call();
    });

    public void setBook_Empty() {
        observableList.clear();
        BookItemViewModel item = new BookEmptyItemViewModel(this);
        item.setItemType(Book_Empty);
        observableList.add(item);
    }

    public void setBook_Grid(List<Book> mBooks) {
        observableList.clear();
    }

    public void setBook_List(List<Book> mBooks) {
    }

    public void refreshAdapter() {
    }

    public void initItemView(Context context, Book book, ImageView imageView) {

    }
}

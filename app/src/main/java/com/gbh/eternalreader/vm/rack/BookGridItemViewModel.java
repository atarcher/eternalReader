package com.gbh.eternalreader.vm.rack;

import androidx.annotation.NonNull;

import com.gbh.eternalreader.database.entity.Book;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by gbh
 * Date 2021/1/18
 * Description
 */
public class BookGridItemViewModel extends BookItemViewModel<BookRackViewModel> {

    public BookGridItemViewModel(@NonNull BookRackViewModel viewModel) {
        super(viewModel);
    }

    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
//            ToastUtils.showShort("短暂点击");
            viewModel.itemClick.setValue(BookGridItemViewModel.this);
        }
    });

    public BindingCommand itemLongClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.LongClick.setValue(BookGridItemViewModel.this);
//            if (BookState) {
//                imgVisibility.set(View.VISIBLE);
//            } else {
//                imgVisibility.set(View.GONE);
//            }
//            ToastUtils.showShort("长按反馈");
//            BookState = !BookState;
        }
    });
}

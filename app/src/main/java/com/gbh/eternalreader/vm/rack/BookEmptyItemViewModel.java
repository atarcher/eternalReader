package com.gbh.eternalreader.vm.rack;

import androidx.annotation.NonNull;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by gbh
 * Date 2021/1/18
 * Description
 */
public class BookEmptyItemViewModel extends BookItemViewModel<BookRackViewModel> {
    public BookEmptyItemViewModel(@NonNull BookRackViewModel viewModel) {
        super(viewModel);
    }

    public BindingCommand itemAddBookClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("添加");

        }
    });
}
